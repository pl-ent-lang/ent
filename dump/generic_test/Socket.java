package generic_test;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
public class Socket {
    public void snapSendTest() { Socket t1 = new Socket();
                                 t1.snapSendTest(); }
    public Socket() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1434127565000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAKVXb2wcxRWfW/93jpx9SRzqJE7sOFHtwB2UAkKOgNjYicM5" +
                                                    "PsVJ1Lotl/HunG/jvd3N7px9MXULSFGifogQNSFUrVWqoP4REFQ1aitEZVSp" +
                                                    "gNIvVKhVKyX0G6htJPIFPqQtfW9m93Zv72xa1dKNd2ffvHl/fu83b165SZpc" +
                                                    "h/TZ1NRoip+xmZvK4nOWOi7TRgzqusdgNqee3d9697YfXh9XiJIhnZRzR58p" +
                                                    "cTZuupyaKuNkZ0YoSQsl6QNRgaEMiRctjaG2E6Cck47MKTpP0yWuG+mM7vKh" +
                                                    "skP22ZZxZtaweIqVeeqUcb9n0+HM/TUW9b2e+OT2s4UOhTRPk03UNC1OuW6Z" +
                                                    "7lHmWsY80zIkEcyOGqzonibfQvM3hIQ56c/4m6Zh0zRs6vsQSIH1dzCzVByx" +
                                                    "hDvc19Rsq2gQJ73VSmzq0KKnJitsBg2t3PNdLAZvd1W89SMfcfH5fenlF57o" +
                                                    "+HkDSUyThG5OoTkqGMFhk2kIKCvOMMc9oGlMmyadJmPaFHN0auiLwu5pknT1" +
                                                    "WZPyksMqYcHJks0csWcQq7iKvjkllVtOxb28zgzNf2vKG3QWfO0KfJUejuE8" +
                                                    "ONiug2FOnqrMX9I4p5uawEb1ioqP/Y+DACxtKTJesCpbNZoUJkhSQsSg5mx6" +
                                                    "CgBlzoJokwWwcjjpXlMpxtqm6hydZTlO7ozKZeUnkGoTgcAlnGyJiglNkKXu" +
                                                    "SJZC+bl5ZP+FJ81DpkJiYLPGVAPtb4dFPZFFR1meOQzqQC6MD2Yu0q43zyuE" +
                                                    "gPCWiLCU+eU3bz16V8/qO1JmWx2ZyZlTTOU59fLMxve2jww81IBmtNqWq2Py" +
                                                    "qzwXVZb1vgyVbSj6ropG/JjyP64e/d1Xn/oZ+7tC2sdJs2oZpSLgqFO1irZu" +
                                                    "MOcgM5lDOdPGSRsztRHxfZy0wHNGN5mcncznXcbHSaMhppot8Q4hyoMKDFEb" +
                                                    "POtm3vKfbcoL4rlsE0Ja4Efi8NtA5J/4z8lQ+rgLcE9TlZq6aaWzjoUBwIwK" +
                                                    "5mEuPM+igbqaw7f0lKXOMZ4CZrL/v+VltC65EItB4LZHy9YAxB+yDI05OXW5" +
                                                    "NDx667XcNQkJhLHnFyebwrpTUjeJxYTOzQh0mQgI4xwUJDBifGDqG4dPnu9r" +
                                                    "AATYC40QAxTdW0PWI0Hl+nSbU/mHj72hZB77VBhSRb0+/aTXJtu6GsnqpYWn" +
                                                    "T3z7HiXKoaiwEcofl2eR+Spb9Edrp57exLmPPrlycckKqqiKlL3irl2JxdkX" +
                                                    "zYVjqUwDugvUD+6iV3NvLvUraGMbsByngEogkJ7oHlVFOuQTHvrSAg7nLadI" +
                                                    "Dfzks1Q7LzjWQjAjQNKBw2aJF0xsxEDBlWO/Xn3x6vf2PSQ89mk1EToPpxiX" +
                                                    "RdoZ4OKYwxjMX7+U/e7zN899TYACJHbX26AfxxEoWcgGROzsO6f//MGNy+8r" +
                                                    "AZA4nF2lGUNXywipYBcoaAOKAtPaf9wE2Oh5nc4YDPH4z8See6/+40KHTJQB" +
                                                    "M36e7/p8BcH8F4bJU9ee+LRHqImpeKAEngdiMgCbAs0HHIeeQTvKT/9hx4tv" +
                                                    "0x8A3wHHuPoiE7RBhGdEhP4ekZF9YkxHvn0Jhz43jM7qAggd/Dn12fc/vuPE" +
                                                    "x7+5Jayt7hzCGZug9pDMv9hzE2y2g3hDFY3h1y02jl0Y+q3Rwj9E3QIo+/Lq" +
                                                    "ka93GKu3Ydtp2FaFM9WddIBiylWg8KSbWv7y1m+7Tr7XQJQx0m5YVBujolRI" +
                                                    "G2CUuQVgp7L9yKPSjoVWGDpEPEhNhMCmnfWzOVq0uYj/4q+2/mL/j1duCDiV" +
                                                    "hYZufzW+7BbjXhwGJNrwcTAi6ZAda52soiu4/Mzyijb58r3y/EtWn1aj0Iy9" +
                                                    "+sd//T516a/v1qHaNm7ZdxtsnhmhPZE8e2vIc0I0HgFdPHdwz2i2s3dpbd4c" +
                                                    "XJs3o8refuZv3cceLpz8HyhzZyQoUZU/nXjl3YN71ecU0lBhy5r+qnrRUDg8" +
                                                    "sKnDoCE00S2ciQvY7qrAtg3Tsx1+mz3Ybo7CVnAbDg+vU2UT63ybxOEwJ3HX" +
                                                    "pPYUNAzH8Ox1yMA67b+jF6E/mfcaqPRS8oO573/0qgRHtNuKCLPzy9/5LHVh" +
                                                    "WQJFtqS7a7rC8BrZlgpbEzIwn8FfDH7/xh86ghOyLUmOeL3RrkpzZNtlAbZ1" +
                                                    "zBJbjH14ZemNnyydU7zADHPSOG/psg++D4fHZdU8+F/VLr7vLwO1y+YC+fPO" +
                                                    "mjuG7IvV11YSrVtXjv9JHIuV3nUDNJD5kmGE4BKGTrPtsLwu9tkg2c4W/6DN" +
                                                    "joe7G5w7aQv7tsJNT/AJdvEp2bKWY7WkcZ88Nz/H2xB77K4Ci7hx+SVTkneu" +
                                                    "nHpl5fCRJ2898LKovya4qy0uig4dLhzyOK+UXe+a2nxdzYcGbm98vW2Pn6uq" +
                                                    "gz5sm8gC0miYa2ouxDn1Oj37RTb24FsKaQo3Jri8N9J8AKUvMGfYKiF0k1U3" +
                                                    "7QlgKOxVkKkqjUnJtj1xnDEiB5OCZkL/QJJehcfXrnC7AjJFuKeIm0PIsQmP" +
                                                    "IXPqDeXsqWsvPbAKiIJj2aHmHIAHzfLA0+KWZnzSMcKXr8DGBA7Hy2geVEJB" +
                                                    "ny3I/e3KfCO0zI/Y0VloZxogRNFpBaaLuiYmTpcDV2KV86CnnitA9qJlyak5" +
                                                    "7YXhH118aVIhzeNE0eGa3DyDUfUDvRHrhuLFVIDEw1YoW56vVfRbLzmJSnK6" +
                                                    "MRfb1usa8L1u6Sfh5It9JRIDIKjYoHgtlf8DeYbHou8RAAA=");
}
