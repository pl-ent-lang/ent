package ent.types;

import polyglot.types.*;

public abstract class EntFlags extends Flags {
  public static final Flags MODESAFE = createFlag("modesafe", null);

  private EntFlags() { super(); }

  public static Flags setModesafe(Flags f) { return f.set(MODESAFE); }

  public static Flags clearModesafe(Flags f) { return f.clear(MODESAFE); }

  public static boolean isModesafe(Flags f) { return f.contains(MODESAFE); }
}
