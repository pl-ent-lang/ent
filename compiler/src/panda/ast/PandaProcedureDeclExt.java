package panda.ast;

import panda.types.*;
import panda.visit.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.util.*;
import polyglot.visit.*;

import java.util.Collections;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PandaProcedureDeclExt extends PandaExt {

  protected List<ModeParamTypeNode> modeParams = Collections.emptyList();

  public List<ModeParamTypeNode> modeParams() {
    return this.modeParams;
  }

  public Node modeParams(List<ModeParamTypeNode> modeParams) {
    return this.modeParams(this.node(), modeParams);
  }

  public <N extends Node> N modeParams(N n, List<ModeParamTypeNode> modeParams) {
    PandaProcedureDeclExt ext = (PandaProcedureDeclExt) PandaExt.ext(n);
    if (CollectionUtil.equals(ext.modeParams, modeParams)) return n;
    if (this.node() == n) {
      n = Copy.Util.copy(n);
      ext = (PandaProcedureDeclExt) PandaExt.ext(n);
    }
    ext.modeParams = ListUtil.copy(modeParams, true); 
    return n;
  }

  // Node Methods
  protected Node reconstruct(Node n, List<ModeParamTypeNode> modeParams) {
    n = this.modeParams(n, modeParams);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    Node n = superLang().visitChildren(this.node(), v);
    List<ModeParamTypeNode> modeParams = visitList(this.modeParams(), v);
    return this.reconstruct(n, modeParams);
  }

  @Override
  public Context enterScope(Context c) {
    ProcedureDecl pd = (ProcedureDecl) this.node();
    c = superLang().enterScope(pd, c);
    for (ModeParamTypeNode t : this.modeParams()) {
      ((PandaContext) c).addModeTypeVariable((ModeTypeVariable) t.type());
    }
    return c;
  } 

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    ProcedureDecl pd = (ProcedureDecl) superLang().buildTypes(this.node(), tb);

    PandaTypeSystem ts = (PandaTypeSystem) tb.typeSystem();
    PandaProcedureInstance pi = (PandaProcedureInstance) pd.procedureInstance();
    PandaClassType ct = (PandaClassType) pi.container();

    int dbInd = ct.modeTypeVars().size();
    if (this.modeParams() != null && !this.modeParams().isEmpty()) {
      List<ModeTypeVariable> mtVars = 
        new ArrayList<ModeTypeVariable>(this.modeParams().size());
      Set<String> mtVarCheck = new HashSet<>();

      for (TypeNode n : this.modeParams()) {
        // Check and catch duplicate error as early as possible
        if (mtVarCheck.contains(n.name())) {
          throw new SemanticException("Duplicate mode type variable declaration.",
                                      n.position());
        }
        mtVarCheck.add(n.name());

        ModeTypeVariable mtVar = (ModeTypeVariable) n.type();
        mtVar.declaringProc(pi);
        mtVar.index(dbInd);
        mtVars.add(mtVar);
        ++dbInd;
      }
      pi.modeTypeVars(mtVars);
    }

    return pd;
  }

  @Override
  public Node typePreserve(TypePreserver tp) {
    ProcedureDecl n = (ProcedureDecl) this.node();
    PandaNodeFactory nf = (PandaNodeFactory) tp.nodeFactory();
    PandaTypeSystem ts = (PandaTypeSystem) tp.typeSystem();

    // To preserve the context of the mode type vars, we simply accept PANDA_Closure
    List<Formal> formals = new ArrayList<>(n.formals());
    Formal f =
      nf.Formal(
        Position.COMPILER_GENERATED,
        Flags.NONE,
        nf.AmbTypeNode(
          Position.COMPILER_GENERATED,
          nf.Id(
            Position.COMPILER_GENERATED,
            "PANDA_Closure"
            )
          ),
        nf.Id(
          Position.COMPILER_GENERATED,
          "PANDA_this"
          )
        );
    LocalInstance li =
      ts.localInstance(
        f.position(),
        f.flags(),
        ts.unknownType(
          Position.COMPILER_GENERATED
          ),
        f.name()
        );

    f = f.localInstance(li);
    formals.add(f);
     
    return n.formals(formals);
  }

}
