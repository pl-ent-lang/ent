package panda.types;

import polyglot.ast.Id;
import polyglot.types.Type;

public interface ModeType extends Type, Mode {
  // Property Methods
  public String mode();
  public void mode(String mode);

  public int rank();
  public void rank(int rank);

  public String compiledIdentifier(); 
}
