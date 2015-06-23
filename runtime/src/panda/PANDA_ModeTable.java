package panda.runtime;

import java.util.HashMap;
import java.util.Map;

public class PANDA_ModeTable {
  private static Map<Object, Integer> modeTab = new HashMap<>();

  public static int get(Object o) {
    Integer mode = PANDA_ModeTable.modeTab.get(o);
    if (mode == null) {
      return -1;
    }
    return mode.intValue();
  }

  public static void put(Object o, int mode) {
    PANDA_ModeTable.modeTab.put(o, new Integer(mode));
  }

  public static String modeDBG(Object o) {
    int m = PANDA_ModeTable.get(o);
    if (m == -1) {
      return o.toString() + ": not in mode table";
    } else {
      return o.toString() + ": mode " + Integer.toString(m); 
    }
  }


}

