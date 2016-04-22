package snapshot_bad3;

public class B@mode<(mid <= X <= high, (mid <= Y <= high)> {
  public void m1(A@mode<?> ad) {
    B@mode<high,mid> b1 = new B@mode<high,mid>();
    A@mode<*> a1 = snapshot ad ?mode [@mode<low>,b1];
    a1.m1();

    B@mode<Y,mid> b2 = new B@mode<Y,mid>();
    A@mode<*> a2 = snapshot ad ?mode [@mode<low>,b2];
    a2.m1();
  }
}
