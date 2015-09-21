package simple_panda.dynamic_interface;

modes {low <: mid; mid <: high; }

public interface General@mode<? -> X> {
  public void foo();
}
