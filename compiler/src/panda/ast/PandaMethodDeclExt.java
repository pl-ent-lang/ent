package panda.ast;

import panda.types.PandaContext;
import panda.types.ModeTypeVariable;
import panda.types.PandaTypeSystem;
import panda.types.PandaMethodInstance;

import polyglot.ast.MethodDecl;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.types.Context;
import polyglot.types.Type;
import polyglot.types.SemanticException;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeChecker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PandaMethodDeclExt extends PandaExt {

  private List<ModeParamTypeNode> modeParams;

  public List<ModeParamTypeNode> modeParams() {
    return this.modeParams;
  }

  public void modeParams(List<ModeParamTypeNode> modeParams) {
    this.modeParams = modeParams;
  }

  public Node reconstruct(Node n, List<ModeParamTypeNode> modeParams) {
    PandaMethodDeclExt ext = (PandaMethodDeclExt) PandaExt.ext(n);
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
    MethodDecl md = (MethodDecl) superLang().buildTypes(this.node(), tb);

    PandaTypeSystem ts = (PandaTypeSystem) tb.typeSystem();
    PandaMethodInstance mi = (PandaMethodInstance) md.methodInstance();

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

    return md;
  }

  @Override
  public Context enterScope(Context c) {
    MethodDecl md = (MethodDecl) this.node();
    c = superLang().enterScope(md, c);
    for (ModeParamTypeNode t : this.modeParams()) {
      ((PandaContext) c).addModeTypeVariable((ModeTypeVariable) t.type());
    }
    return c;
  } 

}
