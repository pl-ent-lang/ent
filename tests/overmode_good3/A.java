package overmode_good3;

modes {low <: mid; mid <: high; }

public class A@mode<X <= high> {
  public void m1() { }
  public @mode<Z> void m2()  { }
}
