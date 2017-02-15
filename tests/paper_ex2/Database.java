package paper_ex2;

import java.util.*;

public class Database {
  public static Map<String,Site> sites;

  // Show David inference bug!
  // Comment out annotation on Site
  public static Site lookupSite(String url) { return sites.get(url); }
}
