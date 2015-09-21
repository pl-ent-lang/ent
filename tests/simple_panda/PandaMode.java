package simple_panda;
import panda.runtime.*;
public class PandaMode {
    public static final int __MODE = -3;
    public static final int DYNAMIC_MODE = PANDA_Modes.DYNAMIC_MODE;
    public static final int WILDCARD_MODE = PANDA_Modes.WILDCARD_MODE;
    public static final int LOW_MODE = 0;
    public static final int MID_MODE = 1;
    public static final int HIGH_MODE = 2;
    public static final int VERYHIGH_MODE = 3;
    public static final String[] MODE_NAMES = new String[] { "low", "mid",
    "high",
    "veryHigh" };
    public PandaMode() { super(); }
    public static final String jlc$CompilerVersion$jl5 = "2.7.0";
    public static final long jlc$SourceLastModified$jl5 = 0L;
    public static final String jlc$ClassType$jl5 = ("H4sIAAAAAAAAAMVYfWwUxxWfO3+dHRsbQzCl2By2IXUCd5CUqo2TBnO28dGz" +
                                                    "fbLBEUecy3h37rywt7vs7tmHqQtESiGthCLVpFRK3PxBkioiEFWJWqlK5apq" +
                                                    "mypVpKCoX1JDmn+aiiLBH02q0jR9M3N7+3F3Jmf+6Ek3Nzfz5s37mt97Mxev" +
                                                    "oxpDR/dpqnwsLatmiOTM0GF5V8g8phEjtC+2K451g4gRGRvGfhhLCp2vNX98" +
                                                    "65npFj+qTaA1WFFUE5uSqhhjxFDlGSLGULM9OiCTjGGilthhPIPDWVOSwzHJ" +
                                                    "MHtj6C7HUhN1xywRwiBCGEQIMxHCfTYVLGoiSjYToSuwYhpH0beQL4ZqNYGK" +
                                                    "Z6LNbiYa1nEmzybONAAOAfp/ApRii3M6ChZ05zoXKXzuvvDC9x9v+XEVak6g" +
                                                    "ZkkZp+IIIIQJmyRQY4Zkpohu9IkiERNotUKIOE50CcvSHJM7gVoNKa1gM6uT" +
                                                    "gpHoYFYjOtvTtlyjQHXTs4Kp6gX1UhKRRetfTUrGadB1na0r13CQjoOCDRII" +
                                                    "pqewQKwl1UckRTTRJu+Kgo7d3wACWFqXIea0WtiqWsEwgFq572SspMPjpi4p" +
                                                    "aSCtUbOwi4k2lGVKba1h4QhOk6SJ1nvp4nwKqOqZIegSE93tJWOcwEsbPF5y" +
                                                    "+Of6yENnjytDip/JLBJBpvIHYFGHZ9EYSRGdKALhCxvvjT2L1715xo8QEN/t" +
                                                    "IeY0P/nmzd3bOpbe4jRfLEEzOnWYCGZSuDC16t2NkZ6vVVExAppqSNT5Ls1Z" +
                                                    "+MfzM705DU7eugJHOhmyJpfGfn3w5Cvkmh81RFGtoMrZDMTRakHNaJJM9L1E" +
                                                    "ITo2iRhF9UQRI2w+iuqgH5MUwkdHUymDmFFULbOhWpX9BxOlgAU1UR30JSWl" +
                                                    "Wn0Nm9Osn9MQQnXwRR3w9SH+Yb+m70okL0SwIMWDwaNZbEhHs6pJvqRlp2RJ" +
                                                    "CLIjGYxjRcTDqkiCx4OH8jMGOz/BlKRgOQjBGkwmh0f7B4IPB7c/sC1Yjqj/" +
                                                    "4EjfcDRikcb7Rvr7kpSzcRxnpuZDzvnyXB6NxvojfWP9Zdm4CMrziY0+arHY" +
                                                    "UZ5qOFrYaGd5qqHo3iGL7P7yZBMDYwedpGVsxU8oU+bQZJDSJsEwA+OwQiGz" +
                                                    "rulQKDQ5ORyc79G0HPV7y6zPByG50QuIMmDJkCqLRE8KC9k9AzcvJd/2FwAi" +
                                                    "HzGASIaU0WSS1KjPQwXPI5+PsV1LUYRHOcToEUA7yAONPeOT+54401kFx0ub" +
                                                    "raZRBqTh8ukoYuNjlGUBAQ4pWjo/e2rixA4/8rvzCpUNhhro8jjNBgXU7/bi" +
                                                    "SSm+zac/+vjys/OqjSyuRJUHvOKVFLA6vVbUVYGIkAJs9vcG8RvJN+e7/aga" +
                                                    "UBCQ38RwUgFUO7x7uICr10oCVJcaUDil6hks0ykLuRvMaV2dtUeYe1fRppV7" +
                                                    "mvrDIyDLHw+Pa8//8Z2/P8AsaaWaZkfyHidmrwPeKLNmBmSrbffu1wkBur+c" +
                                                    "j3/v3PXTh5hvgaKr1IbdtKWIAt4BCz711tE/XX3/wnt+Ox5MyO8sznNMl9Wf" +
                                                    "wccH3//SL4UkOsCgCbUWQ5NGd95qywZQKQNc0+DoPqBkVFFKSXhKJjQY/9O8" +
                                                    "Zecb/zjbwt0tw4gVLdtuz8Ae/8IedPLtxz/pYGx8Ak3Vtv1sMo7/a2zOfbqO" +
                                                    "j1E5cqeutP/gN/h5yCSA3oY0RxggI2YPxBwYYrboYe12z9wO2nQYzhh3HyNH" +
                                                    "SZUUnnnvRtPEjZ/fZNK6azKn34ex1sujiHsBNmtD+caVIOjsGgAU2L/Ne+KH" +
                                                    "sDENjL68NPJYi7x0C7ZMwJYCVCrGqA7wknOFUZ66pu7Pv/jluiferUL+QdQg" +
                                                    "q1gcxOywoXqIcmJMAzLltEd2cxlmA9C0MFugIuvwCGpj/wIgYE95nBmkNZd9" +
                                                    "VNf/e1SeevLDfxVZiSFMiVLDsz4RvvjchsjXr7H19lGnq9tzxZAL9am99v5X" +
                                                    "Mv/0d9b+yo/qEqhFyBe/E1jO0lOWgILPsCpiKJBd8+7ijVcqvQUo2+iFGce2" +
                                                    "XpCxoR76lJr2GzwRsbZkRJio//OVDMsVBr00SbE8wiOEKhSKQqWbJnrrhy9c" +
                                                    "+OTU6a/66XmpmaGKg01bbLqRLK3Qv33xXPtdCx98l2ELYManVOxHmPCbWNtJ" +
                                                    "my0sOqpodytAD5fJBGNQqZi+98Awl2z5EIrrUgZAciZfbYbnW68eee6jV3kl" +
                                                    "6Y0XDzE5s/Cdz0JnF/yO+r2rqIR2ruE1PBOwiUlJT+Dm5XZhKwb/dnn+Zz+a" +
                                                    "P82lanVXowNw2Xr195/+LnT+g9+WSPhV4COeTWi7kza7+RHbVfY4PnjbYCEr" +
                                                    "DJZKCkQeTUijsuxfLgJos5c2Q5brG5186NiwxwQH7twE6RWaoLLq1mmExyoz" +
                                                    "QpOLUSkrTN65FQZWaAVnbW7BBt1rj5WexMqUDVgMS+lJ/n96Om8XXj19tJOp" +
                                                    "UE+LYSk9lTvXc3CFerruR15F/bQzU5mi9QWOpTSdvXNN961Q06IrnldbptGJ" +
                                                    "Cs+qi2spjU9WqPGGYo2TK9CYX0U/zyXViVRnKtO+wWYO+XBL+WzNqm+efBdf" +
                                                    "6nrnxGLXX1l9GpAMKIv69HSJRyjHmhsXr1670tR+id3lqqewwQsk7+td8eOc" +
                                                    "682NSd1Im6e5T9aazjqG20XTNOTx4NPLeDBX2mAMIbbmCp5ln1qvZ+ksVPOO" +
                                                    "uhnRuqK93GscqykuPLmwKI6+uNOfv5CMwpEzVW27TGaI7GIFE4V3AnobWl/0" +
                                                    "FsvfD4VLi82BtsUDf+Dmtd746uEmmsrKsrM2dfRrNZ2kJCZCPa9U+TvHDyGL" +
                                                    "O98q6NgLWgl784I553OrX7B66+3OjcNiXa7IYy/TeR2Hs/xtOilcXtw3cvzm" +
                                                    "V17kl29BxnNzlEsAgoRf8QuvF5vLcrN41Q713Fr1Wv0Wyweuy79Htk2lb7cD" +
                                                    "Gc1k99G5n7a9/tDLi++zwvl/RSevoDIYAAA=");
}
