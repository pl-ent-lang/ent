package panda.runtime;

import java.util.WeakHashMap;
import java.util.HashMap;
import java.util.Map;

public class PANDA_Runtime {
  private static Map<Object, Integer[]> objTab = new HashMap<>();
  private static Map<Integer, Integer> modeVarTab = new HashMap<>();

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

  public static void report() {
    System.out.println("----- Calls -----");
    System.out.println("putObj: " + PANDA_Runtime.numPutObjCalls);
    System.out.println("getObjMode: " + PANDA_Runtime.numGetObjModeCalls);
    System.out.println("-----------------\n");

    System.out.println("----- Table -----");
    System.out.println("Table Size: " + PANDA_Runtime.objTab.size());
    System.out.println("-----------------\n");
  }


}

