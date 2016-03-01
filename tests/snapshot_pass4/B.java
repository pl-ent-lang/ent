package snapshot_pass4;

public class B@mode<(mid <= X <= high)> {
  public @mode<(mid <= Y <= high), (high <= Z <= high)> void m1(A@mode<?> ad) {
    A@mode<*> a1 = snapshot ad ?mode[@mode<Y>,@mode<Z>];
  }

  public void m2(A@mode<?> ad) {
    this.@mode<X,high>m1(ad);
  }

}
