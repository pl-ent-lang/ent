package snapshot_fail1;

public class B@mode<(mid <= X <= high)> {
  public void m1() {
    A@mode<?> ad = new A@mode<?>();
    A@mode<*> a1 = snapshot ad ?mode[@mode<low>,@mode<low>];  // Error!
    a1.m1();
  }
  public static void main(String[] args) {
    B@mode<high> b = new B@mode<high>();
    b.m1();
  }
}
