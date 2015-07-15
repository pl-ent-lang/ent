package simple;
import panda.runtime.*;
public class Prim {
    public byte b1 = 1;
    protected byte b2 = 2;
    private byte b3 = 3;
    public void byteSet(byte b1, byte b2, byte b3) { this.b1 = b1;
                                                     this.b2 = b2;
                                                     this.b3 = b3; }
    public short s1 = 1;
    protected short s2 = 2;
    private short s3 = 3;
    public void shortSet(short s1, short s2, short s3) { this.s1 = s1;
                                                         this.s2 = s2;
                                                         this.s3 = s3; }
    public int i1 = 1;
    protected int i2 = 2;
    private int i3 = 3;
    public void intSet(int i1, int i2, int i3) { this.i1 = i1;
                                                 this.i2 = i2;
                                                 this.i3 = i3; }
    public long l1 = 1;
    protected long l2 = 2;
    private long l3 = 3;
    public void longSet(long l1, long l2, long l3) { this.l1 = l1;
                                                     this.l2 = l2;
                                                     this.l3 = l3; }
    public float f1 = 1.0F;
    protected float f2 = 2.0F;
    private float f3 = 3.0F;
    public void floatSet(float f1, float f2, float f3) { this.f1 = f1;
                                                         this.f2 = f2;
                                                         this.f3 = f3; }
    public double d1 = 1.0;
    protected double d2 = 2.0;
    private double d3 = 3.0;
    public void doubleSet(double d1, double d2, double d3) { this.d1 = d1;
                                                             this.d2 = d2;
                                                             this.d3 = d3;
    }
    public boolean bl1 = true;
    protected boolean bl2 = true;
    private boolean bl3 = true;
    public void booleanSet(boolean bl1, boolean bl2, boolean bl3) { this.
                                                                      bl1 =
                                                                      bl1;
                                                                    this.
                                                                      bl2 =
                                                                      bl2;
                                                                    this.
                                                                      bl3 =
                                                                      bl3;
    }
    public char c1 = 'a';
    protected char c2 = 'b';
    private char c3 = 'c';
    public void charSet(char c1, char c2, char c3) { this.c1 = c1;
                                                     this.c2 = c2;
                                                     this.c3 = c3; }
    public void equalTest() { boolean ba = this.b1 == 1;
                              boolean bb = this.s1 == 1;
                              boolean bc = this.i1 == 1;
                              boolean bd = this.l1 == 1;
                              boolean be = this.f1 == 1.0F;
                              boolean bf = this.d1 == 1.0;
                              boolean bg = this.bl1 == true;
                              boolean bh = this.c1 == 'a'; }
    public void sumTest() { this.b3 = (byte) (this.b1 + this.b2);
                            this.b3 = 1 + 2;
                            this.s3 = (short) (this.s1 + this.s2);
                            this.s3 = 1 + 2;
                            this.i3 = (int) (this.i1 + this.i2);
                            this.i3 = 1 + 2;
                            this.l3 = (long) (this.l1 + this.l2);
                            this.l3 = 1 + 2;
                            this.f3 = (float) (this.f1 + this.f2);
                            this.f3 = 1.0F + 2.0F;
                            this.d3 = (double) (this.d1 + this.d2);
                            this.d3 = 1.0 + 2.0;
                            this.c3 = (char) (this.c1 + this.c2);
                            this.c3 = 'a' + 'b'; }
    public void boolTest() { boolean ba = this.b1 < 1;
                             boolean bb = this.s1 < 1;
                             boolean bc = this.i1 < 1;
                             boolean bd = this.l1 < 1;
                             boolean be = this.f1 < 1.0F;
                             boolean bf = this.d1 < 1.0;
                             boolean bh = this.c1 < 'a'; }
    public static void main(String[] args) { Prim p1 = (Prim) PANDA_Runtime.
                                                         putObj(
                                                           new Prim(
                                                             ),
                                                           new Integer[] { PandaMode.
                                                                             WILDCARD_MODE });
                                             p1.byteSet((byte) 1,
                                                        (byte)
                                                          2,
                                                        (byte)
                                                          3);
                                             p1.shortSet((short) 1,
                                                         (short)
                                                           2,
                                                         (short)
                                                           3);
                                             p1.intSet(1, 2, 3);
                                             p1.longSet(1, 2, 3);
                                             p1.floatSet(1.0F, 2.0F,
                                                         3.0F);
                                             p1.doubleSet(1.0, 2.0,
                                                          3.0);
                                             p1.booleanSet(true, true,
                                                           true);
                                             p1.charSet('a', 'b',
                                                        'c'); }
    public Prim() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1436903987000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAK1aCXDc5BX+d307Nr6IbXLjI5ADL4aEIRhCgrETBzt2TsAp" +
                                                    "OFrtv7YSraRI2nidkBLCUFJmYJgS0kCphzIJUBoShsK0tIRJB1qO0HaSgVLO" +
                                                    "tDCdAiGdZNpShpTS935Ju1pptd611zP6V5b+43vve+/9h97B06RAU0mDwkkh" +
                                                    "rkUfUajW0of3fZyq0VC7yGnaWng6wL/SWvfAn8X97/hJfj+ZLGhdEUUUeEHv" +
                                                    "kUMUa6zn1G5Sxem6KgSjOu2SNJ2TeKqTmd2s8wDrPLDUWaGtm5TxsjKSaDAl" +
                                                    "qUG77R3WjSTG03RS2b2J28oForogBroFTW+LqWSeIosjg6Kst9CY3rJJXGjK" +
                                                    "taJ7oUuqhmcqvjx3/1ClnxT2kxpOkmSd0wVZ0lZTTRa30lA3qUg87RBpRNtC" +
                                                    "vkvyuskkW2WdNHVbgwZg0AAMasmbqAXoy6kUjbTLTBzd6qlQ4RGQTi5M7kTh" +
                                                    "VC5idtPHMEMPxbopO2sM0s6KS2ux5xDxwXmBPT+8tfLZPFLRTyoEaQ3C4QGE" +
                                                    "DoP0g0JpJEhVbWkoREP9pEqiNLSGqgInCtsY7n5SrQmDEqdHVRpXCz6MKlRl" +
                                                    "YyZ0BUyCbGqU12U1Ll5YoGLI+q8gLHKDIGttQlZDwk58DgKWCgBMDXM8tZrk" +
                                                    "bxakELOj5BZxGZtugArQtChC9SE5PlS+xMEDUm2YiMhJg4E1YHzSIFQtkMEE" +
                                                    "VWZrHp2irhWO38wN0gGd1Dvr9RmvoFYJUwQ20clkZzXWE7A0xcGSjZ/TK6++" +
                                                    "b7u0XPITH2AOUV5E/JOg0QxHo9U0TFUKfmA0LJvbvZerPbLbTwhUnuyobNT5" +
                                                    "xW1nl8yfcfQ1o87UFHV6g5sorw/w+4PnHZ/WPmdRHsIoVmRNQPKTJGde1me+" +
                                                    "aYspEDhq4z3iyxbr5dHVv7t551P0lJ+UdpFCXhajEbCjKl6OKIJI1WVUoiqn" +
                                                    "01AXKaFSqJ297yJFcN8tSNR42hsOa1TvIvkie1Qos/9BRWHoAlVUCveCFJat" +
                                                    "e4XTh9h9TCGEFMFFyuAqIMYf+9XJgsA6Dcw9wPGcJEhyoE+VUQHIKAs6VIN7" +
                                                    "TYDoRuGdEGlB61HG2S6GeGqGfT5Q1TSno4pg48tlMUTVAX5P9LqOs4cGjhlG" +
                                                    "gIZrSqKTSUanLdgp8flYX+dj54bKQWGbwfUg9pXNWXPLio27G/KAa2U4H6TF" +
                                                    "qrNdob094aNWYB3gH713cf87dR8d8RM/uHGQ02wRvCkpIBvTgyrzNAQRwSs0" +
                                                    "W+Ep4B2MU+IgR/cN37H+9ksZDnuMxQ4LIDxg8z6MjPEhmpy+larfirs//fLw" +
                                                    "3h1ywsuSgrY117haovM2OJlzCj/Az53FPT9wZEcTzI4QESAK6hxYLQSYGc4x" +
                                                    "kpy4zQqIKEsxCByW1Qgn4isripXqQ6o8nHjCTKoKi8mGdaE5OACyWNr5wtGH" +
                                                    "nn943iImsRV2K2zz5RqqG05clbCmtSql8PzDfX0PPHj67g3MlKBGY6oBmrBs" +
                                                    "B5cGNkBjd7225d2TH+1/y58wPx3mtmgQlgkxNMTEKODwIvgO0tq0TgKzEcIC" +
                                                    "FxQpWvF/K5pbn//ivkqDKBGeWDzPH7uDxPMLriM7j936nxmsGx+PE05C8kQ1" +
                                                    "QwE1iZ6Xqio3gjhid5yY/tCr3I8hHkIM0oRtlIUVwiQjTPWXMUYuYWWr490C" +
                                                    "LJo0u3UmO4BtYTDA3//WmfL1Z146y9AmryzsjPVwSpvBPxvzfBismphFUpjD" +
                                                    "t7UKlnWo+jpnuFjOaUPQ2YKjK79TKR49B8P2w7A8zLlarwoBKZZkFGbtgqL3" +
                                                    "fvNy7cbjecTfSUpFmQt1csxVSAnYKNWGIJbFlGuXGDiGi6GoZPogLg2h0mem" +
                                                    "prMjouiMgG2/rHvu6idGP2L2FGNdTGXN66HxHO+w0onLjYRn1n/dKwZ3ffyV" +
                                                    "S7UsoKSYZR3t+wMHH5nSvvgUa5/wbGzdGHNHdYh9ibaXPRX5t7+h8Ld+UtRP" +
                                                    "Knlz3beeE6PoZP2w1tGsxSCsDZPeJ69bjEm6LR65pjmjim1YZ0xJzCZwj7Xx" +
                                                    "vtxhRiVIy2Rr2rR+7WbkI+ymgzVpZuXFWMwzXB1v58d04g+2MqO3TTq4Q1gT" +
                                                    "DWo6zmCwPNhqrl++OXT7qleby9+w1iapmhg16/8x/HnvZ58uYJNCMU5Oay05" +
                                                    "bNPOUnXQFiIrDdm+hT8fXP/DC2XCB8ZKoLrdXI7Miq9HFCWW3rgcIgR2VJ/c" +
                                                    "/MinTxsiOC3JUZnu3nPPty337TEim7GobXStK+1tjIWtIQ4WPYjuwnSjsBad" +
                                                    "fz+849dP7rjbQFWdvETrgB3I03/65s2WfX95PcVqIz84otO4v/riy4haJz2G" +
                                                    "TO8//ua60rd3XQjzXhcpjkrClijtCiUbXZEWDdr4SqyXE4ZoSgdj5enENxdo" +
                                                    "MKY2LBdi0WkEgEWe0WVJ3JRxzUfq4So2TbnYw5Rv8TBlnZQoqqyDw9EQs2gj" +
                                                    "zq8wMGK52qkg/PdmJ+ZbM8dcik9rLT+0flNgHvTEXKSowlYO93SA+PLxIR7K" +
                                                    "HHGJhbjKRFzlgXgLFssAlNZqB8Xue233a3RSAFOIqmeEVM3SHi6wpkrrNwXS" +
                                                    "7VgMIFIX4dGMQN2WJeF1cNWYoGo8QO3CQkBQLk4zA3VnlpzizVQT1FQPUN+3" +
                                                    "OBXG5DRPkDJj9J4sGUXlTTNxTvPA+YDFqOBi9N6MQO3JklGcQaeboKZ7gHrY" +
                                                    "YlRwMZoZqB9lySiCajZBNXuA+onFqDgmo/miLA1mBPSxcQTt2SbQ2R5Af2ZR" +
                                                    "KrooPZARqIPjiMoXmaAu8gD1rEWp6KI0M1A/z5JSDGcBE1TAA9QLFqXhsQNv" +
                                                    "GFbzmbnpr7LkFF3zUhPppR5IX7Y4Dbs4PZIRqFey5HQKXK0mqFYPUG9YnIZd" +
                                                    "nGYG6tg4OF1kglrkAeq4xWloTE4LQzJsvV1LuZRQT4yD1KtMqFd5QH3fIjXk" +
                                                    "IvXtjEB9MA5S20xQbR6gPrZIDblIzQzUJ1mSippqN0G1e4D6zCQ1Lzh28C0K" +
                                                    "yrJIOSkjsJ9nSetMuK43wV7vAfafJq0A1sXr6YxQ/StLXnEe7TBRdXig+trk" +
                                                    "FVC5iM0M1bksicXlR4+Jqic1Kp/P8lZ+7EmVHzIPTccA6vNnSSq6xUoT6EoP" +
                                                    "oJMsX+WdnPoKMgJVliWnONP3mqB6PUBVW77KOynNEFRNGlCs7jL2fqp5JgWb" +
                                                    "3OleX2nYtnr/rj2jod4DrX7ziG8tbBR1WblEpFupaOuqjG3SnafuPezbVOJs" +
                                                    "aNu5TUN/+6KPdx+4Y08zPI7T53ofTTgHeHXX51PWLh7amMVJ+kyH/M4uf9pz" +
                                                    "8PVls/kf+NnnNeMoyvVZLrlRW/JZQKlK9agqJe/+G5JdC5cJk0zjmOQ0DsZx" +
                                                    "grzE4Wse032e0/s9Nr65rcaMstkByHYa7LsEiwYM3yM6Hv2lDAFbZSGUsN7G" +
                                                    "sVwq6TwVHyzGx74ZycpcQIwNALF+J6JMjx1nbqsxhV2ZRplLsFiok2J2XADa" +
                                                    "xP8DCc1dkRvNzSfGjEis34lozmNnl9tqTDvL02iuD4sOWBvCptyttzHP2DLT" +
                                                    "G7rvHFNvcyaqN4/tU26rMd3clEZvG7FYB+6LW1+34tbnzlUvNxV3+UQV57FH" +
                                                    "yW01ppyhNIpjEzkFV2UbTLfmwrnR3JVwXWNq7pqJas5jI5Dbakw7sTSa24lF" +
                                                    "FFYYxjbOrbqtuVHdtXAtM1W3bKKq81hq57YaU8/uNKq7H4u7dFJq7pXcuvte" +
                                                    "7iLdKlN3qyaoO681bW6rsXf70ujuUSwehEiH+xG34vbmRnF41HGjqbgbM1ac" +
                                                    "HeeTad49hcUBcB26JcqJa6nmlOLx3EiBW8CgKUVwXFI8m+bdc1gc1vHrWSSF" +
                                                    "DM/kRgbciI2YMoyMS4YX07x7CYsXIPyjJ6YQYszDxDGFYJUvhutOU4g7UwrB" +
                                                    "6ru+mvnxdj4shzSWEukMx4mPn7NSfptmqSLGZ9B3j4/sXdWxt8344pr667cj" +
                                                    "e+/j7ccekz845bd2kzcYzgujNXvv8WxDjj7e+IfbRxv/ynI3igUNdolL1cEU" +
                                                    "qYa2NmcOnjx1onz6IZallI97Thy03Jmj6U7BTMqsZHAr4hTgAQOZlY4CVrVe" +
                                                    "J5Us4wMTGlqMRExFUYhT3bZA9Xoa03obi5dh1xThBMlhVmMeB7vNKgY94Yd0" +
                                                    "TEypd2XUGlmg/KHRiuK60XXvGOqzMjXLumF5ExVFe5qF7b5QUWlYYDorM5Iu" +
                                                    "2Mdt3wdodyyzD//7UEmhJCPrI+Zjr+LHFJaYZPJYYtpONhqTbIplFlv2GTVy" +
                                                    "iwf4w6MrVm4/e8UBdmBQwIvctm3YyySg30hLM+m3pyA4e7P6Klw+59x5z5Q0" +
                                                    "W9adlLBmx8a071NQ71McWVhaUzwZ6939V7/0+92FJ8DYNxAfp5OaDe7smJgS" +
                                                    "VcnMDd2pMhXAO1iCWFvpJxv/+NV7vmqWVWS694x0LQb4qff0V558UXvCT4q7" +
                                                    "SIEghWgsKdehMChHpbjznId2waF1M1WYGiyPP8XkQZ00uPM+3AmVpaI8TNXr" +
                                                    "sHfTT5OOUqKKYn/LVHmTEUVAsGKd5A109yiKlfDCNA0eh+Z2hnnZ/wE7cW4r" +
                                                    "/S8AAA==");
    public static final String jlc$CompilerVersion$jl5 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl5 =
      1436903987000L;
    public static final String jlc$ClassType$jl5 =
      ("H4sIAAAAAAAAAK17eazr2Hmf3pvtzZvxrPbMZDybPYs9lvPEVZQ8XqKFpBZS" +
       "okRSpNTEM9xEkeJOSqKUTBI7ae0mwMRoxq7TuoMWsFM3GNtBWiNpirRTtGni" +
       "xihgI4i7IJnUKNqgrgH7jyZF3NQ9pKR379W7983cN7kAP/GS55zv9y3nO99Z" +
       "+Op3C7fEUaEY+M7adPzkipEmV2wHv5KsAyO+0mFwToliQ284ShwL4Nnz2rt/" +
       "/e4//8GnZvdcLNw6KdyveJ6fKInle/HQiH1naehM4e6jp6RjuHFSuIexlaVS" +
       "WiSWU2KsOHmOKdxxrGpSeIrZQygBCCUAoZRDKNWOSoFKbzO8hdvIaiheEoeF" +
       "ny5cYAq3BloGLym862QjgRIp7q4ZLpcAtHAp+38EhMorp1Hhiauyb2W+RuBP" +
       "F0sv/92P3vMbNxXunhTutjw+g6MBEAlgMinc6RquakRxTdcNfVK41zMMnTci" +
       "S3GsTY57UrgvtkxPSRaRcVVJ2cNFYEQ5zyPN3allskULLfGjq+JNLcPR9//d" +
       "MnUUE8j6wJGsWwmp7DkQ8LIFgEVTRTP2VW6eW56eFB4/rHFVxqe6oACoeptr" +
       "JDP/KqubPQU8KNy3tZ2jeGaJTyLLM0HRW/wF4JIUHj6z0UzXgaLNFdN4Pik8" +
       "dFiO274CpW7PFZFVSQrvOCyWtwSs9PCBlY7Z57u9D770k17Lu5hj1g3NyfBf" +
       "ApUeO6g0NKZGZHiasa145/uYzygP/M4nLxYKoPA7Dgpvy/zmT33/x97/2Gu/" +
       "vy3zzlPK9FXb0JLntc+rd33jkcaz1ZsyGJcCP7Yy45+QPHd/bvfmuTQAPe+B" +
       "qy1mL6/sX742/Hfjn/014zsXC5fbhVs131m4wI/u1Xw3sBwjog3PiJTE0NuF" +
       "2w1Pb+Tv24XbwD1jecb2aX86jY2kXbjZyR/d6uf/AxVNQROZim4D95Y39ff3" +
       "gZLM8vs0KBQKt4GrcCe4bils//LfpICVxBi4e0nRFM/y/BIX+ZkCMot6ulJK" +
       "jBjcx5YbOAZ4Z7lXMu8JbrBemuG5Z3XhAlDVI4cd1QE+3vId3Yie115e1Mnv" +
       "f/n5P7h41XF3kiSFO7aNXskaLVy4kLf19qzxrcqBwuag64GgdOez/E90Xvjk" +
       "u28Ctg5WNwNps6Kls2Nj46iztvOQpAGPKbz22dXHRj8DXSxcPBnkMkDg0eWs" +
       "OpeFpqsh6KlD5z6t3bs/8Wd//pXPvOgfufmJqLnrfdfWzHrPuw9VF/maoYN4" +
       "dNT8+55Qvvr877z41MXCzaBLgjCUKMBtQA9/7JDHiV703D4iZbLcAgSe+pGr" +
       "ONmrfRi5nMwif3X0JLfpXfn9vUDH9xV25ISfZW/vDzL69q0PZEY7kCKPeNQ/" +
       "f+1Xvvr3itWLx4Pj3ceGG95Itl3t3iObC5FhgOd//Fnulz/93U/8jdzgoMST" +
       "pzF4KqMN0PGAyYBa/+bvh//p9T/5/B9ePHKSBIxAC9WxtBS08cwRF9AtHeDh" +
       "me2fEj3X162ppaiOkfna/737afir/+ule7bWdMCTvTO8/40bOHr+I/XCz/7B" +
       "R//isbyZC1o2LBxJflRsq4D7j1quRZGyznCkH/vmo7/ye8o/AFELRIrY2hh5" +
       "5y/kkhVy1V/JTfVsTn/04B2Ukcfj4y58spccG76f1z71h9972+h7//L7OdqT" +
       "4/9xi7FK8NzWSTLyRKbVBw/7a0uJZ6Ac9lrvx+9xXvsBaHECWtTAoBf3IxAR" +
       "0hP23pW+5bb//K//zQMvfOOmwkWqcNnxFZ1S8q5SuB34qBHPQDBJg4/82NYP" +
       "V5cAuScXtXCN8Gn+5KH8v3cCgM+eHSWobPg+6mgP/WXfUT/+7f9zjRLy+HDK" +
       "qHVQf1J69XMPNz78nbz+UUfNaj+WXhslQapzVBf5Nfd/X3z3rb97sXDbpHCP" +
       "tsujRoqzyLrDBOQO8T65ArnWifcn84DtoPfc1UD0yGGQOMb2MEQcRWdwn5XO" +
       "7i8fRIXbMy2/Yz8M7X+PR4ULhfzmwxl5MilcVOHrmyEL/2BsXe4G/9KL970+" +
       "/9yffWk7sB/q/KCw8cmXf+GHV156+eKxdOrJazKa43W2KVUu1Nu2Qv0Q/F0A" +
       "1//LrkyY7MF2SL2vsRvXn7g6sAdB5vbvuh6snAX1P77y4r/44ouf2Ipx38ls" +
       "ggTJ8pf+6K++fuWzf/q1UwbGm9V1YmyDa0bhjHxk69jYmZ2getVEWW5QeAhc" +
       "l3YmunSGidi8yrty+lRG3rOPm7cHkZ8ARzL0NLMgkr1sHiDqvXlEl7OnD+y9" +
       "Z/97CiLxTES3BZG1VLLMHuBBT8MzevN4bt/juXeH594z8PzE3oljOK9cywi5" +
       "ZUQnYIo286PkAMdHz2mpH9mPtfvfU3Dk3tDPcOSmeOGA5fScpngQXPfvWN5/" +
       "Bks7I1LGEj2N5fyc2n57Fo93LN95Bkt/r23rVG3fBCZQByiCc+o6E/yRHYpH" +
       "zkCx2uvaynUdHbBMz6nrLFY+umP56Bksf2qvaws9jeWL59R1xvLpHcunz2D5" +
       "sb2unVN1fbPje+YBjI/fQAh6ZgfjmTNg/O29sp1c2T9/wPIXbiDGvGfH8j1n" +
       "sHxpr2wHPY3lL51T2Vn3Le1Yls5g+ct7ZU9PDyNTkPIcuvbL59R25s7QDgd0" +
       "Bo6/v9f2NNf2Zw5Yfu6c2n4YXPCOJXwGy3+41/YUPY3lP7oBbVd3LKtnsPzC" +
       "Xtv6qdq+VffBpOBwfP3VG1D3B3ZAPnAGkC/t1a3n6v7iAcsv34C6n9uxfO4M" +
       "lr+xV7eOnsbyn55T3ZmUjR3Lxhksf3On7pvU00PJbarvO4biHUD5rXMq/HFw" +
       "NXdQmmdA+Vc7hQMoucZ/+4Dna+fUeBavyR1P8gye/3anccATPY3n755T5dkQ" +
       "xe54smfw/Nrew7XTg7c2Uw7HkH9/TnVnztbbweidAeMbe//Wcm1//YDlN8+p" +
       "7Wy86O9Y9s9g+Ud7/9bQ01h+6w1ZbjV1AWSUtyBXylfyWv/l9Kzzpuz2vSBg" +
       "xPnCchanLU9xQAb6oO1oT+3nBiMjisE08SnbIfIW3pEU7slnuNmE7Mp2YfYA" +
       "JvmmYYK5xl1HjTFgUH7uF//bp77+S0++DuYNncIty2wKCCYYxzj2Ftmy9996" +
       "9dOP3vHyn/5ivhwCMujeS898q5K1+t8z8sdJ4eFMBt5fRJrBKHHC5osYhp6J" +
       "kZU4HBbfPOTk3v/YwuJ2bf/XhZWG3NTwulzUUYjoReW2KjN2G681GUwh28NZ" +
       "w4xIob5JECGuBX0qSpbEmI+UYRvxLH1eZxoi1XHHHZOH5o1gtml3xLrPz+Iu" +
       "lLSHnToc9qGWwpsjpp80G3rNovVJL+BngcBXUbyzgIMg0cswvOHKI1wtYuhy" +
       "aRRL1VmVqEPlYU2E3OZqPp9Fc3EqKrYE+VN0OlmH0KhBrGlPkTyzvGJKvLSc" +
       "1ryKXCVKs87ULk4YeqaToigaZGs5F1lRKW88mMb51HDLXZO3+wuE73UQweiG" +
       "gqsjcWXVjDimKUoKnbi4kMZ2l1eaNScUaYIYopLNCzSEDnlERTpIxaf8yDfr" +
       "kU8JUpEJJd5o+7FtlrtxUm2M0ajTkWm7mygdwhcX4czVwqWdoiW44XipgMzT" +
       "oD93eR1ajSaQMxGEoYJCTg0NRbNCDOvBsJc0lvPlehHP5xIi6b3WSDVXExOq" +
       "1VgldtWwJvvr8ly0VYEBhtHLoTPAhvRAsHCWmAuzUQO39IRrsSTLpaof9JGY" +
       "heJyl0cWKZ4205Xs1jmhWEmmzISjmKGw9kllgDawAU02fQ4yoU7QIWcjVZr7" +
       "Q5KrBmRo9RieDs2210jiMeHhfDRwm2Sot0bDQG5symzXGTrj9QSqi+uGSoci" +
       "jIgw4+Bjn2baxbBiMq1us00nycCZAHvilabZlRpmnW3KbCWZRHM1VeYk07fx" +
       "+iSlJ8nCrEWDpDFrVHkS5xy+mLJkU3SRscXOOiZGwuPWhm9Tg2TOkuRImkSt" +
       "ALGimdRHK6seZNI63FxG1aG79KlxR1wNEEhz5pONLJFEDUo1mrcnJaFbLhZV" +
       "YgHJXFtoIbW+OMLJUqdvQoO05VdFSKDE7tStQUxnqS4nRsSpjCqsZqK3WPtq" +
       "T8BTCfacynqqTQewXyFEqjUqm3jsR6vSqohxXmtSkjhXH7bitj9yh70B1xU6" +
       "tG7ArlFUxnFANmchtkGtdapGOFDlkkhwHOYgp1MU9YBqyDPFJtWG3XXK/W67" +
       "04alkehboQfBNbmrUHzYQarEukNpvUrC94YYMov7Ciws28u5ZcADPy5V60Op" +
       "5deckdhYGg2qA2bomCzMQO8SGjRJsRS1XtTCNjueFqtyx4xr5aa2qq44WgP2" +
       "B15RHImDVrtbi8ujdo8Qxk1/uKB1knbqZbhFI3yZNuiQngr4ABZ4z8cCE6sk" +
       "DisKo5qMc1Um7MeYRtszhzbbk4gPuwIvKh6jm0Fl6oxBxLFxTO0OJFpdTUWz" +
       "Gsg9SeYYy0cIutStKYiItcoCFUY93VehKqb0pHEdNVsEjGrTCqEPK3plyJYW" +
       "LXoAIavhsjEP2vWlYK6jTa82GK74GJKKZIDVEVYsts1hSeuhRKtYnceTprdg" +
       "0wHGtJupWQH+a3PkzA3WRUeJplYUl0pTZoXO4PUqXvBmNIZhYqb4Sxbpd5xN" +
       "wy7VNjhKcUJ1NSE1YdPGBiRf65Zn6ZgZ8GNaw9J5sMY2g7DdJeO0UV9L8MD0" +
       "MCuOrEAOB2W/4rXshtD1u1bFpXWFxPu1xlrqRfgqoVueV8VQq+3GwxHSRTYb" +
       "fBiGNIRrjtpwEz50KtymOZUhjmvZZcJEWadbp8dlv+5G9biOa3RvXrd1AkFw" +
       "HRFaRbi0xisrLkXdxDUHjjlPe6xAdvvNVXtp9qEkhASzuyG1doNKB4G0pEpd" +
       "PFC9UslazsMmaNqQmhuuRSyaKccykcUSdhUte6kb02mRIyG8UZkP4MGKhK1J" +
       "WQ4tHzXtmaXj63EpaURouzJrzdss5A8Tpy2OVtbAFjhJZsXxYixJEVaFGkzH" +
       "hxao6PaxejIWmjW0VJLcKQNPYKy8rtbcaL2JbW604oNlnUuXm2ndit1gRomB" +
       "3UwIset4kl+0A29B2X1F5AbIuGt3iarQHWLVeoVDVkStsmGAZVd+vTuZzCla" +
       "TcyQQafL3prQsiVZYcqLAeUOEliL0xFf88bdnkS3Oyrl0AuZblq0Vab9Ceeb" +
       "jInMINdwg4i1/YXFmjRSilrLRdnSaQ5hEIru6QIt98rlVtrqF1eQ7zH2eOjB" +
       "XknvribodLoe8YQY+11+PBmiSXHFtlq4xy+XpqjgpZVKb3S61Wvp41nVUhWZ" +
       "G8p4QhRnFUYvismQ63rOBlUNgyxzAA+yghM6TvlG3JxwRqdcVW3SLTPDMh7Y" +
       "rm258HgCpZ1NY7KyCTldJulsaI3aa2QYIdGwSEjqvF1cu+ZmFEwWMiWxq41c" +
       "h5d4RYcws4iwYR8VhBAbwKbQlPzyxm2suN4sjJxisS8LROgY6rpaXMGWErgz" +
       "Ey6XKmmp5S0neGUx7vQarIj3lTWEqz2uL3rygLLaUVN3izOhMpW5UrOByI16" +
       "ZdL1mUiX2wRrhdUW1ZAkNDI10FuRUaeklVjLA6Ox1W+gHXHiLVCP45I1ihIC" +
       "abkE7asyn3SLcGK3paQ9wCRubC9WHm/HdUWfbooVrKpzRGSrA0rSl6QQmXW0" +
       "DaGlMh4j0lSu4pw2CtubCuYnfWxZiqw1NlM6G4QW+pVSaaGUoUhH5YG9kWda" +
       "YM+jhmPZScMSVXlCSk09wvhwLZPYxOdNEMsMj+ylus92xwhT1y1ImpMmKRmQ" +
       "NOqgnWG6EachxvuIoHrjFT5cVoS6400Gfhium6sqOYgR096ElRVLd3EknJsl" +
       "Tnc7xYnKVnTNmlcjRihCU6dntpYCKXd5f+WM+1WUrxU5Jm0PiW6rRXdmqt0d" +
       "UhvHlE2GiuOa05uE9Yq8MBAUVUD+UqP67dA1yBGL8i2rvArmUdRbyTwxMtk2" +
       "1ZYxnWYHPUTqGBSsgXDr035l0Ecbg3l9wMyxTQ0deU6wsTajgQBTA07sFdF+" +
       "Xabx5pCbNzt2tYPFtCg7gtBLhSVXK46bVNivW9VemQXtdOYOrQ0UQ/W7E8Js" +
       "ReOxM1yxEx+ArDpr3moo9qTekxe1ou9RYdN0/ZJrMTUYJCx8qg2EBOHVATLE" +
       "a1NkMuxGvs9NasOwzdkW68uuUltUzCYckVJQ7rYhFq6VNniHb9JanYSFWq06" +
       "YKhQnsWD3pKm1I2rekFEMHOKQjfijAuCId6WiTHWVGOMm7SHIWWs8dagK3ch" +
       "N6TGnEhXx157onv0mC4nJtan4LnpRPOaEWKp5PgCiTGDTSdejmBmMRkOBGzd" +
       "hLsJG1I6Pmih07Ytz9tLObHJepWt4TwCpQN1WDa5aNJuqk57M9iIHdjlrSAI" +
       "exANG1izvGkMYGYzQyoNqLuOOu0+P7fjTtXjjYRddDF0NSKWtaky7YRQDY7l" +
       "qDxOByU+YWSPNAihPtRlyywm0ZzKkkIWqESdx5YmO0Os2AktDe4GJa7iTIoE" +
       "6Y5amzYnaFqtvOx5PB+WU9EXyqTP+R02louS7nF+lzU7I8eSvH6PQ92wXubE" +
       "+kgiiG5/7fVIatijnUZM2AwyDQO8Y0oq2ncMGfHGiReVIhuxfR1rRjKxsehK" +
       "otaHsKQ6ZEAtAqW/CG0a5T1luuCMjTrqd2Klt8AFsVcqlrucnhaxMVTZcG1C" +
       "wFF/Kghmy2jJMsiXJGZGKC476hTn61aJxRbTarCpcCMnLs2nxZ7n9QzWXy9H" +
       "EyZyhlKpMxl0nW5VMcOOUSrSeEdv4FpnDm1stz+AmvEkNaMNiUmxAaGIAZXi" +
       "KpgT4ZGmsHEyr69bY4VgYtJajVl0zZZbS8Rp1ih1wtNi3CPoeLQKy8Jq1Fpv" +
       "6JTAJmst0AbtVa/GUMVOucPaKeypYZzORsCBA4eJKcufgymFXGsqKVdpNjRY" +
       "nw+XvQ0/pW3SilHM8Tduz3OqSSg0lwyEWIsmFvel+rIRJOsk2bQ4pMkINBEt" +
       "W1Rvqa2VsIeVEhEpR6yOag45WdIENa4Xy4wEF1EsUb2NIy576QgtioMRTlCt" +
       "tQSBWdh61msHNrkBroKIYGYxdVeRN0Q7o5Us9UIiCDtKjIN5YVNV5isiFngF" +
       "wRyKosbj1Fb7kRomi1FLT+RawsISqna7YQWehTgxVsRKadHrhkQLWxA61QFt" +
       "ydpimRiExehdp4eGa8XvlyuwKpWjWJ4S0MJeiPzKmJFWq5jSKLZU3aFHYSDl" +
       "tEPVVFlY0VqGISg6Vp1pYl2rI+WOBvCVPdfpMylKWnWp0jC7IN2QqXbFLaq6" +
       "BpsavGxFEs9TTbY0H5qqZrWRMfAMlsfGMkSkRGMcy21ubXfK48E43jTTJp3N" +
       "HpazRdIXMZMQV1G9HC2K7tJLdRuLZxteIhdzNsVcziN6/DRI3JBn6Em1jtpY" +
       "KZZ1KU0qKIYFUblMaDaY19qEphlzMN7gqo7MN9p4Yy7snrxcVqS0aduEUWxP" +
       "1WG6LK29NmqZIkh8HQbWSDEcK0jP1foecCDf25TbE8Vx2lVCmK0htOPaZZGo" +
       "C2wYT1q1CJsJZm3QnzhjrDGKRkYHF2NsxQq0E7JDMqXE8UxA1UmtKvsKhPqq" +
       "heszOBVmddaZjVzWqBPLXtccV7GxDgnkOtARWaQd1yx1W0Nr0S1Dsryk++GG" +
       "tWVm4VI4mY4oAw8pQsUHouSMwTQhYvH2AmH1BF3pvr5eb5qlEiGS7qzT4KJ2" +
       "cWJHRaYF5iPjCdcZj5OgD9IKQW0HDuUsweA7WlurKbYqs9HIxUpGRazrbn0A" +
       "ElYtcdHxes2bKDRj2KKLBusaZhBcueusing1xTf4AJKqiu4wdTetKBAB9wYE" +
       "zGrSaKaIm9WkXEWrI6IEFasI1ak0x720Ng1AmtI0iNjtmPxI89imjgTsTK71" +
       "YashUcugA+xR7IFA64bSSOLLVSyFmIGKhdMpNMLxiigvGwIWEnybNGeSGXd1" +
       "Y8Y2Zq1AtTdeW0dWCgWDPjGmrTXHVeT5YAJBC44tojV3FCjtitCTo7gFQhre" +
       "74thtzv2Ssyo5QqkOKrFdFfHazBUXVtdZ9ps9GqQMStOkJnnTUorktAkLCrx" +
       "lVqt9qEPZUs8392tH92br4FdPam4Wzb69jmWjfJN+Cfz9w/tTm/EUeHRsw4U" +
       "5tvqn//4y6/o/S/AF3fnXFpJ4fbED37UMZaGc6ypO0FL7zv7zAGbn6c8On/x" +
       "ex//nw8LH569cI6zYY8f4Dxs8p+wr36N");
    public static final String jlc$ClassType$jl5$1 =
      ("fkb7OxcLN109jXHNSc+TlZ47eQbjcmQki8gTTpzEePTkanG203PHbs30jsM1" +
       "09wWR0o+Oil009H6ZvOI5OX/6qDo8UNFP8zIX2Yr+uskO5dy6trz0rf0I/v/" +
       "4I2WDY/zyB/8xUkBscJ2P7Ow/z2vgC8ckaz8hctnC3jhzozclhQu5fv6QMLs" +
       "/wtXpblw6a1K8/7CdiOjsP89rzTREckRv/060jyQkXuTwq2Wd4os971VWTLX" +
       "e3Yny7M3IsvPH5Ec72PXkeWJjLwTuF62L32tMI/8dbgZuhMGvRFhPnNEcsDv" +
       "vY4w78vI08DN8n3fa6V55q1KUwHXh3bSfOhGpPniEckRI9eRBstICcTh7b7q" +
       "teJAb1Wcj4CL3olD34g4v31EcsgfvI442bm1C9WkcHm3bXmtPB/46+g5g508" +
       "gxuR5+tHJMdMXUeeVkYaoOdkm4LXCtN8q8JkO/LSThjpTQtzHCF3nXfDjLDA" +
       "uYxwoTiCER/if8NTaG+EP9tvVXf41RvCP7nOux/PiAS0Hy/cU9DLbxV9tme6" +
       "3qFf3xB6/TrvphlRQKDK+sIp8NW3AD8v9l5w/dwO/s+dCv/0rdmL2e17M/J6" +
       "eoD/wtUPBJ4+O//Lj1lvj5C+8qtP/oefeeXJ/5qfVL5kxSMlqkXmKV+2HKvz" +
       "vVdf/8433/bol/Mz+TerSrxN0A4/Cbr2i58TH/LkAt55VSHZjnvhiTdSyLeD" +
       "INgaJ7iO4ZYZmYNszFUs78BoznmMloI2ssOt2TH1h675IGv7EZH25VfuvvTg" +
       "K+K3turYf+hzOwMGuIXjHD9VfOz+1iAyplYu0+3bM8ZBDv3FbMM9/zAk+++n" +
       "g1P21LeHnNMLJ6cOewELb5jZHJttPHnCR/IP0/b5/GL7adrz2lde6fR+8vvl" +
       "L+STg1s0R9lsslYuAXNuP6q4Oid415mt7du6tfXsD+769duf3s9f7toCPnL1" +
       "Y9geP/2DA9INkvwTgc1vPfjPPviPX/mTfIv//wNhAJwmMTgAAA==");
}
class PandaMode {
    public static final int HIGH_MODE = 7;
    public static final int LOW_MODE = 5;
    public static final int MID_MODE = 6;
    public static final int WILDCARD_MODE =
      PANDA_Modes.
        WILDCARD_MODE;
    public static final int __MODE = 2;
    public static final int DYNAMIC_MODE =
      PANDA_Modes.
        DYNAMIC_MODE;
    public PandaMode() { super(); }
    public static final String jlc$CompilerVersion$jl5 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl5 =
      1436903987000L;
    public static final String jlc$ClassType$jl5 =
      ("H4sIAAAAAAAAAMVYb2wcxRWfW9vnPzjxxQlxmibOxXEQTsJdiRoodSiYs40v" +
       "nO1TnARxNDnGu3PnTfZ2N7tz9sWpW4JUHLVShFSHhopa/RDUCgWCqkYtqqiM" +
       "EJSKfqFCRVQqIL4UiUYiHwpV05a+mbn967vEJB9q6cZzM++9eX9/8+YuXEZN" +
       "toV2moZ2oqgZNEEqNHFU25OgJ0xiJ/Zl9mSxZRMlpWHbPgBrebnnpY7Prj41" +
       "FZNQNIfWYl03KKaqodv7iW1o00TJoA5vdUgjJZuiWOYonsbJMlW1ZEa1aX8G" +
       "3eJjpag346iQBBWSoEKSq5Ac8KiAaRXRy6UU48A6tY+j76JIBkVNmalH0dag" +
       "EBNbuFQVk+UWgIQW9v0QGMWZKxaKu7YLm5cZfHZncuHHR2K/bEAdOdSh6hNM" +
       "HRmUoHBIDrWXSGmSWPaAohAlh9bohCgTxFKxps5yvXOo01aLOqZli7hOYotl" +
       "k1j8TM9z7TKzzSrL1LBc8woq0RTnW1NBw0Wwdb1nq7BwmK2DgW0qKGYVsEwc" +
       "lsZjqq5QtCXM4drY+xAQAGtzidApwz2qUcewgDpF7DSsF5MT1FL1IpA2GWU4" +
       "haKNdYUyX5tYPoaLJE/RhjBdVmwBVSt3BGOh6NYwGZcEUdoYipIvPpfH9p45" +
       "qY/oEtdZIbLG9G8Bpu4Q035SIBbRZSIY23dknsbrXzktIQTEt4aIBc2vv3Pl" +
       "/l3dS28Kmq/WoBmfPEpkmpfPT65+e1Oq754GpkaLadgqC37Acp7+2epOf8WE" +
       "ylvvSmSbCWdzaf8bjzz+PPlEQm1pFJUNrVyCPFojGyVT1Yj1INGJhSlR0qiV" +
       "6EqK76dRM8wzqk7E6nihYBOaRo0aX4oa/Du4qAAimIuaYa7qBcOZm5hO8XnF" +
       "RNW/LvhEqnP+n0ZyqaoScVeLb8aPl7GtHi8blNzOazGexbqCRw2FxE/GHzXL" +
       "k5oqx21eOPGCqmMtDlkaH0k/OJIfHR8cit8bv3tXvB5ZZvxhh2pPfarR9KBD" +
       "dVd9qofTmcHUwH6XNDswNjiQZ4raJ3Fpci4RIKgvJ+8I2F2fZvCRsYHRdKru" +
       "Uf79w6PxuT7TrDD/x2YiEUiNTWFg0qCmRwxNIVZeXig/MHTlxfxbkluo1cgB" +
       "1tpqydRIwg0BikS4wHWsjkWeQZYcA7wBJG7vmzi877HTPQ2Q4OZMI4szkCbr" +
       "XwgpD6HSHIdlKBO0dG7m1KHvfU1CUhDZmVaw1MbYswyPXdztDVd0Lbkd8x9/" +
       "dvHpOcOr7cBVUYWc5ZwMMnrC/rMMmSgAwp74HXF8Kf/KXK+EGgGHAHsphloB" +
       "WOsOnxGAjn4HhpktTWBwwbBKWGNbDna20SnLmPFWeGBXs6FTxJjFI6QgR/Dh" +
       "l5eeufSTnfdIfrDv8F2fE4QK6FjjhfOARQis//Vc9kdnL88/ymMJFNtqHdDL" +
       "RlbDEA3w2PffPP7eB++ff0dy448qwHqbJxzQRQOEY9HsPaiXDEUtqHhSIyx7" +
       "/t2x/c5Lfz8TE/HRYMUJ767rC/DWv/IAevytI593czERmd1unsEembB7rSd5" +
       "wLLwCaZH5dSfNj/ze/xTAF8APFudJQLDqgYxpXbyQNzGxx2hvTvY0G37kzKY" +
       "974uJC8/9c6nqw59+rsrXNtgG+MP1Cg2+0XY+Zlr4LAYqg4BTGW7a6H24fyu" +
       "cImOYHsKBH19aezbMW3pKhyZgyNluNztcQuQoBLIgyp1U/NfXn1t/WNvNyBp" +
       "GLVpBlaGMa8O1AppSewpAJGKed/9QoeZFkezClrmnQpf6eLfoqBgX31gGGZt" +
       "ildbG/41rk0+8dE/l3mJQ0KN2znEn0teeHZj6lufcH6vNhn35spydISWzuPd" +
       "/XzpH1JP9HUJNedQTK72i4ewVmZlkoMeyXaaSOgpA/vBfkdc7v0u9mwK44Lv" +
       "2DAqeKgMc0bN5m2hjFhX+5ZFw9e5ZVd0pfazG4VDv8gRZlIiDe1hkVidH/3s" +
       "/Oen5r8hsYppmmamg1djHt1YmbW1T144u/mWhQ9/yOEBNGtmiu/l6m/hYw8b" +
       "tvP8aKDQinO9YCI0o+ASpluFolZXtWunUdZSS9ADTVebtORc5wfHnv34BdGA" +
       "hXMmRExOL/zgi8SZBcnX9m5b1nn6eUTry4OySgTlC/iLwOe/7MOCwRZEUDqX" +
       "B0WU7dZrqcWPGP7bxbnf/mJuXpjRGez6huBR88Kf//PHxLkP/1DjQm+AwIo7" +
       "g40JNtwr6nJ33Rq++7oZNnSDGebvxpwEY2fdB1Kb2ORAnexg00E2DLFhGFKi" +
       "xZHFFh4KmXjw/2eiv5UMmxhlk8MrN9GRVcvEIzdvYvEGTfxyfbBwAjKZNoUV" +
       "274qIKOWA4o374DUDTrAa+DDEZbYRF+xldF8XfOMmzeP3KB5X+bt4Y/uzIrt" +
       "bveLqGV95RrWc8Dd6usteNO5ud4jn0Po+ScWFpXx5+6Uqk3bONwp1DDv0Mg0" +
       "0QKiYMN9/LCOccOyn3jEzxLyi4sdLV2LB9/l/b/700ErvN8LZU3z39++edS0" +
       "SEHlKrSK21w8255ktx5/erFv8yZXaR3136minahEgoa7Huu8Xr74fLUtcIPy" +
       "n7qq1o2WxY9defni4r6xk1fueo6/yprgYT47y6S0ZFCzeLG4j7GtdaU5sqIj" +
       "fVdXv9S63fF+4C0T0m1L7d5/qGRS3q3P/qbrV3t/vvg+byr+Bx4vXm2DFAAA");
}
