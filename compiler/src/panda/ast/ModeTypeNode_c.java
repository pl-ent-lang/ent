package panda.ast;

import polyglot.ast.Id;
import polyglot.ast.TypeNode_c;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.PrettyPrinter;

public class ModeTypeNode_c extends TypeNode_c implements ModeTypeNode {

  private Id id;

  public ModeTypeNode_c(Position pos, Id id) {
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
