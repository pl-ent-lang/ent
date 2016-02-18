package waterfall_bad2;

modes {low <: mid; mid <: high; };

public class A@mode<? -> X <= mid> {
  attributor {
    return @mode<high>;
  }

  public void m1(String@mode<low> s) {
    s.charAt(0);  // Good!
  }

  public void m2(String@mode<high> s) {
    s.charAt(0);  // Error!
  }
}
