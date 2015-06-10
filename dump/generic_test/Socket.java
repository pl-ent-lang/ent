package generic_test;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
public class Socket {
    private String f1;
    public Socket() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1433962925000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAKVXaWwcRRau6fiOk5k4iRNMEhLHIDmBGcJyCDliSSYxcXYc" +
                                                    "WzHJwrDLUO6usdvu6W66a+KJWa5IUdg/CGlDDsRa+yPRSohLiAgQAhkhllNC" +
                                                    "IAQCxCH+gARBRCKACLvLe1V9ewxIWOpyTdWrV+/83quHT5NG1yHdNjU1muX7" +
                                                    "beZmh3E+TB2XaXmDuu71sFpSD25uueT8f300oBClQJZQzh19tMrZgOlyaqqM" +
                                                    "kwsKgklOMMltSRL0FUh7xdIYctsLzDnJFCboPpqrct3IFXSX99UcstG2jP1j" +
                                                    "hsWzrMazE8YVnkw7C1fMkaj78fR35+4bzyikqUiWUtO0OOW6Zbq7mWsZ+5hW" +
                                                    "IOlwdbvBKu6t5A4Uf2GEmJOegn9pDi7NwaW+DiEVSL+ImdVK3hLqcJ9Tk62i" +
                                                    "QJysizOxqUMrHpthITNwaOGe7uIwaLs20Na3fELF+zfmDh+9OfPEApIukrRu" +
                                                    "jqA4KgjB4ZIiGJRVRpnjbtE0phXJEpMxbYQ5OjX0aSF3kXS4+phJedVhgVlw" +
                                                    "sWozR9wZ2qpdRd2cqsotJ1CvrDND8381lg06Brp2hrpKDftxHRRs00Ewp0xV" +
                                                    "5h9pmNRNTcRG/ESgY8+fgACONlcYH7eCqxpMCgukQ4aIQc2x3AgElDkGpI0W" +
                                                    "hJXDSde8TNHWNlUn6RgrcbIySTcst4CqVRgCj3CyPEkmOIGXuhJeivjn9K7N" +
                                                    "995m7jAVkgKZNaYaKH8bHFqTOLSblZnDIA/kwfYNhSO087l7FEKAeHmCWNI8" +
                                                    "9bcz1168ZvYVSXN+HZqh0Qmm8pJ6YnTxW6vyvVcvQDFabMvV0fkxzUWWDXs7" +
                                                    "fTUbkr4z4IibWX9zdvd/brzrIfalQtoGSJNqGdUKxNES1arYusGc65jJHMqZ" +
                                                    "NkBamanlxf4AaYZ5QTeZXB0ql13GB0iDIZaaLPEbTFQGFmiiVpjrZtny5zbl" +
                                                    "42JeswkhzfCRdvgWEvkn/nPSl9vjQrjnqEpN3bRyw46FBkCPCuRhLszHUEBd" +
                                                    "LeGv3IilTjKeBWSyf9/xGkrXMZVKgeFWJdPWgIjfYRkac0rq4erW7WceLb0u" +
                                                    "QwLD2NOLk6VR3lnJm6RSgucyDHTpCDDjJCQkIGJ778hfd95yT/cCiAB7qgFs" +
                                                    "gKQXzQHrfJi5PtyWVP75tmeVwrbvhSAx6PXhJzc/2NblSGaPTd29985LlSSG" +
                                                    "IsMGSH88PozIF1zRk8ydenzTh7747rEjt1thFsVA2UvuuScxObuTvnAslWkA" +
                                                    "dyH7DWvpqdJzt/coKGMroBynEJUAIGuSd8SStM8HPNSlGRQuW06FGrjlo1Qb" +
                                                    "H3esqXBFBEkGh2UyXtCxCQEFVvY/M3v81AMbrxYa+7CajtTDEcZlki4J4+J6" +
                                                    "hzFY/+jY8D/uP33oJhEUQLG+3gU9OOYhZcEbYLGDr9z6/icfn3hHCQOJQ+2q" +
                                                    "jhq6WsOQCm+BhDYgKdCtPXtMCBu9rNNRg2E8/pS+cNOpr+7NSEcZsOL7+eJf" +
                                                    "ZxCun7eV3PX6zd+vEWxSKhaUUPOQTBpgach5i+PQ/ShH7e63Vx9/mf4T8A4w" +
                                                    "xtWnmYANIjQjwvSXCo9sFGMusXcZDt1uNDrjCRAp/CX1vne+WbT3m+fPCGnj" +
                                                    "nUPUY4PU7pP+F3cuhct6iTfEYAx3l9s4dqLpVyQTfwd1x4HZ5bO7/pIxZs/B" +
                                                    "tUW4VoWa6g45ADG1WFB41I3NH7zwYuctby0gSj9pMyyq9VORKqQVYpS544BO" +
                                                    "NfuP10o5plpgyAh7kDkWApkuqO/N7RWbC/tPP73iyc3/nvlYhFNNcOgK4Kl3" +
                                                    "flTpx24iTMyVPw4Zowc++2GOZQWe1CmiifPF3MMPduWv+VKcDxMbT6+rzYVp" +
                                                    "gL7w7GUPVc4q3U0vKaS5SDKq19btpUYVc6wIrYzr93rQ+sX2422JrMF9AXCt" +
                                                    "SoJK5NokpITlAeZIjfP2RBS1oVeWwbfIi6JFyShKETHJiyPrxXgRDr1+pjfb" +
                                                    "jr6PYs9IlPImQbAC+m/hZVQiK3srCVk4/gGHbdKzV9WLmto8d+F0QzQiRDyt" +
                                                    "nq+DEt3fiQOHZ7Shk5tkn9MR70q2Q9P9yLv/fSN77NNX65TUVm7ZlxhsHzNi" +
                                                    "dwK4yfKKCLJyTpctO0P10Zl0y4qZPe+JwhB0bwuhhSpXDSPqm8i8yXZYWRc+" +
                                                    "Wig9ZYt/f+akPVrfce0Gu46tZcDUUnEjBVZf9gtWT9p1fSzVxJvD03GwKl8d" +
                                                    "JfWxmZ27bjtz5UlRtBvhtTI9LXpUaLllQQtq9bp5ufm8mnb0nlv8eOuFigek" +
                                                    "sVIXlQ3nmxFIoq3KnCdhSX323a9eWzlw9P1EaRYZnMgVALUp5my1qvie6Iy9" +
                                                    "NQe93gZfIFXb9qjwnJbIJQWlg8JJlnu5lE7mktAFh0HbDqBREWopLiJSRKHB" +
                                                    "sKcSFa6kfvvT19e9eSRjCX2aRlEMv1dYjPFFMc2EMT0fRCMrIjtCTFJDWV3S" +
                                                    "gS7noehdv1hd6hW/Dmg5bxDdfx1NSurZvg+PT/x9/G0IFyitDjUnQUhsHmXK" +
                                                    "RUT4P/yl4PsffsgeF2Sf3pH3Hgtrg9eCbdfkXmqDOD4e6LEK11f/Jj1SIchM" +
                                                    "BMrcKH/+DDCcMw/KEAAA");
}
