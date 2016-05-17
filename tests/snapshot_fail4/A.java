package snapshot_fail4;

modes {low <: mid; mid <: high; }

public class A@mode<? -> X> {
  attributor {
    return @mode<low>;
  }

  public void m1() { }
}
