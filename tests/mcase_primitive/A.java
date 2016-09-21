package mcase_primitive;

modes { low <: mid; mid <: high; }

public class A@mode<X> {
  public mcase<int> m1 = mcase<int> {
    low: 0; 
    mid: 1; 
    high: 2; 
  };
}
