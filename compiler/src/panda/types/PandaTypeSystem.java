package panda.types;

import polyglot.ast.Id;
import polyglot.types.*;
import polyglot.util.Position;
import polyglot.ext.jl7.types.JL7TypeSystem;

import java.util.Map;

public interface PandaTypeSystem extends JL7TypeSystem {
  // Property Methods
  Map<String, ModeType> createdModeTypes();

  ModeType bottomModeType();
  ModeType dynamicModeType();

  // Factory Methods 
  ModeType createModeType(String mode);

  ModeOrderingInstance createModeOrderingInstance();

  ModeTypeVariable createModeTypeVariable(Position pos, String name);

  PandaType createPandaType(Type baseType, Type modeType);
  
  // TypeSystem Methods
  boolean isSubtypeModes(ModeType lowerBound, ModeType upperBound);

  boolean isSupertypeModes(ModeType lowerBound, ModeType upperBound);

  Type substModeTypeVariables(PandaParsedClassType classType, 
                              Map<ModeTypeVariable, Type> modeTypeMap);

}
