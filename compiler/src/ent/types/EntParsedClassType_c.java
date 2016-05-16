package ent.types;

import polyglot.frontend.*;
import polyglot.types.*;
import polyglot.ext.jl5.types.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntParsedClassType_c extends JL5ParsedClassType_c implements EntParsedClassType {

  private List<ModeTypeVariable> modeTypeVars = null;
  private AttributeInstance attributeInstance;
  private CopyInstance copyInstance;
  private boolean isImplicitModeTypeVar = true;
  private boolean hasMcaseFields;
  private boolean instancesNeedTypePreservation;

  public EntParsedClassType_c(EntTypeSystem ts, LazyClassInitializer init, Source fromSource) {
    super(ts, init, fromSource);
  }

  // EntClassType Methods
  public List<ModeTypeVariable> modeTypeVars() {
    if (this.modeTypeVars == null) {
      // Inject a mode type variable here, done this way to catch as many classes
      // as possible.
      EntTypeSystem ts = (EntTypeSystem)this.typeSystem();
      ModeTypeVariable mtv = ts.createWildcardModeTypeVariable(this.position(), "_LM");
      if (mtv.lowerBound() == null) {
        System.err.format("Lowerbound not set from %s!\n", this);
        System.exit(1);
      }
      this.modeTypeVars = Arrays.asList(mtv);
      this.isImplicitModeTypeVar = true;
    }
    return this.modeTypeVars;
  }

  public void modeTypeVars(List<ModeTypeVariable> modeTypeVars) {
    this.modeTypeVars = modeTypeVars;
    this.isImplicitModeTypeVar = false;
  }

  public boolean isImplicitModeTypeVar() { return this.isImplicitModeTypeVar; }

  public boolean hasAttribute() { return (this.attributeInstance() != null); }

  public AttributeInstance attributeInstance() { return this.attributeInstance; }

  public void attributeInstance(AttributeInstance attributeInstance) {
    this.attributeInstance = attributeInstance;
  }

  public CopyInstance copyInstance() { return this.copyInstance; }

  public void copyInstance(CopyInstance copyInstance) { this.copyInstance = copyInstance; }

  public boolean hasCopy() { return (this.copyInstance() != null); }

  public boolean hasMcaseFields() { return this.hasMcaseFields; }

  public void hasMcaseFields(boolean hasMcaseFields) { this.hasMcaseFields = hasMcaseFields; }

  public boolean hasDynamicRecv() {
    return (this.modeTypeVars().size() > 0 && this.modeTypeVars().get(0).isDynRecvr());
  }

  public boolean containsModeTypeVariable(ModeTypeVariable mt) {
    // TODO : This needs to be fixed to properly handle super types
    // and member classes. Also, it's a O(N) lookup when it could be
    // O(1), but probably doesn't matter.
    for (ModeTypeVariable m : this.modeTypeVars()) {
      if (this.typeSystem().typeEquals(mt, m)) {
        return true;
      }
    }
    return false;
  }

  public boolean instancesNeedTypePreservation() { return this.instancesNeedTypePreservation; }

  public void instancesNeedTypePreservation(boolean needs) {
    this.instancesNeedTypePreservation = needs;
  }
}
