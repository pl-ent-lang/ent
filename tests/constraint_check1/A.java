// Test: Simple type substitution check on class mode type parameters.

package constraint_check1;

modes {low <: mid; mid <: high; };

public class A@mode<X <= mid, Y <= X> {
  public static void main(String[] args) {
    A@mode<mid,mid> a1 = new A@mode<mid,mid>();     // Good!
    A@mode<mid,low> a2 = new A@mode<mid,low>();     // Good!

    A@mode<mid,high> a3 = new A@mode<mid,high>();   // Error, Y !<= X
    A@mode<high,high> a4 = new A@mode<high,high>(); // Error, high !<= mid 
  }
}

