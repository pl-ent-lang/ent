package snapshot_bad4;

public class B@mode<(low <= X <= mid), (mid <= Y <= high), Z <= Y, W <= Z> {
  public void m1(A@mode<?> ad) {
    A@mode<*> a1 = snapshot ad ?mode [@mode<low>,@mode<Y>];
    a1.m1();

    A@mode<*> a2 = snapshot ad ?mode [@mode<low>,@mode<Z>];
    a2.m1();

    A@mode<*> a3 = snapshot ad ?mode [@mode<low>,@mode<W>];
    a3.m1();
  }
}
