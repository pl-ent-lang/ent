package paper_ex3;

import java.util.*;

public class Agent@mode<managed> {
  public Agent(String file) { } 

  public ArraySet work(String url) { 
    Site s = Database.lookupSite(url);
    if (Env.mediaSearch) {
      return s.mediaCrawl();
    } else {
      Rule[] rules = generateRules(s);
      return s.crawl(rules); 
    }
  } 

  /*
  public @mode<Y> Rule@mode<Y>[] generateRules(Site@mode<Y> site) {
    return new null;
  }
  */

  public @mode<Y> List<Rule@mode<Y> > generateRules(Site@mode<Y> site) {
    return new ArrayList<Rule@mode<Y> >();
  }

  public @mode<?->X> ArraySet saveImages(Site s) 
    attributor {
      if (s.parsedimgs.length > 20) { return @mode<full_throttle>; }
      else if (s.parsedimgs.length > 10) { return @mode<managed>; }
      else { return @mode<energy_saver>; }
    }
  {
    JPEGWriter@mode<X> writer = new JPEGWriter@mode<X>();
    for (Image i : Arrays.asList(s.parsedimgs)) {
      writer.write(i);
    }
  }
}
