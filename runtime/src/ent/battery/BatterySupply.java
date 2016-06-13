package ent.runtime.battery;

import ent.runtime.util.OsUtil;

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
    return 0.40f;
    // return ((float) this.getRemainingCapacity()) / ((float) this.getTotalCapacity());
  }
}
