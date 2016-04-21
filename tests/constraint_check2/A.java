// Test: Simple type substitution check on method mode type parameters.

package constraint_check2;

modes {low <: mid; mid <: high; };

public class A@mode<X <= high, Y <= mid> {
  public @mode<Z <= mid> void m1() { }

  public @mode<Z <= Y> void m2() { } 

  public static void main(String[] args) {
    A@mode<mid,mid> a1 = new A@mode<mid,mid>(); 

    a1.@mode<mid>m1();    //Good!
    a1.@mode<high>m1();   //Error!  

    a1.@mode<mid>m2();    //Good!
    a1.@mode<high>m2();   //Error!
  }
}

