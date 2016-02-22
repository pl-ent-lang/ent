package snapshot_pass5;

public class B@mode<(mid <= X <= high)> {
  public @mode<(mid <= Y <= high), (high <= Z <= high)> void m1(A@mode<?> ad) {
    A@mode<*> a1 = snapshot ad ?mode[@mode<Y>,@mode<Z>];
  }

  public @mode<Z> void m2(A@mode<?> ad) {
    this.@mode<Z,high>m1(ad);
  }

}
