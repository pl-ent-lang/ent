package snapshot_fail1;

modes {low <: mid; mid <: high; }

public class A@mode<? -> X> {
  attributor {
    return @mode<mid>;
  }

  public void m1() { }
}
