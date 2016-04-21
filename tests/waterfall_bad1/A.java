// Test: Simple waterfall invariant check. 

package waterfall_bad1;

modes {low <: mid; mid <: high; };

public class A@mode<(low<= X <= mid)> {
  public void m1(String@mode<low> s) {
    s.charAt(0);  // Good!
  }

  public void m2(String@mode<high> s) {
    s.charAt(0);  // Error!
  }
}
