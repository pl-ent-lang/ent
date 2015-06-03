package generic_test;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

modes {mid <: high; low <: mid;}

public class Socket @mode<X extends high, Y extends X & mid> {

  public void foo() {
    String@mode<low> s1 = new String@mode<low>();
    String@mode<high> s2 = new String@mode<high>();

    // Valid, s1 <: this
    s1.charAt(0);

    // Invalid, this <: s2
    s2.charAt(0);
  }

  public static void main(String[] args) {
    Socket@mode<mid,low> s1 = new Socket@mode<mid,low>();
  }

}

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

/*
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

    // Some harder cases 

    // Let's test a subst over a subst
    Socket<String @mode<high> > @mode<low,low> sok1 =
      new Socket<String @mode<high> > @mode<low,low>();

    // Should compile
    sok1.setF1(new String @mode<high>());

    Socket<Socket<String @mode<high> > @mode<high,high> > @mode<high, high> sok2 =
      new Socket<Socket<String @mode<high> > @mode<high,high> > @mode<high,high>();

    // Should compile
    //sok2.setF1(sok1);

    String @mode<high> s = sok2.getF1().getF2();
    
  }

}
*/

/*
public class Socket {

  public @mode<W> Socket(String@mode<W> s1) {
  }

  public @mode<W,X> List<String@mode<W> >@mode<X> m1(List<String@mode<W> >@mode<X> p1) {
    return p1;
  }

  public @mode<W> void m2(Map<List<String@mode<W> >, String@mode<W> >@mode<W> p1) {
  }

  public void foo() {
    Socket@mode<high> s1 = new Socket@mode<high>(new String@mode<low>());
    List<String@mode<low> >@mode<high> v1 = s1.m1(new ArrayList<String@mode<low> >@mode<high>());
    /*
    Map<List<String@mode<high> >, String@mode<low> >@mode<low> map = 
      new HashMap<List<String@mode<high> >, String@mode<low> >@mode<low>();
    s1.m2(map);
  }
}
*/

