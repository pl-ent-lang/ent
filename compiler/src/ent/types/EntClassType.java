package ent.types;

import polyglot.ext.jl5.types.*;

import java.util.List;

public interface EntClassType extends JL5ClassType {

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

  boolean hasMcaseFields();
  public void hasMcaseFields(boolean hasMcaseFields);

  boolean hasDynamicRecv();

  boolean containsModeTypeVariable(ModeTypeVariable mt);

  void instancesNeedTypePreservation(boolean needs);
  boolean instancesNeedTypePreservation();

  // TODO : This probably needs to become a little more OO
  // Something like needsPreservation instead of seperate methods.
}
