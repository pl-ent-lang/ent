package fsub_inference;

public class A {
  public static <T extends B> T find() {
    return (T) new C();
  }

  public static void main(String[] args) {
    A.find().call();
  }
}
