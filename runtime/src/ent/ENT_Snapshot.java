package ent.runtime;

public class ENT_Snapshot {
  public static int WILDCARD = 0;
  public static int DYNAMIC = 0;

  public static int methodSnapshot(int methodMode, int ub) {
    if (methodMode > ub && ub != ENT_Modes.WILDCARD_MODE && !ENT_Runtime.NOEXIT_MODE) {
      throw new ENT_RuntimeException("Method mode failed to resolve!", methodMode, ub, ub);
    }
    return methodMode;
  }

  public static <T extends ENT_Attributable> T snapshot(T o, int lb, int ub, boolean saveMode) {
    int mode = o.ENT_attribute();

    if (mode < lb && lb != ENT_Modes.WILDCARD_MODE && !ENT_Runtime.NOEXIT_MODE) {
      throw new ENT_RuntimeException("Dynamic mode failed to resolve!", mode, lb, ub);
    }

    if (mode > ub && ub != ENT_Modes.WILDCARD_MODE && !ENT_Runtime.NOEXIT_MODE) {
      throw new ENT_RuntimeException("Dynamic mode failed to resolve!", mode, lb, ub);
    }

    if (ENT_Runtime.objectCopied(o)) {
      T copy = (T)o.ENT_copy();
      if (saveMode) {
        Integer[] cmodes = ENT_Runtime.getObjAll(o).clone();
        cmodes[0] = mode;
        ENT_Runtime.putObj(copy, cmodes);
      }
      return copy;
    } else {
      Integer[] cmodes = ENT_Runtime.getObjAll(o);
      cmodes[0] = mode;
      ENT_Runtime.updateObject(o, true, cmodes);
      return o;
    }
  }

  public static <T extends ENT_Attributable>
      T forceSnapshot(T o, int lb, int ub, boolean saveMode) {
    int mode = o.ENT_attribute();
    if (mode < lb || mode > ub) {
      mode = ub;
    }

    if (ENT_Runtime.objectCopied(o)) {
      T copy = (T)o.ENT_copy();
      if (saveMode) {
        Integer[] cmodes = ENT_Runtime.getObjAll(o).clone();
        cmodes[0] = mode;
        ENT_Runtime.putObj(copy, cmodes);
      }
      return copy;
    } else {
      Integer[] cmodes = ENT_Runtime.getObjAll(o);
      cmodes[0] = mode;
      ENT_Runtime.updateObject(o, true, cmodes);
      return o;
    }
  }
}
