package util.temp;

public class TemperatureTest {
  public static void main(String[] args) {
    System.out.format("Temperature (C) : %f\n", PANDA_Util.Temperature.getTempC());
    System.out.format("Temperature (F) : %f\n", PANDA_Util.Temperature.getTempF());
  }
}
