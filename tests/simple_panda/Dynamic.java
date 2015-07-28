package simple_panda;

modes { low <: mid; mid <: high; high <: veryHigh; }

public class Dynamic@mode<X <= high, Y <= X> {
  private boolean choice;

  attribute {
    if (this.choice) {
      return @mode<high>;
    } else {
      return @mode<low>;
    }
  }

  public Dynamic() {
    this.choice = false;
  }

  public Dynamic(boolean choice) {
    this.choice = choice;
  }

  public void internal() {
    Dynamic@mode<X,Y> dc = new Dynamic@mode<X,Y>();
  }

  public static void main(String[] args) {
    Dynamic@mode<high,high> t1 = new Dynamic@mode<high,high>();
    Dynamic@mode<high,mid> t2 = new Dynamic@mode<high,mid>();
    Dynamic@mode<*,*> t3 = new Dynamic@mode<*,*>();

    Dynamic@mode<?,mid> dt = new Dynamic@mode<?,mid>(true);
    Dynamic@mode<?,mid> df = new Dynamic@mode<?,mid>(false);

    Dynamic@mode<*,mid> dr1 = snapshot dt ?mode[@mode<mid>,@mode<high>];
    Dynamic@mode<*,mid> dr2 = snapshot df ?mode[@mode<mid>,@mode<high>];

    // Uncomment to cause errors
    //Dynamic@mode<mid,high> e1 = new Dynamic@mode<mid,high>();
    //Dynamic@mode<veryHigh,high> e2 = new Dynamic@mode<veryHigh,high>();
  }
}
