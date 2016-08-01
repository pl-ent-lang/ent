package j7_exceptions;

import java.io.IOException;
import java.lang.NullPointerException;

public class A {
  public static void main(String[] args) {
    try {
      throw new IOException();
    } catch (IOException | NullPointerException e) {
    }
  }
}
