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

public class PandaNewExt extends PandaExt {

  protected Map<ModeTypeVariable, Type> infModeTypes;

  protected Map<ModeTypeVariable, Type> infModeTypes() {
    return this.infModeTypes;
  }

  protected New infModeTypes(Map<ModeTypeVariable, Type> infModeTypes) {
    return this.infModeTypes((New)this.node(), infModeTypes);
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
    n = ext.infModeTypes(subst.modeTypeMap());
    return this.needsTypePreservation(n, true);
  }

  @Override
  public Node typePreserve(TypePreserver tp) {
    New n = (New) this.node();
    PandaNodeFactory nf = (PandaNodeFactory) tp.nodeFactory();

    if (!this.needsTypePreservation()) {
      return n;
    }

    System.out.println("Preserving!");

    List<Expr> elems = new ArrayList<>();
    List<Expr> args = new ArrayList<>(n.arguments());

    PandaProcedureInstance pi = (PandaProcedureInstance) n.procedureInstance();
    for (ModeTypeVariable v : pi.modeTypeVars()) {
      ModeType mt = (ModeType) this.infModeTypes().get(v);
      elems.add(mt.rewriteForLookup(nf));
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

    args.add(larg);

    return n.arguments(args);
  }

}
