// Test: FIX

package waterfall_good5;

modes {low <: mid; mid <: high; };

public class A {
  public void m1() {
    String@mode<high> s1 = new String@mode<high>();
    s1.charAt(0);
  }
}
