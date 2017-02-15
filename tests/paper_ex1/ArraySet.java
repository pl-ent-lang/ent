package paper_ex1;

import java.util.*;
import java.lang.*;

public class ArraySet implements Iterable<String> {
  public List<String> urls = new ArrayList<>();

  public ArraySet(String seed) { }

  public ArraySet() { }

  public ArraySet remove(String s) { 
    return this;
  }

  public ArraySet add(ArraySet s) { 
    return this;
  }

  public Iterator<String> iterator() {
    return urls.iterator();
  }
}


