package overmode_bad2;

public class B@mode<X <= mid, Y <= high> {
  public void m1() {
    A@mode<mid,high> a1 = new A@mode<mid,high>();
    a1.m2(); // Error! m2 is overmoded to high

    A@mode<mid,high> a2 = new A@mode<mid,Y>();
    a2.m2(); // Error! m2 is overmoded to high
  }
}
