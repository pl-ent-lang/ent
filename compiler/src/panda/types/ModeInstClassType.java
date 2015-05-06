package panda.types;

import polyglot.types.ReferenceType;
import polyglot.ext.param.types.SubstType;

public interface ModeInstClassType extends ReferenceType {

  ModeSubstParsedClassType base();

  ModeInst inst();

}
