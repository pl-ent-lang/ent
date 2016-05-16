package ent.types;

import polyglot.util.Position;
import polyglot.ext.jl5.types.RawClass_c;
import polyglot.ext.jl5.types.JL5ParsedClassType;

import java.util.List;

public class EntRawClass_c extends RawClass_c implements EntRawClass {

  public EntRawClass_c(JL5ParsedClassType classType, Position pos) { super(classType, pos); }

  // EntClassType Methods
  public List<ModeTypeVariable> modeTypeVars() {
    return ((EntParsedClassType)this.base()).modeTypeVars();
  }

  public void modeTypeVars(List<ModeTypeVariable> modeTypeVars) {
    ((EntParsedClassType)this.base()).modeTypeVars(modeTypeVars);
  }

  public boolean isImplicitModeTypeVar() {
    return ((EntParsedClassType)this.base()).isImplicitModeTypeVar();
  }

  public AttributeInstance attributeInstance() {
    return ((EntParsedClassType)this.base()).attributeInstance();
  }

  public void attributeInstance(AttributeInstance attributeInstance) {
    ((EntParsedClassType)this.base()).attributeInstance(attributeInstance);
  }

  public boolean hasAttribute() { return ((EntParsedClassType)this.base()).hasAttribute(); }

  public CopyInstance copyInstance() { return ((EntParsedClassType)this.base()).copyInstance(); }

  public void copyInstance(CopyInstance copyInstance) {
    ((EntParsedClassType)this.base()).copyInstance(copyInstance);
  }

  public boolean hasCopy() { return ((EntParsedClassType)this.base()).hasCopy(); }

  public boolean hasMcaseFields() { return ((EntParsedClassType)this.base()).hasMcaseFields(); }

  public void hasMcaseFields(boolean hasMcaseFields) {
    ((EntParsedClassType)this.base()).hasMcaseFields(hasMcaseFields);
  }

  public boolean hasDynamicRecv() { return ((EntParsedClassType)this.base()).hasDynamicRecv(); }

  public boolean containsModeTypeVariable(ModeTypeVariable mt) {
    return ((EntParsedClassType)this.base()).containsModeTypeVariable(mt);
  }

  public boolean instancesNeedTypePreservation() {
    return ((EntParsedClassType)this.base()).instancesNeedTypePreservation();
  }

  public void instancesNeedTypePreservation(boolean needs) {
    ((EntParsedClassType)this.base()).instancesNeedTypePreservation(needs);
  }
}
