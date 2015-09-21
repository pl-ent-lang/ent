package panda.types;

import polyglot.types.*;

public abstract class PandaFlags extends Flags {
  public static final Flags MODESAFE = createFlag("modesafe", null);

  private PandaFlags() {
    super();
  }

  public static Flags setModesafe(Flags f) {
    return f.set(MODESAFE);
  }

  public static Flags clearModesafe(Flags f) {
    return f.clear(MODESAFE);
  }

  public static boolean isModesafe(Flags f) {
    return f.contains(MODESAFE);
  }

}

