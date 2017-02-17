package nonequivocate;

modes { low <: mid; mid <: high; };

public class A@mode<?->X> {
  attributor {
    return @mode<low>;
  }

  void static_a(A a) {
    dynamic_a(a);
  }

  void dynamic_a(A@mode<?> a) {
  }
}
