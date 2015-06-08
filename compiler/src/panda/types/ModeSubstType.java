package panda.types;

import polyglot.types.Type;

import java.util.List;

// ModeSubstType represents the composition of a polyglot type and a substituted mode type
public interface ModeSubstType extends Type {

  // Property Methods
  Type baseType();
  void baseType(Type baseType);

  List<Mode> modeTypeArgs();
  void modeTypeArgs(List<Mode> modeTypeArgs); 

  Mode modeType(); 
  void modeType(Mode modeType); 

  ModeSubstType deepCopy();

  String argsAsString();
}
