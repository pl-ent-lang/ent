package panda.types;

import polyglot.ast.Id;
import polyglot.types.TypeObject;
import polyglot.types.Type;
import polyglot.types.Type_c;
import polyglot.types.Resolver;

public class ModeType_c extends Type_c implements ModeType {
  protected String name;

  private int rank;
  protected Type superType;
  protected Type subType;

  public ModeType_c(PandaTypeSystem ts, String name) {
    super(ts);
    this.rank = -1;
    this.name = name;
  }

  // Property Methods
  public int rank() {
    return this.rank;
  }

  public void rank(int rank) {
    this.rank = rank;
  }

  public String name() {
    return this.name;
  }

  public void name(String name) {
    this.name = name;
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

  public String runtimeCode() {
    String runtimeCode = "MODE_";
    if (this.name().equals("?")) {
      runtimeCode += "DYN";
    } else if (this.name().equals("*")) {
      runtimeCode += "WILDCARD"; 
    } else {
      runtimeCode += this.name().toUpperCase();
    }
    return runtimeCode;
  }

  @Override
  public boolean isCanonical() {
    return true;
  }

  @Override
  public String toString() {
    return this.name();
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

}
