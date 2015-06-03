package panda.types;

import polyglot.types.ClassType;
import polyglot.types.Type;
import polyglot.types.ReferenceType;
import polyglot.ext.param.types.Param;

import java.util.List;

public interface ModeTypeVariable extends Type, Mode {

  // Property Methods
  String name();
  void name(String name);

  List<Type> bounds();
  void bounds(List<Type> bounds);

  ModeType upperBound();
  void upperBound(ModeType upperBound);

  ClassType declaringClass();
  void declaringClass(ClassType declaringClass);

  public boolean inferUpperBound();
}

