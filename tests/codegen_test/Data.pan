package codegen_test;

import java.util.List;

public class Data<T> extends Code<T> {
  private List<T>@mode<*> l1;

  public List<T>@mode<*> iter() {
    return this.l1;
  }

}
