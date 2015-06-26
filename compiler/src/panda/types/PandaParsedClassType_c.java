package panda.types;

import polyglot.frontend.*;
import polyglot.types.*;
import polyglot.ext.jl5.types.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PandaParsedClassType_c extends JL5ParsedClassType_c implements PandaParsedClassType {

  private List<ModeTypeVariable> modeTypeVars = null;
  private AttributeInstance attributeInstance;
  private CopyInstance copyInstance;

  public PandaParsedClassType_c(PandaTypeSystem ts,
                                LazyClassInitializer init, 
                                Source fromSource) {
    super(ts, init, fromSource);
  }

  // PandaClassType Methods
  public List<ModeTypeVariable> modeTypeVars() {
    if (this.modeTypeVars == null) {
      // Inject a mode type variable here, done this way to catch as many classes
      // as possible.
      PandaTypeSystem ts = (PandaTypeSystem) this.typeSystem();
      ModeTypeVariable mtv = ts.createModeTypeVariable(this.position(), "_LM");
      if (!mtv.inferUpperBound()) {
        // Problem
      }
      this.modeTypeVars = Arrays.asList(mtv);
    }
    return this.modeTypeVars;
  }
  
  public void modeTypeVars(List<ModeTypeVariable> modeTypeVars) {
    this.modeTypeVars = modeTypeVars;
  }

  public boolean hasAttribute() {
    return (this.attributeInstance() != null);
  }

  public AttributeInstance attributeInstance() {
    return this.attributeInstance;
  }

  public void attributeInstance(AttributeInstance attributeInstance) {
    this.attributeInstance = attributeInstance;
  }

  public CopyInstance copyInstance() {
    return this.copyInstance;
  }

  public void copyInstance(CopyInstance copyInstance) {
    this.copyInstance = copyInstance;
  } 

  public boolean hasCopy() {
    return (this.copyInstance() != null);
  }

}

