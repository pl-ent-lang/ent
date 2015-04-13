package generic_test;
public class Socket {
    private String ipAddr;
    private int port;
    public Socket(String ipAddr, int port) { super();
                                             this.ipAddr = ipAddr;
                                             this.port = port; }
    public static void main(String[] args) {  }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1428692165000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAKVYa2wc1RW+O7bXj9heP4gdTOJ3UJzQXUChFTIKcZw4drDj" +
                                                    "Vew4wm2zuZ69a088OzPMzNprg3kEqkT8SBE1NFTgX4G2KCSIEtEKUblClCDa" +
                                                    "SiDaqpVK2v4BNY2U/Citmrb0nHt3dh67dlTV0tydufeec8/zO+f63FVSZpmk" +
                                                    "06BakkbtBYNZ0Ti+x6lpsWS/Si1rHGYT8timmifbHu08IpHQMKlP60mG8xPU" +
                                                    "VOiUyiyb1A0fp3M0lrEVNTasWHZv1iQ7DF1dmFZ1O8qydvS4ek/uiAPD9xQc" +
                                                    "0Pl65Isbz8zUSSQ8SRqppuk2tRVdsw4xS1fnWHKYRNzZfSpLWw+RR1GaDZ7N" +
                                                    "Nukedg6NwaExODTGD431ubt6h0kN0zLpfqSgmu1wChsyCmSTDj8Tg5o0nWMT" +
                                                    "5zIDhwpbGEAQg7bteW0dQwZUfG5HbPm7R+veKCGRSRJRtDEURwYhbDhkklSn" +
                                                    "WXqKmVZfMsmSk6ReYyw5xsDAqrLI5Z4kDZYyrVE7Y7K8WXAyYzCTn+naqlpG" +
                                                    "3cyMbOtmXr2UwtSk81WWUuk06Nrk6io0HMB5ULBKAcHMFJWZQ1I6q2hJm7QF" +
                                                    "KfI6dj8AG4C0PM3sGT1/VKlGYYI0iBBRqTYdG7NNRZuGrWV6Bk6xScuaTNHW" +
                                                    "BpVn6TRL2GRTcF9cLMGuSm4IJLHJxuA2zgm81BLwksc/Vw/ed/phbVDjMV6a" +
                                                    "ZLKK8lcCUWuA6BBLMZNpMhOE1duHn6dN75ySCIHNGwObxZ63Hrm++47W1Uti" +
                                                    "z21F9oxOHWeynZDPTtV+tLm/594SFKPC0C0Fne/TnGdZPLfSmzUgh5vyHHEx" +
                                                    "6iyuHvr5g4+/yq5IpGqIhGVdzaQhjuplPW0oKjP3M42Z1GbJIVLJtGQ/Xx8i" +
                                                    "5fA+rGhMzI6mUhazh0ipyqfCOv8GE6WABZqoAt4VLaU77wa1Z/h71iCElMND" +
                                                    "quEpI+KP/9qkN3bYgnCPUZlqiqbH4qaOBkCPAgLFbEAVKzaNAipyAr9iY7o8" +
                                                    "y+wooJXx/5FnUbr6+VAIDLc5mLYqRPygriaZmZCXM3v2XT+f+FDKh3FOL5s0" +
                                                    "enlHBW8SCnGet2CgC0eAGWchIQERq3vGvnng2KnOEogAY74UbIBbY2tjZL+b" +
                                                    "wkMcqGSII7J6Zv6JicfulIjkhz4UrASyFsnjCFh5YOoOhnwxvpGTn39x4fkl" +
                                                    "3Q1+H5bmcrKQEnOqM2hCU5dZElDKZb+9nV5MvLPULZFSSFQAJ5tCMEHetwbP" +
                                                    "8OVWr4NTqEsYFE7pZpqquOSAS5U9Y+rz7gz3bYS/N4CNKzHYIs6L84urtxg4" +
                                                    "bhSxgE4LaMFxcOAnqy9c/N6OeyUvZEY8tW6M2SIB612fj5uMwfwfzsS/89zV" +
                                                    "k1/nDocdXcUO6MaxH9IRXAZm/dalh353+dOzn0hukNhQlzJTqiJngcft7imQ" +
                                                    "rCoEPPq++7AGJVlJ8WKMsfavyNa7Lv71dJ3wpgozTjDccXMG7vyte8jjHx79" +
                                                    "eytnE5KxWLiau9uEARpdzn2mSRdQjuwTH2954X36EmAZ4IelLDIOCRLXTAKi" +
                                                    "5oLWQ8Dl2/RHvb9+pNngYV4xRS0eDqjEBvh2+g/+zR1YKxz+JfyF4PkPPuho" +
                                                    "nBBw09Cfw7z2POgZRpaTNUH7woXH0hQVpclC1PfINhJoeRLy0lNHGtQ37AUu" +
                                                    "Yi1mDEVCXlB44fAhBo41+ahsQZluC2JiQVQCoDzIae7E4W4MgJ51eipTSQPo" +
                                                    "z+WqUmyp4fLsi5+/JipOsIQFNrNTy09/GT29LHnqfFdBqfXSiFovjJ+XrmO9" +
                                                    "UzjFwGcXlt7+wdJJIVWDv2rtg6bstd/8+xfRM3/8oAjklkBHwktdEb8k5Euf" +
                                                    "bVvc8Oy3fwn+gFgzqTYL9BgprgNygoqAqI73Hdzbl9gzOj4+OsKXY9zW2/kY" +
                                                    "xU08TAlf249Du+VFU78DPP1lQn7mk2s1E9d+el0qbFC94DFCDWHBCA4daMHm" +
                                                    "YOkYpNYM7Nu5evAbderqDeA4CRxl6MqsUROKVNYHPbndZeW//9m7Tcc+KiHS" +
                                                    "AKlSdZocoBy1SSXAJbNmoL5ljft3i+Cbr4ChjqtKCpQXKXJrPmPXCcAB7C9d" +
                                                    "zN/0z1F16sSf/1FgBF6qisRkgH4ydu7Flv5dVzi9WzOQui1bWLghM13au19N" +
                                                    "/03qDL8nkfJJUifnGv0JqmYQmSehubWc7h8uA751f6MqurLefE3cHEwKz7HB" +
                                                    "auVGL7zbPrxyC1QzWrkDnnAOCsJBKAgR/nKYk3TycSsO25z6UG6YyhzFWwQJ" +
                                                    "KwbcH0wvaOC4E4eviUqH4yAOE8K1D6wZBvG8kFU42+g0c85vESGP4nDEhh5Q" +
                                                    "N+2gFLtw2BuQIrGOFJyq2xOABFNky1otPAeYsyeWV5KjL98l5RJ3tw0xrxtf" +
                                                    "UdkcUz2ssPnavnYsj/AbjBtQ75/4S8v4rplj/0Pf1RaQM8jyhyPnPth/u/ys" +
                                                    "hPS58Cq4W/mJev1BVWUyuAxq/lLYmvcat+I2eGpzXqst3vsUjSsJX3sgoix+" +
                                                    "Tc0GsDGUM6K/Om1d26C8JxBIvfJK168eW+n6E8eyCsWCFOozp4tczjw0185d" +
                                                    "vvJxzZbzvIEsxYYg1wz4b7WFl1bfXbRYGW6/mYF2GobBJ/fyT3OdKrGAA7ix" +
                                                    "NA1QxXf05myDP/fDwpyuJN3gT98sBX1IDM4Q9wxstzYV/LtBXJHl8yuRiuaV" +
                                                    "w78VlnKusVXQNqUyquqFJM972DBZSuE6VAmAMvjPk1AnvRcdnHvKKNI1CZzM" +
                                                    "hvzJmle08WaKevK7yxdE/J8vTgZlxL9fEvKFlQMHH77+1Zd5OpZB67W4mOu5" +
                                                    "ysUVIZ+FHWtyc3iFB3tu1L5eudVBjIgQ2M0Nj2xtxdvnfWnD5g3v4o+b37zv" +
                                                    "+yuf8v49mCwchLP/BRiP/Z51EwAA");
}
