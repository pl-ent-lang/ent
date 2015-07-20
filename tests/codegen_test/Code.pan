package codegen_test;

import java.util.ArrayList;
import java.util.List;

modes { low <: mid; mid <: high; high <: veryHigh; }

public class Code@mode<X,Y> {

  attribute {
    return @mode<high>;
  }

  public Code() {
  }

  public @mode<T> Code(Code@mode<X,T> c1) {
    Code@mode<?,T> dyn = new Code@mode<?,T>();

    Code@mode<*,T> val = snapshot dyn ?mode[@mode<low>,@mode<T>];
    
    // The followed should be forbidden (mode value used from class in cons)
    Code@mode<*,T> inval = snapshot dyn ?mode[@mode<low>,@mode<high>];
  }

  public static void main(String[] args) {
    Code@mode<high,mid> c2 = new Code@mode<high,mid>();
    Code@mode<high,low> c1 = new Code@mode<high,low>(c2);
  }
}

