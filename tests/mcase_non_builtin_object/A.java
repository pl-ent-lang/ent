package mcase_non_builtin_object;

import java.util.Date;

modes { low <: mid; mid <: high; }

public class A@mode<X> {
  public mcase<Date> m1 = mcase<Date> {
    low: new Date(0);
    mid: new Date(0);
    high: new Date(0);
  };
}
