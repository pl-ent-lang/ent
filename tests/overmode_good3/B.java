package overmode_good3;

public class B@mode<X <= mid, Y <= low> {
  public void m1() {
    A@mode<high> a1 = new A@mode<high>();
    a1.@mode<low>m2();  // Good! m2 is overmoded to low
    a1.@mode<Y>m2();    // Good! m2 is overmoded to Y
  }
}
