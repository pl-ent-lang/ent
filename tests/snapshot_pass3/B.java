package snapshot_pass3;

public class B@mode<(mid <= X <= high)> {
  public @mode<(mid <= Y <= high)> void m1(A@mode<?> ad) {
    A@mode<*> a1 = snapshot ad ?mode[@mode<Y>,@mode<high>];
  }

  public void m2(A@mode<?> ad) {
    this.@mode<X>m1(ad);
  }

}
