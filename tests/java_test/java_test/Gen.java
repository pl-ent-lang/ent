package java_test;

public class Gen<T> {
    private T t;
    
    public Gen() { super(); }
    
    public static void main(String[] args) {
        Gen<String> g1 = new Gen<String>();
    }
    
    public static final String jlc$CompilerVersion$jl5 = "2.6.1";
    public static final long jlc$SourceLastModified$jl5 = 1429215209000L;
    public static final String jlc$ClassType$jl5 =
      ("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" +
       "htG0nZkSAAA=");
}
