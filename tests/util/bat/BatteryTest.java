package util.bat;

public class BatteryTest {
  public static void main(String[] args) {
    System.out.format("Battery Level: %f\n", PANDA_Util.Battery.percentRemaining());
  }
}
