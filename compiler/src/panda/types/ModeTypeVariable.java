package panda.types;

import polyglot.types.*;
import polyglot.ext.param.types.*;

import java.util.List;

public interface ModeTypeVariable extends ModeType {

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

  ProcedureInstance declaringProc();
  void declaringProc(ProcedureInstance declaringProc);

  int uniqueId();

  public boolean inferUpperBound();

  int index();
  void index(int index);
}

