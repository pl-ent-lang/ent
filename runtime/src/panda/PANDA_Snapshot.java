package panda.runtime;

public class PANDA_Snapshot {
  public static int WILDCARD  = 0;
  public static int DYNAMIC   = 0;

  public static <T extends PANDA_Attributable> T snapshot(T o, int lb, int ub) {
    int mode = o.PANDA_attribute();

    if (mode < lb && lb != PANDA_Modes.WILDCARD_MODE) {
      throw new PANDA_RuntimeException("Dynamic mode failed to resolve!", mode, lb, ub);
    }

    if (mode > ub && ub != PANDA_Modes.WILDCARD_MODE) {
      throw new PANDA_RuntimeException("Dynamic mode failed to resolve!", mode, lb, ub);
    }

    T copy = (T) o.PANDA_copy();
    Integer[] cmodes = PANDA_Runtime.getObjAll(o).clone();
    cmodes[0] = mode;

    PANDA_Runtime.putObj(copy, cmodes);
    return copy;
  }

}
