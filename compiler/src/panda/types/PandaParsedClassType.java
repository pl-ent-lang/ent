package panda.types;

import polyglot.ext.jl5.types.JL5ParsedClassType;

import java.util.List;

public interface PandaParsedClassType extends JL5ParsedClassType {

  // Property Methods
  List<ModeTypeVariable> modeTypeVariables();
  void modeTypeVariables(List<ModeTypeVariable> modeTypeVariables);

}
