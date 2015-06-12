package panda.types;

import polyglot.ast.Id;
import polyglot.types.Type;

public interface ModeType extends Type {
  // Property Methods
  int rank();
  void rank(int rank);

  String mode();
  void mode(String mode);

  Type superType();
  void superType(Type superType);

  Type subType();
  void subType(Type subType);

  String compiledIdentifier(); 
}
