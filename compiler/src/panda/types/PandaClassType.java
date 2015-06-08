package panda.types;

import panda.types.ModeTypeVariable;

import polyglot.ext.jl5.types.JL5ClassType;

import java.util.List;

public interface PandaClassType extends JL5ClassType {

  // Property Methods
  List<ModeTypeVariable> modeTypeVars();
  void modeTypeVars(List<ModeTypeVariable> modeTypeVars);

  AttributeInstance attributeInstance();
  void attributeInstance(AttributeInstance ai);
}
