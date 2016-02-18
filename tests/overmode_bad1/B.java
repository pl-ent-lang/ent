package overmode_good1;

public class B@mode<X <= mid> {
  public void m1() {
    A@mode<mid> a = new A@mode<mid>();
    a.m2(); // Error! m2 is overmoded to high
  }
}
