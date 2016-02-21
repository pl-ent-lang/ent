package overmode_good2;

public class B@mode<(low <= X <= mid), Y <= low> {
  public void m1() {
    A@mode<mid,low> a1 = new A@mode<mid,low>();
    a1.@mode<low>m2(); // Good! m2 is overmoded to low

    A@mode<mid,Y> a2 = new A@mode<mid,Y>();
    a2.@mode<Y>m2(); // Good! m2 is overmoded to mid
  }
}
