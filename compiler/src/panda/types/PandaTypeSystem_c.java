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

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class PandaTypeSystem_c extends JL7TypeSystem_c implements PandaTypeSystem { 
  private Map<String, ModeType> createdModeTypes = new HashMap<>();

  private ModeType WildcardModeType;
  private ModeType DynamicModeType;

  private final String WILDCARD_MODE_TYPE_ID = "*";
  private final String DYNAMIC_MODE_TYPE_ID = "?";

  private ModeSubstEngine substEngine = new ModeSubstEngine();
  
  public PandaTypeSystem_c() {
    // Setup both the bottom and dynamic mode type instances
    this.WildcardModeType = this.createModeType(this.WILDCARD_MODE_TYPE_ID);
    this.DynamicModeType = this.createModeType(this.DYNAMIC_MODE_TYPE_ID);
  } 

  // Property Methods
  public Map<String, ModeType> createdModeTypes() {
    return this.createdModeTypes;
  } 

  public ModeType WildcardModeType() {
    return this.WildcardModeType;
  }

  public ModeType DynamicModeType() {
    return this.DynamicModeType;
  }

  private ModeSubstEngine substEngine() {
    return this.substEngine;
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
  public boolean isSubtypeModes(ModeType lb, ModeType ub) {
    return lb.isSubtypeOfModeImpl(ub);
  }

  public boolean isSupertypeModes(ModeType lb, ModeType ub) {
    return lb.isSupertypeOfModeImpl(ub);
  }

  public Type createModeSubst(Type bt, List<Type> modeTypes) {
    return this.substEngine().createModeSubst(bt, modeTypes);
  }

  public ClassType wrapperClassOfModeSubstPrimitive(ModeSubstPrimitiveType t) {
    // We need to inject a boxed primitive type with a mode
    ClassType ct = this.wrapperClassOfPrimitive(t);
    return (ClassType) this.createModeSubst(ct, t.modeTypeArgs());
  }

}
