package simple;

modes {low <: mid; mid <: high; }

public class Prim {
  // 1. Create public, protected, and private fields for primtive types
  public byte b1 = 1;
  protected byte b2 = 2;
  private byte b3 = 3;

  public void byteSet(byte b1, byte b2, byte b3) {
    this.b1 = b1;
    this.b2 = b2;
    this.b3 = b3;
  }

  public short s1 = 1;
  protected short s2 = 2;
  private short s3 = 3;

  public void shortSet(short s1, short s2, short s3) {
    this.s1 = s1;
    this.s2 = s2;
    this.s3 = s3;
  } 

  public int i1 = 1;
  protected int i2 = 2;
  private int i3 = 3;

  public void intSet(int i1, int i2, int i3) {
    this.i1 = i1;
    this.i2 = i2;
    this.i3 = i3;
  } 

  public long l1 = 1;
  protected long l2 = 2;
  private long l3 = 3;

  public void longSet(long l1, long l2, long l3) {
    this.l1 = l1;
    this.l2 = l2;
    this.l3 = l3;
  } 

  public float f1 = 1.0f;
  protected float f2 = 2.0f;
  private float f3 = 3.0f;

  public void floatSet(float f1, float f2, float f3) {
    this.f1 = f1;
    this.f2 = f2;
    this.f3 = f3;
  } 

  public double d1 = 1.0;
  protected double d2 = 2.0;
  private double d3 = 3.0;

  public void doubleSet(double d1, double d2, double d3) {
    this.d1 = d1;
    this.d2 = d2;
    this.d3 = d3;
  } 

  public boolean bl1 = true;
  protected boolean bl2 = true;
  private boolean bl3 = true;

  public void booleanSet(boolean bl1, boolean bl2, boolean bl3) {
    this.bl1 = bl1;
    this.bl2 = bl2;
    this.bl3 = bl3;
  } 

  public char c1 = 'a';
  protected char c2 = 'b';
  private char c3 = 'c';

  public void charSet(char c1, char c2, char c3) {
    this.c1 = c1;
    this.c2 = c2;
    this.c3 = c3;
  } 

  public void equalTest() {
    boolean ba = (this.b1 == 1);
    boolean bb = (this.s1 == 1);
    boolean bc = (this.i1 == 1);
    boolean bd = (this.l1 == 1);
    boolean be = (this.f1 == 1.0f);
    boolean bf = (this.d1 == 1.0);
    boolean bg = (this.bl1 == true);
    boolean bh = (this.c1 == 'a');
  }

  public void sumTest() {
    this.b3 = (byte) (this.b1 + this.b2);
    this.b3 = 1 + 2;

    this.s3 = (short) (this.s1 + this.s2);
    this.s3 = 1 + 2;

    this.i3 = (int) (this.i1 + this.i2);
    this.i3 = 1 + 2;

    this.l3 = (long) (this.l1 + this.l2);
    this.l3 = 1 + 2;

    this.f3 = (float) (this.f1 + this.f2);
    this.f3 = 1.0f + 2.0f;

    this.d3 = (double) (this.d1 + this.d2);
    this.d3 = 1.0 + 2.0;

    this.c3 = (char) (this.c1 + this.c2);
    this.c3 = 'a' + 'b';
  }

  public void boolTest() {
    boolean ba = (this.b1 < 1);
    boolean bb = (this.s1 < 1);
    boolean bc = (this.i1 < 1);
    boolean bd = (this.l1 < 1);
    boolean be = (this.f1 < 1.0f);
    boolean bf = (this.d1 < 1.0);
    boolean bh = (this.c1 < 'a');
  }

  public static void main(String[] args) {
    Prim p1 = new Prim();
    p1.byteSet((byte)1,(byte)2,(byte)3);
    p1.shortSet((short)1,(short)2,(short)3);
    p1.intSet(1,2,3);
    p1.longSet(1,2,3);
    p1.floatSet(1.0f,2.0f,3.0f);
    p1.doubleSet(1.0,2.0,3.0);
    p1.booleanSet(true,true,true);
    p1.charSet('a','b','c');
  }

}
