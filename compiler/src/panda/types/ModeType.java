package panda.types;

import polyglot.ast.Id;
import polyglot.types.Type;

public interface ModeType extends Type, Mode {
  // Property Methods
  int rank();
  void rank(int rank);

  String mode();
  void mode(String mode);

  Mode superType();
  void superType(Mode superType);

  Mode subType();
  void subType(Mode subType);

  String compiledIdentifier(); 
}
