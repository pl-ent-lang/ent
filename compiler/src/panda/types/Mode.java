package panda.types;

public interface Mode {
  public int rank();

  public boolean isSubtypeOfMode(Mode mode);
  public boolean isSupertypeOfMode(Mode mode);

  public boolean isSubtypeOfModeImpl(Mode mode);
  public boolean isSupertypeOfModeImpl(Mode mode);

}
