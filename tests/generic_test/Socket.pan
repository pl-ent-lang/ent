package generic_test;

import java.util.ArrayList;
import java.util.List;

modes {mid <: high; low <: mid;}

/*
public class Socket @mode<MX,MY> {
  private String @mode<MY> f2;

  public String @mode<MY> getF2() {
    return this.f2;
  }

  public void setF2(String @mode<MY> f2) {
    this.f2 = f2;
  } 

  public void foo() {
    String @mode<high> s2 = "a";

    Socket@mode<low,low> socket = new Socket@mode<low,low>();

    Socket@mode<low,high> socket2 = new Socket@mode<low,high>();

    s2 = socket.getF2();
    //socket.setF2(s2);

    s2 = socket2.getF2();
    //socket2.setF2(s2);
  }
}
*/

public class Socket<X> @mode<MX,MY> {
  private X f1;
  private String @mode<MY> f2;

  public X getF1() {
    return this.f1;
  }

  public void setF1(X f1) {
    this.f1 = f1;
  }

  public String @mode<MY> getF2() {
    return this.f2;
  }

  public void setF2(String @mode<MY> f2) {
    this.f2 = f2;
  } 

  public void foo() {
    String @mode<high> s1 = "a";
    String @mode<high> s2 = "a";

    Socket<String @mode<high> > @mode<low,high> socket = 
      new Socket<String @mode<high> > @mode<low,high>();

    s1 = socket.getF1();
    socket.setF1(s1);

    s2 = socket.getF2();
    socket.setF2(s2);
  }

}
