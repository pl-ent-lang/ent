package copy_pass1;

modes {low <: mid; mid <: high; }

public class A@mode<?->X> {
  attributor {
    return @mode<low>;
  }
  public static void main(String[] args) {
    A@mode<?> d1 = new A@mode<?>();
    A@mode<*> d2 = snapshot d1 ?mode[@mode<low>,@mode<high>];
    A@mode<*> d3 = snapshot d1 ?mode[@mode<low>,@mode<high>];

    A@mode<?> d4 = new A@mode<?>();
    A@mode<*> d5 = snapshot d4 ?mode[@mode<low>,@mode<high>];
  }
}
