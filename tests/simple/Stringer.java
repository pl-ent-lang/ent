package simple;

public class Stringer {
  private String intern = null;

  public Stringer() {
    this.intern = "";
  }

  public Stringer(String intern) {
    this.intern = intern;
  }

  public Stringer(Stringer strr) {
    this.intern = strr.intern;
  }

  public char charAt(int index) {
    return this.intern.charAt(index);
  }

  public Stringer concat(Stringer s) {
    return new Stringer(this.intern.concat(s.intern));
  }

  public boolean equals(Object o) {
    if (!(o instanceof Stringer)) {
      return false;
    }
    Stringer s = (Stringer) o;
    return this.intern.equals(s.intern);
  } 

  public int length() {
    return this.intern.length();
  }

  public static void main(String[] args) {
    Stringer s = new Stringer();
    Stringer t = s;
    for (int i = 0; i < 'z' - 'a'; i++) {
      s = s.concat(new Stringer(String.valueOf('a' + i)));
    }
    for (int i = 0; i < 'z' - 'a'; i++) {
      System.out.println(i + ":" + s.charAt(i));
    }
    System.out.println("Equal: " + s.equals(t));
    System.out.println("Length: " + s.length());
  }

}
