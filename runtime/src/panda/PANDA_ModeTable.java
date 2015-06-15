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
    PandaTypeTable.put(o, new Integer(mode));
  }


}

