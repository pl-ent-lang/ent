package snapshot_good3;

public class B@mode<(mid <= X <= high)> {
  public void m1(A@mode<?> ad) {
    B@mode<mid> b1 = new B@mode<mid>();

    A@mode<*> a1 = snapshot ad ?mode [@mode<low>,b1];
    a1.m1();

    A@mode<*> a2 = snapshot ad ?mode [b1,@mode<mid>];
    a2.m1();

    B@mode<X> b2 = new B@mode<X>();

    A@mode<*> a3 = snapshot ad ?mode [@mode<low>,b2];
    a3.m1();

    String@mode<low> b3 = new String@mode<low>();

    A@mode<*> a4 = snapshot ad ?mode [b3,@mode<mid>];
    a4.m1();

  }
}
