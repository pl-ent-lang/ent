package panda.ast;

import panda.types.PandaContext;
import panda.types.ModeTypeVariable;
import panda.types.PandaTypeSystem;
import panda.types.PandaParsedClassType;

import polyglot.ast.ClassDecl;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.types.Context;
import polyglot.types.Type;
import polyglot.types.SemanticException;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeBuilder;

import java.util.List;
import java.util.ArrayList;

public class PandaClassDeclExt extends PandaExt {

  private List<ModeParamTypeNode> modeParams;

  public List<ModeParamTypeNode> modeParams() {
    return this.modeParams;
  }

  public void modeParams(List<ModeParamTypeNode> modeParams) {
    this.modeParams = modeParams;
  }

  public Node reconstruct(Node n, List<ModeParamTypeNode> modeParams) {
    PandaClassDeclExt ext = (PandaClassDeclExt) PandaExt.ext(n);
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
    ClassDecl decl = (ClassDecl) superLang().buildTypes(this.node(), tb);

    PandaTypeSystem ts = (PandaTypeSystem) tb.typeSystem();
    PandaParsedClassType ct = (PandaParsedClassType) decl.type();

    if (this.modeParams() != null && !this.modeParams().isEmpty()) {
      List<ModeTypeVariable> mtVars = 
        new ArrayList<ModeTypeVariable>(this.modeParams().size());
      for (TypeNode n : this.modeParams()) {
        ModeTypeVariable mtVar = (ModeTypeVariable) n.type();
        mtVar.declaringClass(ct);
        mtVars.add(mtVar);
      }
      ct.modeTypeVars(mtVars);
    }

    return decl;
  }

  @Override
  public Context enterChildScope(Node child, Context c) {
    PandaClassDeclExt decl = (PandaClassDeclExt) PandaExt.ext(this.node());
    PandaContext context = (PandaContext) c;
    for (ModeParamTypeNode t : this.modeParams()) {
      context.addModeTypeVariable((ModeTypeVariable) t.type());
    }
    return superLang().enterChildScope(this.node(), child, c);
  }

}
