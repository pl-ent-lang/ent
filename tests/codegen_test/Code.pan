package codegen_test;

modes {low <: mid; mid <: high; high <: veryHigh; }

public class Code {
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
  

  /*
  public void foo(Code c) {
    c.f1 = this.f1;
  }

  public static void main(String[] args) {
    int i1                  = 0;
    int@mode<low> i2        = 0;
    int@mode<mid> i3        = 0;
    int@mode<high> i4       = 0;
    int@mode<veryHigh> i5   = 0;

    String s1                 = "abc";
    String@mode<low> s2       = "abc";
    String@mode<mid> s3       = "abc";
    String@mode<high> s4      = "abc";
    String@mode<veryHigh> s5  = "abc";

    Code@mode<*> c1         = new Code@mode<*>();
    Code@mode<low> c2       = new Code@mode<low>();
    Code@mode<mid> c3       = new Code@mode<mid>();
    Code@mode<high> c4      = new Code@mode<high>();
    Code@mode<veryHigh> c5  = new Code@mode<veryHigh>();
    Code@mode<?> c6         = new Code@mode<?>();

    System.out.println(PANDA_ModeTable.modeDBG(c6));

    Code@mode<*> c7 = snapshot c6 ?mode[@mode<low>,@mode<high>];
    Code@mode<*> c8 = snapshot c6 ?mode[@mode<*>,@mode<*>];

    System.out.println(PANDA_ModeTable.modeDBG(c7));
    System.out.println(PANDA_ModeTable.modeDBG(c8));


  }

}
*/
