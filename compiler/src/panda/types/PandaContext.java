package panda.types;

import polyglot.types.Context;
import polyglot.ext.jl5.types.JL5Context;

public interface PandaContext extends JL5Context {

  AttributeInstance currentAttribute();
  void currentAttribute(AttributeInstance currentAttribute);

  void addModeTypeVariable(ModeTypeVariable modeTypeVar);

  ModeTypeVariable findModeTypeVariableInThisScope(String name);

  boolean isAttribute();
}
