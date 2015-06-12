package panda.types;

import polyglot.types.ClassType;
import polyglot.types.Type;
import polyglot.types.ReferenceType;
import polyglot.ext.param.types.Param;

import java.util.List;

public interface ModeTypeVariable extends Type {

  // Property Methods
  String name();
  void name(String name);

  List<Type> bounds();
  void bounds(List<Type> bounds);

  Type lowerBound();
  void lowerBound(Type lowerBound);
  boolean hasLowerBound();

  Type upperBound();
  void upperBound(Type upperBound);

  ClassType declaringClass();
  void declaringClass(ClassType declaringClass);

  int uniqueId();

  public boolean inferUpperBound();
}

