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
    if (!(o instanceof McaseType)) {
      return false;
    }
    McaseType_c m = (McaseType_c) o;
    PandaTypeSystem ts = (PandaTypeSystem) this.typeSystem();
    return ts.typeEquals(this.base(), m.base());
  }

}
