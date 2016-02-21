package constraint_check4;

public class B@mode<(low <= X <= high), (high <= Y <= high)> {
  public void m1() {
    A@mode<Y> a1 = new A@mode<Y>(); // Good!
    A@mode<X> a2 = new A@mode<X>(); // Error, not always high
  }
}
