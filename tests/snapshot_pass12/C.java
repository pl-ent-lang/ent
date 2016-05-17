package snapshot_pass12;

public class C@mode<? -> (mid <= X <= mid)> {

  int x = 0;

  attributor {
    return @mode<mid>;
  }

  public void m1() {
    B@mode<X> b = new B@mode<X>();
    b.m1();
  }

  public static void main(String[] args) {
    C@mode<?> c = new C@mode<?>();

    C@mode<*> c1 = snapshot c ?mode[@mode<mid>,@mode<mid>];
    c1.m1();

  }

}
