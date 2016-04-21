package method_mcase_pass1;

modes {low <: mid; mid <: high; }

public class A@mode<X> {
  public mcase<String> msg = mcase<String> {
    low: "low";
    mid: "mid";
    high: "high";
  };
}
