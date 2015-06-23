package panda.types;

import polyglot.types.Type;

import java.util.ArrayList;
import java.util.List;

public class ModeSubstMcaseType_c extends ModeSubstType_c implements ModeSubstMcaseType {

  public ModeSubstMcaseType_c(McaseType baseType,
                              List<Type> modeTypeArgs) {
    super(baseType, modeTypeArgs);
  }

  @Override
  public ModeSubstType deepCopy() {
    return 
      new ModeSubstMcaseType_c((McaseType) this.baseType(),
                               new ArrayList<Type>(this.modeTypeArgs()));
  }

  // McaseType Methods
  public Type base() {
    return ((McaseType) this.baseType()).base();
  }

}
