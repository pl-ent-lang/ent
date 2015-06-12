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

  List<Mode> bounds();
  void bounds(List<Mode> bounds);

  Mode lowerBound();
  void lowerBound(Mode lowerBound);
  boolean hasLowerBound();

  Mode upperBound();
  void upperBound(Mode upperBound);

  ClassType declaringClass();
  void declaringClass(ClassType declaringClass);

  public boolean inferUpperBound();
}

