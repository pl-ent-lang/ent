package overmode_bad1;

modes {low <: mid; mid <: high; }

public class A@mode<X <= mid> {
  public void m1() { }
  public @mode<high> void m2() { }
}
