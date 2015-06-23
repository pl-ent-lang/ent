package codegen_test;
import panda.runtime.*;
public class Code implements PANDA_Attributable {
    private int f1;
    public String f2;
    public int PANDA_attribute() { if (true) { return PandaMode.LOW_MODE;
                                   }
                                   else {
                                       return PandaMode.
                                                HIGH_MODE;
                                   } }
    public static void main(String[] args) { Code c1 = new Code();
                                             PANDA_ModeTable.put(c1, PandaMode.
                                                                       WILDCARD_MODE);
                                             Code c2 = new Code();
                                             PANDA_ModeTable.put(c2, PandaMode.
                                                                       LOW_MODE);
                                             Code c3 = new Code();
                                             PANDA_ModeTable.put(c3, PandaMode.
                                                                       MID_MODE);
                                             Code c4 = new Code();
                                             PANDA_ModeTable.put(c4, PandaMode.
                                                                       HIGH_MODE);
                                             Code c5 = new Code();
                                             PANDA_ModeTable.put(c5, PandaMode.
                                                                       VERYHIGH_MODE);
                                             Code c6 = new Code();
                                             PANDA_ModeTable.put(c6, PandaMode.
                                                                       DYNAMIC_MODE);
                                             Code c7 = PANDA_Snapshot.snapshot(
                                                                        c6,
                                                                        PandaMode.
                                                                          MID_MODE,
                                                                        PandaMode.
                                                                          HIGH_MODE);
                                             Code c8 =
                                               PANDA_Snapshot.
                                               snapshot(
                                                 c6,
                                                 PandaMode.
                                                   WILDCARD_MODE,
                                                 PandaMode.
                                                   WILDCARD_MODE);
                                             System.
                                               out.
                                               println(
                                                 "All good!");
    }
    public Code() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1435071009000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "FgAA");
    public PANDA_Attributable PANDA_copy() { Code PANDA_ld =
                                               new Code(
                                               );
                                             PANDA_ld.
                                               f1 =
                                               this.
                                                 f1;
                                             PANDA_ld.
                                               f2 =
                                               this.
                                                 f2;
                                             return PANDA_ld;
    }
    public static final String jlc$CompilerVersion$jl5 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl5 =
      1435071009000L;
    public static final String jlc$ClassType$jl5 =
      ("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");
}
class PandaMode {
    public static final int HIGH_MODE = 7;
    public static final int LOW_MODE = 5;
    public static final int MID_MODE = 6;
    public static final int WILDCARD_MODE = PANDA_Modes.
                                              WILDCARD_MODE;
    public static final int VERYHIGH_MODE = 8;
    public static final int __MODE = 2;
    public static final int DYNAMIC_MODE = PANDA_Modes.
                                             DYNAMIC_MODE;
    public PandaMode() { super(); }
    public static final String jlc$CompilerVersion$jl5 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl5 =
      1435071009000L;
    public static final String jlc$ClassType$jl5 =
      ("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");
}
