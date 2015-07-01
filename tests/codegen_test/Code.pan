package codegen_test;

modes { low <: mid; mid <: high; high <: veryHigh; }

public class Code @mode<X, Y <= X> {
  attribute {
    if (true) {
      return @mode<high>;
    } else {
      return @mode<high>;
    }
  } 

  copy {
    return new Code@mode<X,Y>();
  }

  public void foo(Code@mode<X,Y> c1) {
  }

  public @mode<Z> Code@mode<*,Z> scopy(Code@mode<*,Z> c1) {
    Code@mode<?,Z> cd = new Code@mode<?,Z>();
    return snapshot cd ?mode[@mode<low>, @mode<Z>];
  }

  public Code@mode<X,Y> dcopy(Code@mode<X,Y> c1) {
    c1.scopy(c1);

    return new Code@mode<X,Y>();
  }

  public static void main(String[] args) {
    Code@mode<high,high> c1 = new Code@mode<high,high>();
    Code@mode<mid,mid> c2 = new Code@mode<mid,mid>();

    c1.scopy(c1);
    c1.foo(c1);

    c2.scopy(c2);
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
