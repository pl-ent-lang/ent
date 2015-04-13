package panda.types;

import polyglot.frontend.Source;
import polyglot.types.LazyClassInitializer;
import polyglot.ext.jl5.types.JL5ParsedClassType_c;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PandaParsedClassType_c extends JL5ParsedClassType_c implements PandaParsedClassType {

  private List<ModeTypeVariable> modeTypeVariables = Collections.emptyList();

  public PandaParsedClassType_c(PandaTypeSystem typeSystem,
                                LazyClassInitializer init, 
                                Source fromSource) {
    super(typeSystem, init, fromSource);
  }

  // Property Methods
  public List<ModeTypeVariable> modeTypeVariables() {
    return this.modeTypeVariables;
  }
  
  public void modeTypeVariables(List<ModeTypeVariable> modeTypeVariables) {
    if (modeTypeVariables == null) {
      this.modeTypeVariables = Collections.emptyList();
      return;
    }
    this.modeTypeVariables = modeTypeVariables;
  }

}

