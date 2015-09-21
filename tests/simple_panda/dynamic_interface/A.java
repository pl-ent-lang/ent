package simple_panda.dynamic_interface;

public class A@mode<? -> X> implements General@mode<X> {

  attribute {
    return @mode<high>;
  }

  public void foo() {
  }

  public static void main(String[] args) {
    General@mode<?> g = new A@mode<?>();
  }
}
