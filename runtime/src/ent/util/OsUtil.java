package ent.runtime.util;

public class OsUtil {

  public static OsType getOsType() {
    String osName = System.getProperty("os.name");

    if (isWindowsVariant(osName)) {
      return OsType.WINDOWS;
    }

    if (isUnixVariant(osName)) {
      if (osName.equals("Mac OS X")) {
        return OsType.MACOS;
      }

      String vendor = System.getProperty("java.vendor");

      if (vendor.equals("The Android Project")) {
        return OsType.ANDROID;
      }

      return OsType.LINUX;
    }

    return OsType.NONE;
  }

  private static boolean isWindowsVariant(String osName) {
    return (osName.equals("Windows 7") || osName.equals("Windows XP") ||
            osName.equals("Windows 2003") || osName.equals("Windows NT"));
  }

  private static boolean isUnixVariant(String osName) {
    return (osName.equals("Linux") || osName.equals("Mac OS X"));
  }
}
