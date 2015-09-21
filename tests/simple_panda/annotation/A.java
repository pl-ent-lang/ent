package simple_panda.annotation;

modes {low <: mid; mid <: high; }

public class A@mode<?->X<=high> {
  mcase<int> HACK = mcase<int> {
    low:0;
    mid:0;
    high:0;
  };

  panda_attribute {
    return @mode<low>;
  }

  public void useB(B@mode<low <= * <= X> b) {
    b.method();
  }

  public static void main(String[] args) {
    A@mode<?> d_a = new A@mode<?>();
    A@mode<*> a = snapshot d_a ?mode[@mode<low>,@mode<high>];

    B@mode<?> d_b = new B@mode<?>();
    B@mode<*> b = snapshot d_b ?mode[@mode<low>,a];

    a.useB(b);  // Sound, but we need more annotations

  }
}
