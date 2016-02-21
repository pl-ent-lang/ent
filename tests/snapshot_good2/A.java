package snapshot_good2;

modes {low <: mid; mid <: high; }

public class A@mode<? -> X> {
  attributor {
    return @mode<high>;
  }

  public void m1() { }
}
