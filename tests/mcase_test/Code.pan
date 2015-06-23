package mcase_test;

modes {l <: m; m <: h; h <: vh; }

public class Code @mode<X> {
  public mcase<int> vals = mcase<int> {
    l:  0;
    m:  1;
    h:  2;
    vh: 3;
  };

  /*
  public static void main(String[] args) {
    Code@mode<l> c1 = new Code@mode<l>();
  }
  */
}
