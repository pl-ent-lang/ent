package ent.runtime.temperature;

import ent.runtime.util.OsUtil;

public class TemperatureSupply {
  private BareMetalTemperature bareMetalTemperature;

  public TemperatureSupply() {
    // Load specific OS version for BareMetalBattery
    switch (OsUtil.getOsType()) {
    case WINDOWS:
      System.err.println("Windows not supported. Exiting.");
      System.exit(1);
      break;
    case MACOS:
      bareMetalTemperature = new OSXBareMetalTemperature();
      break;
    case LINUX:
      bareMetalTemperature = new UnixBareMetalTemperature();
      break;
    case ANDROID:
      System.err.println("WARNING: Android not supported!");
      bareMetalTemperature = new AndroidBareMetalTemperature();
      break;
    case NONE:
      System.err.println("Encountered unsupported operating system. Exiting.");
      System.exit(1);
      break;
    }
  }

  public float getTempC() { return bareMetalTemperature.getTempC(); }

  public float getTempF() { return ((float)this.getTempC()) * 1.80f + 32.0f; }
}
