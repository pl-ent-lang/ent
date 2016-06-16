package ent.runtime.temperature;

import ent.runtime.temperature.BareMetalTemperature;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class AndroidBareMetalTemperature implements BareMetalTemperature {

  private static final String TEMPERATURE_ZONE0_PATH = "/sys/class/thermal/thermal_zone0/temp";

  private static final int TEMPERATURE_ZONE0_CONVERSION = 1000;

  public AndroidBareMetalTemperature() {}

  public float getTempC() {
    /*
    float currentTemperature = 0;
    try {
      currentTemperature =
          ((float)this.getBareMetalZoneTemperature()) / ((float)TEMPERATURE_ZONE0_CONVERSION);
    } catch (IOException e) {
      // TODO: How to handle?
    }

    return currentTemperature;
    */
    return 0;
  }

  private int getBareMetalZoneTemperature() throws IOException {
    /*
    FileReader fileReader = new FileReader(TEMPERATURE_ZONE0_PATH);
    BufferedReader bufferedReader = new BufferedReader(fileReader);

    int currentTemperature = Integer.parseInt(bufferedReader.readLine());

    return currentTemperature;
    */
    return 0;
  }
}
