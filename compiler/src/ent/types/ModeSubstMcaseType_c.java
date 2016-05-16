package ent.types;

import polyglot.types.*;

import java.util.ArrayList;
import java.util.List;

public class ModeSubstMcaseType_c extends ModeSubstType_c implements ModeSubstMcaseType {

  public ModeSubstMcaseType_c(McaseType baseType, List<Type> modeTypeArgs) {
    super(baseType, modeTypeArgs);
  }

  @Override
  public ModeSubstType deepCopy() {
    return new ModeSubstMcaseType_c((McaseType)this.baseType(),
                                    new ArrayList<Type>(this.modeTypeArgs()));
  }

  // McaseType Methods
  public Type base() { return ((McaseType)this.baseType()).base(); }

  @Override
  public boolean isCastValidImpl(Type toT) {
    if (!(toT instanceof ModeSubstType)) {
      return this.ts.isCastValid(this.baseType(), toT);
    }

    ModeSubstType st = (ModeSubstType)toT;
    return this.ts.isCastValid(this.baseType(), st.baseType()) && this.modeTypeArgsEquals(st);
  }

  @Override
  public boolean isImplicitCastValidImpl(Type toT) {
    if (!(toT instanceof ModeSubstType)) {
      return this.ts.isImplicitCastValid(this.baseType(), toT);
    }

    ModeSubstType st = (ModeSubstType)toT;
    return this.ts.isImplicitCastValid(this.baseType(), st.baseType()) &&
        this.modeTypeArgsEquals(st);
  }
}
