package ent.types;

import polyglot.types.TypeObject;
import polyglot.types.SemanticException;

import java.util.Map;
import java.util.HashMap;

public interface ModeOrderingInstance extends TypeObject {

  Map<ModeType, ModeType> modeOrdering();

  void insertModeTypeOrdering(ModeType lowerBound, ModeType upperBound) throws SemanticException;
  void buildModeTypeOrdering() throws SemanticException;
}
