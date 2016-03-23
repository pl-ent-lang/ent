package method_mcase_pass1;

public class B {
  public @mode<X> void m1() {
    A@mode<X> a = new A@mode<X>();
    System.out.println(a.msg);
  }
  public static void main(String[] args) {
    B b = new B();
    b.@mode<high>m1();
    b.@mode<mid>m1();
    b.@mode<low>m1();
  }
}
