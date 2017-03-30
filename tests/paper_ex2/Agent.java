package paper_ex2;

import java.util.*;

public class Agent@mode<?->X> {
  List<Rule@mode<X> > rules;

  String file;

  attributor { 
    if (Ext.battery >= 0.75) { 
      return @mode<full_throttle>;
    } else if (Ext.battery >= 0.50) {
      return @mode<managed>;
    } else {
      return @mode<energy_saver>;
    }
  } 

  public Agent(String file) { 
    this.file = file;
  } 

  public ArraySet work(String url) { 
    rules = parseConfigurationFile(file);
    Site@mode<X> s = Database.@mode<X>lookupSite(url);
    return s.crawl(rules); 
  } 

  public List<Rule@mode<X> > parseConfigurationFile(String file) { 
    return new ArrayList<Rule@mode<X> >();
  }

}
