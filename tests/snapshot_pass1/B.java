package snapshot_pass1;

public class B@mode<(mid <= X <= high)> {
  public void m1(A@mode<?> ad) {
    A@mode<*> a1 = snapshot ad ?mode[@mode<low>,@mode<mid>];
    a1.m1();
  }
}
