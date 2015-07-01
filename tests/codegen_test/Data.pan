package codegen_test;

public class Data@mode<X <= mid, Y <= X> {
  attribute {

    if (true) {
      return @mode<high>;
    } else {
      return @mode<high>;
    }
  } 

  copy {
    return new Data@mode<X,Y>();
  }

  public void t1() {
    Data@mode<?,mid> d1 = new Data@mode<?,mid>();
    Data@mode<*,mid> d2 = snapshot d1 ?mode[@mode<low>,@mode<Y>];
  }

}
