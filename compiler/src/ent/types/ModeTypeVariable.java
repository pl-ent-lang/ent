package ent.types;

import polyglot.types.*;
import polyglot.ext.param.types.*;

import java.util.List;

public interface ModeTypeVariable extends ModeType {

  // Property Methods
  String name();
  void name(String name);

  boolean isDynRecvr();
  void isDynRecvr(boolean isDynRecvr);

  List<Type> lowerBounds();
  void lowerBounds(List<Type> lowerBounds);

  List<Type> upperBounds();
  void upperBounds(List<Type> upperBounds);

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

  boolean isExistential();
  void isExistential(boolean isExistential);

  public boolean inferBounds();

  int index();
  void index(int index);
}
