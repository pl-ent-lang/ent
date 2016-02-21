package snapshot_bad3;

public class B@mode<(mid <= X <= high)> {
  public void m1(A@mode<?> ad) {
    B@mode<high> b1 = new B@mode<high>();
    A@mode<*> a1 = snapshot ad ?mode [@mode<low>,b1];
    a1.m1();

    B@mode<X> b2 = new B@mode<X>();
    A@mode<*> a2 = snapshot ad ?mode [@mode<low>,b2];
    a2.m1();
  }
}
