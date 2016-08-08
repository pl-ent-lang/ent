package method_attributor_mcase_pass;

modes { low <: mid; mid <: high; }

public class A {
  public @mode<?->X> int m1(int x) 
    attributor {
      if (x <= 0) {
        return @mode<low>;
      } else if (x <= 1) {
        return @mode<mid>;
      } else {
        return @mode<high>;
      }
    }
  {
    C@mode<X> c = new C@mode<X>();
    return c.f;
  }
}
