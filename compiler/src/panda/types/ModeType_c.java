package panda.types;

import polyglot.ast.Id;
import polyglot.types.TypeObject_c;
import polyglot.types.TypeObject;

public class ModeType_c extends TypeObject_c implements ModeType {

  private String mode;

  // TODO: Might be a more firm way to do this other set an int
  private int rank;

  public ModeType_c(PandaTypeSystem ts, String mode) {
    super(ts);
    this.mode = mode;
    this.rank = -1;
  }

  // Property Methods
  public String mode() {
    return this.mode;
  }

  public void mode(String mode) {
    this.mode = mode;
  }

  public int rank() {
    return this.rank;
  }

  public void rank(int rank) {
    this.rank = rank;
  }

  public String compiledIdentifier() {
    String compiledIdentifier = "MODE_";
    if (this.mode().equals("?")) {
      compiledIdentifier += "DYN";
    } else {
      compiledIdentifier += this.mode().toUpperCase();
    }
    return compiledIdentifier;
  }

  // Business Methods
  public final boolean isSubtypeOfMode(ModeType mode) {
    PandaTypeSystem pandaTypeSystem = (PandaTypeSystem) this.typeSystem();
    return pandaTypeSystem.isSubtypeModes(this, mode);
  }

  public final boolean isSupertypeOfMode(ModeType mode) {
    PandaTypeSystem pandaTypeSystem = (PandaTypeSystem) this.typeSystem();
    return pandaTypeSystem.isSupertypeModes(this, mode);
  }

  public boolean isSubtypeOfModeImpl(ModeType mode) {
    return (this.rank() <= mode.rank());
  }

  public boolean isSupertypeOfModeImpl(ModeType mode) {
    return (this.rank() >= mode.rank());
  }

  @Override
  public boolean isCanonical() {
    return true;
  }

  @Override 
  public boolean equalsImpl(TypeObject o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof ModeType)) {
      return false;
    }

    ModeType m = (ModeType) o;

    return this.mode().equals(m.mode());
  } 

  @Override
  public int hashCode() {
    return this.mode().hashCode();
  }

}
