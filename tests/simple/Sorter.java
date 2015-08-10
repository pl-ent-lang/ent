package simple;

import java.lang.Comparable;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Sorter {

  private static <T extends Comparable<T>> void swap(List<T> l, int x, int y) {
    T t = l.get(x);
    l.set(x, l.get(y));
    l.set(y, t);
  }

  private static <T extends Comparable<T>> int qsortPiv(List<T> l, int s, int e) {
    int i = s-1;
    int j = e+1;
    T pv = l.get(s);
    while (true) {
      while (++i <= e && l.get(i).compareTo(pv) < 0);
      while (--j >= 0 && l.get(j).compareTo(pv) > 0);
      if (i < j) {
        swap(l, i, j);
      } else {
        break;
      }
    }
    return j;
  }

  private static <T extends Comparable<T>> void qsort(List<T> l, int s, int e) {
    if (e - s <= 0) return;
    int p = qsortPiv(l, s, e);
    qsort(l, s, p);
    qsort(l, p+1, e);
  }

  public static <T extends Comparable<T>> List<T> qsort(List<T> l) {
    //List<T> copy1 = new ArrayList<>(l);
    List<T> copy1 = new ArrayList<T>(l);
    Sorter.qsort(copy1, 0, copy1.size()-1); 
    return copy1;
  }

  private static void swapInt(List<Integer> l, int x, int y) {
    int t = l.get(x);
    l.set(x, l.get(y));
    l.set(y, t);
  }

  private static int qsortIntPiv(List<Integer> l, int s, int e) {
    int i = s-1;
    int j = e+1;
    int pv = l.get(s);
    while (true) {
      while (++i <= e && l.get(i) < pv);
      while (--j >= 0 && l.get(j) > pv);
      if (i < j) {
        swapInt(l, i, j);
      } else {
        break;
      }
    }
    return j;
  }

  private static void qsortInt(List<Integer> l, int s, int e) {
    if (e - s <= 0) return;
    int p = qsortIntPiv(l, s, e);
    qsortInt(l, s, p);
    qsortInt(l, p+1, e);
  }

  public static List<Integer> qsortInt(List<Integer> l) {
    //List<Integer> copy1 = new ArrayList<>(l);
    List<Integer> copy1 = new ArrayList<Integer>(l);
    Sorter.qsortInt(copy1, 0, copy1.size()-1); 
    return copy1;
  }

  public static void main(String[] args) {
    Random rand = new Random();

    // Just test for lists up to size 100
    for (int i = 0; i < 100; ++i) {
      //List<Integer> l = new ArrayList<>();
      List<Integer> l = new ArrayList<Integer>();
      for (int j = 0; j < i; ++j) {
        l.add(rand.nextInt(1000));
      }
      l = Sorter.qsortInt(l);
      for (int j = 0; j < i-1; j++) {
        if(l.get(j) > l.get(j+1)) {
          System.out.println("Sort failed!");
          System.exit(1);
        }
      }
    }

    // Just test for Strings up to size 100
    for (int i = 0; i < 100; ++i) {
      //List<String> l = new ArrayList<>();
      List<String> l = new ArrayList<String>();
      for (int j = 0; j < i; ++j) {
        l.add(UUID.randomUUID().toString());
      }
      l = Sorter.qsort(l);
      for (int j = 0; j < i-1; j++) {
        if(l.get(j).compareTo(l.get(j+1)) > 0) {
          System.out.println("Sort failed!");
          System.exit(1);
        }
      }
    }

    System.out.println("All sorted!");
  }
}
