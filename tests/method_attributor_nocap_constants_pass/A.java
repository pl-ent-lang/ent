package method_attributor_nocap_constants_pass;

modes { low <: mid; mid <: high; }

public class A {
  public @mode<?->X> void m1() 
    attributor {
      return @mode<mid>;
    }
  {
    System.out.format("Reached\n");
  }
}
