package codegen_test;

modes { low <: mid; mid <: high; high <: veryHigh; }

public class Code @mode<X, Y <= X> {
  private String@mode<Y> s1;

  attribute {
    if (true) {
      return @mode<high>;
    } else {
      return @mode<high>;
    }
  } 

  public Code(String@mode<Y> s1) {
    this.s1 = s1;
  }

  public static void main(String[] args) {
    String@mode<mid> s1 = new String@mode<mid>();

    Code@mode<high,mid> s2 = new Code@mode<high,mid>(s1);
  }
}
