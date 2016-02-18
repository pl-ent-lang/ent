package overmode_good2;

public class B@mode<X <= mid, Y <= mid> {
  public void m1() {
    A@mode<mid,mid> a1 = new A@mode<mid,mid>();
    a1.m2(); // Good! m2 is overmoded to mid

    A@mode<mid,low> a2 = new A@mode<mid,low>();
    a2.m2(); // Good! m2 is overmoded to mid

    A@mode<mid,Y> a3 = new A@mode<mid,Y>();
    a3.m2(); // Good! m2 is overmoded to mid
  }
}
