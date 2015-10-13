package simple_panda;

public class Modesafe@mode<?->X> {
  attribute {
    return @mode<high>;
  }
  modesafe public String s1;
  modesafe public void foo() {
  }
  public static void main(String[] args) {
    Modesafe@mode<?> m1 = new Modesafe@mode<?>();
    String s1 = m1.s1;
    m1.foo();
  }
}
