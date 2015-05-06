package panda.types;

import polyglot.types.Type;

// PandaType represents the composition of a polyglot type and a substituted mode type
public interface PandaType extends Type {
  // Property Methods
  Type modeType(); 
  void modeType(Type modeType); 

  Type baseType();
  void baseType(Type baseType);

  PandaType deepCopy();
}
