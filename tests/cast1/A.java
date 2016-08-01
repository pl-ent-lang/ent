package cast1;

import java.util.Set;
import java.util.HashSet;

/* POLY-BUG: The following is a bug in polyglot. javac can compile. */
public class A {
  public static void main(String[] args) {
    Set<Long> longs = new HashSet<>();
    long l = (long)longs.toArray()[0];
  }
}
