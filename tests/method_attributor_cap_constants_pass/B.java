package method_attributor_cap_constants_pass;

public class B@mode<X> {
  public void m1(int x, int y) {
    A a = new A();
    a.@mode<?>m1(x,y);
  }

  public static void main(String[] args) {
    B@mode<high> b1 = new B@mode<high>();

    b1.m1(1,2);
    b1.m1(2,1);

    B@mode<low> b3 = new B@mode<low>();
    boolean thrown = false;

    b3.m1(2,1);

    try {
      b3.m1(1,2);
    } catch (Exception e) {
      thrown = true;
    }

    if (!thrown) {
      System.out.format("b3 should fail\n");
      throw new RuntimeException();
    }
  }
}
