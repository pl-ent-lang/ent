package waterfall_good4;

modes {low <: mid; mid <: high; };

public class A@mode<X <= high, Y <= high, Z <= Y, W <= Z> {
  public void m1(String@mode<low> s) {
    s.charAt(0);  // Good!
  }

  public void m2(String@mode<Y> s) {
    s.charAt(0);  // Good!
  }

  public void m3(String@mode<Z> s) {
    s.charAt(0);  // Good!
  }

  public void m4(String@mode<W> s) {
    s.charAt(0);  // Good!
  }
}
