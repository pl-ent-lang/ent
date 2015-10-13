package simple_panda;

public class Mcase@mode<X> {

  public mcase<int> f1 = mcase<int> {
    low:  0;
    mid:  1;
    high: 2;
  };

  public mcase<String> f2 = mcase<String> {
    low:  "low";
    mid:  "mid";
    high: "high";
  };

  public int getF1() {
    return this.f1;
  }

  attribute {
    return @mode<high>;
  }

  // Issue with Mcase
  // 1. When does
  //    this.mcase become this.mcase[@mode<X>] remain
  //    an mcase (non projected) for things like initialization
  //
  // 2. Right now mcases are completely tied to fields, is this
  //    a requirement.
  //
  //    Loosen to method call returns, 

  /*
  public Mcase() {
    this.f1 = mcase<int> {
      low: 1;
      mid: 2;
      high: 3;
    };
    int x = this.f1;
  }


  public void internal() {
    int x = this.f1;
    int y = f1;

    String sx = this.f2;
    String sy = f2;

  }
  */

  public static void main(String[] args) {
    Mcase@mode<?> m1 = new Mcase@mode<?>();
    Mcase@mode<*> m2 = snapshot m1 ?mode[@mode<low>, @mode<high>];

    System.out.println("Getting: " +  m2.getF1());
  }
}
