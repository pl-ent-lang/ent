package panda.runtime;

import java.util.WeakHashMap;
import java.util.HashMap;
import java.util.Map;

public class PANDA_Runtime {
  private static Map<Object, Integer[]> objTab = new WeakHashMap<>();
  private static Map<Integer, Integer> modeVarTab = new WeakHashMap<>();
  public static boolean NOEXIT_MODE = false;

  static {
    String panda_noexit_val = System.getenv("PANDA_NOEXIT");
    if (panda_noexit_val != null && panda_noexit_val.equals("true")) {
      NOEXIT_MODE = true;
    }
  }

  // For performance analysis
  private static int numGetObjModeCalls = 0;
  private static int numPutObjCalls = 0;

  public static int getObjMode(Object o, int m) {
    PANDA_Runtime.numGetObjModeCalls++;

    Integer mode = PANDA_Runtime.objTab.get(o)[m];
    if (mode == null) {
      return -1;
    }
    return mode.intValue();
  }

  public static Integer[] getObjAll(Object o) {
    return PANDA_Runtime.objTab.get(o);
  }

  public static Object putObj(Object o, Integer[] modes) {
    PANDA_Runtime.numPutObjCalls++;

    PANDA_Runtime.objTab.put(o, modes);
    return o;
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
}

