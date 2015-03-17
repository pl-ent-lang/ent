package panda.ast;

import polyglot.ast.Id;
import polyglot.ast.TypeNode;

public interface ModeTypeNode extends TypeNode {

  Id id();
  void id(Id id);
  

}
