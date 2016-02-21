package snapshot_good1;

modes {low <: mid; mid <: high; }

public class A@mode<? -> X> {
  attributor {
    return @mode<high>;
  }

  public void m1() { }
}
