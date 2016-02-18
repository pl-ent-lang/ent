package waterfall_good1;

modes {low <: mid; mid <: high; };

public class A@mode<X <= high> {
  public void m1(String@mode<low> s) {
    s.charAt(0);  // Good!
  }

  public void m2(String@mode<mid> s) {
    s.charAt(0);  // Good!
  }

  public void m3(String@mode<high> s) {
    s.charAt(0);  // Good!
  }

  public void dummy() { }

  public void m3(A@mode<low> a) {
    a.dummy();  // Good!
  }

  public void m4(A@mode<mid> a) {
    a.dummy();  // Good!
  }

  public void m5(A@mode<high> a) {
    a.dummy();  // Good!
  }

}
