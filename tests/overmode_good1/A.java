package overmode_good1;

modes {low <: mid; mid <: high; }

public class A@mode<X <= mid> {
  public void m1() { }
  public @mode<low> void m2() { }
}
