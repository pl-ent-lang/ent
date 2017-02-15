package paper_ex1;

import java.util.*;

public class Agent@mode<?->X> {
  Rule[] rules;

  public Agent(String file) { 
    rules = parseConfigurationFile(file);
  }

  attributor { 
    if (Ext.battery >= 0.75) { 
      return @mode<full_throttle>;
    } else {
      for (Rule r : Arrays.asList(rules)) {
        if (r instanceof LocalOnlyRule) {
          return @mode<full_throttle>; 
        }
      }
    } 
    if (Ext.battery >= 0.50) {
      return @mode<managed>;
    } else {
      return @mode<energy_saver>;
    }
  } 

  public ArraySet work(String url) { 
    Site ds = Database.lookupSite(url);
    Site s = snapshot ds ?mode[@mode<*>, @mode<X>];
    return s.crawl(depth); 
  } 

  Rule[] parseConfigurationFile(String file) { 
    return new Rule[0];
  }

  mcase<int> depth = mcase<int> { 
    energy_saver: 1; 
    managed: 2; 
    full_throttle: 3; 
  };

}
