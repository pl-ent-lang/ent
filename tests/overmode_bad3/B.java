package overmode_bad3;

public class B@mode<(low <= X <= mid), Y <= high> {
  public void m1() {
    A@mode<mid> a1 = new A@mode<mid>();
    a1.@mode<high>m2(); // Bad! m2 is overmoded to high
    a1.@mode<Y>m2(); // Bad! m2 is overmoded to high
  }
}
