package codegen_test;

import java.util.ArrayList;
import java.util.List;

modes { low <: mid; mid <: high; high <: veryHigh; }

public class Code<T> {
  private List<T>@mode<*> l1;

  public List<T>@mode<*> iter() {
    return this.l1;
  }
}

