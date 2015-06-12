package panda.types;

import polyglot.ast.Id;
import polyglot.types.TypeObject;
import polyglot.types.Type;
import polyglot.types.Type_c;
import polyglot.types.Resolver;

public class ModeType_c extends Type_c implements ModeType {
  private String mode;

  private int rank;
  private Type superType;
  private Type subType;

  public ModeType_c(PandaTypeSystem ts, String mode) {
    super(ts);
    this.rank = -1;
    this.mode = mode;
  }

  // Property Methods
  public int rank() {
    return this.rank;
  }

  public void rank(int rank) {
    this.rank = rank;
  }

  public String mode() {
    return this.mode;
  }

  public void mode(String mode) {
    this.mode = mode;
  }

  public Type superType() {
    return this.superType;
  }

  public void superType(Type superType) {
    this.superType = superType;
  }

  public Type subType() {
    return this.subType;
  }

  public void subType(Type subType) {
    this.subType = subType;
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
    return (this == o);
  } 

  @Override 
  public boolean typeEqualsImpl(Type o) {
    if (this == o) {
      return true;
    }

    PandaTypeSystem ts = (PandaTypeSystem) this.typeSystem();

    return (this == o ||
            this == ts.WildcardModeType() ||
            o == ts.WildcardModeType());
  } 

  @Override
  public boolean descendsFromImpl(Type o) {
    PandaTypeSystem ts = (PandaTypeSystem) this.typeSystem();
    if (ts.typeEquals(this,o)) {
      return true;
    }
    if (this.superType() == null) {
      return false;
    }
    return ts.isSubtype(this.superType(), o);
  }

  @Override
  public int hashCode() {
    return this.mode().hashCode();
  }

}
