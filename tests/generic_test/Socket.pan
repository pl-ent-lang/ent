package generic_test;

import java.util.ArrayList;
import java.util.List;

modes {mid <: high; low <: mid;}

public class Socket<X> @mode<MX,MY> {
  private X f1;
  private int @mode<MY> f2;

  public Socket(X f1, int @mode<MY> f2) {
    this.f1 = f1;
    this.f2 = f2;
  }

  public X getF1() {
    return this.f1;
  }

  public void setF1(X f1) {
    this.f1 = f1;
  }

  public int @mode<MY> getF2() {
    return this.f2;
  }

  public void setF2(int @mode<MY> f2) {
    this.f2 = f2;
  }

  public void foo() {
    String @mode<high> s1 = "a";
    int @mode<high> i1 = 1;

    Socket<String @mode<high> > @mode<low,high> socket = 
      new Socket<String @mode<high> > @mode<low, high>(s1, i1);
  }

}
