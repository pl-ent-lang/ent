package snapshot_pass6;

public class B@mode<(mid <= X <= mid)> {
  public @mode<(mid <= Y <= mid), (high <= Z <= high)> void m1() {
    A@mode<?> ad = new A@mode<?>();
    A@mode<*> a1 = snapshot ad ?mode[@mode<Y>,@mode<Z>];
  }

  public @mode<Z> void m2() {
    this.@mode<Z,high>m1();
  }

  public @mode<W> void m3() {
    this.@mode<W,high>m2();
  } 

  public @mode<Z1,Z2> void m4() {
    this.@mode<Z2,Z1>m3();
  }

  public static void main(String[] args) {
    B@mode<mid> b = new B@mode<mid>();
    b.@mode<high,mid>m4();
  }

}
