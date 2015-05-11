package java_test;

public class B<T> extends A<T> {
    
    public static void main() {
        B<String> b = new B<String>();
        String f1 = b.getF1();
    }
    
    public B() { super(); }
    
    public static final String jlc$CompilerVersion$jl5 = "2.6.1";
    public static final long jlc$SourceLastModified$jl5 = 1430938460000L;
    public static final String jlc$ClassType$jl5 =
      ("H4sIAAAAAAAAAK1Ye2wURRifu75L6V1bXvIoBQrK6w5USEh9tbWF4hUarjSx" +
       "osd0d65d2NtddufaAy0CiYGYgEargtH+hYkaEGNCjDGaGhMFlT8wRsVE8T8R" +
       "JZE/1D9Q9JuZvX21V5DYZOdmZ+b75nv+vm974goqsUy03NDV3f2qTmMkR2M7" +
       "1DUxutsgVmxjYk0XNi0it6rYsrphLSUtfDvyx7VnB6JhVNqL6rCm6RRTRdes" +
       "LcTS1UEiJ1DEXW1TScaiKJrYgQdxPEsVNZ5QLNqUQFM8pBQ1JvIixEGEOIgQ" +
       "5yLEm91TQDSVaNlMK6PAGrV2ob0olEClhsTEo2iBn4mBTZyx2XRxDYBDOXvv" +
       "AaU4cc5EDY7uQudxCr+wPD7y0mPRd4pQpBdFFC3JxJFACAqX9KKqDMn0EdNq" +
       "lmUi96IajRA5SUwFq8oeLncvqrWUfg3TrEkcI7HFrEFMfqdruSqJ6WZmJaqb" +
       "jnpphahy/q0kreJ+0HWGq6vQsJ2tg4KVCghmprFE8iTFOxVNpmh+kMLRsfEh" +
       "OACkZRlCB3TnqmINwwKqFb5TsdYfT1JT0frhaImehVsoml2QKbO1gaWduJ+k" +
       "KJoVPNcltuBUBTcEI6FoevAY5wRemh3wksc/Vzbdc+RxbYMW5jLLRFKZ/OVA" +
       "VB8g2kLSxCSaRARh1bLEi3jGB4fCCMHh6YHD4sy7T1x9YEX92BlxZs4EZzb3" +
       "7SASTUnH+6rPz21duq6IiVFu6JbCnO/TnId/l73TlDMg82Y4HNlmLL85tuWT" +
       "h/e9SX4Jo8oOVCrpajYDcVQj6RlDUYm5nmjExJTIHaiCaHIr3+9AZTBPKBoR" +
       "q5vTaYvQDlSs8qVSnb+DidLAgpmoDOaKltbzcwPTAT7PGQihMnhQFTxFSPzx" +
       "X4ruim+1INzjWMKaounxLlNnBmAe1WQcp8SCOYuYFJvGWxicGLdGlmPSRIdC" +
       "ITDU3GCaqhDhG3RVJmZKGsm2tF19K/V52AlbWw+Kpjg8Yy0oFOK8prE1YXAw" +
       "105IPICkqqXJRzduP7QQ1MwZQ8WgKzsaL4yMrW6qdnBAkiBe0NjRof09T64K" +
       "o7Af4phAsFTJyLsYMDkA1BgM7Yn4Rg5e+uPUi8O6G+Q+zLRzbzwly52FQdOZ" +
       "ukRkQCOX/bIGfDr1wXBjGBVDQgIIUQxBA/ldH7zDl0NNeTxiupSAwmndzGCV" +
       "beVBpJIOmPqQu8J9Ws2GWuFe5o+AgBzK2t8bO3b65eXrwl7Ui3jqSJJQkUM1" +
       "rju7TUJg/fujXc+/cOXgI9yXcGLRRBc0srEVMgq8ARZ76syuCxd/OP5V2PU/" +
       "hdKS7VMVKQc8lri3QL6pELvMrY1btYwuK2kF96mEhdFfkcWrT/96JCocpcJK" +
       "3s8rbszAXb+tBe37/LE/6zmbkMTw3tXcPSYMUOdybjZNvJvJkdv/5bxjn+JX" +
       "AY4AAixlD+FZjbhmiJt+JffIHXxcEdiLs2G+5Y1OfwJ46nJKevar36b2/Pbh" +
       "VS6tv7B7PdaJjSbhf35nDVw2H9mDD2XYbp3BxmnM9DOD+boBWwPA7O6xTdui" +
       "6tg1uLYXrpWg5FmbTUCEnC8o7NMlZd999PGM7eeLULgdVao6ltsxTxVUATFK" +
       "rAEAk5xx/wNCjqFyGKLcHmichXJ8ZZb9xl8a+LiIDUtE9LDp7YGTJppXqJDx" +
       "Inz8wMiovPm11aLc1PqLQxv0Pie//vuL2NEfz06AdBVUN1aqZJConjsZhi0r" +
       "jGGdvOC7OPDpgcuzu+8b2P4f4Gt+QKEgyzc6T5xdv0R6LoyKHOQa14r4iZq8" +
       "qsGlJoHeSWNGYiuVPITmOSHEnTQHnhI7hEqCISRwZkIvhbmXINEt3tXlJkmJ" +
       "jZPsJdjwIEXFGcBNMPnSSRpqU8lAjR+0m5D4cO3Fna9cOik8HuxYAofJoZGn" +
       "/4kdGQl72rpF4zorL41o7biMU4XF/oG/EDzX2cMUYAuitNe22v1Fg9NgGAYL" +
       "2gWTicWvaP/p1PD7rw8fDNsGuRdsMagropdcxYY2Ydu7by7BKAq1MGibNa47" +
       "Fx2l9NZopHzm6NZveMVyur4KaL3SWVX1RI83kkoNk6QVLmCFACKD/2yD7HH6" +
       "BLbwqDF53iSzfRb19KCHldFzn/0e2S+86Pc+/wyxSYN0F74tunMKbXyG51tx" +
       "H7a4pOVQ8Cx2kqKGwp80nJdw7RQnGf6nrq351ro2Tib8PN3XejWzJeKLpIlt" +
       "mpI6Mqnk6QsH1/IYjwwq0BATudv+aPPXQbd5a/J9yE1o9ZR06dThMwsu99Tx" +
       "Dl0YmIm1Gmoo+11jR2aIR2bYBs/FBQS2JeJlOyU98cr1cz8P/3C2CJUCXrFo" +
       "xCZ059D+xwp92HoZNHbD7EGgAuSrFtTwmcXjxI6HWmfV6d4oWlmIN4eZQJPH" +
       "Pg1VfYiYLXpWkxnb+gDMZg3Du8sjq8qJrAiy83VcpZY4zMq5vdDj3IS1HGVt" +
       "PqiWJ001L9jsK5MXOu8mBGJda6I5mUx1P9zVlupp3tLR3JJo4zFlwGaoOx9y" +
       "UZeJ+C4TwajehBbeYsGPRwVrNh3MBUp+LjS+A1glmtoboJynFVhUMGA7s+L/" +
       "FSnp1OjGTY9fXfsaB4gSCIE9e+yIKBO9tlOHFxTkludVumHpteq3KxbnMdrX" +
       "hecROOQ2L7lgNfTtsfGpfwG6NXSqQBIAAA==");
}
