package generic_test;
import java.util.ArrayList;
import java.util.List;
public class Socket<X> {
    private X f1;
    private String f2;
    public X getF1() { return this.f1; }
    public void setF1(X f1) { this.f1 = f1; }
    public String getF2() { return this.f2; }
    public void setF2(String f2) { this.f2 = f2; }
    public void foo() { String s1 = "a";
                        String s2 = "a";
                        Socket<String> socket = new Socket<String>();
                        s1 = socket.getF1();
                        socket.setF1(s1);
                        s2 = socket.getF2();
                        socket.setF2(s2); }
    public Socket() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1431700480000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAKUYW2wU1/Xu+G2M19jggAvGmDXFPHYBQZXUVYJx7dh0jVes" +
                                                    "ccumZRnP3LUHz85MZu7aaxLnQRuB+kHTxKQkJfSH9BGRkKRFVVWlclW1CUr7" +
                                                    "kSpq1EpNqv40bYoaPppWpU16zp3Zmdmxd4Gw0ty9c+855573OXcuXiVVlkk6" +
                                                    "DVGTxSibNagVTeA8IZoWlftU0bJGYTUtzbz8o388/Pb8fQIJxUlDVpcpro8B" +
                                                    "GCNN8WPitBjLMUWNxRWL9eRNstXQ1dkJVWdRmmfRY+oeh/r++J5FtDtfCn94" +
                                                    "/fHJJoFUp0iLqGk6E5mia9ZBaunqNJXjJOyt9qs0a91PHkJGlvmAGYnEC4fG" +
                                                    "4NAYHBrjh8Z6PaieOFlOtVy2DzFEjRUoVRsSMsTIhmIihmiKWYdMgvMMFGqZ" +
                                                    "IztHBmk7XGkLOgyIeGZrbP5bR5peqSDhFAkrWhLZkYAJBoekQKE0O05Nq1eW" +
                                                    "qZwiKzRK5SQ1FVFVjnO+U6TZUiY0keVM6qoFF3MGNfmZnq4aJJTNzElMN13x" +
                                                    "MgpV5cJbVUYVJ0DWVk9WW8IBXAcB6xVgzMyIEi2gVE4pmszI+iCGK2PkCwAA" +
                                                    "qDVZyiZ196hKTYQF0my7iCpqE7EkMxVtAkCr9BycwkhbSaKoa0OUpsQJmmZk" +
                                                    "dRAuYW8BVB1XBKIwsioIximBldoCVvLZ5+qBz51+QBvUuHtXylRSkf86QGoP" +
                                                    "IB2kGWpSTaI2YsOW+FNi66unBEIAeFUA2Ib58YPX9m5rX3jdhvnUEjAj48eo" +
                                                    "xNLShfHGN9f2dd9VgWzUGrqloPGLJOdRlnB2evIGhG+rSxE3o4XNhYO/OvzI" +
                                                    "8/R9gdQPkWpJV3NZ8KMVkp41FJWa91KNmiKj8hCpo5rcx/eHSA3M44pG7dWR" +
                                                    "TMaibIhUqnypWufvoKIMkEAV1cJc0TJ6YW6IbJLP8wYhpAYe0gBPhNg//s9I" +
                                                    "T+yQBe4eEyVRUzQ9ljB1VABaFJJPjFEL5hPIoCKl8S2W1KUpyqKQqIzbQ88j" +
                                                    "dytmQiFQ3Npg2Krg8YO6KlMzLc3n9vVfezH9huC6sSMXIy1+2lGbNgmFOM2V" +
                                                    "6Oi2IUCNUxCQkBEbupNf2X/0VGcFeIAxUwk6QNBY6RzZ54XwEE9UEvgRWTg7" +
                                                    "8+jYwzsEIhSnPmSsAqIW0ROYsNzEFAm6/FJ0wyff+/DSU3O65/xFudSJycWY" +
                                                    "GFOdQRWaukRlyFIe+S0d4uX0q3MRgVRCoEJyYiI4E8R9e/CMotjqKeQplKUa" +
                                                    "BM7oZlZUcauQXOrZpKnPeCvctmEcWmwzoz0CDPIUN/CThacvP7P1LsGfDcO+" +
                                                    "MpakzI6tFZ45R01KYf2PZxNPnrl68j5uS4DYuNQBERz7INLAGqCxx16///fv" +
                                                    "vnPhLcGzP4OSkxtXFSkPNDZ5p0AcquDLaNbIIQ0KrZJRxHGVohv9N9y18/Lf" +
                                                    "TzfZhlJhpWDnbTcm4K2v2UceeePIv9o5mZCEdcCT3AOzFdDiUe41TXEW+cg/" +
                                                    "+tt1T78mPgtpClKDpRynPNoJl4xw1Ue5Rbr5uD2wtwOHDsvvncUB4KvXaenx" +
                                                    "tz5YPvbBz64Jiwu+32LDotFj25+f2VxINpFg9sHdlQaOq1D1dwTjdVC0JoHY" +
                                                    "7oUDX25SF67DsSk4VoJSaI2YkBnyRU7hQFfV/OHnv2g9+mYFEQZIvaqL8oDI" +
                                                    "Q4XUgY9SaxKSSt64Z6/Nx0wtDE1cH2SRhvJ8ZQ1/E4DB7tJZYgCLuhdoq/8z" +
                                                    "oo6f+PO/F2mK54clalkAPxW7eK6t7+73Ob4XqIi9Pr84W0ID5OHuej77T6Gz" +
                                                    "+pcCqUmRJsnprsZENYcxk4KOwiq0XNCBFe0Xdwd2KexxE9HaYJLwHRtMEV6W" +
                                                    "hjlC43xZwCvqUctheLocr+gKekWI8Mk9HGUDHyM4fLoQuTWGqUyL2LoRIbMT" +
                                                    "bNRVwkZOj8zjLy09eO6j3/x17p0rFchrPYonmlB+ob5HS3WufgKRUZh9HrBA" +
                                                    "7EYbG/oo3sTwZiVOmt1VNw0zsr0UbWzFg9kaez9Vn6HmPj2nydzyxWqtzxmG" +
                                                    "f5crd7mr3JWoy1XlQu4hSFY3oS1XWIcOUgcfbOSRh44S7Ycu3r+Jdbkv3ptM" +
                                                    "pkcPJ/rTY70Hh3r3xft5HTBgM/QlzmUr3Fc8Ira3OcUCx5047LVjcE/JeP1s" +
                                                    "sTe1w7PJYWVTCW9K4tCLDrNrCT7snjjAxmgZNvIlnBOnm/0phGCSW1eq8+Vd" +
                                                    "+4UT8+flked22v1pc3E3iWp+4Xf/+3X07J+uLNEK1THd2K7Saar6zsQ75ZbS" +
                                                    "aWuY3xC83PHaib+1jd49efQW+pr1AYGCJH8wfPHKvZukJwTEdzLJortLMVJP" +
                                                    "wNEhdnKmNlqURdpduy8jji02O3bfHLQ7NyYOqTKlcLLM3jEcIICrJigb4A4x" +
                                                    "6PlG5kYu6qfGF8Zd3utwcQ082xzet9007yHPywY5lFlGAN6d6CCAhQKUL2UJ" +
                                                    "U8nCrWXauVbF5prfnTr33gu2SwbrVgCYnpr/+sfR0/OC76K6cdFd0Y9jX1Y5" +
                                                    "k422Wj6GXwiej/BBCXAB/6E69Tk3pg73ymQYGFUbyrHFjxj4y6W5n35/7qTg" +
                                                    "aOQII5XTuiJ7djRuw45tuLgenh2OHXd8Ih98rMzeSRxOOD5YOnF58nz1dv0S" +
                                                    "42i3I8/uW/XLkgxyzG+WEfQMDqcdX92FL7OeUN+4XaFa4LnTEerOT2SkZ8vs" +
                                                    "fQeHZxipyOh6gPNv3wrn0M5U25dZbPxXL/qmZX+HkV48H6694/yht/l9zv1W" +
                                                    "Uh8ntZmcqvpbMN+82jBpRuHs1tsNmcH/vstIg/82jWvf44oZyYeKy5grVMuN" +
                                                    "hPJVvo1FKYd/zSuUjJz9PS8tXTq//8AD1z7zHK8/VdBBHT+OVKChqrHvnG7Z" +
                                                    "2VCSWoFW9WD39caX6roKEV90G10yi94ow77sbgnunaDd/8F22PsY6zSac1/7" +
                                                    "YrP6CpvlAi3dKvqqOI5eB7eukFRKdnBcFmhkhu2G6ocuagdCdt4U6uH8/wHf" +
                                                    "bcC9exYAAA==");
}
