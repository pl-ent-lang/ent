package codegen_test;
import panda.runtime.*;
public class Code implements PANDA_Attributable {
    public int PANDA_attribute() { if (true) { return PandaMode.MODE_LOW;
                                   }
                                   else {
                                       return PandaMode.MODE_HIGH;
                                   } }
    public static void main(String[] args) { int i1 = 0;
                                             int i2 = 0;
                                             int i3 = 0;
                                             int i4 = 0;
                                             int i5 = 0;
                                             String s1 = "abc";
                                             PandaTypeTable.put(s1, PandaMode.MODE_WILDCARD);
                                             String s2 = "abc";
                                             PandaTypeTable.put(s2, PandaMode.MODE_LOW);
                                             String s3 = "abc";
                                             PandaTypeTable.put(s3, PandaMode.MODE_MID);
                                             String s4 = "abc";
                                             PandaTypeTable.put(s4, PandaMode.MODE_HIGH);
                                             String s5 = "abc";
                                             PandaTypeTable.put(s5, PandaMode.MODE_VERYHIGH);
                                             Code c1 = new Code();
                                             PandaTypeTable.put(c1, PandaMode.MODE_WILDCARD);
                                             Code c2 = new Code();
                                             PandaTypeTable.put(c2, PandaMode.MODE_LOW);
                                             Code c3 = new Code();
                                             PandaTypeTable.put(c3, PandaMode.MODE_MID);
                                             Code c4 = new Code();
                                             PandaTypeTable.put(c4, PandaMode.MODE_HIGH);
                                             Code c5 = new Code();
                                             PandaTypeTable.put(c5, PandaMode.MODE_VERYHIGH);
                                             Code c6 = new Code();
                                             PandaTypeTable.put(c6, PandaMode.MODE_DYN);
                                             Code c7 = PANDA_Snapshot.snapshot(c6, PandaMode.MODE_MID, PandaMode.MODE_HIGH);
                                             PandaTypeTable.put(c7, PandaMode.MODE_HIGH);
    }
    public Code() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1434390429000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAKVYbWwcxRmeW/v8FSdnO8SmDnH8lah24A5KaRU5ApLDjh0u" +
                                                    "ySlOIuq2ucztzvk23tud7M7Za1M3gIQS8SNFYGioqNUfoR8oH6giQBVRuapU" +
                                                    "QFSVQKiolSDtL6LSqMkf+JG29J2Z29u9vXNoVUs7tzvzzvv9PvOOz11DUcdG" +
                                                    "/RSbGo6zeUqceJq/p7HtEC1pYMc5CLMZ9ckdTXdt/MlHEwpSUqgdM2br2SIj" +
                                                    "E6bDsKkShjanBJOEYJLYGSYYSaHWgqURzu0wMGeoLXUMz+JEkelGIqU7bMS1" +
                                                    "0TZqGfPThsXixGXxY8Z9JZ32pO6r0qj/ldhnN5/OtymoYQqtx6ZpMcx0y3QO" +
                                                    "EMcyZomWQjF/dtQgBec4+j5Xf02AmKHBlCc0AUITINSzwacC7dcSs1hIWsIc" +
                                                    "5nFqoCpXiKG+SiYU27hQYpMWOgOHJlayXWwGa3vL1nqeD5n43LbE0g+PtP2y" +
                                                    "DsWmUEw3J7k6KijBQMgUOJQUssR2dmoa0aZQu0mINklsHRv6gtB7CnU4+rSJ" +
                                                    "WdEmZbfwySIltpDp+6pV5bbZRZVZdtm8nE4MzfuK5gw8DbZ2+rZKC8f4PBjY" +
                                                    "ooNidg6rxNtSP6ObmsiNyh1lGwcfBgLY2lggLG+VRdWbGCZQh0wRA5vTiUlI" +
                                                    "KHMaSKMWpJXNUPeqTLmvKVZn8DTJMHR7mC4tl4CqWTiCb2FoQ5hMcIIodYei" +
                                                    "FIjPtX07Tj9qjpsKioDOGlENrn8LbOoJbTpAcsQmUAdyY+tw6nnc+eYpBSEg" +
                                                    "3hAiljSvf+/Gg3f2rLwtaTbWoNmfPUZUllHPZte9d0dyaHsdV6OJWo7Og19h" +
                                                    "uaiydGllxKVQ9J1ljnwx7i2uHPjdtx57mXyqoJYJ1KBaRrEAedSuWgWqG8Te" +
                                                    "TUxiY0a0CdRMTC0p1idQI7yndJPI2f25nEPYBKo3xFSDJb7BRTlgwV3UDO+6" +
                                                    "mbO8d4pZXry7FCHUCA9qhSeK5J/4ZWh74pAD6Z7AKjZ100qkbYs7gEdUIA9x" +
                                                    "4F0FkJkmZoZ/JZLwEQdcov/PZpdr1jEXiYDT7giXrAHZPm4ZGrEz6lJx1+iN" +
                                                    "C5l3ZTrwFC7ZxLj/fM5xzhlFIoLjbTzFZQjAgTNQioCFrUOT391z9FR/HcSe" +
                                                    "ztWD9Zx0axVMJ/2a9YA2o7JPHrqspB76XKhRAboe8CRWh9maHNHKmbnHD5+4" +
                                                    "WwmjJ2dYD4XPt6c55pVFDIarphbf2Mmrn118ftHy66cCjktlXb2Tl2V/OBK2" +
                                                    "pRINgM5nP9yLL2XeXBxUuI7NgG8MQz4CdPSEZVSU54gHddyWRjA4Z9kFbPAl" +
                                                    "D59aWN625vwZkSJtfLhNZgsPbEhBgZJjv1p54dKPtm0XFnuAGguchJOEyfJs" +
                                                    "9/PioE0IzH90Jv3sc9dOflskBVAM1BIwyMckFCtEAzz25NvH/3Tl47MfKH4i" +
                                                    "MTi1illDV12eUr4UKGUDCoKHdfCQCWmj53ScNQjPx3/Gttxz6e+n22SgDJjx" +
                                                    "4nznlzPw57+yCz327pHPewSbiMqPEt9yn0w6YL3Peadt43muh/v4+5teeAv/" +
                                                    "GJAO0MXRF4gADCQsQ8L1d4uIbBNjIrT2NT70O8HsrCyAwJGfUZ/+4Praw9d/" +
                                                    "fUNoW9kzBCO2F9MRGX8hcz0I24hKQwWA8dUNlI+d3PVd4cIfx04emH19Zd93" +
                                                    "2oyVmyB2CsSqcJo6+20AGLciKUrU0cY//+a3nUffq0PKGGoxLKyNYVEqqBly" +
                                                    "lDh5wCaXPvCg1GOuCYY24Q9U5SHQaXPtaI4WKBP+X3ij69UdP1v+WKSTKzh0" +
                                                    "e7v5x4AYt/JhSGYbfx0OUdpo02pnqugHzj6xtKztf+keefJ1VJ5To9CGnf/j" +
                                                    "v34fP/OXd2oAbTOz6F0GmSVGQCYHz74q8NwrWg4fLp7ZvWU03d63uDpuDq+O" +
                                                    "m2Fmbz3xt+6D9+eP/g+QuTnklDDLX+w9987ureozCqoro2VVZ1W5aSToHhBq" +
                                                    "E2gFTW4Wn2kVadtbTluRGF+FJ1ZK21g4bSW21Yy0IiIN4OKIJtUNlWGkHIgt" +
                                                    "qztR1LnsfpZ/OvCHE8sDfxVV0KQ7EIad9nSNdiyw5/q5K5++v3bTBYH39Vns" +
                                                    "SCvDfWx1m1rRfQoj15Wd0s190HsrpwjSLrjQiOLhzWpcNquUUok76Vtg0iN8" +
                                                    "2MNQfQEOJ3DP0C2uQLZegB5tttREJhY7rsy8ePW8LJNwxxkiJqeWnvoifnpJ" +
                                                    "loxsyweqOuPgHtmaCx1j0htfwF8Enn/zhxvAJ2Rr1pEs9Ye95QaRUleU3S3U" +
                                                    "EiLGPrm4ePnniyeVkkN2gS9mLV1G414+PCxd/M3/CsX49w4XePAmi58jt1fd" +
                                                    "suTNQL2wHGvqWj70oUwXr3tfAy10rmgYgbIJllADtUlOF1LWSNSn4ifP+A3K" +
                                                    "7/H4nE5rpIZs2t1INXjeK/uHL7E1gKIDFaki7pwedBTlrTOjXlzes+/RG994" +
                                                    "SeBQFG6rCwvijgJJL9uaMvz0rcrN49UwPnRz3SvNW7xIVTQ8Qd1EDPhxEsTc" +
                                                    "qn8JZNQPf/DsfM9l+pqCosEGjW/vCzVhcLTNEXuXVTS1UlFHOUKXa7pIaXA5" +
                                                    "dCArXC3om9DaUhE31kQ2PtxPyymlSGATd6WAIXtLJ0NGPZ8cf7HrH5koZBC0" +
                                                    "IzY2ZyqTpdEpZstgG7xu+jrG+HDElY1ZHRgpxbvlaQWmC7oWnq6DDM/r0/nw" +
                                                    "fD1DTbPEnh+vsRZlKPIAFQYvurQGNPfUMhOQV7RxGbV7w9WXT1yeg3O5YQI1" +
                                                    "FU39eJFMaFATWe53LxTreCVhjn8ibUrZVh2/ioOpVvhKinOQAcUfoaLTq1nv" +
                                                    "HTRsKkBSZFhYOu/+B9laW/vlEgAA");
}
