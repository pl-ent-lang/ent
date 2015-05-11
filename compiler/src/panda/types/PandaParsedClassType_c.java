package panda.types;

import polyglot.frontend.Source;
import polyglot.types.LazyClassInitializer;
import polyglot.ext.jl5.types.JL5ParsedClassType_c;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PandaParsedClassType_c extends JL5ParsedClassType_c implements PandaParsedClassType {

  private List<ModeTypeVariable> modeTypeVars = Collections.emptyList();

  public PandaParsedClassType_c(PandaTypeSystem ts,
                                LazyClassInitializer init, 
                                Source fromSource) {
    super(ts, init, fromSource);
  }

  // PandaClassType Methods
  public List<ModeTypeVariable> modeTypeVars() {
    return this.modeTypeVars;
  }
  
  public void modeTypeVars(List<ModeTypeVariable> modeTypeVars) {
    if (modeTypeVars == null) {
      this.modeTypeVars = Collections.emptyList();
    } else {
      this.modeTypeVars = modeTypeVars;
    }
  }

}

