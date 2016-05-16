package ent.types;

import polyglot.types.*;
import polyglot.util.*;
import polyglot.ext.jl5.types.*;
import polyglot.ext.jl7.types.*;

import java.util.List;

public class EntDiamondType_c extends DiamondType_c implements EntDiamondType {

  public EntDiamondType_c(Position pos, JL5ParsedClassType base) { super(pos, base); }

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

  public boolean hasMcaseFields() { return ((EntClassType)this.base).hasMcaseFields(); }

  public void hasMcaseFields(boolean hasMcaseFields) {
    ((EntClassType)this.base).hasMcaseFields(hasMcaseFields);
  }

  public boolean hasDynamicRecv() { return ((EntClassType)this.base).hasDynamicRecv(); }

  public boolean containsModeTypeVariable(ModeTypeVariable mt) {
    return ((EntClassType)this.base()).containsModeTypeVariable(mt);
  }

  public boolean instancesNeedTypePreservation() {
    return ((EntClassType)this.base()).instancesNeedTypePreservation();
  }

  public void instancesNeedTypePreservation(boolean needs) {
    ((EntClassType)this.base()).instancesNeedTypePreservation(needs);
  }
}
