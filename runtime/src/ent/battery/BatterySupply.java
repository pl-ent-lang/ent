package ent.runtime.battery;

import ent.runtime.util.OsUtil;
import ent.runtime.util.OsType;
import ent.runtime.ENT_Properties;

public class BatterySupply {
  private BareMetalBattery bareMetalBattery;

  public BatterySupply() {
    // Load specific OS version for BareMetalBattery
    switch (OsUtil.getOsType()) {
    case WINDOWS:
      System.err.println("Windows not supported. Exiting.");
      System.exit(1);
      break;
    case MACOS:
      bareMetalBattery = new OSXBareMetalBattery();
      break;
    case LINUX:
      bareMetalBattery = new UnixBareMetalBattery();
      break;
    case ANDROID:
      bareMetalBattery = new AndroidBareMetalBattery();
      break;
    case NONE:
      System.err.println("Encountered unsupported operating system. Exiting.");
      System.exit(1);
      break;
    }
  }

  public int getRemainingCapacity() { return bareMetalBattery.getRemainingCapacity(); }

  public int getTotalCapacity() { return bareMetalBattery.getTotalCapacity(); }

  public float percentRemaining() {
    if (OsUtil.getOsType() == OsType.ANDROID) {
      Float level = (Float) ENT_Properties.getProperty("ENT_BATTERY_LEVEL");
      return level;
    }

    String simBattery = System.getenv("ENT_BATTERY_LEVEL");
    if (simBattery == null) {
      return ((float) this.getRemainingCapacity()) / ((float) this.getTotalCapacity());
    } else {
      Float batteryLevel = Float.parseFloat(simBattery);
      return batteryLevel;
    }

  }
}
