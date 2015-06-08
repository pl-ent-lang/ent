package panda.util;

import java.util.List;
import java.util.Collections;

public class PandaUtil {

  public static <T> List<T> nonEmptyList(List<T> l) {
    if (l != null) {
      return l;
    } else {
      return Collections.emptyList();
    }
  }

}
