package snapshot_pass7;

public class C@mode<(mid <= X <= mid)> {

  public void m1() {
    B@mode<mid> b = new B@mode<mid>();
    b.@mode<X>m3();
  }

  public static void main(String[] args) {
    C@mode<mid> c = new C@mode<mid>();
    c.m1();
  }

}
