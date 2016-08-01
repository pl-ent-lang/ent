package switch_enum;

public class A {
  public static void main(String[] args) {
    Color color = Color.RED;
    switch (color) {
      case RED:
        System.out.println("RED");
        break;
      case BLUE:
        System.out.println("BLUE");
        break;
      case YELLOW:
        System.out.println("YELLOW");
        break;
      default:
        System.out.println("None");
        break;
    }
  }
}
