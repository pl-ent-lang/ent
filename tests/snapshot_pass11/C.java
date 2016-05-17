package snapshot_pass11;

public class C@mode<? -> (mid <= X <= high)> {

  int x = 0;

  attributor {
    if (x == 0) {
      x++;
      return @mode<mid>;
    } else {
      return @mode<high>;
    }
  }

  public void m1() {
    B@mode<mid> b = new B@mode<mid>();
    b.@mode<X>m3();
  }

  public static void main(String[] args) {
    C@mode<?> c = new C@mode<?>();

    /* First snapshot is good */
    C@mode<*> c1 = snapshot c ?mode[@mode<mid>,@mode<mid>];
    c1.m1();

    /* This should cause a copy on c1 */
    C@mode<*> c2 = snapshot c ?mode[@mode<mid>,@mode<high>];
    boolean ok = false;
    try {
      /* This should fail on B snapshot */
      c2.m1();
    } catch (RuntimeException e) {
      ok = true;
    }

    if (!ok) {
      System.exit(1);
    }

    /* This should trigger another snapshot, which should succed.
     * Checking that the lazy copy doesn't screw up. */
    c1.m1();
  }

}
