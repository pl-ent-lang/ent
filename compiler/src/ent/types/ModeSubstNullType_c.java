package ent.types;

import polyglot.types.*;

import java.util.ArrayList;
import java.util.List;

public class ModeSubstNullType_c extends ModeSubstType_c implements ModeSubstNullType {

  public ModeSubstNullType_c(NullType baseType, List<Type> modeTypeArgs) {
    super(baseType, modeTypeArgs);
  }

  @Override
  public ModeSubstType deepCopy() {
    return new ModeSubstNullType_c((NullType)this.baseType(),
                                   new ArrayList<Type>(this.modeTypeArgs()));
  }

  @Override
  public boolean isCanonical() {
    return true;
  }

  @Override
  public boolean isNull() {
    return true;
  }

  @Override
  public boolean descendsFromImpl(Type ansT) {
    if (!(ansT instanceof ModeSubstType)) {
      return this.ts.descendsFrom(this.baseType(), ansT);
    }

    ModeSubstType st = (ModeSubstType)ansT;
    return this.ts.descendsFrom(this.baseType(), st.baseType());
  }

  @Override
  public boolean isCastValidImpl(Type toT) {
    if (!(toT instanceof ModeSubstType)) {
      return this.ts.isCastValid(this.baseType(), toT);
    }

    ModeSubstType st = (ModeSubstType)toT;
    return this.ts.isCastValid(this.baseType(), st.baseType());
  }

  @Override
  public boolean isImplicitCastValidImpl(Type toT) {
    if (!(toT instanceof ModeSubstType)) {
      return this.ts.isImplicitCastValid(this.baseType(), toT);
    }

    ModeSubstType st = (ModeSubstType)toT;
    return this.ts.isImplicitCastValid(this.baseType(), st.baseType());
  }
}
