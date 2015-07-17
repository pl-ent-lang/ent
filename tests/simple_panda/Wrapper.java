package simple_panda;

import java.lang.Iterable;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

modes {low <: mid; mid <: high; };

public class Wrapper<T> implements Iterable<T> {
  private List<T> wrap;

  public Wrapper() {
    this.wrap = new ArrayList<>();
  }

  /*
  public Wrapper(T... args) {
    this.wrap = new ArrayList<>();
    for (int i = 0; i < args.length; ++i) {
      this.wrap.add(args[i]);
    }
  }
  */

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
    // 1. Simple cases, test how String is contained
    Wrapper<String@mode<low> > w1 = new Wrapper<String@mode<low> >();
    w1.add("a");
    w1.add("b");
    w1.add("c");
    w1.add("d");
    w1.add("e"); 

    String@mode<low> sl1 = "f";
    String@mode<low> sl2 = "g";
    String@mode<low> sl3 = "h";

    w1.add(sl1);
    w1.add(sl2);
    w1.add(sl3);

    w1.set(0,sl1);
    w1.set(1,sl2);

    for (int i = 0; i < w1.size(); ++i) {
      System.out.println(w1.get(i));
    }

    for (String@mode<low> e : w1) {
      System.out.println(e);
    }


    // 2. Simple cases, test how String is contaiend with a different mode
    Wrapper<String@mode<high> > w2 = new Wrapper<String@mode<high> >();
    
    w2.add("a");
    w2.add("b");
    w2.add("c");
    w2.add("d");
    w2.add("e");

    String@mode<high> sh1 = "f";
    String@mode<high> sh2 = "g";
    String@mode<high> sh3 = "h";

    w2.add(sh1);
    w2.add(sh2);
    w2.add(sh3);

    w2.set(0,sh1);
    w2.set(1,sh2);

    for (int i = 0; i < w2.size(); ++i) {
      System.out.println(w2.get(i));
    }

    for (String@mode<high> e : w2) {
      System.out.println(e);
    }


    // 2. Testing more complicated generic for (w3).
    List<String@mode<low> >@mode<mid> l1 = Arrays.asList("a", "b", "c");
    List<String@mode<low> >@mode<mid> l2 = Arrays.asList("b", "c", "d");
    List<String@mode<low> >@mode<mid> l3 = Arrays.asList("e", "f", "g");

    Wrapper< List< String@mode<low> >@mode<mid> > w3 = new Wrapper< List< String@mode<low> >@mode<mid> >();
    w3.add(l1);
    w3.add(l2);
    w3.add(l3);

    List<String@mode<low> >@mode<mid> l4 = Arrays.asList("h", "i", "j");

    w3.add(l4);

    w3.set(0, l4);

    for (int i = 0; i < w3.size(); ++i) {
      System.out.println(w3.get(i));
    }

    for (List<String@mode<low> >@mode<mid> e : w3) {
      System.out.println(e);
    }

  }

}
