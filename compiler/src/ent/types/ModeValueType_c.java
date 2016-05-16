package ent.types;

import polyglot.types.Type;
import polyglot.types.Type_c;
import polyglot.types.Resolver;

public class ModeValueType_c extends Type_c implements ModeValueType {

  private Type mode;

  public ModeValueType_c(EntTypeSystem ts, Type mode) {
    super(ts);
    this.mode = mode;
  }

  public Type mode() { return this.mode; }

  public String name() { return "@mode<" + this.mode() + ">"; }

  @Override
  public String toString() {
    return this.name();
  }

  @Override
  public String translate(Resolver c) {
    return this.toString();
  }

  @Override
  public boolean isCanonical() {
    return true;
  }

  @Override
  public boolean containsBuiltin() {
    return !(this.mode() instanceof ModeTypeVariable);
  }

  @Override
  public boolean containsVariable() {
    return (this.mode() instanceof ModeTypeVariable);
  }
}
