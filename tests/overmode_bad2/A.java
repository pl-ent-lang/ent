package overmode_bad2;

modes {low <: mid; mid <: high; }

public class A@mode<X <= mid, Y> {
  public void m1() { }
  public void m2() overmode<Y> { }
}
