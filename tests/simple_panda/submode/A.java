package simple_panda.submode;

modes { low <: mid; mid <: high; }

public class A {
  public void m1(String@mode<high> s) {
  }

  public void m2(String@mode<low> s) {
  }

  public static void main(String[] args) {
    String@mode<high> highStr = "abc";
    String@mode<low>  lowStr  = "abc";

    A a = new A();

    a.m1(lowStr);   // Valid <:
    a.m2(highStr);  // Invalid <:

  }
}
