package codegen_test;

modes { low <: mid; mid <: high; high <: veryHigh; }

public class Code @mode<X, Y <= X> {
  private String@mode<Y> f1;

  attribute {
    if (true) {
      return @mode<mid>;
    } else {
      return @mode<high>;
    }
  } 

  copy {
    return new Code@mode<X,Y>(this.f1);
  }

  public Code(String@mode<*> f1) {
    this.f1 = f1;
  }

  public int count() {
    return this.f1.length();
  }

  public static void main(String[] args) {
    Code@mode<high,mid> c1 = new Code@mode<high,mid>("just a test");

    Code@mode<?,high> cd = new Code@mode<?,high>("just a test");

    Code@mode<*,high> c2 = 
      snapshot cd ?mode[@mode<low>,
                        snapshot cd ?mode[@mode<low>,@mode<high>]];

    System.out.println("Count: " + c2.count());
  }
}

/*
public class Code @mode<X,Y> {
  private int f1;
  public String f2;

  attribute {
    if (true) {
      return @mode<low>;
    } else {
      return @mode<high>;
    }
  }

  public static void main(String[] args) {
    Code@mode<?,*> c1 = new Code@mode<?,*>();
    Code@mode<*,*> c2 = snapshot c1 ?mode[@mode<mid>,@mode<high>];

    Code@mode<?,low> c3 = new Code@mode<?,low>();
    Code@mode<*,low> c4 = snapshot c3 ?mode[@mode<mid>,@mode<high>];

    // BUG : the equal of c6 should cause some kind of error here,
    // only the * should be inferred
    Code@mode<?,high> c5 = new Code@mode<?,high>();
    Code@mode<*,*> c6 = snapshot c5 ?mode[@mode<mid>,@mode<high>];


    Code@mode<?,high> c7 = new Code@mode<?,high>();

    /*
    Code@mode<*> c1         = new Code@mode<*>();
    Code@mode<low> c2       = new Code@mode<low>();
    Code@mode<mid> c3       = new Code@mode<mid>();
    Code@mode<high> c4      = new Code@mode<high>();
    Code@mode<veryHigh> c5  = new Code@mode<veryHigh>();
    Code@mode<?> c6         = new Code@mode<?>();

    Code@mode<*> c7 = snapshot c6 ?mode[@mode<mid>,@mode<high>];
    Code@mode<*> c8 = snapshot c6 ?mode[@mode<*>,@mode<*>];

    System.out.println("All good!");
  }


}
*/
