package overmode_good2;

modes {low <: mid; mid <: high; }

public class A@mode<X <= high, Y> {
  public void m1() { }
  public void m2() overmode<Y> { }
}
