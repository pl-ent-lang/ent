package generic_test;
import java.util.ArrayList;
import java.util.List;
public class Socket<X> {
    private X f1;
    private int f2;
    public Socket(X f1, int f2) { super();
                                  this.f1 = f1;
                                  this.f2 = f2; }
    public X getF1() { return this.f1; }
    public void setF1(X f1) { this.f1 = f1; }
    public int getF2() { return this.f2; }
    public void setF2(int f2) { this.f2 = f2; }
    public void foo() { String s1 = "a";
                        int i1 = 1;
                        Socket<String> socket = new Socket<String>(s1, i1);
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1431363190000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAKVYa2wc1RW+Hr8dJ+tH4jxIHNuxEXFgt0lLJTACHGMTp+vY" +
                                                    "ip1ADGQznr27nnh2Zpi5a29MDCSiTdofgYKhgQZLVQMUGkiaNqqqKpWrPkhE" +
                                                    "+UGFilqpUPUPtDQq+VFaNS30nHvnteO1nTQrzd2Ze+859zy/c2ZOXSKltkVa" +
                                                    "TFlPylF2wKR2dADvB2TLpskuTbbtIZhNKBM/+NHfH3tv+gGJFMVJdcZIUpzf" +
                                                    "DdsYqYnvl8flWJapWiyu2qwjZ5FNpqEdSGsGi9Ici+7XbnW4b4/fOod3y5nI" +
                                                    "p1eeGq2RSNkwqZd13WAyUw3d3kltQxunyTiJ+LPdGs3YD5NHUZAlgc2MtMbd" +
                                                    "Q2NwaAwOjfFDY53+ro44WUr1bKYLKWSduZzKTAUFYqQ5n4kpW3LGYTPAZQYO" +
                                                    "FczRnRODtk2etq4NQyo+uyk2/a29NWeLSWSYRFR9EMVRQAgGhwyDQWlmhFp2" +
                                                    "ZzJJk8OkVqc0OUgtVdbUSS73MKmz1bQus6xFPbPgZNakFj/Tt1W1grpZWYUZ" +
                                                    "lqdeSqVa0n0qTWlyGnRt8HUVGvbgPChYpYJgVkpWqEtSMqbqSUbWhyk8HVu/" +
                                                    "AhuAtDxD2ajhHVWiyzBB6kSIaLKejg0yS9XTsLXUyMIpjKyZlyna2pSVMTlN" +
                                                    "E4ysCu8bEEuwq5IbAkkYWRHexjmBl9aEvBTwz6Uddxx7RN+m8/AuSVJFQ/kr" +
                                                    "gagxRLSTpqhFdYUKwur2+HNyw/mjEiGweUVos9jz44OX7765cfaC2HNDgT39" +
                                                    "I/upwhLKyZFl76zt2nhbMYpRYRq2is7P05xn2YCz0pEzIX0bPI64GHUXZ3f+" +
                                                    "es/jr9GPJVLVS8oUQ8tmII5qFSNjqhq17qU6tWRGk72kkurJLr7eS8rhPq7q" +
                                                    "VMz2p1I2Zb2kRONTZQZ/BhOlgAWaqALuVT1luPemzEb5fc4khJTDRarhqiDi" +
                                                    "x/8Z6YjtsiHcY7Ii66puxAYsAw2AHgXwiTFqw30aBVSVBD7FBg1ljLIoAJV5" +
                                                    "feQ5lK52oqgIDLc2nLYaRPw2Q0tSK6FMZ7d2X34j8ZbkhbGjFyP1Qd5RwZsU" +
                                                    "FXGeyzHQhSPAjGOQkICI1RsHH9q+72hLMUSAOVECNsCtsfkxsstP4V4OVArE" +
                                                    "EZk9PnFo92NfkIiUD30oWDFkLZIPIGB5wNQaDvlCfCNHPvr09HNThh/8eVjq" +
                                                    "5ORcSsyplrAJLUOhSUApn317k3wucX6qVSIlkKgATkyGYIK8bwyfkZdbHS5O" +
                                                    "oS5loHDKsDKyhksuuFSxUcuY8Ge4byP8vg5sXInBFnEi0I1EgqvLTRxXiFhA" +
                                                    "p4W04DjY85PZ58+9sOk2KQiZkUCtG6RMJGCt7/Mhi1KY/+PxgWeevXTkAe5w" +
                                                    "2LGh0AGtOHZBOoLLwKxfvfDw7z94/+S7kh8kDOpSdkRTlRzwuNE/BZJVg4BH" +
                                                    "37fu0qEaqylVHtEoxtp/Im2bz/3tWI3wpgYzbjDcvDgDf371VvL4W3v/2cjZ" +
                                                    "FClYLHzN/W3CAPU+507Lkg+gHLlDv133/Jvyi4BlgB+2Okk5JEhcMwmI2uYJ" +
                                                    "f6ex4PIklIMnPnv7L1PvXyzGGKjC4JQtwCwAxeh85T7IoHUI7u4BKginZYIa" +
                                                    "ig9Hfo7wcVLnzXqxy8gt8/HG/iUc4lgwNWOCWluNrJ5EtuuDiAGrWdMMrvK4" +
                                                    "W+rF6XIMyxVhlAzG6aPgvKuwlqeswwe5g9+Xce9g7Y12Q+sTXEQw64p3Dg4m" +
                                                    "hvYMdCd2d+7s7dwa7+Z5YcJi0f1cygZo8nwmolphSLYGG8c+aAoHsyM2G7DU" +
                                                    "DFSgcadEfl26/bvtpzZ1uOWvEInYeXzToV+eeWXmZQ5wFSOyzTmgAEsCTWen" +
                                                    "lQ6k+zJhxs/hVwTXZ3ihdjghik1dl1PxmrySZ5oo/cYFetR8FWJTdR+Mnfjo" +
                                                    "daFCuCUIbaZHp7/xefTYtBTomzbMaV2CNKJ3EurgcBdK17zQKZyi58PTUz/9" +
                                                    "3tQRIVVdfheAnn79d//9TfT4ny4WKGHFqtP7RhFnvJLUEPaOUOnChzdNLnn6" +
                                                    "ybfBMZDLlqyPAT/0h+AXEBz4FDNSMqqmR3N8PsZPaffOIvwswtd24NBkB8tU" +
                                                    "vicCjXtCeerdT5bu/uRnl6W5nX8QlftkU5gygkMzmnJluCZvk+1R2Pel2R0P" +
                                                    "1mizV4DjMHBUoN21+y2o/rk8THd2l5b/4ee/aNj3TjGRejDh5WSPzMshqYQ6" +
                                                    "RO1RaBxy5l13i+yawCSu4aqSOcrn+MxqDwoXiMQebNz9Yrrq3/3ayOE//2uO" +
                                                    "EXgPUCA4Q/TDsVMn1nTd+TGn94uxgKW5HRHgiU+75bXMP6SWsl9JpHyY1CjO" +
                                                    "G9RuWctiyRuGtwbbfa2Ct6y89fw3AAEgHV6zsTacHYFjw21AEFdLmAsPocpf" +
                                                    "5Vb+SgfrKsOIWkT4zYOcpIWPbTjc5BbectNSx2V8PSNSajMufVF0Czj24/CQ" +
                                                    "8OLOeT1+X748N7g37n8BeXh92ItHbuHEd4iswvGeAunaWChd/do59cR9ddpZ" +
                                                    "doDjaeHqF0CF/KLUhBK2LFSUuDlA1L49uZBl6AKW4XtbA/FPMEPXzfdqxoHu" +
                                                    "5OHpmWT/S5slBzd6GKScYd6i0XGqBVjht4z2+VOpj7+Z+vH85uG/rhm6c3Tf" +
                                                    "NfTT60Nyhlm+2nfq4r03Kk9LSO9E95x35nyijlCvAO1H1tKH8iK70XPLEuKY" +
                                                    "stZxS21Bt/hGLoy8kwusHcRhgpHSNGU9IvJ91+YWC/ogNz7B8vvx1UT0OsT9" +
                                                    "vyrZnZDnwvBdTyygwNdwOAQK2KgA39LplFX864b6NG6oSV+pw9eh1EqcXOso" +
                                                    "5ip47Q755gJrz+BwzHHI4riAj/tzvnZPXq/L2uBa52i37lpddpXi8rUXFzDC" +
                                                    "d3B4wXHqFnw46qv47etVsR6uZkfF5v/Lga8usPZ9HF6GpitlGCHJX7kWyaEW" +
                                                    "lYmvDfjStWrOR0fxoUx5YyZSsXJm13v8hdv7mFUFLXUqq2nB+hm4LzMtmlK5" +
                                                    "uFWimpr87ywj1cHPHTj3Q26Y23NF+TDuKVW/mFIB5N+Qh9f8c6uLrVnxwTWh" +
                                                    "nJ7ZvuORy19+iQN1KVSxyUnkAuWrXHwU8PC5eV5uLq+ybRuvLDtT2ebWkogQ" +
                                                    "mPu4MNwsBkXnvSX33RafVS/AeMqsJ1dTSe8XyfA/EC4Y9pcXAAA=");
}
