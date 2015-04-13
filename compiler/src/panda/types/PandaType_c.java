package panda.types;

import polyglot.types.Type;
import polyglot.types.Type_c;
import polyglot.types.Resolver;
import polyglot.types.TypeObject;

public class PandaType_c extends Type_c implements PandaType {
  private Type baseType;
  private Type modeType;

  public PandaType_c(PandaTypeSystem typeSystem, Type baseType, Type modeType) {
    super(typeSystem);
    this.baseType = baseType;
    this.modeType = modeType;
  }

  // Property Methods
  public Type baseType() {
    return this.baseType;
  }

  public void baseType(Type baseType) {
    this.baseType = baseType;
  }

  public Type modeType() {
    return this.modeType;
  }

  public void modeType(Type modeType) {
    this.modeType = modeType;
  }

  public String toString() {
    return this.baseType() +  "@mode(" + this.modeType() + ")";
  }

  @Override
  public boolean isCanonical() {
    return true;
  } 

  @Override
  public String translate(Resolver c) {
    return this.baseType().translate(c);
  }
  
  @Override
  public boolean typeEqualsImpl(Type o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof PandaType)) {
      return false;
    }

    PandaType p = (PandaType) o;
    return (this.baseType().typeEquals(p.baseType()) &&
           this.modeType().typeEquals(p.modeType()));

  }

}
