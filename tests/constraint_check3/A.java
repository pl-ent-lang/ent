// Test: Checks that type substitution on a class that operates under a single mode (a mode value 
// type parameter) is enforced on said class's object creation when using (concrete) mode types.

package constraint_check3;

modes {low <: mid; mid <: high; };

public class A@mode<high> {
  public static void main(String[] args) {
    A@mode<high> a1 = new A@mode<high>();  // Good!
    A@mode<mid> a2 = new A@mode<mid>();    // Error, not high!
  }
}

