package simple_panda;

import java.util.ArrayList;
import java.util.List;

public class ComplexWrapper<T> @mode<X <= high, Y <= X> {
  /*
  private List<T>@mode<Y> wrap;

  public ComplexWrapper() {
    this.wrap = new ArrayList<>();
  }
  */

  public ComplexWrapper(List<T>@mode<Y> cpy) {
    //this.wrap = new ArrayList<>(cpy);
  }

  /*
  public boolean add(T t) {
    return this.wrap.add(t);
  }

  public T get(int i) {
    return this.wrap.get(i);
  }
  */

  public static void main(String[] args) {
    // Test the standard T type from ComplexWrapper 
    /*
    ComplexWrapper<String@mode<low> >@mode<high,mid> c1 = new ComplexWrapper<String@mode<low> >@mode<high,mid>();
    String@mode<low> sa = "a";
    String@mode<low> sb = "b";
    String@mode<low> sc = "c";

    c1.add(sa);
    c1.add(sb);
    c1.add(sc);

    String@mode<low> s2 = c1.get(0);

    // Test the mode type varaiable Y from ComplexWrapper 
    List<String@mode<low> >@mode<mid> l1 = new ArrayList<String@mode<low> >@mode<mid>();

    ComplexWrapper<String@mode<low> >@mode<high,mid> c2 = 
      new ComplexWrapper<String@mode<low> >@mode<high,mid>(l1);

    */
    // Uncomment to cause errors 
    /*
    List<String@mode<low> >@mode<mid> l2 = new ArrayList<String@mode<low> >@mode<mid>();

    ComplexWrapper<String@mode<low> >@mode<high,high> ce1 = 
      new ComplexWrapper<String@mode<low> >@mode<high,high>(l2);
    */

    // Uncomment to cause errors
    /*
    String@mode<high> sa1 = "a";
    String@mode<high> sb1 = "b";
    String@mode<high> sc1 = "c";

    c1.add(sa1);
    c1.add(sb1);
    c1.add(sc1);

    String@mode<high> s3 = c1.get(0);
    */
  }

}
