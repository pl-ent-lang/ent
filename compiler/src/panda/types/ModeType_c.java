package panda.types;

import polyglot.ast.Id;
import polyglot.types.TypeObject;
import polyglot.types.Type;
import polyglot.types.Type_c;
import polyglot.types.Resolver;

public class ModeType_c extends Type_c implements ModeType {
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

  public void rank(int rank) {
    this.rank = rank;
  }

  public String compiledIdentifier() {
    String compiledIdentifier = "MODE_";
    if (this.mode().equals("?")) {
      compiledIdentifier += "DYN";
    } else if (this.mode().equals("*")) {
      compiledIdentifier += "WILDCARD"; 
    } else {
      compiledIdentifier += this.mode().toUpperCase();
    }
    return compiledIdentifier;
  }

  @Override
  public boolean isCanonical() {
    return true;
  }

  @Override
  public String toString() {
    return this.mode();
  }

  @Override
  public String translate(Resolver c) {
    return this.toString();
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
  public boolean typeEqualsImpl(Type o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof ModeType)) {
      return false;
    }

    ModeType m = (ModeType) o;

    PandaTypeSystem ts = (PandaTypeSystem) this.typeSystem();
    // Either the mode types are equal, or one is a wildcard (for now)

    return (this == m ||
            this == ts.WildcardModeType() ||
            m == ts.WildcardModeType());
  } 

  @Override
  public int hashCode() {
    return this.mode().hashCode();
  }

  // Mode Methods
  public int rank() {
    return this.rank;
  }

  public final boolean isSubtypeOfMode(Mode mode) {
    return ((PandaTypeSystem) this.typeSystem()).isSubtypeModes(this, mode);
  }

  public final boolean isSupertypeOfMode(Mode mode) {
    return ((PandaTypeSystem) this.typeSystem()).isSupertypeModes(this, mode);
  }

  public boolean isSubtypeOfModeImpl(Mode mode) {
    return (this.rank() <= mode.rank());
  }

  public boolean isSupertypeOfModeImpl(Mode mode) {
    return (this.rank() >= mode.rank());
  }


}
