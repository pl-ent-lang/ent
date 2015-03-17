package panda.types;

import polyglot.ext.jl7.types.JL7TypeSystem_c;

import polyglot.ast.Id;
import polyglot.types.*;

import java.util.Map;
import java.util.HashMap;

public class PandaTypeSystem_c extends JL7TypeSystem_c implements PandaTypeSystem { 
  private Map<String, ModeType> createdModeTypes = new HashMap<String, ModeType>();
  
  public PandaTypeSystem_c() {
  } 

  // Property Methods
  Map<String, ModeType> createdModeTypes() {
    return this.createdModeTypes;
  }

  // Factory Methods
  public ModeType ModeType(String mode) {
    if (this.createdModeTypes().containsKey(mode)) {
      return this.createdModeTypes().get(mode);
    }
    ModeType modeType = new ModeType_c(this, mode);
    this.createdModeTypes().put(mode, modeType);
    return modeType;
  } 

  public ModeOrderingInstance ModeOrderingInstance() {
    return new ModeOrderingInstance_c(this);
  }
  
  public boolean isSubtypeModes(ModeType lowerBound, ModeType upperBound) {
    return lowerBound.isSubtypeOfModeImpl(upperBound);
  }

  public boolean isSupertypeModes(ModeType lowerBound, ModeType upperBound) {
    return lowerBound.isSupertypeOfModeImpl(upperBound);
  }

}
