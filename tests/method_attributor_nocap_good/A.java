package method_attributor_nocap_good;

modes { low <: mid; mid <: high; }

public class A {
  public @mode<?->X> void m1()
    attributor {
      return @mode<high>;
    }
  {
  }
}
