package panda.ast;

import polyglot.ast.Id;
import polyglot.ast.TypeNode;

public interface ModeTypeNode extends TypeNode {

  boolean isImplicitMode();
  void isImplicitMode(boolean isImplicitMode);

  Id id();
  void id(Id id);

  TypeNode base();
  void base(TypeNode base);

}
