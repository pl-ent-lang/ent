package snapshot_pass2;

public class B@mode<(mid <= X <= high)> {
  public @mode<Y <= mid> void m1(A@mode<?> ad) {
    A@mode<*> a1 = snapshot ad ?mode[@mode<Y>,@mode<mid>];
    a1.m1();
  }

  public static void main(String[] args) {
    A@mode<?> ad = new A@mode<?>();
    B@mode<mid> b1 = new B@mode<mid>();

    b1.@mode<low>m1(ad);
  }
}
