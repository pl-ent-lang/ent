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

  // TypeSystem Methods
  ClassType wrapperClassOfModeSubstPrimitive(ModeSubstPrimitiveType t);

  boolean isSubtypeModes(ModeType lowerBound, ModeType upperBound);

  boolean isSupertypeModes(ModeType lowerBound, ModeType upperBound);

  Type instModeTypeVariables(ModeSubstParsedClassType classType, 
                             Map<ModeTypeVariable, Type> modeTypeMap);

  Type substModeType(Type baseType, Type modeType);

}
