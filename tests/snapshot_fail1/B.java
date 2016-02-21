package snapshot_fail1;

public class B@mode<(mid <= X <= high)> {
  public void m1(A@mode<?> ad) {
    A@mode<*> a1 = snapshot ad ?mode[@mode<low>,@mode<low>];
    a1.m1();
  }
}
