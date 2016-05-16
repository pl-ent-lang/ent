package ent.runtime.temperature;

import ent.runtime.temperature.BareMetalTemperature;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class OSXBareMetalTemperature implements BareMetalTemperature {

  public OSXBareMetalTemperature() {}

  public float getTempC() { return 0.0f; }
}
