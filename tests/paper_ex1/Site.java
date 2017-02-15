package paper_ex1;

import java.util.*;

public class Site@mode<?->X> {
  Resource[] resources;

  attributor { 
    if (resources.length > 200) {
      return @mode<full_throttle>; 
    } else if (resources.length > 50) {
      return @mode<managed>;
    } else {
      return @mode<energy_saver>;
    }
  } 

  public Site(String url) { 
    resources = discoverLinks(url); 
  }

  private Resource[] discoverLinks(String url) { 
    return new Resource[0];
  }

  public ArraySet crawl(int depth) { 
    ArraySet moreWork = new ArraySet();
    for (Resource r : Arrays.asList(resources)) { 
      moreWork.add(r.process(depth)); 
    }
    return moreWork;
  } 
}
