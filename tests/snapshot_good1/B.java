package snapshot_good1;

public class B@mode<(mid <= X <= high)> {
  public void m1(A@mode<?> ad) {
    A@mode<*> a1 = snapshot ad ?mode [@mode<low>,@mode<mid>];
    a1.m1();

    A@mode<*> a2 = snapshot ad ?mode [@mode<low>,@mode<low>];
    a2.m1();

    A@mode<low<=*<=mid> a3 = snapshot ad ?mode [@mode<low>,@mode<mid>];
    a3.m1();
  }
}
