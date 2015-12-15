package ent.runtime;

import java.util.WeakHashMap;
import java.util.HashMap;
import java.util.Map;

public class ENT_Runtime {
  private static Map<Object, Integer[]> objTab = new WeakHashMap<>();
  private static Map<Integer, Integer> modeVarTab = new WeakHashMap<>();
  public static boolean NOEXIT_MODE = false;

  static {
    String ent_noexit_val = System.getenv("ENT_NOEXIT");
    if (ent_noexit_val != null && ent_noexit_val.equals("true")) {
      NOEXIT_MODE = true;
    }
  }

  // For performance analysis
  private static int numGetObjModeCalls = 0;
  private static int numPutObjCalls = 0;

  public static int getObjMode(Object o, int m) {
    ENT_Runtime.numGetObjModeCalls++;

    Integer mode = ENT_Runtime.objTab.get(o)[m];
    if (mode == null) {
      return -1;
    }
    return mode.intValue();
  }

  public static Integer[] getObjAll(Object o) {
    return ENT_Runtime.objTab.get(o);
  }

  public static Object putObj(Object o, Integer[] modes) {
    ENT_Runtime.numPutObjCalls++;

    ENT_Runtime.objTab.put(o, modes);
    return o;
  }

  public static int getModeVar(int uniqueId) {
    Integer mode = ENT_Runtime.modeVarTab.get(uniqueId);
    if (mode == null) {
      return -1;
    }
    return mode.intValue();
  }

  public static void putModeVar(int uniqueId, int mode) {
    ENT_Runtime.modeVarTab.put(uniqueId, mode);
  } 
}

