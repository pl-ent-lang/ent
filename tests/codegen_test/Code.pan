package codegen_test;

import java.util.ArrayList;
import java.util.List;

modes { low <: mid; mid <: high; high <: veryHigh; }

public class Code<T> @mode<X, Y <= X> {
  private int i = 0;
  private String s = "abc";

  attribute {
    if (this.s == "abc") {
      return @mode<high>;
    } else {
      return @mode<high>;
    }
  } 

  public static void main(String[] args) {
    // NOTE 
    //int x = 1 + 2;
    //List<String@mode<high> > l1 = new ArrayList<String@mode<high> >();

    //List<Code@mode<high,mid> > l2 = new ArrayList<Code@mode<high,mid> >();
    Code<String>@mode<*,*> c1 = new Code<String>@mode<*,*>();
  }
}

