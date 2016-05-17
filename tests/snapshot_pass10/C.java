package snapshot_pass10;

public class C@mode<? -> (mid <= X <= mid)> {

  attributor {
    return @mode<mid>;
  }

  public void m1() {
    B@mode<mid> b = new B@mode<mid>();
    b.@mode<X>m3();
  }

  public static void main(String[] args) {
    C@mode<?> c = new C@mode<?>();

    C@mode<*> c1 = snapshot c ?mode[@mode<mid>,@mode<mid>];
    c1.m1();
  }

}
