package panda.types;

import panda.ast.PandaLang_c;

import polyglot.ast.Id;
import polyglot.frontend.Source;
import polyglot.types.*;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;

import polyglot.ext.param.types.Subst;

import polyglot.ext.jl5.types.RawClass;
import polyglot.ext.jl5.types.TypeVariable;
import polyglot.ext.jl5.types.JL5ArrayType;
import polyglot.ext.jl5.types.JL5PrimitiveType;

import polyglot.ext.jl5.types.JL5ParsedClassType;
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

  // Factory Methods / Previous TypeSystem Methods
  @Override
  public ParsedClassType createClassType(LazyClassInitializer init, 
                                         Source fromSource) {
    return new PandaParsedClassType_c(this, init, fromSource);
  }

  @Override
  public Context createContext() {
    return new PandaContext_c(PandaLang_c.instance, this);
  }

  @Override
  public RawClass rawClass(JL5ParsedClassType base, Position pos) {
    if (!canBeRaw(base)) {
      throw new InternalCompilerError("Can only create a raw class with a parameterized class");
    }
    return new PandaRawClass_c(base, pos);
  }

  @Override
  protected 
  Subst<TypeVariable, ReferenceType> substImpl(Map<TypeVariable, ? extends ReferenceType> substMap) {
    return new PandaSubst_c(this, substMap);
  }

  // Panda TypeSystem Methods

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

  // TypeSystem Methods
  public boolean isSubtypeModes(ModeType lowerBound, ModeType upperBound) {
    return lowerBound.isSubtypeOfModeImpl(upperBound);
  }

  public boolean isSupertypeModes(ModeType lowerBound, ModeType upperBound) {
    return lowerBound.isSupertypeOfModeImpl(upperBound);
  }

  public Type instModeTypeVariables(
      ModeSubstParsedClassType classType, 
      Map<ModeTypeVariable, Type> modeTypeMap) {
    return this.createModeInst(modeTypeMap).instClassType(classType);
  }

  protected ModeInst createModeInst(Map<ModeTypeVariable, Type> modeTypeMap) {
    // TODO: Add the caching here
    ModeInst modeInst = this.modeInstImpl(modeTypeMap);
    return modeInst;
  }

  protected ModeInst modeInstImpl(Map<ModeTypeVariable, Type> modeTypeMap) {
    return new ModeInst_c(this, modeTypeMap);
  }

  public Type substModeType(Type baseType, Type modeType) {
    // TODO : These are seperated because they are special cases and need some
    // more complicated logic to be handled. 
    if (baseType instanceof TypeVariable || baseType instanceof ModeTypeVariable) {
      return baseType;
    }


    if (baseType instanceof JL5PrimitiveType) {
      return new ModeSubstPrimitiveType_c((JL5PrimitiveType) baseType, modeType);

    } else if (baseType instanceof JL5ArrayType) {
      return new ModeSubstArrayType_c((JL5ArrayType) baseType, modeType);

    } else if (baseType instanceof PandaParsedClassType) {
      return new ModeSubstParsedClassType_c((PandaParsedClassType) baseType, modeType);

    } else if (baseType instanceof PandaRawClass) {
      return new ModeSubstRawClass_c((PandaRawClass) baseType, modeType);

    } else if (baseType instanceof PandaSubstClassType) {
      return new ModeSubstSubstClassType_c((PandaSubstClassType) baseType, modeType);

    } else {
      // Not yet impl
      throw new InternalCompilerError("Implement " + baseType.getClass() + "!");
    } 
  }

  public ClassType wrapperClassOfModeSubstPrimitive(ModeSubstPrimitiveType t) {
    // We need to inject a boxed primitive type with a mode
    ClassType ct = this.wrapperClassOfPrimitive(t);
    return (ClassType) this.substModeType(ct, t.modeType());
  }

}
