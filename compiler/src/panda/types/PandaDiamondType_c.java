package panda.types;

import polyglot.types.*;
import polyglot.util.*;
import polyglot.ext.jl5.types.*;
import polyglot.ext.jl7.types.*;

import java.util.List;

public class PandaDiamondType_c extends DiamondType_c implements PandaDiamondType {

  public PandaDiamondType_c(Position pos, JL5ParsedClassType base) {
    super(pos, base);
  }

  // PandaClassType Methods
  public List<ModeTypeVariable> modeTypeVars() {
    return ((PandaParsedClassType) this.base).modeTypeVars();
  }
  
  public void modeTypeVars(List<ModeTypeVariable> modeTypeVars) {
    ((PandaParsedClassType) this.base).modeTypeVars(modeTypeVars);
  } 

  public boolean isImplicitModeTypeVar() {
    return ((PandaParsedClassType) this.base).isImplicitModeTypeVar();
  } 

  public AttributeInstance attributeInstance() {
    return ((PandaParsedClassType) this.base).attributeInstance();
  }

  public void attributeInstance(AttributeInstance attributeInstance) {
    ((PandaParsedClassType) this.base).attributeInstance(attributeInstance);
  } 

  public boolean hasAttribute() {
    return ((PandaParsedClassType) this.base).hasAttribute();
  }

  public CopyInstance copyInstance() {
    return ((PandaParsedClassType) this.base).copyInstance();
  }

  public void copyInstance(CopyInstance copyInstance) {
    ((PandaParsedClassType) this.base).copyInstance(copyInstance);
  } 

  public boolean hasCopy() {
    return ((PandaParsedClassType) this.base).hasCopy();
  }

  public boolean needsAttribute() {
    return ((PandaParsedClassType) this.base).needsAttribute();
  }

  public void needsAttribute(boolean needsAttribute) {
    ((PandaParsedClassType) this.base).needsAttribute(needsAttribute);
  } 

  public boolean hasMcaseFields() {
    return ((PandaClassType) this.base).hasMcaseFields();
  } 

  public void hasMcaseFields(boolean hasMcaseFields) {
    ((PandaClassType) this.base).hasMcaseFields(hasMcaseFields);
  } 

  public boolean hasDynamicRecv() {
    return ((PandaClassType) this.base).hasDynamicRecv();
  } 

  public boolean containsModeTypeVariable(ModeTypeVariable mt) {
    return ((PandaClassType) this.base()).containsModeTypeVariable(mt);
  }

  public boolean instancesNeedTypePreservation() {
    return ((PandaClassType) this.base()).instancesNeedTypePreservation();
  }

  public void instancesNeedTypePreservation(boolean needs) {
    ((PandaClassType) this.base()).instancesNeedTypePreservation(needs);
  }


}
