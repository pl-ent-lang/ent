package simple;

public class Stringer {
    private String intern = null;
    
    public Stringer() {
        super();
        this.intern = "";
    }
    
    public Stringer(String intern) {
        super();
        this.intern = intern;
    }
    
    public Stringer(Stringer strr) {
        super();
        this.intern = strr.intern;
    }
    
    public char charAt(int index) { return this.intern.charAt(index); }
    
    public Stringer concat(Stringer s) {
        return new Stringer(this.intern.concat(s.intern));
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof Stringer)) { return false; }
        Stringer s = (Stringer) o;
        return this.intern.equals(s.intern);
    }
    
    public int length() { return this.intern.length(); }
    
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
    
    public static final String jlc$CompilerVersion$jl5 = "2.6.1";
    public static final long jlc$SourceLastModified$jl5 = 1436906852000L;
    public static final String jlc$ClassType$jl5 =
      ("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" +
       "E3HD/C8ht+zTrRUAAA==");
}
