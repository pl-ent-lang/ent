package simple.link.impl1;

import simple.link.inter1.AInterface;
import simple.link.inter1.A;

public class B {
  public static void main(String[] args) {
    AInterface ai = new A();
    ai.foo(1, new String());
  }
}
