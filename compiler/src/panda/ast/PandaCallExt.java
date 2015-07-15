package panda.ast;

import panda.types.*;
import panda.visit.*;


import polyglot.ast.*;
import polyglot.types.*;
import polyglot.visit.*;
import polyglot.util.*;

import polyglot.ext.jl5.ast.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class PandaCallExt extends PandaExt {

  protected Type expectedReturnType;
  protected Map<ModeTypeVariable, Type> infModeTypes;

  protected Map<ModeTypeVariable, Type> infModeTypes() {
    return this.infModeTypes;
  }

  protected Call infModeTypes(Map<ModeTypeVariable, Type> infModeTypes) {
    return this.infModeTypes((Call)this.node(), infModeTypes);
  }

  protected <N extends Call> N infModeTypes(N n, Map<ModeTypeVariable, Type> infModeTypes) {
    PandaCallExt ext = (PandaCallExt) PandaExt.ext(n);
    if (ext.infModeTypes == infModeTypes) return n;
    if (this.node() == n) {
      n = Copy.Util.copy(n);
      ext = (PandaCallExt) PandaExt.ext(n);
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
    Call n = (Call) this.node();
    PandaTypeSystem ts = (PandaTypeSystem) tc.typeSystem();
    PandaContext ctx = (PandaContext) tc.context();
    PandaClassType ct = (PandaClassType) ctx.currentClassScope();

    Type t = n.target().type();
    if (!(t instanceof ModeSubstType)) {
      return superLang().typeCheck(n, tc);
    } 

    // Disallow dynamic type seperately for better diagnostics
    ModeSubstType mt = (ModeSubstType) t;
    if (mt.modeType() == ts.DynamicModeType()) {
      throw new SemanticException("Dynamic mode type cannot receive messages. Resolve using snapshot.");
    }

    if (ctx.inStaticContext()) {
      // Kick up to super lang for now
      return superLang().typeCheck(this.node(), tc);
    } 

    // Call is valid if the first mode type variable upper bound is greater
    // than the recievers mode type.
    ModeTypeVariable mtThis = ct.modeTypeVars().get(0);
    if (!ts.isSubtype(mt.modeType(), mtThis)) {
      throw new SemanticException("Cannot send message to " + t + " from mode " + mtThis.upperBound() + ".");
    }

    n = (Call) superLang().typeCheck(this.node(), tc);
    PandaProcedureInstance pi = (PandaProcedureInstance) n.methodInstance();
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
    Type expectedRetType = ((PandaCallExt) PandaExt.ext(n)).expectedReturnType;
    ModeSubst subst = ts.inferModeTypeArgs(pi.baseInstance(), argTypes, expectedRetType);
    PandaCallExt ext = (PandaCallExt) PandaExt.ext(n);
    return ext.infModeTypes(subst.modeTypeMap());
  }

  @Override
  public Node typePreserve(TypePreserver tp) {
    Call n = (Call) this.node();

    if (this.infModeTypes() == null) {
      return n;
    }

    PandaNodeFactory nf = (PandaNodeFactory) tp.nodeFactory();
    Context c = tp.context();

    PandaProcedureInstance pi = (PandaProcedureInstance) n.procedureInstance();
    List<Expr> elems = new ArrayList<>();
    for (ModeTypeVariable v : pi.modeTypeVars()) {
      ModeType mt = (ModeType) this.infModeTypes().get(v);
      elems.add(mt.rewriteForLookup(nf, c));
    }

    List<Expr> closInit = new ArrayList<>();
    closInit.add(
      nf.NewArray(
        Position.COMPILER_GENERATED,
        nf.AmbTypeNode(
          Position.COMPILER_GENERATED,
          nf.Id(
            Position.COMPILER_GENERATED,
            "Integer"
            )
          ),
        1,
        nf.ArrayInit(
          Position.COMPILER_GENERATED,
          elems
          )
        )
      );

    Expr larg =
      nf.New(
        Position.COMPILER_GENERATED,
        nf.AmbTypeNode(
          Position.COMPILER_GENERATED,
          nf.Id(
            Position.COMPILER_GENERATED,
            "PANDA_Closure"
            )
          ),
        closInit
        );

    List<Expr> args = new ArrayList<>(n.arguments());
    args.add(larg);

    return n.arguments(args);
  }


}
