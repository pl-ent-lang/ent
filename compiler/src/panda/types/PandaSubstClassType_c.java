package panda.types;

import polyglot.ext.jl5.types.JL5SubstClassType_c;
import polyglot.util.Position;

import java.util.List;

public class PandaSubstClassType_c extends JL5SubstClassType_c implements PandaSubstClassType {

  public PandaSubstClassType_c(PandaTypeSystem ts, 
                               Position pos, 
                               PandaParsedClassType base, 
                               PandaSubst subst) {
    super(ts, pos, base, subst);
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

}
