package simple_panda;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

modes {low <: mid; mid <: high; };

public class Mapper<K,V> {
  private Map<K,V> wrap;

  public Mapper() {
    this.wrap = new HashMap<>();
  }

  public boolean containsKey(Object k) {
    return this.wrap.containsKey(k);
  }

  public boolean containsValue(Object v) {
    return this.wrap.containsValue(v);
  }

  Set<Map.Entry<K,V>> entrySet() {
    return this.wrap.entrySet();
  }

  public V get(Object k) {
    return this.wrap.get(k);
  }

  public V put(K k, V v) {
    return this.wrap.put(k,v);
  }

  public static void main(String[] args) {
    // 1. Simple, just String
    Mapper<String@mode<mid>,String@mode<high> > m1 = new Mapper<String@mode<mid>,String@mode<high> >();
    m1.put("a", "a");
    m1.put("b", "b");
    m1.put("c", "c");
    m1.put("d", "d");

    String@mode<mid> sm1 = "f";
    String@mode<high> sh1 = "f";

    m1.put(sm1, sh1);

    System.out.println(m1.containsKey("a"));
    System.out.println(m1.containsKey("f"));
    System.out.println(m1.containsValue("a"));
    System.out.println(m1.containsValue("f"));

    for (Map.Entry<String@mode<mid>,String@mode<high> > e : m1.entrySet()) {
      System.out.println(e.getKey() + " " + e.getValue());
    }


    // 1. Simple, just String with different mode types
    Mapper<String@mode<low>,String@mode<low> > m2 = new Mapper<String@mode<low>,String@mode<low> >();
    m2.put("a", "a");
    m2.put("b", "b");
    m2.put("c", "c");
    m2.put("d", "d");

    String@mode<low> sl1 = "f";
    String@mode<low> sl2 = "f";

    m2.put(sl1, sl2);

    System.out.println(m2.containsKey("a"));
    System.out.println(m2.containsKey("f"));
    System.out.println(m2.containsValue("a"));
    System.out.println(m2.containsValue("f"));

    for (Map.Entry<String@mode<low>,String@mode<low> > e : m2.entrySet()) {
      System.out.println(e.getKey() + " " + e.getValue());
    }

    // 2. Complex, map Strings to Lists
    List<String>@mode<high> l1 = Arrays.asList("a", "b", "c");
    List<String>@mode<high> l2 = Arrays.asList("b", "c", "d");
    List<String>@mode<high> l3 = Arrays.asList("e", "f", "g");

    Mapper<String, List<String>@mode<high> > m3 = new Mapper<String, List<String>@mode<high> >();
    m3.put("a", l1);
    m3.put("b", l2);
    m3.put("c", l3);

    List<String>@mode<high> l4 = new ArrayList<>();

    System.out.println(m3.containsKey("a"));
    System.out.println(m3.containsKey("f"));
    System.out.println(m3.containsValue(l1));
    System.out.println(m3.containsValue(l4));

    for (Map.Entry<String,List<String>@mode<high> > e : m3.entrySet()) {
      System.out.println(e.getKey() + " " + e.getValue());
    }
  }

}

