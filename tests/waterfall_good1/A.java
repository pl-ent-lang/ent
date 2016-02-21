package waterfall_good1;

modes {low <: mid; mid <: high; };

public class A@mode<(mid <= X <= high)> {
  public void m1(String@mode<low> s) {
    s.charAt(0);  // Good!
  }

  public void m2(String@mode<mid> s) {
    s.charAt(0);  // Good!
  }

  public void dummy() { }

  public void m4(A@mode<mid> a) {
    a.dummy();  // Good!
  }

}
