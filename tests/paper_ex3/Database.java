package paper_ex3;

import java.util.*;

public class Database {
  public static Map<String,Site@mode<energy_saver> > sites;

  // Show David inference bug!
  // Comment out annotation on Site
  public static Site@mode<energy_saver> lookupSite(String url) { return sites.get(url); }
}
