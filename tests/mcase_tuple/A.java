package mcase_tuple;

import java.util.List; 

modes { low <: mid; mid <: high; }

public class A@mode<X> {
  public mcase<Tuple<String, String> > m1 = mcase<Tuple<String, String> > {
    low: null;
    mid: null;
    high: null;
  };
}
