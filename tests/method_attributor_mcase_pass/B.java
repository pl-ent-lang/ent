package method_attributor_mcase_pass;

public class B@mode<X> {
  public void m1() {
    A a = new A();
    
    if (a.@mode<?>m1(0) != 0) {
      throw new RuntimeException("mcase projcted return should be 0");
    }

    if (a.@mode<?>m1(1) != 1) {
      throw new RuntimeException("mcase projcted return should be 1");
    }

    if (a.@mode<?>m1(2) != 2) {
      throw new RuntimeException("mcase projcted return should be 2");
    }

  }

  public static void main(String[] args) {
    B@mode<high> b1 = new B@mode<high>();
    b1.m1();
  }
}
