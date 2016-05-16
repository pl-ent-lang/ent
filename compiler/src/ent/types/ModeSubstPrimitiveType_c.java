package ent.types;

import polyglot.types.*;
import polyglot.util.*;

import polyglot.ext.jl5.types.*;

import java.util.ArrayList;
import java.util.List;

public class ModeSubstPrimitiveType_c extends ModeSubstType_c implements ModeSubstPrimitiveType {

  public ModeSubstPrimitiveType_c(JL5PrimitiveType baseType, List<Type> modeTypeArgs) {
    super(baseType, modeTypeArgs);
  }

  @Override
  public ModeSubstType deepCopy() {
    return new ModeSubstPrimitiveType_c((JL5PrimitiveType)this.baseType(),
                                        new ArrayList<Type>(this.modeTypeArgs()));
  }

  // PrimitiveType Methods
  @Override
  public Kind kind() {
    return ((JL5PrimitiveType)this.baseType()).kind();
  }

  // JL5PrimitiveType Methods
  @Override
  public String wrapperTypeString(TypeSystem ts) {
    return ((JL5PrimitiveType)this.baseType()).wrapperTypeString(ts);
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

  // Type Methods
  /*
  @Override
  public boolean descendsFromImpl(Type ancestor) {
    return false;
  }

  @Override
  public boolean isImplicitCastValidImpl(Type toT) {
    if (!(toT instanceof ModeSubstType)) {
      return this.ts.isImplicitCastValid(this.baseType(), toT);
    }

    ModeSubstType st = (ModeSubstType) toT;
    return this.ts.isImplicitCastValid(this.baseType(), st.baseType()) &&
           this.modeTypeArgsImplicit(st);
  }

  @Override
  public boolean isCastValidImpl(Type toT) {
    if (!(toT instanceof ModeSubstType)) {
      return this.ts.isCastValid(this.baseType(), toT);
    }

    ModeSubstType st = (ModeSubstType) toT;
    return this.ts.isCastValid(this.baseType(), st.baseType()) &&
           this.modeTypeArgsEquals(st);
  }
  */
}
