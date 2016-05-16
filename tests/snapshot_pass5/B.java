package snapshot_pass5;

public class B@mode<(mid <= X <= mid)> {
  public @mode<(mid <= Y <= mid), (high <= Z <= high)> void m1() {
    A@mode<?> ad = new A@mode<?>();
    A@mode<*> a1 = snapshot ad ?mode[@mode<Y>,@mode<Z>];
  }

  public @mode<Z> void m2() {
    this.@mode<Z,high>m1();
  }

  public static void main(String[] args) {
    B@mode<mid> b = new B@mode<mid>();
    b.@mode<mid>m2();
  }

}
