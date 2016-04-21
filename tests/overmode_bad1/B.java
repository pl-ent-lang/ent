package overmode_bad1;

public class B@mode<X <= mid> {
  public void m1() {
    A@mode<mid> a = new A@mode<mid>();
    a.@mode<high>m2(); // Error! m2 is overmoded to high
  }
}
