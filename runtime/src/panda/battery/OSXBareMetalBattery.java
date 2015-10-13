package panda.runtime.battery;

import panda.runtime.battery.BareMetalBattery;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class OSXBareMetalBattery implements BareMetalBattery {

  public int getRemainingCapacity() {
    return 20;
  }

  public int getTotalCapacity() {
    return 100;
  }

}

