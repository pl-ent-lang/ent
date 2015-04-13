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

  private List<ModeParamTypeNode> modeParamTypes;

  public List<ModeParamTypeNode> modeParamTypes() {
    return this.modeParamTypes;
  }

  public void modeParamTypes(List<ModeParamTypeNode> modeParamTypes) {
    this.modeParamTypes = modeParamTypes;
  }

  public Node reconstruct(Node n, List<ModeParamTypeNode> modeParamTypes) {
    PandaClassDeclExt pandaClassDecl = (PandaClassDeclExt) PandaExt.ext(n);
    pandaClassDecl.modeParamTypes(modeParamTypes);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    Node n = superLang().visitChildren(this.node(), v);
    List<ModeParamTypeNode> modeParamTypes = 
      visitList(this.modeParamTypes(), v);
    return reconstruct(n, modeParamTypes);
  }

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    ClassDecl decl = (ClassDecl) superLang().buildTypes(this.node(), tb);

    PandaTypeSystem typeSystem = (PandaTypeSystem) tb.typeSystem();
    PandaParsedClassType classType = (PandaParsedClassType) decl.type();

    if (this.modeParamTypes() != null && !this.modeParamTypes().isEmpty()) {
      List<ModeTypeVariable> modeTypeVars = 
        new ArrayList<ModeTypeVariable>(this.modeParamTypes().size());
      for (TypeNode n : this.modeParamTypes()) {
        ModeTypeVariable modeTypeVar = (ModeTypeVariable) n.type();
        modeTypeVar.declaringClass(classType);
        modeTypeVars.add(modeTypeVar);
      }
      classType.modeTypeVariables(modeTypeVars);
    }

    return decl;
  }

  @Override
  public Context enterChildScope(Node child, Context c) {
    PandaClassDeclExt decl = (PandaClassDeclExt) PandaExt.ext(this.node());
    PandaContext context = (PandaContext) c;
    for (ModeParamTypeNode t : this.modeParamTypes()) {
      context.addModeTypeVariable((ModeTypeVariable) t.type());
    }
    return superLang().enterChildScope(this.node(), child, c);
  }

}
