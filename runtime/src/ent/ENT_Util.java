package ent.runtime;

import ent.runtime.battery.BatterySupply;
import ent.runtime.temperature.TemperatureSupply;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.BufferedReader;

import android.content.Context;
import android.os.Environment;

// import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;


public class ENT_Util {
  public static BatterySupply Battery = new BatterySupply();
  public static TemperatureSupply Temperature = new TemperatureSupply();
  public static Stopwatch stopwatch = new Stopwatch();
  public static Context context = null; 

  public static void registerAndroidContext(Context context) {
    ENT_Util.context = context;
  } 

  public static String dumpMode(Object o) {
    Integer[] m = ENT_Runtime.getObjAll(o);
    if (m == null) {
      return o.getClass() + ":-1";
    }
    return o.getClass() + ":" + Integer.toString(m[0]);
  }

  public static String dumpTable(Object o) {
    Integer[] m = ENT_Runtime.getObjAll(o);
    if (m == null) {
      return o.getClass() + ": not in mode table";
    } else {
      String s = o.getClass() + " - Table \n";
      for (int i = 0; i < m.length; ++i) {
        s += Integer.toString(i) + " : " + Integer.toString(m[i]) + "\n";
      }
      return s;
    }
  }

  private static BufferedWriter modeOut = null;

  public static void initModeFile() {
    try {
      modeOut = new BufferedWriter(new FileWriter("mode.txt"));
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  public static void closeModeFile() {
    try {
      modeOut.close();
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  public static void writeModeFile(String msg) {
    try {
      modeOut.write(msg);
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  public static void startStopwatch() { stopwatch.start(); }

  public static void stopStopwatch() { stopwatch.stop(); }

  public static void resetStopwatch() { stopwatch.reset(); }

  public static double elapsedTime() { return stopwatch.read(); }
}
