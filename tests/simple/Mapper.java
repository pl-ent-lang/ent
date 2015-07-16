package simple;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

modes {low <: mid; mid <: high; }

public class Mapper<K,V> {
  private Map<K,V> wrap;

  public Mapper() {
    // LAST: Working on diamond type
    this.wrap = new HashMap<>();
    this.wrap = new HashMap<K,V>();
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
    Mapper<String,String> m1 = new Mapper<String,String>();
    m1.put("a", "a");
    m1.put("b", "b");
    m1.put("c", "c");
    m1.put("d", "d");

    System.out.println(m1.containsKey("a"));
    System.out.println(m1.containsKey("f"));
    System.out.println(m1.containsValue("a"));
    System.out.println(m1.containsValue("f"));

    for (Map.Entry<String,String> e : m1.entrySet()) {
      System.out.println(e.getKey() + " " + e.getValue());
    }

    // 2. Complex, map Strings to Lists
    List<String> l1 = Arrays.asList("a", "b", "c");
    List<String> l2 = Arrays.asList("b", "c", "d");
    List<String> l3 = Arrays.asList("e", "f", "g");

    Mapper<String, List<String>> m2 = new Mapper<String, List<String>>();
    m2.put("a", l1);
    m2.put("b", l2);
    m2.put("c", l3);

    List<String> l4 = new ArrayList<String>();

    System.out.println(m2.containsKey("a"));
    System.out.println(m2.containsKey("f"));
    System.out.println(m2.containsValue(l1));
    System.out.println(m2.containsValue(l4));

    for (Map.Entry<String,List<String>> e : m2.entrySet()) {
      System.out.println(e.getKey() + " " + e.getValue());
    }
  }

}

