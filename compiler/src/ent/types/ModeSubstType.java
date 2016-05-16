package ent.types;

import polyglot.types.Type;

import java.util.List;

// ModeSubstType represents the composition of a polyglot type and a substituted mode type
public interface ModeSubstType extends Type {

  // Property Methods
  Type baseType();
  void baseType(Type baseType);

  List<Type> modeTypeArgs();
  void modeTypeArgs(List<Type> modeTypeArgs);

  Type modeType();
  void modeType(Type modeType);

  ModeSubstType deepCopy();

  String argsAsString();
}
