package null_compare;

modes { low <: mid; }

public class B@mode<?->X> implements A@mode<X> {
  attributor {
    return @mode<low>;
  }

  public B() { }

  public static void main(String[] args) {
    A@mode<?> a = new B@mode<?>();
    if (a == null) {
    }
  }
}
