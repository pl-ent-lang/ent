package panda.runtime;

public class PANDA_Snapshot {
  public static int WILDCARD  = 0;
  public static int DYNAMIC   = 0;

  public static <T extends PANDA_Attributable> T snapshot(T o, int lb, int ub) {
    int mode = o.PANDA_attribute();

    if (mode < lb && lb != PANDA_Modes.WILDCARD_MODE) {
      System.out.println("Failed to resolve");
      System.exit(1);
    }

    if (mode > ub && ub != PANDA_Modes.WILDCARD_MODE) {
      System.out.println("Failed to resolve");
      System.exit(1);
    }

    //Object clone = o.clone();
    PandaTypeTable.put(o, new Integer(mode));
    return o;
  }

}
