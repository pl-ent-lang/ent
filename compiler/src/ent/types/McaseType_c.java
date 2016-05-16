package ent.types;

import ent.types.*;

import polyglot.types.*;

public class McaseType_c extends Type_c implements McaseType {
  protected Type base;

  public McaseType_c(EntTypeSystem ts, Type base) {
    super(ts);
    this.base = base;
  }

  // Property Methods
  public Type base() { return this.base; }

  @Override
  public boolean isCanonical() {
    return true;
  }

  @Override
  public PrimitiveType toPrimitive() {
    return this.base().toPrimitive();
  }

  @Override
  public boolean isNumeric() {
    return this.base().isNumeric();
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
    EntTypeSystem ts = (EntTypeSystem)this.typeSystem();
    if (o instanceof McaseType) {
      McaseType_c m = (McaseType_c)o;
      return ts.typeEquals(this.base(), m.base());
    }
    return ts.typeEquals(this.base(), o);
  }

  @Override
  public boolean isCastValidImpl(Type toT) {
    EntTypeSystem ts = (EntTypeSystem)this.typeSystem();
    if (toT instanceof McaseType) {
      McaseType m = (McaseType)toT;
      return ts.isCastValid(this.base(), m.base());
    }

    return ts.isCastValid(this.base(), toT);
  }

  @Override
  public boolean isImplicitCastValidImpl(Type toT) {
    EntTypeSystem ts = (EntTypeSystem)this.typeSystem();
    if (toT instanceof McaseType) {
      McaseType m = (McaseType)toT;
      return ts.isImplicitCastValid(this.base(), m.base());
    }

    return ts.isImplicitCastValid(this.base(), toT);
  }
}
