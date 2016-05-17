package snapshot_pass11;

modes {low <: mid; mid <: high; }

public class A@mode<? -> X> {
  attributor {
    return @mode<mid>;
  }

  public void m1() { }
}
