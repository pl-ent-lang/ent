package panda.types;

import polyglot.types.PrimitiveType;
import polyglot.types.Resolver;
import polyglot.types.Type;
import polyglot.types.Type_c;
import polyglot.types.TypeSystem;

import polyglot.ext.jl5.types.JL5PrimitiveType;

public class ModeSubstPrimitiveType_c extends PandaType_c implements ModeSubstPrimitiveType {

  public ModeSubstPrimitiveType_c(JL5PrimitiveType baseType,
                                  Type modeType) {
    super(baseType, modeType);
  }

  @Override
  public PandaType deepCopy() {
    return 
      new ModeSubstPrimitiveType_c((JL5PrimitiveType) this.baseType(),
                                   this.modeType());
  }

  // PrimitiveType Methods
  @Override
  public Kind kind() {
    return ((JL5PrimitiveType) this.baseType()).kind();
  }

  @Override
  public PrimitiveType toPrimitive() {
    return this;
  }

  // JL5PrimitiveType Methods
  @Override
  public String wrapperTypeString(TypeSystem ts) {
    return ((JL5PrimitiveType) this.baseType()).wrapperTypeString(ts);
  }

  // Named Methods
  @Override
  public String fullName() {
    return this.toString();
  }

  @Override
  public String name() {
    return this.toString();
  }

}
