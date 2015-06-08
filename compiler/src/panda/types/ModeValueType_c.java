package panda.types;

import polyglot.types.Type;
import polyglot.types.Type_c;
import polyglot.types.Resolver;

public class ModeValueType_c extends Type_c implements ModeValueType {

  private Mode mode;

  public ModeValueType_c(PandaTypeSystem ts,
                         Mode mode) {
    super(ts);
    this.mode = mode;
  }

  public Mode mode() {
    return this.mode;
  }

  public String name() {
    return "@mode<" + this.mode() + ">";
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
  public boolean isCanonical() {
    return true;
  }



}
