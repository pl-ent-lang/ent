package codegen_test;

import java.util.ArrayList;
import java.util.List;

modes { low <: mid; mid <: high; high <: veryHigh; }

public class Code@mode<X,Y> {
  public Code() {
  }

  public @mode<T> Code(Code@mode<X,T> c1) {
  }

  public static void main(String[] args) {
    Code@mode<high,mid> c2 = new Code@mode<high,mid>();
    Code@mode<high,low> c1 = new Code@mode<high,low>(c2);
  }
}

