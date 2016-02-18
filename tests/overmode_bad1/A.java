package overmode_good1;

modes {low <: mid; mid <: high; }

public class A@mode<X <= mid> {
  public void m1() { }
  public void m2() overmode<high> { }
}
