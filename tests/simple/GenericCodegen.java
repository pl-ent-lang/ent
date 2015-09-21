package simple;

import java.util.List;
import java.util.Collections;

public class GenericCodegen {
  private List<String> strings;

  public List<String> getString() {
    return strings != null ? strings : Collections.<String>emptyList();
  }
}
