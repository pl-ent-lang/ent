// Test: Simple waterfall invariant check: Checks that we may send messages to
// objects declared with class mode type variables that all have upper bounds
// below the classes mode type bounded by each other.
package waterfall_good5;

modes {low <: mid; mid <: high; };

public class A@mode<(mid <= X <= high), Y <= X, Z <= Y, W <= Z> {
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
