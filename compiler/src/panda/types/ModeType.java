package panda.types;

import polyglot.ast.Id;
import polyglot.types.Type;

public interface ModeType extends Type {
  // Property Methods
  public String mode();
  public void mode(String mode);

  public int rank();
  public void rank(int rank);

  public String compiledIdentifier();

  // Business Methods
  public boolean isSubtypeOfMode(ModeType mode);
  public boolean isSupertypeOfMode(ModeType mode);

  public boolean isSubtypeOfModeImpl(ModeType mode);
  public boolean isSupertypeOfModeImpl(ModeType mode);
}
