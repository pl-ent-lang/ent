package generic_test;
import java.util.ArrayList;
import java.util.List;
public class Socket {
    public static void main(String[] args) { Socket s1 = new Socket(); }
    public Socket() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1430854003000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAKUYa2wURXhu+6AtLdcWKYjQN0pB7yCKCSlRSm2leG0vtGCs" +
                                                    "j2O6O3ddure77s61R7GoqIH4A40WRKNNjKhoEIyRqDFq1UQxPhKN8ZUo+kuj" +
                                                    "kkBi9Ae+vpm5vd3bu1aNTXZud+Z7v6fHzqAS20LNJtYVHKK7TGKHouw9ii2b" +
                                                    "KJ0atu1B2I3JA0uq7mrY03y9hAIRVJM0FML2t2NLxcMasSmqjuzEYzicoqoW" +
                                                    "jqg2bU9baLVpaLsSmkFDJE1DO7V1GRZbIuvyGDQ/H/z1/P0j1RIqHUILsa4b" +
                                                    "FFPV0O2txDa0MaJEUNDd7dJI0r4V7WHSzPcAU9QacZiGgWkYmIY503CHC9Ue" +
                                                    "QVVETyU7GQbWqUOp1JSZQBQ15RIxsYWTGTJRLjNQKKPCAAIZtG3MausY0qfi" +
                                                    "wdXhqYduqX6hCAWHUFDVB5g4MghBgckQqkyS5DCx7A5FIcoQqtEJUQYIGFhT" +
                                                    "J7jcQ6jWVhM6pimLZM3CNlMmsThP11aVMtPNSsnUsLLqxVWiKc5XSVzDCdC1" +
                                                    "ztVVaNjN9kHBChUEs+JYJg5K8aiqKxQ1+DGyOrZeBwCAOi9J6IiRZVWsY9hA" +
                                                    "tSJENKwnwgPUUvUEgJYYKeBC0dJZiTJbm1gexQkSo2iJHy4qjgCqnBuCoVC0" +
                                                    "yA/GKYGXlvq85PHPmb4NB3brm3Ue48UKkTUmfzkg1fuQtpI4sYguE4FYuSpy" +
                                                    "CNe9tl9CCIAX+YAFzEu3ndt4af3MKQFzUQGY/uGdRKYx+cjwgo+XdbatL2Ji" +
                                                    "lJmGrTLn52jOsyyaOWlPm5DDdVmK7DDkHM5sfeeGO54lP0moogeVyoaWSkIc" +
                                                    "1chG0lQ1Yl1LdGJhSpQeVE50pZOf96B58B5RdSJ2++Nxm9AeVKzxrVKDf4OJ" +
                                                    "4kCCmagM3lU9bjjvJqYj/D1tIoTmwYMq4SlD4o//UtQe3mZDuIexjHVVN8JR" +
                                                    "y2AGYB6FChSmUFXscIIJqMox9hUeMORRQkNQrcz/h55m0tWMBwJguGX+tNUg" +
                                                    "4jcbmkKsmDyV2tR17njsfSkbxhm9KFropR0StFEgwGlewAJdOALMOAoJCRWx" +
                                                    "sm3g5i079jcXQQSY48VgAwYanr1Gdrop3MMLlQxxhGYOj9+5/fY1EpJySx8T" +
                                                    "rAiylqFHWcHKFqZWf8gXohvc98OvJw5NGm7w59TSTE7mY7Kcavab0DJkokCV" +
                                                    "csmvasQnY69NtkqoGBIVihPFEEyQ9/V+Hjm51e7UKaZLKSgcN6wk1tiRU1wq" +
                                                    "6IhljLs73LdBtiwUbmb+8AnIS1z3KzMPn3xk9XrJWw2DnjY2QKjIrRrXnYMW" +
                                                    "IbD/9eHogwfP7LuR+xIgWgoxaGVrJ2QaeAMsds+pW788/c2RTyXX/xRaTmpY" +
                                                    "U+U00LjY5QJ5qEEsM7e2btOh26px3mdZGP0eXLH25M8HqoWjNNhx/HzpPxNw" +
                                                    "9y/chO54/5bf6jmZgMz6gKu5CyYMsNCl3GFZeBeTI33nJ8sffhc/BmUKSoOt" +
                                                    "ThCe7YhrhrjpQ9wjbXy9zHe2hi2Ntjc6cxPA069j8v2fnq3afvb1c1J+w/d6" +
                                                    "rBeb7cL/nGctMGtAmSWn+rDTC0y2LmKmX+zP183YHgFiV8z03VStzZwHtkPA" +
                                                    "VoZWaPdbUBnSOUGRgS6Z99Wbb9ft+LgISd2oQjOw0o15qqByiFFij0BRSZtX" +
                                                    "bxRyjDNBqrk9UJ6F0nznwswX/2jiaytbLhHRw15X+iAttHy2Bseb85G9U9NK" +
                                                    "/5NrRRuqzW0aXTATPffZHx+EDn/7XoGKV04N8zKNjBHNw5PVsFWz17BePgi4" +
                                                    "deDdvT8uHbxqZMd/KF8NPoX8JJ/pPfbetRfLD0gMP1O58kaUXKR2r2rA1CIw" +
                                                    "U+nMSGxnPg+h+mwIcSethKc8E0Ll/hASdaaglyTuJUh0m097aV9KBLJGbPQO" +
                                                    "4b0wYA+khm3K001MEGu+uPzoeOKNB4XrWguC+waTU0f1V/c+vv4JgbI4b8wX" +
                                                    "UL+c3vj7W3uaN3CflA1jm2QtAd/OrO9aZoGwzF/wF4DnT/Ywi7AN0dprOzPz" +
                                                    "RWN2wDBNFpwrZo8Uj6bTT7V8dPt0y3c87cpUG6bsDitRYHjz4Jw9dvqnT6qW" +
                                                    "H+cNppgpkVEgd+rNH2pzZlWuX1XW80uZOo1zeZ6D1sH1h1cDNtqGxGhrmibi" +
                                                    "Y5nPTRnHfL9yYv4D930IJofiaWF9FIRmlhbJxq3MlmhaGLQy2tF3TUdsU//g" +
                                                    "YH8vP75ujtp6E1u6KFCEJmvPFitRS03CjDiWEenL1ek3m0q1r6QMkT7BH9Db" +
                                                    "5rjG5dIIT9aeHn30h+dEvPmnZh8w2T9171+hA1OS52rRkjfde3HE9cJrHZCu" +
                                                    "aS4uHKP7+xOTrx6d3OdodhVYZsxQFfZ+AzfgWrZ0C2eu+3cFGjJazHysPy7J" +
                                                    "u/qJ64p8fDpYtnh62+ciKp0rRQWkVTylaZ4S5C1HpaZF4ioXtUJ0M5P/QF2r" +
                                                    "9A6dbC9pFohAcZNIB/KbyFoxF/2Dop5u0pLje34RduIoJa7CMfnE9Ja+3eeu" +
                                                    "fJLXjxK4Qk9M8IsT5JYY17KlvGlWag6t0s1t5xc8X77C8VXOIOeTraHwvNOV" +
                                                    "NCmfUCZeXvzihqenv+EDV9aDkijLNovPArnp/EsjJk/efX2t9gLdxdVawHyH" +
                                                    "WWJzQTP6efpjgcJx0VxTB9cILgwiAHdnMZcxwOX/BnMo/TehAGPavxEAAA==");
}
