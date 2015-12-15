package simple_panda;

modes { low <: mid; mid <: high; }

public class Code@mode<?->X> {

  ent_attribute {
    return @mode<high>;
  }

  public static void main(String[] args) {
    Code@mode<?> c1 = new Code@mode<?>();
  }

}
