package simple;

import java.util.concurrent.*;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class InfMap {

   public static <K, V> Map<K, V> map() {
     return new HashMap<K, V>();
   }

   public static <K, V> ConcurrentMap<K, V> concurrentMap() {
     return new ConcurrentHashMap<K, V>();
   }

   public static <E> Set<E> set() {
     return new HashSet<E>();
   }

   public static <E> Set<E> newSetFromMap(Map<E, Boolean> map) {
     return new HashSet<E>();
   }

  public static <E> Set<E> concurrentSet() {
    return newSetFromMap(InfMap.<E, Boolean>concurrentMap());
  }
}
