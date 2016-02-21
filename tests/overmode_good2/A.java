package overmode_good2;

modes {low <: mid; mid <: high; }

public class A@mode<X <= high, Y> {
  public void m1() { }
  public @mode<Z> void m2() { }
}
