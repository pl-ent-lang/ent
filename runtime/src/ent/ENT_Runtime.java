package ent.runtime;

import java.util.WeakHashMap;
import java.util.HashMap;
import java.util.Map;

public class ENT_Runtime {
  private static Map<Object, ENT_ObjTag> objTab = new WeakHashMap<>();
  public static boolean NOEXIT_MODE = false;

  static {
    String ent_noexit_val = System.getenv("ENT_NOEXIT");
    if (ent_noexit_val != null && ent_noexit_val.equals("true")) {
      NOEXIT_MODE = true;
    }
  }

  public static int getObjMode(Object o, int m) {
    Integer mode = ENT_Runtime.objTab.get(o).modes[m];
    if (mode == null) {
      return -1;
    }
    return mode.intValue();
  }

  public static Integer[] getObjAll(Object o) { return ENT_Runtime.objTab.get(o).modes; }

  public static void updateObject(Object o, boolean copied, Integer[] modes) {
    ENT_Runtime.objTab.get(o).copied = copied;
    ENT_Runtime.objTab.get(o).modes = modes;
  }

  public static Object putObj(Object o, Integer[] modes) {
    ENT_Runtime.objTab.put(o, new ENT_ObjTag(false, modes));
    return o;
  }

  public static boolean objectCopied(Object o) { return ENT_Runtime.objTab.get(o).copied; }

  public static int objectTableSize() {
    return ENT_Runtime.objTab.size();
  }

}
