package method_attributor_pass1;

public class B@mode<X> {
  public void m1() {
    A a = new A();
    a.m1();
  }

  public static void main(String[] args) {
    B@mode<high> b1 = new B@mode<high>();
    b1.m1();

    B@mode<mid> b2 = new B@mode<mid>();
    b2.m1();

    B@mode<low> b3 = new B@mode<low>();
    boolean thrown = false;
    try {
      b3.m1();
    } catch (Exception e) {
      thrown = true;
    }

    if (!thrown) {
      System.out.format("b3 should fail\n");
      throw new Exception();
    }
  }
}
