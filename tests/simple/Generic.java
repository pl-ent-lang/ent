package simple;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

modes {low <: mid; mid <: high; }

public class Generic<X,Y> {
  public static void main(String[] args) {
    List<String> l1 = new ArrayList<String>();
    List<String> l2 = new ArrayList<>();

    List<List<String>> l3 = new ArrayList<>();
    List<List<String>> l4 = new ArrayList<>();

    Map<String,String> m1 = new HashMap<String,String>();
    Map<String,String> m2 = new HashMap<>();

    Map<String,List<String>> m3 = new HashMap<String,List<String>>();
    Map<String,List<String>> m4 = new HashMap<>();

    Generic<String,String> g1 = new Generic<String,String>();
    Generic<String,String> g2 = new Generic<>();
  }
}
