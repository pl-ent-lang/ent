package panda.ast;

import panda.types.PandaContext;
import panda.types.ModeTypeVariable;
import panda.types.PandaTypeSystem;
import panda.types.PandaProcedureInstance;

import polyglot.ast.ProcedureDecl;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.types.Context;
import polyglot.types.Type;
import polyglot.types.SemanticException;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeChecker;

import java.util.Collections;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PandaProcedureDeclExt extends PandaExt {

  private List<ModeParamTypeNode> modeParams = Collections.emptyList();

  public List<ModeParamTypeNode> modeParams() {
    return this.modeParams;
  }

  public void modeParams(List<ModeParamTypeNode> modeParams) {
    if (modeParams != null) {
      this.modeParams = modeParams;
    } else {
      this.modeParams = Collections.emptyList();
    }
  }

  public Node reconstruct(Node n, List<ModeParamTypeNode> modeParams) {
    PandaProcedureDeclExt ext = (PandaProcedureDeclExt) PandaExt.ext(n);
    ext.modeParams(modeParams);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    Node n = superLang().visitChildren(this.node(), v);
    List<ModeParamTypeNode> modeParams = visitList(this.modeParams(), v);
    return reconstruct(n, modeParams);
  }

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    ProcedureDecl pd = (ProcedureDecl) superLang().buildTypes(this.node(), tb);

    PandaTypeSystem ts = (PandaTypeSystem) tb.typeSystem();
    PandaProcedureInstance mi = (PandaProcedureInstance) pd.procedureInstance();

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
        //mtVar.declaringClass(ct);
        mtVars.add(mtVar);
      }
      mi.modeTypeVars(mtVars);
    }

    return pd;
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

}
