package waterfall_bad3;

modes {low <: mid; mid <: high; };

public class A@mode<X <= mid, Y <= high, Z <= Y> {
  public void m1(String@mode<low> s) {
    s.charAt(0);  // Good!
  }

  public void m2(String@mode<Y> s) {
    s.charAt(0);  // Error!
  }

  public void m3(String@mode<Z> s) {
    s.charAt(0);  // Error!
  }
}
