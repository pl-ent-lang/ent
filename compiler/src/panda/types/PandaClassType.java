package panda.types;

import polyglot.ext.jl5.types.*;

import java.util.List;

public interface PandaClassType extends JL5ClassType {

  // Property Methods
  List<ModeTypeVariable> modeTypeVars();
  boolean isImplicitModeTypeVar();
  void modeTypeVars(List<ModeTypeVariable> modeTypeVars);

  AttributeInstance attributeInstance();
  void attributeInstance(AttributeInstance ai);
  boolean hasAttribute();

  CopyInstance copyInstance();
  void copyInstance(CopyInstance ci);
  boolean hasCopy();
}
