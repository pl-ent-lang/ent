package snapshot_good2;

public class B@mode<(mid <= X <= high), Y <= mid> {
  public void m1(A@mode<?> ad) {
    A@mode<*> a1 = snapshot ad ?mode [@mode<low>,@mode<Y>];
    a1.m1();
  }
}
