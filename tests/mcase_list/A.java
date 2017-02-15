package mcase_list;

import java.util.List; 
import java.util.ArrayList; 

modes { low <: mid; mid <: high; }

public class A@mode<X> {
  public mcase<List<String> > m1 = mcase<List<String> > {
    low: new ArrayList<String>();
    mid: new ArrayList<String>();
    high: new ArrayList<String>();
  };
}
