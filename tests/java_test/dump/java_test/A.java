package java_test;

public class A<T> {
    private T f1;
    
    public T getF1() { return this.f1; }
    
    public A() { super(); }
    
    public static final String jlc$CompilerVersion$jl5 = "2.6.1";
    public static final long jlc$SourceLastModified$jl5 = 1430938307000L;
    public static final String jlc$ClassType$jl5 =
      ("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");
}
