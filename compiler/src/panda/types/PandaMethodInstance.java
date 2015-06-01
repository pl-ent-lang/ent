package panda.types;

import polyglot.ext.jl5.types.JL5MethodInstance;

import java.util.List;

public interface PandaMethodInstance extends JL5MethodInstance {

  List<ModeTypeVariable> modeTypeVars();
  void modeTypeVars(List<ModeTypeVariable> modeTypeVars);

}
