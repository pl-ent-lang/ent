package panda.ast;

import polyglot.ast.Id;
import polyglot.ast.TypeNode_c;
import polyglot.util.Position;
import polyglot.util.CodeWriter;
import polyglot.visit.PrettyPrinter;

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
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
  }

}
