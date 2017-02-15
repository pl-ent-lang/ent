package mcase_easy_object;

modes { low <: mid; mid <: high; }

public class A@mode<X> {
  public mcase<String> m1 = mcase<String> {
    low: new String("abc");
    mid: new String("def");
    high: new String("abc");
  };
}
