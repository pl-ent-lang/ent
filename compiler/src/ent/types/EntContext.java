package ent.types;

import polyglot.types.Context;
import polyglot.ext.jl5.types.JL5Context;

public interface EntContext extends JL5Context {

  void addModeTypeVariable(ModeTypeVariable modeTypeVar);

  ModeTypeVariable findModeTypeVariableInThisScope(String name);

  boolean isAttribute();
  AttributeInstance currentAttribute();
  EntContext attributorParent();
}
