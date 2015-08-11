package panda.types;

import polyglot.util.Position;
import polyglot.ext.jl5.types.RawClass_c;
import polyglot.ext.jl5.types.JL5ParsedClassType;

import java.util.List;

public class PandaRawClass_c extends RawClass_c implements PandaRawClass {

  public PandaRawClass_c(JL5ParsedClassType classType, Position pos) {
    super(classType, pos);
  }

  // PandaClassType Methods
  public List<ModeTypeVariable> modeTypeVars() {
    return ((PandaParsedClassType) this.base()).modeTypeVars();
  }
  
  public void modeTypeVars(List<ModeTypeVariable> modeTypeVars) {
    ((PandaParsedClassType) this.base()).modeTypeVars(modeTypeVars);
  } 

  public boolean isImplicitModeTypeVar() {
    return ((PandaParsedClassType) this.base()).isImplicitModeTypeVar();
  }

  public AttributeInstance attributeInstance() {
    return ((PandaParsedClassType) this.base()).attributeInstance();
  }

  public void attributeInstance(AttributeInstance attributeInstance) {
    ((PandaParsedClassType) this.base()).attributeInstance(attributeInstance);
  } 

  public boolean hasAttribute() {
    return ((PandaParsedClassType) this.base()).hasAttribute();
  }

  public CopyInstance copyInstance() {
    return ((PandaParsedClassType) this.base()).copyInstance();
  }

  public void copyInstance(CopyInstance copyInstance) {
    ((PandaParsedClassType) this.base()).copyInstance(copyInstance);
  } 

  public boolean hasCopy() {
    return ((PandaParsedClassType) this.base()).hasCopy();
  }

  public boolean needsAttribute() {
    return ((PandaParsedClassType) this.base()).needsAttribute();
  }

  public void needsAttribute(boolean needsAttribute) {
    ((PandaParsedClassType) this.base()).needsAttribute(needsAttribute);
  }

  public boolean hasMcaseFields() {
    return ((PandaParsedClassType) this.base()).hasMcaseFields();
  } 
  
  public void hasMcaseFields(boolean hasMcaseFields) {
    ((PandaParsedClassType) this.base()).hasMcaseFields(hasMcaseFields);
  } 

  public boolean hasDynamicRecv() {
    return ((PandaParsedClassType) this.base()).hasDynamicRecv();
  } 

  public boolean containsModeTypeVariable(ModeTypeVariable mt) {
    return ((PandaParsedClassType) this.base()).containsModeTypeVariable(mt);
  }

  public boolean instancesNeedTypePreservation() {
    return ((PandaParsedClassType) this.base()).instancesNeedTypePreservation();
  }

  public void instancesNeedTypePreservation(boolean needs) {
    ((PandaParsedClassType) this.base()).instancesNeedTypePreservation(needs);
  }



}

