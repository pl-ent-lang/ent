package simple;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

public class ConMap {
  private final ConcurrentMap<Object, Object> internalMap;
  public @mode<T> ConcurrentMap<Object, Object> getMap() {
    return this.internalMap;
  }

  public ConMap() {
    this.internalMap = new ConcurrentHashMap<>();
  }

  public static void main(String[] args) {
    ConMap cmap = new ConMap();
    Map<Object, Object> mmap = cmap.getMap();
  }
}
