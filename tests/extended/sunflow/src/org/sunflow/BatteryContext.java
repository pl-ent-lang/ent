package org.sunflow;

import org.sunflow.core.Scene;
import org.sunflow.core.renderer.BucketRenderer;

public class BatteryContext {
  public static int batteryMode = 0;
}

/*
public class BatteryContext@mode<?->X> {

  public static int batteryMode = 0;

  attribute {
    // Just simulate for now
    int batteryMode = BatteryContext.batteryMode;

    if (batteryMode == 2) {
      return @mode<high>;
    } else if (batteryMode == 1) {
      return @mode<mid>;
    } else {
      return @mode<low>;
    }
  }

  public Scene@mode<?> scene;
  public BucketRenderer@mode<X> bucketRenderer;

  public BatteryContext() {
  }

  public void PandaInit() {
    this.scene = new Scene@mode<?>();
    this.bucketRenderer = new BucketRenderer@mode<X>();
  }

  public Scene@mode<*,X> resolvedScene() {
    return snapshot this.scene ?mode[@mode<low>,@mode<X>];
  }

}
*/
