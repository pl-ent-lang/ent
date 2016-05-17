package snapshot_fail2;

public class B@mode<(mid <= X <= high)> {
  public @mode<Y <= mid> void m1(A@mode<?> ad) {
    A@mode<*> a1 = snapshot ad ?mode[@mode<Y>,@mode<mid>];
    a1.m1();
  }

  public static void main(String[] args) {
    A@mode<?> ad = new A@mode<?>();
    B@mode<mid> b1 = new B@mode<mid>();

    try {
      b1.@mode<mid>m1(ad);
    } catch (RuntimeException e) {
      System.exit(2);
    }
  }
}
