package overmode_bad3;

modes {low <: mid; mid <: high; }

public class A@mode<X <= mid> {
  public void m1() { }
  public @mode<Y> void m2() { }
}
