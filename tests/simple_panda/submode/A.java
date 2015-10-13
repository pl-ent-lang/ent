package simple_panda.submode;

// Compile with /submode/B.java, should have 8 errors

import java.util.List;

public class A<T> {
  public void m1(String@mode<high> s) {
  }

  public void m2(String@mode<low> s) {
  }

  public void m3(List<String>@mode<high> l) {
  }

  public void m4(List<String>@mode<low> l) {
  }

  public void m5(List<T>@mode<high> l) {
  }

  public void m6(List<T>@mode<low> l) {
  }

  public void m7(String@mode<low <= * <= high> l) {
  }

  public void m8(String@mode<low <= * <= mid> l) {
  }

  public void testT() {
    List<T>@mode<high> highList = null;
    List<T>@mode<low>  lowList = null;
    this.m5(lowList);   // Valid <=
    this.m6(highList);  // Invalid <=

    this.m6(lowList);   // Valid <=
    this.m5(highList);  // Invalid <=
  }

  public static void main(String[] args) {
    // Test ParsedClassType
    A<String> a = new A<String>();

    String@mode<high> highStr = "abc";
    String@mode<mid>  midStr = "abc";
    String@mode<low>  lowStr  = "abc";

    a.m1(lowStr);   // Valid <=
    a.m2(highStr);  // Invalid <=

    a.m2(lowStr);   // Valid <=
    a.m1(highStr);  // Valid <=

    // Test SubstClassType
    List<String>@mode<high> highList = null;
    List<String>@mode<low>  lowList = null;

    a.m3(lowList);    // Valid <=
    a.m4(highList);   // Invalid <=

    a.m4(lowList);    // Valid <=
    a.m3(highList);   // Valid <=

    // Test SubstClassType with T
    List<String>@mode<high> highList2 = null;
    List<String>@mode<low>  lowList2 = null;

    a.m5(lowList2);    // Valid <=
    a.m6(highList2);   // Invalid <=

    a.m6(lowList2);    // Valid <=
    a.m5(highList2);   // Valid <=

    // Test Boxed 
    A<Integer> a2 = new A<Integer>();
    List<Integer>@mode<high> highList3 = null;
    List<Integer>@mode<low>  lowList3 = null;
    a2.m5(lowList3);    // Valid <=
    a2.m6(highList3);   // Invalid <=

    a2.m6(lowList3);    // Valid <=
    a2.m5(highList3);   // Valid <=

    // Test Existential
    a.m7(lowStr);   // Valid <=
    a.m7(midStr);   // Valid <=
    a.m7(highStr);  // Valid <=

    String@mode<low <= * <= high> fullStr = null;
    String@mode<mid <= * <= high> mhStr = null;
    String@mode<low <= * <= mid> lmStr = null;

    a.m7(fullStr);    // Valid <=
    a.m7(mhStr);      // Valid <=
    a.m7(lmStr);      // Valid <=

    a.m8(lowStr);   // Valid <=
    a.m8(midStr);   // Valid <=
    a.m8(highStr);  // Invalid <=

    a.m8(fullStr);    // Invalid <=
    a.m8(mhStr);      // Invalid <=
    a.m8(lmStr);      // Valid <=
  }
}
