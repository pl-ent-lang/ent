package generic_test;

import java.util.ArrayList;
import java.util.List;

modes {mid <: high; low <: mid;}

public class Socket @mode<Y,Z> {
  public static void main(String[] args) {
    Socket @mode<low,high,high> s1 = new Socket @mode<low,high,high>();
  }
}
