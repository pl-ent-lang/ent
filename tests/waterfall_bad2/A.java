// Test: Simple waterfall invariant check; A potentially having a dynamic mode type should
// not affect the static type checking.

package waterfall_bad2;

modes {low <: mid; mid <: high; };

public class A@mode<?->(low<=X<= mid)> {
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
