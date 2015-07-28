package perf;

import panda.runtime.*;

import java.util.Random;
import java.util.UUID; 

modes { low <: mid; mid <: high; }

public class SimplePerf {
  public static void main(String[] args) {
    for (int i = 0; i < 1000000; i++) {
      String s = new String(UUID.randomUUID().toString());
    }

    PANDA_Runtime.report();
  }
}
