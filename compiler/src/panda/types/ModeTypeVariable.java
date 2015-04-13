package panda.types;

import polyglot.types.ClassType;
import polyglot.types.ReferenceType;
import polyglot.ext.param.types.Param;

public interface ModeTypeVariable extends ReferenceType, Param {

  // Property Methods
  String name();
  void name(String name);

  ClassType declaringClass();
  void declaringClass(ClassType declaringClass);
}

