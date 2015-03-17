package panda.ast;

import polyglot.ast.Id;
import polyglot.ast.TypeNode;

public interface ModeParamTypeNode extends TypeNode {

  public Id id();
  public void id(Id id);

}
