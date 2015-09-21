package simple;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class CollectionsTest {
  private class Chunk implements Comparable<Chunk> {
    public int compareTo(Chunk o) {
      return 0;
    }
  }

  public static void main(String[] args) {
    List<Chunk> chunks = null;
    Collections.sort(chunks);
  }
}
