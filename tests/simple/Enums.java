package simple;

import java.lang.Iterable;
import java.util.Arrays;
import java.util.Collections;

public enum Enums {
  FIRST(1),
  SECOND(2),
  THIRD(3),
  FOURTH(4),
  FIFTH(5);

  private final int num;

  Enums(int num) {
    this.num = num;
  }

  Enums fix(String s) {
    return valueOf(s);
  }

  private static Iterable<Enums> allFlags = Collections.unmodifiableList(Arrays.asList(Enums.values()));
}
