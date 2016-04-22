// Test: Simple waterfall invariant check: Checks that we may not send messages to
// objects declared with class mode type variables that all have upper bounds
// above the classes mode type bounded by each other.
package waterfall_bad5;

modes {low <: mid; mid <: high; };

public class A@mode<(mid <= X <= high), Y <= high, Z <= Y, W <= Z> {
  public void m1(String@mode<low> s) {
    s.charAt(0);  // Good!
  }

  public void m2(String@mode<Y> s) {
    s.charAt(0);  // Error!
  }

  public void m3(String@mode<Z> s) {
    s.charAt(0);  // Error!
  }

  public void m4(String@mode<W> s) {
    s.charAt(0);  // Error!
  }
}
