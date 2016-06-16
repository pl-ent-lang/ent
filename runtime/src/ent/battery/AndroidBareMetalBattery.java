package ent.runtime.battery;

import ent.runtime.ENT_Util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class AndroidBareMetalBattery implements BareMetalBattery {
  private Intent batteryStatus = null;

  private Intent batteryStatus() {
    if (this.batteryStatus == null) {
      IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED); 
      this.batteryStatus = ENT_Util.context.registerReceiver(null, intentFilter);
    }
    return this.batteryStatus;
  }

	public AndroidBareMetalBattery() {
	}
	
	public int getRemainingCapacity() {
    return this.batteryStatus().getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
	}

	public int getTotalCapacity() {
    return this.batteryStatus().getIntExtra(BatteryManager.EXTRA_SCALE, -1);
  }

}
