package panda.types;

import polyglot.types.PrimitiveType;
import polyglot.types.Resolver;
import polyglot.types.Type;
import polyglot.types.Type_c;
import polyglot.types.TypeSystem;

import polyglot.ext.jl5.types.JL5PrimitiveType;

import java.util.ArrayList;
import java.util.List;

public class ModeSubstPrimitiveType_c extends ModeSubstType_c implements ModeSubstPrimitiveType {

  public ModeSubstPrimitiveType_c(JL5PrimitiveType baseType,
                                  List<Type> modeTypeArgs) {
    super(baseType, modeTypeArgs);
  }

  @Override
  public ModeSubstType deepCopy() {
    return 
      new ModeSubstPrimitiveType_c((JL5PrimitiveType) this.baseType(),
                                   new ArrayList<Type>(this.modeTypeArgs()));
  }

  // PrimitiveType Methods
  @Override
  public boolean isPrimitive() {
    return true;
  }

  @Override
  public Kind kind() {
    return ((JL5PrimitiveType) this.baseType()).kind();
  }

  @Override
  public PrimitiveType toPrimitive() {
    return this;
  }

  @Override
  public boolean isImplicitCastValidImpl(Type toType) {
    if (!(toType instanceof ModeSubstType)) {
      System.out.println("WARNING: isImplicitCastValidImpl check on " + this + " " + toType);
      return this.ts.typeEquals(this.baseType(), toType);
    }

    ModeSubstType m = (ModeSubstType) toType;
    return ts.isImplicitCastValid(this.baseType(), m.baseType()) &&
           ts.typeEquals(this.modeType(), m.modeType());
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
