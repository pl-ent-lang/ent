package panda.types;

import polyglot.util.Position;
import polyglot.ext.jl5.types.RawClass_c;
import polyglot.ext.jl5.types.JL5ParsedClassType;

public class PandaRawClass_c extends RawClass_c implements PandaRawClass {

  public PandaRawClass_c(JL5ParsedClassType classType, Position pos) {
    super(classType, pos);
  }

}

