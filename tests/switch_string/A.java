package switch_string;

public class A {
  public static void main(String[] args) {
    String s = "";
    switch (s) {
      case "An":
        System.out.format("A!\n");
        break;
      case "Bc":
        System.out.format("B!\n");
        break;
      default:
        System.out.format("Default!\n");
        break;
    }
  }
}
