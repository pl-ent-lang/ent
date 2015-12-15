package ent.ast;

import ent.types.*;
import ent.visit.*;
import ent.translate.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.visit.*;
import polyglot.util.*;
import polyglot.translate.*;

import polyglot.ext.jl5.ast.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EntCallExt extends EntExt {

  protected Type expectedReturnType;
  protected Map<ModeTypeVariable, Type> infModeTypes;

  protected Map<ModeTypeVariable, Type> infModeTypes() {
    return this.infModeTypes;
  }

  protected Call infModeTypes(Map<ModeTypeVariable, Type> infModeTypes) {
    return this.infModeTypes((Call)this.node(), infModeTypes);
  }

  protected <N extends Call> N infModeTypes(N n, Map<ModeTypeVariable, Type> infModeTypes) {
    EntCallExt ext = (EntCallExt) EntExt.ext(n);
    if (ext.infModeTypes == infModeTypes) return n;
    if (this.node() == n) {
      n = Copy.Util.copy(n);
      ext = (EntCallExt) EntExt.ext(n);
    }
    ext.infModeTypes = infModeTypes;
    return n;
  }

  @Override
  public Node typeCheckOverride(Node parent, TypeChecker tc)
          throws SemanticException {
      JL5CallExt ext = (JL5CallExt) JL5Ext.ext(this.node());
      if (parent instanceof Return) {
          CodeInstance ci = tc.context().currentCode();
          if (ci instanceof FunctionInstance) {
              this.expectedReturnType = ((FunctionInstance) ci).returnType();
          }
      }
      if (parent instanceof Assign) {
          Assign a = (Assign) parent;
          if (this.node() == a.right()) {
              Type type = a.left().type();
              if (type == null || !type.isCanonical()) {
                  // not ready yet
                  return this.node();
              }
              this.expectedReturnType = type;
          }
      }
      if (parent instanceof LocalDecl) {
          LocalDecl ld = (LocalDecl) parent;
          Type type = ld.type().type();
          if (type == null || !type.isCanonical()) {
              // not ready yet
              return this.node();
          }
          this.expectedReturnType = type;
      }
      if (parent instanceof FieldDecl) {
          FieldDecl fd = (FieldDecl) parent;
          Type type = fd.type().type();
          if (type == null || !type.isCanonical()) {
              // not ready yet
              return this.node();
          }
          this.expectedReturnType = type;
      }

      return superLang().typeCheckOverride(this.node(), parent, tc);
  }


  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException { 
    Call n = (Call) superLang().typeCheck(this.node(), tc);

    EntTypeSystem ts = (EntTypeSystem) tc.typeSystem();
    EntContext ctx = (EntContext) tc.context();
    EntClassType ct = (EntClassType) ctx.currentClassScope();

    // NOTE: No target means this is a static call
    if (n.target() == null) {
      return n;
    } 

    Type t = n.target().type();
    if (!(t instanceof ModeSubstType)) {
      return n;
    } 

    // Disallow dynamic type seperately for better diagnostics
    ModeSubstType mt = (ModeSubstType) t;
    if (mt.modeType() == ts.DynamicModeType() && 
        !EntFlags.isModesafe(n.methodInstance().flags())) {
      throw new SemanticException(
        "Dynamic mode type cannot receive messages. Resolve using snapshot.");
    }

    if (ctx.inStaticContext()) {
      // Kick up to super lang for now
      return n;
    } 

    // Call is valid if the first mode type variable upper bound is greater
    // than the recievers mode type.
    ModeTypeVariable mtThis = ct.modeTypeVars().get(0);
    if (!ts.isSubtype(mt.modeType(), mtThis)) {
      throw new SemanticException("Cannot send message to " + t + " from mode " + mtThis.upperBound() + ".");
    }

    EntProcedureInstance pi = (EntProcedureInstance) n.methodInstance();
    if (pi.modeTypeVars().isEmpty()) {
      return n;
    }

    // TODO : This is ugly, but until the parent language has been type checked
    // can not really use the inference functions. At least it can be a protoype
    // for now
    List<Type> argTypes = new ArrayList<>();
    for (Expr e : n.arguments()) {
      argTypes.add(e.type());
    }
    Type expectedRetType = ((EntCallExt) EntExt.ext(n)).expectedReturnType;
    ModeSubst subst = ts.inferModeTypeArgs(pi.baseInstance(), argTypes, expectedRetType);
    EntCallExt ext = (EntCallExt) EntExt.ext(n);
    return ext.infModeTypes(subst.modeTypeMap());
  }

  public boolean needsEntClosure() {
    return this.infModeTypes() != null;
  }

  @Override
  public Node typePreserve(TypePreserver tp) {
    Call n = (Call) this.node();

    if (this.needsEntClosure()) {
      List<Expr> args = new ArrayList<>(n.arguments());
      args.add(
        EntBuilder.instance().buildEntClosure(
          tp.nodeFactory(),
          tp.toTypeSystem(),
          ((EntProcedureInstance) n.procedureInstance()).modeTypeVars(),
          this.infModeTypes(),
          tp.context()
          )
        ); 
      n = (Call) n.arguments(args);
    }

    return n;
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter) rw;
    JL5NodeFactory nf = (JL5NodeFactory) prw.to_nf();

    JL5CallExt ext = (JL5CallExt) JL5Ext.ext(this.node());
    Call call = (Call) super.extRewrite(rw);

    Call n =
      nf.Call(
        call.position(),
        call.target(),
        ext.typeArgs(),
        call.id(),
        call.arguments()
        );
    return n;
  }


}
