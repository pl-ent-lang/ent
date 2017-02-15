package mcase_tuple2.p1;

import mcase_tuple2.p2.Tuple;

import java.util.List; 

modes { low <: mid; mid <: high; }

public class A@mode<X> {
  public mcase<Tuple> m1 = mcase<Tuple> {
    low: new Tuple(true,1);
    mid: new Tuple(true,2);
    high: new Tuple(true,3);
  };
}
