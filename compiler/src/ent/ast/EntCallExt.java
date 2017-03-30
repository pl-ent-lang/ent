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
import polyglot.ext.jl5.types.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EntCallExt extends EntExt {

  protected Type expectedReturnType;
  protected List<ModeTypeNode> modeTypeArgs;

  protected List<ModeType> actualModeTypes;

  protected Map<ModeTypeVariable, Type> infModeTypes;

  protected List<ModeType> actualModeTypes() { return this.actualModeTypes; }

  protected boolean proxyCall;

  protected boolean proxyCall() { return this.proxyCall; }

  protected Call proxyCall(boolean proxyCall) {
    return this.proxyCall((Call)this.node(), proxyCall);
  }

  protected <N extends Node> N proxyCall(N n, boolean proxyCall) {
    EntCallExt ext = (EntCallExt)EntExt.ext(n);
    if (ext.proxyCall == proxyCall)
      return n;
    if (this.node() == n) {
      n = Copy.Util.copy(n);
      ext = (EntCallExt)EntExt.ext(n);
    }
    ext.proxyCall = proxyCall;
    return n;
  } 

  protected Call actualModeTypes(List<ModeType> actualModeTypes) {
    return this.actualModeTypes((Call)this.node(), actualModeTypes);
  }

  protected <N extends Node> N actualModeTypes(N n, List<ModeType> actualModeTypes) {
    EntCallExt ext = (EntCallExt)EntExt.ext(n);
    if (ext.actualModeTypes == actualModeTypes)
      return n;
    if (this.node() == n) {
      n = Copy.Util.copy(n);
      ext = (EntCallExt)EntExt.ext(n);
    }
    ext.actualModeTypes = actualModeTypes;
    return n;
  }

  protected Map<ModeTypeVariable, Type> infModeTypes() { return this.infModeTypes; }

  protected Call infModeTypes(Map<ModeTypeVariable, Type> infModeTypes) {
    return this.infModeTypes((Call)this.node(), infModeTypes);
  }

  protected <N extends Node> N infModeTypes(N n, Map<ModeTypeVariable, Type> infModeTypes) {
    EntCallExt ext = (EntCallExt)EntExt.ext(n);
    if (ext.infModeTypes == infModeTypes)
      return n;
    if (this.node() == n) {
      n = Copy.Util.copy(n);
      ext = (EntCallExt)EntExt.ext(n);
    }
    ext.infModeTypes = infModeTypes;
    return n;
  }

  protected List<ModeTypeNode> modeTypeArgs() { return this.modeTypeArgs; }

  protected Call modeTypeArgs(List<ModeTypeNode> modeTypeArgs) {
    return this.modeTypeArgs((Call)this.node(), modeTypeArgs);
  }

  protected <N extends Node> N modeTypeArgs(N n, List<ModeTypeNode> modeTypeArgs) {
    EntCallExt ext = (EntCallExt)EntExt.ext(n);
    if (CollectionUtil.equals(ext.modeTypeArgs, modeTypeArgs))
      return n;
    if (this.node() == n) {
      n = Copy.Util.copy(n);
      ext = (EntCallExt)EntExt.ext(n);
    }
    ext.modeTypeArgs = ListUtil.copy(modeTypeArgs, true);
    return n;
  }

  // Node Methods
  protected <N extends Node> N reconstruct(N n, List<ModeTypeNode> modeTypeArgs) {
    n = this.modeTypeArgs(n, modeTypeArgs);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    Node n = superLang().visitChildren(this.node(), v);
    List<ModeTypeNode> modeTypeArgs = visitList(this.modeTypeArgs(), v);
    return this.reconstruct(n, modeTypeArgs);
  }

  @Override
  public Node typeCheckOverride(Node parent, TypeChecker tc) throws SemanticException {
    JL5CallExt ext = (JL5CallExt)JL5Ext.ext(this.node());
    if (parent instanceof Return) {
      CodeInstance ci = tc.context().currentCode();
      if (ci instanceof FunctionInstance) {
        this.expectedReturnType = ((FunctionInstance)ci).returnType();
      }
    }
    if (parent instanceof Assign) {
      Assign a = (Assign)parent;
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
      LocalDecl ld = (LocalDecl)parent;
      Type type = ld.type().type();
      if (type == null || !type.isCanonical()) {
        // not ready yet
        return this.node();
      }
      this.expectedReturnType = type;
    }
    if (parent instanceof FieldDecl) {
      FieldDecl fd = (FieldDecl)parent;
      Type type = fd.type().type();
      if (type == null || !type.isCanonical()) {
        // not ready yet
        return this.node();
      }
      this.expectedReturnType = type;
    }

    return superLang().typeCheckOverride(this.node(), parent, tc);
  }

  protected Type computeReturnType(EntMethodInstance mi) throws SemanticException {
    // See JLS 3rd ed 15.12.2.6
    EntTypeSystem ts = (EntTypeSystem)mi.typeSystem();
    // If the method being invoked is declared with a return type of void, then the result is void.
    if (mi.returnType().isVoid()) {
      return ts.Void();
    }

    // Otherwise, if unchecked conversion was necessary for the method to be applicable then the
    // result type is the erasure (�4.6) of the method�s declared return type.
    // XXX how to check this? We need to implement it properly.

    // Otherwise, if the method being invoked is generic, then for 1 � i � n ,
    // let Fi be the formal type parameters of the method, let Ai be the actual type arguments
    // inferred for the method invocation, and
    // let R be the declared return type of the method being invoked. The result type is obtained by
    // applying capture conversion (�5.1.10) to R[F1 := A1, ..., Fn := An].
    // --- mi has already had substitution applied, so it is covered by the following case.

    // Otherwise, the result type is obtained by applying capture conversion (�5.1.10) to the type
    // given in the method declaration.
    return ts.applyCaptureConversion(mi.returnType(), this.node().position());
  }

  public Node jl5TypeCheck(TypeChecker tc) throws SemanticException {
    EntTypeSystem ts = (EntTypeSystem)tc.typeSystem();
    Context c = tc.context();

    Call n = (Call)this.node();

    JL5CallExt ext = (JL5CallExt)JL5Ext.ext(n);

    List<Type> argTypes = new ArrayList<>(n.arguments().size());

    for (Expr e : n.arguments()) {
      if (!e.type().isCanonical()) {
        return n;
      }
      argTypes.add(e.type());
    }

    if (n.target() == null) {
      return tc.lang().typeCheckNullTarget(n, tc, argTypes);
    }

    if (!n.target().type().isCanonical()) {
      return n;
    }

    List<ReferenceType> actualTypeArgs = new ArrayList<>();
    for (TypeNode tn : ext.typeArgs()) {
      actualTypeArgs.add((ReferenceType)tn.type());
    }

    ReferenceType targetType = tc.lang().findTargetType(n);

    /* This call is in a static context if and only if
     * the target (possibly implicit) is a type node.
     */
    boolean staticContext = (n.target() instanceof TypeNode);

    if (staticContext && targetType instanceof RawClass) {
      targetType = ((RawClass)targetType).base();
    }

    // LAST : Here we go, make sure all mode type variables
    // trigger preservation
    List<ModeType> actualModeTypes = null;
    if (this.modeTypeArgs() != null) {
      actualModeTypes = new ArrayList<>();
      for (ModeTypeNode mn : this.modeTypeArgs()) {
        ModeType mt = ((ModeType)mn.type());
        if (mt instanceof ModeTypeVariable) {
          ModeTypeVariable mtv = (ModeTypeVariable) mt;
          if (mtv.declaringClass() != null) {
            EntClassType ct = (EntClassType) mtv.declaringClass();
            ct.instancesNeedTypePreservation(true);
          }
        }
        actualModeTypes.add(mt);
      }
    }
    // Save for closure
    n = this.actualModeTypes(actualModeTypes); 

    EntMethodInstance mi = (EntMethodInstance)ts.findMethod(targetType,
                                                            n.name(),
                                                            argTypes,
                                                            actualTypeArgs,
                                                            c.currentClass(),
                                                            ext.expectedReturnType(),
                                                            !(n.target() instanceof Special),
                                                            actualModeTypes);

    if (staticContext && !mi.flags().isStatic()) {
      throw new SemanticException("Cannot call non-static method " + n.name() + " of " +
                                      n.target().type() + " in static "
                                      +
                                      "context.",
                                  n.position());
    }

    // If the target is super, but the method is abstract, then complain.
    if (n.target() instanceof Special && ((Special)n.target()).kind() == Special.SUPER &&
        mi.flags().isAbstract()) {
      throw new SemanticException("Cannot call an abstract method "
                                      +
                                      "of the super class",
                                  n.position());
    }

    Type returnType = computeReturnType(mi);

    n = (Call)n.methodInstance(mi).type(returnType);
    ext = (JL5CallExt)JL5Ext.ext(n);

    // Need to deal with Object.getClass() specially. See JLS 3rd ed., section 4.3.2
    if (mi.name().equals("getClass") && mi.formalTypes().isEmpty()) {
      // the return type of the call is "Class<? extends |T|>" where T is the static type of
      // the receiver.
      Type t = n.target().type();
      ReferenceType et = (ReferenceType)ts.erasureType(t);
      ReferenceType wt = ts.wildCardType(n.position(), et, null);
      Type instClass = ts.instantiate(
          n.position(), (JL5ParsedClassType)ts.Class(), Collections.singletonList(wt));
      n = (Call)n.type(instClass);
    }

    return n;
  }

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    Call n = (Call)this.jl5TypeCheck(tc);
    EntProcedureInstance pi = (EntProcedureInstance)n.methodInstance();
    EntTypeSystem ts = (EntTypeSystem)tc.typeSystem();
    EntContext ctx = (EntContext)tc.context();
    EntClassType ct = (EntClassType)ctx.currentClassScope();

    // NOTE: No target means this is a static call
    if (n.target() == null) {
      return n;
    }

    Type t = n.target().type();
    if (!(t instanceof ModeSubstType)) {
      return n;
    }

    // Disallow dynamic type seperately for better diagnostics
    ModeSubstType mt = (ModeSubstType)t;
    if (mt.modeType() == ts.DynamicModeType() && !EntFlags.isModesafe(n.methodInstance().flags())) {
      throw new SemanticException(
          "Dynamic mode type cannot receive messages. Resolve using snapshot.");
    }

    if (ctx.inStaticContext()) {
      // Kick up to super lang for now
      return n;
    }

    // Call is valid if the first mode type variable upper bound is greater
    // than the recievers mode type.
    //
    // Overmoded methods take precedence
    if (mt.modeType() != ts.DynamicModeType()) {
      ModeTypeVariable mtThis = ct.modeTypeVars().get(0);
      if (pi.modeTypeVars().isEmpty()) {
        if (!ts.isSubtype(mt.modeType(), mtThis)) {
          throw new SemanticException("Cannot send message to " + t + " from mode " +
                                      mtThis.upperBound() + ".");
        }
      } else {
        EntProcedureInstance pi2 = pi;
        if (mt instanceof ModeSubstClassType) {
          ModeSubstClassType mct = (ModeSubstClassType)mt;
          ModeSubst ms = mct.modeSubst();
          pi2 = ms.substProcedure(pi);
        }

        if (pi2.modeTypeVars().get(0) == ts.DynamicModeType()) {
          // Mark class for object saving
          n = (Call) ((EntCallExt) EntExt.ext(n)).proxyCall(true);
          ct.instancesNeedTypePreservation(true); 

        } else {
          if (!ts.isSubtype(pi2.modeTypeVars().get(0), mtThis)) {
            throw new SemanticException("Cannot send message to overmode<" +
                                        pi2.modeTypeVars().get(0) + "> from mode " +
                                        mtThis.upperBound() + ".");
          }
        }
      }
    }

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
    Type expectedRetType = ((EntCallExt)EntExt.ext(n)).expectedReturnType;
    ModeSubst subst = ts.inferModeTypeArgs(pi.baseInstance(), argTypes, expectedRetType);
    EntCallExt ext = (EntCallExt)EntExt.ext(n);
    return ext.infModeTypes(subst.modeTypeMap());
  }

  public boolean needsEntClosure() { return this.actualModeTypes() != null; }

  @Override
  public Node typePreserve(TypePreserver tp) {
    Call n = (Call)this.node();

    if (this.needsEntClosure()) {
      Map<ModeTypeVariable, Type> mtMap = new HashMap<>();
      EntProcedureInstance pi = ((EntProcedureInstance)n.procedureInstance()).baseInstance();
      for (int i = 0; i < pi.modeTypeVars().size(); i++) {
        mtMap.put(pi.modeTypeVars().get(i), this.actualModeTypes().get(i));
      }
      List<Expr> args = new ArrayList<>(n.arguments());
      args.add(EntBuilder.instance().buildEntClosure(
          tp.nodeFactory(), tp.toTypeSystem(), pi.modeTypeVars(), mtMap, tp.context()));
      n = (Call)n.arguments(args);
    }

    return n;
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter)rw;
    JL5NodeFactory nf = (JL5NodeFactory)prw.to_nf();

    JL5CallExt ext = (JL5CallExt)JL5Ext.ext(this.node());
    EntCallExt entext = (EntCallExt)EntExt.ext(this.node());
      
    Call call = (Call) this.node();

    if (entext.proxyCall()) {
      EntMethodInstance mi = (EntMethodInstance)call.methodInstance();

      call = (Call)super.extRewrite(rw);

      Id forwardId = nf.Id(Position.COMPILER_GENERATED, String.format("ENT_PROXY_%s", mi.name()));

      call = call.id(forwardId); 
      List<Expr> forwardArguments = new ArrayList<>();
      forwardArguments.add(
        nf.Call(
          Position.COMPILER_GENERATED,
          nf.AmbReceiver(
            Position.COMPILER_GENERATED,
            nf.Id(Position.COMPILER_GENERATED, "ENT_Runtime")
            ),
          nf.Id(Position.COMPILER_GENERATED, "getObjMode"),
          nf.This(Position.COMPILER_GENERATED), 
          nf.IntLit(Position.COMPILER_GENERATED, IntLit.INT, 0)
        )
      );
      for (Expr e : call.arguments()) {
        forwardArguments.add(e);
      } 

      call = (Call) call.arguments(forwardArguments);

      return nf.Call(call.position(), call.target(), ext.typeArgs(), call.id(), call.arguments());
    } else {
      call = (Call)super.extRewrite(rw);
      return nf.Call(call.position(), call.target(), ext.typeArgs(), call.id(), call.arguments());
    }
  


  }
}
