package simple;

public class Exceptions {
  public void foo(Exception e) {
  }

  public static void main(String[] args) {
    Exceptions exp = new Exceptions();
    exp.foo(true ? new Exception() : new RuntimeException());
  }
}
