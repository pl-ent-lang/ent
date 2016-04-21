package method_mcase_pass1;

public class B {
  public @mode<X> String m1() {
    A@mode<X> a = new A@mode<X>();
    return a.msg;
  }
  public static void main(String[] args) {
    B b = new B();
    if (!((b.@mode<high>m1()).equals("high"))) {
      throw new RuntimeException();
    }
    if (!((b.@mode<mid>m1()).equals("mid"))) {
      throw new RuntimeException();
    }
    if (!((b.@mode<low>m1()).equals("low"))) {
      throw new RuntimeException();
    }
  }
}
