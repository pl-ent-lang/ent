package snapshot_fail5;

modes {low <: mid; mid <: high; }

public class A@mode<? -> X> {
  attributor {
    return @mode<low>;
  }

  public void m1() { }
}
