package panda.ast;

import panda.types.PandaTypeSystem;
import panda.types.ModeTypeVariable;

import polyglot.ast.Id;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.ast.TypeNode_c;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.util.CodeWriter;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeBuilder;

public class ModeParamTypeNode_c extends TypeNode_c implements ModeParamTypeNode {

  private Id id;

  public ModeParamTypeNode_c(Position pos, Id id) {
    super(pos);
    this.id = id;
  }

  public Id id() {
    return this.id;
  }

  public void id(Id id) {
    this.id = id;
  }

  @Override
  public Node buildTypes(TypeBuilder typeBuilder) throws SemanticException {
    PandaTypeSystem pandaTypeSystem = (PandaTypeSystem) typeBuilder.typeSystem();
    ModeTypeVariable modeTypeVar = 
      pandaTypeSystem.createModeTypeVariable(this.position(), this.id().id());
    TypeNode n = this;
    return n.type(modeTypeVar);
  }

  @Override
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
  }

}
