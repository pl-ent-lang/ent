package overmode_good1;

public class B@mode<X <= low> {
  public void m1() {
    A@mode<mid> a = new A@mode<mid>();
    a.m2(); // Good! m2 is overmoded to low
  }
}
