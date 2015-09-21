package simple_panda;

import java.util.*;

public class ClassGen {
  public <T> List<T> findChildrenOfType(Class<T> targetType) {
    /*
    List<T> list = new ArrayList<T>();
    findChildrenOfType(targetType, list);
    return list;
    */
    return null;
  }

  public <T> void findChildrenOfType(Class<T> targetType, List<T> results) {
    //findChildrenOfType(targetType, results, true);
  }

  public <T> void findChildrenOfType(Class<T> targetType, List<T> results, boolean descendIntoNestedClasses) {
  }

  public static void main(String[] args) {
    ClassGen g = new ClassGen();
    List<String> results = new ArrayList<String>();
    g.findChildrenOfType(String.class, results, true);
  }

}
