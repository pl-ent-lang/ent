package simple_panda;

import java.util.ArrayList;
import java.util.List;

public class SimpleWrapper@mode<X <= high, Y <= X> {
  private List<String@mode<Y> > wrap;

  public SimpleWrapper() {
    this.wrap = new ArrayList<>();
  }

  public boolean add(String@mode<Y> t) {
    return this.wrap.add(t);
  }

  public String@mode<Y> get(int i) {
    return this.wrap.get(i);
  }

  public void set(int i, String@mode<Y> t) {
    this.wrap.set(i, t);
  }

  public int size() {
    return this.wrap.size();
  }

  public static void main(String[] args) {
    SimpleWrapper@mode<high,mid> s1 = new SimpleWrapper@mode<high,mid>();
    String@mode<mid> sa = "a";
    String@mode<mid> sb = "b";
    String@mode<mid> sc = "c";

    s1.add(sa);
    s1.add(sb);
    s1.add(sc);

    String@mode<mid> s2 = s1.get(0);

    // Uncomment to cause errors
    /*
    String@mode<high> sa1 = "a";
    String@mode<high> sb1 = "b";
    String@mode<high> sc1 = "c";

    s1.add(sa1);
    s1.add(sb1);
    s1.add(sc1);

    String@mode<high> s3 = s1.get(0);
    */
  }

}
