package simple;

import java.lang.Iterable;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

public class Wrapper<T> implements Iterable<T> {
  private List<T> wrap;

  public Wrapper() {
    this.wrap = new ArrayList<>();
  }

  public Wrapper(T... args) {
    this.wrap = new ArrayList<>();
    for (int i = 0; i < args.length; ++i) {
      this.wrap.add(args[i]);
    }
  }

  public boolean add(T t) {
    return this.wrap.add(t);
  }

  public T get(int i) {
    return this.wrap.get(i);
  }

  public void set(int i, T t) {
    this.wrap.set(i, t);
  }

  public int size() {
    return this.wrap.size();
  }

  public Iterator<T> iterator() {
    return this.wrap.iterator();
  }

  public static void main(String[] args) {
    // 1. Simple cases, just String
    Wrapper<String> w1 = new Wrapper("a", "b", "c", "d", "e");

    w1.add("f");
    w1.add("g");
    w1.add("h");

    w1.set(0,"a!");
    w1.set(1,"b!");

    for (int i = 0; i < w1.size(); ++i) {
      System.out.println(w1.get(i));
    }

    for (String e : w1) {
      System.out.println(e);
    }

    // 2. Complex, build a bunch of Lists
    List<String> l1 = Arrays.asList("a", "b", "c");
    List<String> l2 = Arrays.asList("b", "c", "d");
    List<String> l3 = Arrays.asList("e", "f", "g");

    Wrapper<List<String> > w2 = new Wrapper(l1, l2, l3);
    w2.add(Arrays.asList("h", "i", "j"));

    w2.set(0, Arrays.asList("a!", "b!", "c!"));

    for (int i = 0; i < w2.size(); ++i) {
      System.out.println(w2.get(i));
    }

    for (List<String> e : w2) {
      System.out.println(e);
    }

  }

}
