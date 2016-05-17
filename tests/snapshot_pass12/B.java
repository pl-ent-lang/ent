package snapshot_pass12;

public class B@mode<(low <= X <= mid)> {
  public void m1() {
    A@mode<?> ad = new A@mode<?>();
    A@mode<*> a1 = snapshot ad ?mode[@mode<X>,@mode<high>];
  }

}
