package panda.types;

import panda.ast.PandaLang_c;

import polyglot.ast.Id;
import polyglot.frontend.Source;
import polyglot.types.*;
import polyglot.util.Position;
import polyglot.ext.jl7.types.JL7TypeSystem_c;

import java.util.Map;
import java.util.HashMap;

public class PandaTypeSystem_c extends JL7TypeSystem_c implements PandaTypeSystem { 
  private Map<String, ModeType> createdModeTypes = new HashMap<>();

  private ModeType bottomModeType;
  private ModeType dynamicModeType;

  private final String BOTTOM_MODE_TYPE_ID = "PANDA_BOTTOM";
  private final String DYNAMIC_MODE_TYPE_ID = "?";
  
  public PandaTypeSystem_c() {
    // Setup both the bottom and dynamic mode type instances
    this.bottomModeType = this.createModeType(this.BOTTOM_MODE_TYPE_ID);
    this.dynamicModeType = this.createModeType(this.DYNAMIC_MODE_TYPE_ID);
  } 

  // Property Methods
  public Map<String, ModeType> createdModeTypes() {
    return this.createdModeTypes;
  } 

  public ModeType bottomModeType() {
    return this.bottomModeType;
  }

  public ModeType dynamicModeType() {
    return this.dynamicModeType;
  }

  // Factory Methods
  @Override
  public ParsedClassType createClassType(LazyClassInitializer init, 
                                         Source fromSource) {
    return new PandaParsedClassType_c(this, init, fromSource);
  }

  @Override
  public Context createContext() {
    return new PandaContext_c(PandaLang_c.instance, this);
  }

  public ModeType createModeType(String mode) {
    if (this.createdModeTypes().containsKey(mode)) {
      return this.createdModeTypes().get(mode);
    }
    ModeType modeType = new ModeType_c(this, mode);
    this.createdModeTypes().put(mode, modeType);
    return modeType;
  } 

  public ModeOrderingInstance createModeOrderingInstance() {
    return new ModeOrderingInstance_c(this);
  }

  public ModeTypeVariable createModeTypeVariable(Position pos, String name) {
    return new ModeTypeVariable_c(this, pos, name);
  }

  public PandaType createPandaType(Type baseType, Type modeType) {
    return new PandaType_c(this, baseType, modeType);
  }
  
  // TypeSystem Methods
  public boolean isSubtypeModes(ModeType lowerBound, ModeType upperBound) {
    return lowerBound.isSubtypeOfModeImpl(upperBound);
  }

  public boolean isSupertypeModes(ModeType lowerBound, ModeType upperBound) {
    return lowerBound.isSupertypeOfModeImpl(upperBound);
  }

  public Type substModeTypeVariables(
      PandaParsedClassType classType, 
      Map<ModeTypeVariable, Type> modeTypeMap) {
    return this.createPandaModeSubst(modeTypeMap).substClassType(classType);
  }

  protected PandaModeSubst createPandaModeSubst(
      Map<ModeTypeVariable, Type> modeTypeMap) {
    // TODO: Add the caching here
    PandaModeSubst modeSubst = this.modeSubstImpl(modeTypeMap);
    return modeSubst;
  }

  protected PandaModeSubst modeSubstImpl(
      Map<ModeTypeVariable, Type> modeTypeMap) {
    return new PandaModeSubst_c(this, modeTypeMap);
  }



}
