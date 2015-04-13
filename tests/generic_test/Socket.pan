package generic_test;

modes {mid <: high; low <: mid;}

public class Socket @mode<Y> {
  private String @mode(Y) ipAddr;
  private int port;
  
  public Socket(String @mode(Y) ipAddr, int port) {
    this.ipAddr = ipAddr;
    this.port = port;
  }

  public static void main(String[] args) {
    Socket @mode(high)<high> s1 = new Socket("127.0.0.1", 5150);
  }

}
