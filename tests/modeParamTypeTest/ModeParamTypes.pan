package modeParamTypeTest;

modes { low <: mid; mid <: high; high <: veryHigh; }

public class Socket @mode<X,Y> {

  private String[] @mode(Y) buffer;

  public Socket() {
  }
  
  public void send(String[] @mode(Y) string) {
  }

  public void recv(Socket @mode(X) socket, String[] @mode(Y) string) {
  }

  public static void main(String[] args) {
    System.out.println("Hello world!");
  }
}
