package panda.types;

import polyglot.ext.jl5.types.JL5Context;

public interface PandaContext extends JL5Context {

  void addModeTypeVariable(ModeTypeVariable modeTypeVar);

  ModeTypeVariable findModeTypeVariableInThisScope(String name);
}
