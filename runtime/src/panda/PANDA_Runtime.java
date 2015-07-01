package panda.runtime;

import java.util.HashMap;
import java.util.Map;

public class PANDA_Runtime {
  private static Map<Object, Integer[]> objTab = new HashMap<>();
  private static Map<Integer, Integer> modeVarTab = new HashMap<>();

  public static int getObjMode(Object o, int m) {
    Integer mode = PANDA_Runtime.objTab.get(o)[m];
    if (mode == null) {
      return -1;
    }
    return mode.intValue();
  }

  public static Integer[] getObjAll(Object o) {
    return PANDA_Runtime.objTab.get(o);
  }

  public static void putObj(Object o, Integer[] modes) {
    PANDA_Runtime.objTab.put(o, modes);
  }

  public static int getModeVar(int uniqueId) {
    Integer mode = PANDA_Runtime.modeVarTab.get(uniqueId);
    if (mode == null) {
      return -1;
    }
    return mode.intValue();
  }

  public static void putModeVar(int uniqueId, int mode) {
    PANDA_Runtime.modeVarTab.put(uniqueId, mode);
  }

  public static String modeDBG(Object o) {
    Integer[] m = PANDA_Runtime.getObjAll(o);
    if (m == null) {
      return o.getClass() + ": not in mode table";
    } else {
      String s = o.getClass() + " - Table \n";
      for (int i = 0; i < m.length; ++i) {
        s += Integer.toString(i) + " : " + Integer.toString(m[i]) + "\n";
      }
      return s;
    }
  }


}

