package generic_test;

import java.util.List;
import java.util.ArrayList;

modes {mid <: high; low <: mid;}

public class Socket extends ArrayList<String> @mode(high) {
  /*
  private String @mode(Y) ipAddr;
  private int port;
  
  public Socket(String @mode(Y) ipAddr, int port) {
    this.ipAddr = ipAddr;
    this.port = port;
  }
  */

  public static void main(String[] args) {
    List<String> @mode(high) l1;
  }

}
