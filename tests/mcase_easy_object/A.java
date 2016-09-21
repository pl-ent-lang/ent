package mcase_easy_object;

modes { low <: mid; mid <: high; }

public class A@mode<X> {
  public mcase<String> m1 = mcase<String> {
    low: "first"; 
    mid: "second"; 
    high: "third"; 
  };
}
