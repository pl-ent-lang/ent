package panda.types;

import polyglot.types.Type;

public interface Mode extends Type {
  public int rank();

  public boolean isSubtypeOfMode(Mode mode);
  public boolean isSupertypeOfMode(Mode mode);

  public boolean isSubtypeOfModeImpl(Mode mode);
  public boolean isSupertypeOfModeImpl(Mode mode);

}
