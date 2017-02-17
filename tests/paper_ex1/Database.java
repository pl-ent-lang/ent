package paper_ex1;

import java.util.*;

public class Database {
  public static Map<String,Site@mode<?> > sites;

  // Show David inference bug!
  // Comment out annotation on Site
  public static Site lookupSite(String url) { return sites.get(url); }
}
