package simple;

import java.lang.Iterable;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

public class Wrapper<T> implements Iterable<T> {
    private List<T> wrap;
    
    public Wrapper() {
        super();
        this.wrap = new ArrayList<T>();
    }
    
    public Wrapper(T ... args) {
        super();
        this.wrap = new ArrayList<T>();
    }
    
    public boolean add(T t) { return this.wrap.add(t); }
    
    public T get(int i) { return this.wrap.get(i); }
    
    public void set(int i, T t) { this.wrap.set(i, t); }
    
    public int size() { return this.wrap.size(); }
    
    public Iterator<T> iterator() { return this.wrap.iterator(); }
    
    public static void main(String[] args) {  }
    
    public static final String jlc$CompilerVersion$jl5 = "2.6.1";
    public static final long jlc$SourceLastModified$jl5 = 1436903036000L;
    public static final String jlc$ClassType$jl5 =
      ("H4sIAAAAAAAAAK0Za2wUx3nu/Da2z9i8al42NiFAuAsopEWuSIyLg+kZLGxc" +
       "xWlzrHfn7IW93c3unn2QOAlILTRqKGodSiri9gepGkQAVaJRFKVyFKUB0lRK" +
       "FCWlakObPyVQJJCatCpt0u+b2dvXPcyjlnZudma+b7/3Y3ziKikzDbJS15Td" +
       "w4pmRWnGiu5U1kat3To1o5vja3sFw6RSpyKYZj+sJcQlpyOf3zg0Uh8m5YOk" +
       "UVBVzRIsWVPNbdTUlFEqxUnEXd2o0JRpkfr4TmFUiKUtWYnFZdNqj5MZHlCL" +
       "tMWzJMSAhBiQEGMkxDrcUwBUS9V0qhMhBNUyHyNPklCclOsikmeRFj8SXTCE" +
       "lI2ml3EAGCrxfQCYYsAZgzQ7vHOecxh+bmVs4ieP1v+qhEQGSURW+5AcEYiw" +
       "4CODpCZFU0PUMDskiUqDZKZKqdRHDVlQ5D2M7kHSYMrDqmClDeoICRfTOjXY" +
       "N13J1YjIm5EWLc1w2EvKVJGyb2VJRRgGXue4vHIOu3AdGKyWgTAjKYg0C1K6" +
       "S1YliywOQjg8tn0TDgBoRYpaI5rzqVJVgAXSwHWnCOpwrM8yZHUYjpZpafiK" +
       "RZoKIkVZ64K4SximCYvMC57r5VtwqooJAkEsMjt4jGECLTUFtOTRz9UtXz/4" +
       "uLpJDTOaJSoqSH8lAC0KAG2jSWpQVaQcsGZF/LAw5/UDYULg8OzAYX7mlSeu" +
       "P3jPoqmz/Mz8PGe2Du2kopUQjw3Vvbegc/m6EiSjUtdMGZXv45yZf6+9057R" +
       "wfPmOBhxM5rdnNr224efPk6vhEl1NykXNSWdAjuaKWopXVao8RBVqSFYVOom" +
       "VVSVOtl+N6mAeVxWKV/dmkya1OompQpbKtfYO4goCShQRBUwl9Wklp3rgjXC" +
       "5hmdEFIBD1kFTw3hf+zXIl+NbTfB3GOCKKiyqsV6DQ0FgBpVJSFmURPmppzS" +
       "FRr7liHooNsoGpB++6AZpKp+LBQCgS0IuqsClr5JUyRqJMSJ9IaN108m3gk7" +
       "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" +
       "BLvmlFMZTFc1/ON//fW8MoIbAAA=");
}
