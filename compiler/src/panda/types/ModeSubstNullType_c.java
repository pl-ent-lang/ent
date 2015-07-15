package panda.types;

import polyglot.types.*;

import java.util.ArrayList;
import java.util.List;

public class ModeSubstNullType_c extends ModeSubstType_c implements ModeSubstNullType {

  public ModeSubstNullType_c(NullType baseType, List<Type> modeTypeArgs) {
    super(baseType, modeTypeArgs);
  }

  @Override
  public ModeSubstType deepCopy() {
    return 
      new ModeSubstNullType_c(
          (NullType) this.baseType(), 
          new ArrayList<Type>(this.modeTypeArgs())
          );
  }

}
