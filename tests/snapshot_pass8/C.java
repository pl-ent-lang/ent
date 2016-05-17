package snapshot_pass8;

public class C@mode<(mid <= X <= mid)> {

  public @mode<Z> void m1() {
    B@mode<mid> b = new B@mode<mid>();
    b.@mode<Z>m3();
  }

  public static void main(String[] args) {
    C@mode<mid> c = new C@mode<mid>();
    c.@mode<mid>m1();
  }

}
