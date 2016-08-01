package diamond_type;

import java.util.List;
import java.util.ArrayList;

public class Optional<T> {
  public T t;

  public Optional(T t) {
    this.t = t;
  }

  public static <T> Optional<T> of(T ref) {
    return new Optional<T>(ref);
  }

  public static void main(String[] args) {
    Optional<Pair<String,Integer>> o = Optional.of(new Pair<>("abc",1));
  }
}
