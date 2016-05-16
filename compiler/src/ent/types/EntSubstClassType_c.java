package ent.types;

import polyglot.types.*;
import polyglot.util.*;
import polyglot.ext.jl5.types.*;

import java.util.List;

public class EntSubstClassType_c extends JL5SubstClassType_c implements EntSubstClassType {

  public EntSubstClassType_c(EntTypeSystem ts,
                             Position pos,
                             EntParsedClassType base,
                             EntSubst subst) {
    super(ts, pos, base, subst);
  }

  // EntClassType Methods
  public List<ModeTypeVariable> modeTypeVars() {
    return ((EntParsedClassType)this.base).modeTypeVars();
  }

  public void modeTypeVars(List<ModeTypeVariable> modeTypeVars) {
    ((EntParsedClassType)this.base).modeTypeVars(modeTypeVars);
  }

  public boolean isImplicitModeTypeVar() {
    return ((EntParsedClassType)this.base).isImplicitModeTypeVar();
  }

  public AttributeInstance attributeInstance() {
    return ((EntParsedClassType)this.base).attributeInstance();
  }

  public void attributeInstance(AttributeInstance attributeInstance) {
    ((EntParsedClassType)this.base).attributeInstance(attributeInstance);
  }

  public boolean hasAttribute() { return ((EntParsedClassType)this.base).hasAttribute(); }

  public CopyInstance copyInstance() { return ((EntParsedClassType)this.base).copyInstance(); }

  public void copyInstance(CopyInstance copyInstance) {
    ((EntParsedClassType)this.base).copyInstance(copyInstance);
  }

  public boolean hasCopy() { return ((EntParsedClassType)this.base).hasCopy(); }

  public boolean hasMcaseFields() { return ((EntParsedClassType)this.base).hasMcaseFields(); }

  public void hasMcaseFields(boolean hasMcaseFields) {
    ((EntParsedClassType)this.base).hasMcaseFields(hasMcaseFields);
  }

  public boolean hasDynamicRecv() { return ((EntParsedClassType)this.base).hasDynamicRecv(); }

  public boolean containsModeTypeVariable(ModeTypeVariable mt) {
    return ((EntParsedClassType)this.base).containsModeTypeVariable(mt);
  }

  public boolean instancesNeedTypePreservation() {
    return ((EntParsedClassType)this.base).instancesNeedTypePreservation();
  }

  public void instancesNeedTypePreservation(boolean needs) {
    ((EntParsedClassType)this.base).instancesNeedTypePreservation(needs);
  }

  // LAST

  @Override
  public List<? extends ConstructorInstance> constructors() {
    List<? extends ConstructorInstance> constructors = this.base().constructors();
    if (!constructors.equals(this.constructors)) {
      this.constructors = deepCopy(constructors);
      substConstructors = subst.substConstructorList(constructors);
    }
    return substConstructors;
  }
}
