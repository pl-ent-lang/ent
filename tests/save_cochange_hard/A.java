package save_cochange_hard;

modes {low <: mid; mid <: high; }

public class A@mode<? -> (low <= X <= mid)> {
  attributor {
    return @mode<low>;
  }

  public void m1() { 
    B@mode<X> b = new B@mode<X>();
    b.m1();
  }

  public static void main(String[] args) {
    A@mode<*> a = snapshot (new A@mode<?>()) ?mode[@mode<low>,@mode<high>];
    a.m1();
  }
}
