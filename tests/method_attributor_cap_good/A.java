package method_attributor_cap_good;

modes { low <: mid; mid <: high; }

public class A {
  public @mode<?->X> void m1(int x, int y)
    attributor {
      if (x < y) { return @mode<high>; } 
      else { return @mode<low>; }
    }
  {
  }
}
