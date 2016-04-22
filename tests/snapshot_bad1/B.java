package snapshot_bad1;

public class B@mode<(mid <= X <= high)> {
  public void m1(A@mode<?> ad) {
    A@mode<*> a1 = snapshot ad ?mode[@mode<low>,@mode<high>]; 
    a1.m1();

    A@mode<*> a2 = snapshot ad ?mode[@mode<mid>,@mode<high>];
    a2.m1();
  }
}
