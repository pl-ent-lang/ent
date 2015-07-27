package panda.ast;

import panda.types.*;
import panda.visit.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.visit.*;
import polyglot.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PandaNewExt extends PandaExt implements NewOps {

  protected Map<ModeTypeVariable, Type> infModeTypes;

  @Override
  public New node() {
    return (New) super.node();
  }

  protected Map<ModeTypeVariable, Type> infModeTypes() {
    return this.infModeTypes;
  }

  protected New infModeTypes(Map<ModeTypeVariable, Type> infModeTypes) {
    return this.infModeTypes(this.node(), infModeTypes);
  }

  protected <N extends New> N infModeTypes(N n, Map<ModeTypeVariable, Type> infModeTypes) {
    PandaNewExt ext = (PandaNewExt) PandaExt.ext(n);
    if (ext.infModeTypes == infModeTypes) return n;
    if (this.node() == n) {
      n = Copy.Util.copy(n);
      ext = (PandaNewExt) PandaExt.ext(n);
    }
    ext.infModeTypes = infModeTypes;
    return n;
  }

  // ProcedureCallOps
  @Override
  public void printArgs(CodeWriter w, PrettyPrinter tr) {
    superLang().printArgs(this.node(), w, tr);
  }

  // ExprOps
  @Override
  public boolean constantValueSet(Lang lang) {
    return superLang().constantValueSet(node(), lang);
  }

  @Override
  public boolean isConstant(Lang lang) {
    return superLang().isConstant(node(), lang);
  }

  @Override
  public Object constantValue(Lang lang) {
    return superLang().constantValue(node(), lang);
  }


  @Override
  public TypeNode findQualifiedTypeNode(AmbiguityRemover ar, ClassType outer,
            TypeNode objectType) throws SemanticException {
    return superLang().findQualifiedTypeNode(this.node(), ar, outer, objectType);
  }

  @Override
  public Expr findQualifier(AmbiguityRemover ar, ClassType ct) throws SemanticException {
    return superLang().findQualifier(this.node(), ar, ct);
  }

  @Override
  public void typeCheckFlags(TypeChecker tc) throws SemanticException {
    superLang().typeCheckFlags(this.node(), tc);
  }

  @Override
  public void typeCheckNested(TypeChecker tc) throws SemanticException {
    superLang().typeCheckNested(this.node(), tc);
  }

  @Override
  public void printQualifier(CodeWriter w, PrettyPrinter tr) {
    superLang().printQualifier(this.node(), w, tr);
  }

  @Override
  public void printShortObjectType(CodeWriter w, PrettyPrinter tr) {
    superLang().printShortObjectType(this.node(), w, tr);
  } 

  @Override
  public void printBody(CodeWriter w, PrettyPrinter tr) {
    superLang().printBody(this.node(), w, tr);
  }

  @Override
  public ClassType findEnclosingClass(Context c, ClassType ct) {
    if (ct instanceof ModeSubstType) {
      ct = (ClassType) ((ModeSubstType) ct).baseType();
    }
    return superLang().findEnclosingClass(this.node(), c, ct);
  } 

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException { 
    New n = (New) superLang().typeCheck(this.node(), tc);
    PandaTypeSystem ts = (PandaTypeSystem) tc.typeSystem();

    PandaProcedureInstance pi = 
      (PandaProcedureInstance) n.constructorInstance();

    if (pi.modeTypeVars().isEmpty()) {
      return n;
    }

    // TODO : This is ugly, but until the parent language has been type checked
    // can not really use the inference functions. At least it can be a protoype
    // for now.

    System.out.println("OCont: " + pi.container());

    System.out.println("Base: " + pi.baseInstance());
    System.out.println("Cont: " + pi.baseInstance().container());

    List<Type> argTypes = new ArrayList<>();
    for (Expr e : n.arguments()) {
      argTypes.add(e.type());
    }
    ModeSubst subst = ts.inferModeTypeArgs(pi.baseInstance(), argTypes, null);

    PandaNewExt ext = (PandaNewExt) PandaExt.ext(n);
    return ext.infModeTypes(subst.modeTypeMap());
  }

  @Override
  public Node typePreserve(TypePreserver tp) {
    New n = this.node();

    PandaNodeFactory nf = (PandaNodeFactory) tp.nodeFactory();
    Context ctx = tp.context();

    if (!(n.type() instanceof ModeSubstType)) {
      return n;
    }

    ModeSubstType st = (ModeSubstType) n.type();
    PandaClassType ct = (PandaClassType) st.baseType();

    // Steps to preserve types
    // 1. Create a PANDA_Closure for method mode type variables
    // 2. Rewrite new A() to new A(new PANDA_Closure()) if non
    // builtin constructor.
    // 3. Rewrite new A() to ((A) PANDA_Runtime.putObj(new A(), constants))

    // 1.1. Capture the inferred variables from the instantiation of the method
    // mode type variables.
    PandaProcedureInstance pi = (PandaProcedureInstance) n.procedureInstance();

    if (pi.modeTypeVars().size() != 0) {
      // 1.1. Capture the inferred method mode type variables.
      List<Expr> closElems = new ArrayList<>();
      for (ModeTypeVariable v : pi.modeTypeVars()) {
        ModeType mt = (ModeType) this.infModeTypes().get(v);
        closElems.add(mt.rewriteForLookup(nf, ctx, tp.to_ts()));
      }

      // 1.2. Build the closure expression
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
            closElems
            )
          )
        );

      // 1.3. Rewrite if non builtin
      List<Expr> args = new ArrayList<>(n.arguments());
      args.add(
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
          )
        );
      n = n.arguments(args);
    }

    // 2.1. Capure mode type arguments to insert into the mode table.
    List<Expr> tabElems = new ArrayList<>();
    for (Type t : ((ModeSubstType) n.type()).modeTypeArgs()) {
      ModeType mt = (ModeType) t;
      tabElems.add(mt.rewriteForLookup(nf, ctx, tp.to_ts()));
    }

    // 2.2. Rewrite to insert the new object into the mode table.
    Cast c =
      nf.Cast(
        Position.COMPILER_GENERATED,
        n.objectType(),
        nf.Call(
          Position.COMPILER_GENERATED,
          nf.AmbTypeNode(
            Position.COMPILER_GENERATED,
            nf.Id(
              Position.COMPILER_GENERATED,
              "PANDA_Runtime"
              )
            ),
          nf.Id(
            Position.COMPILER_GENERATED,
            "putObj"
            ),
          n,
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
              tabElems
              )
            )
          )
        );

    return c;
  }
}
