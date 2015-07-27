package panda.types;

import panda.types.PandaTypeSystem;

import polyglot.types.Type;
import polyglot.types.Type_c;
import polyglot.types.Resolver;

public class McaseType_c extends Type_c implements McaseType {
  protected Type base;

  public McaseType_c(PandaTypeSystem ts, Type base) {
    super(ts);
    this.base = base;
  }

  // Property Methods
  public Type base() {
    return this.base;
  }

  @Override
  public boolean isCanonical() {
    return true;
  }

  @Override
  public String toString() {
    return "mcase<" + this.base() + ">";
  }

  @Override
  public String translate(Resolver c) {
    return this.base().translate(c) + "[]";
  } 

  @Override
  public boolean typeEqualsImpl(Type o) {
    PandaTypeSystem ts = (PandaTypeSystem) this.typeSystem();
    if (o instanceof McaseType) {
      return false;
    }
    McaseType_c m = (McaseType_c) o;
    return ts.typeEquals(this.base(), m.base());
  }

  @Override
  public boolean isCastValidImpl(Type toT) {
    PandaTypeSystem ts = (PandaTypeSystem) this.typeSystem();
    if (toT instanceof McaseType) {
      McaseType m = (McaseType) toT;
      return ts.isCastValid(this.base(), m.base());
    }

    return ts.isCastValid(this.base(), toT);
  }

  @Override
  public boolean isImplicitCastValidImpl(Type toT) {
    PandaTypeSystem ts = (PandaTypeSystem) this.typeSystem();
    if (toT instanceof McaseType) {
      McaseType m = (McaseType) toT;
      return ts.isImplicitCastValid(this.base(), m.base());
    }

    return ts.isImplicitCastValid(this.base(), toT);
  }

}
