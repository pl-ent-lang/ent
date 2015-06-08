package panda.types;

import polyglot.ast.Id;
import polyglot.types.*;
import polyglot.util.Position;
import polyglot.ext.jl7.types.JL7TypeSystem;

import java.util.List;
import java.util.Map;

public interface PandaTypeSystem extends JL7TypeSystem {
  // Property Methods
  Map<String, ModeType> createdModeTypes();

  ModeType WildcardModeType();
  ModeType DynamicModeType();

  // Factory Methods 
  AttributeInstance createAttributeInstance(Position pos, ReferenceType container, Flags flags);

  ModeType createModeType(String mode);

  ModeOrderingInstance createModeOrderingInstance();

  ModeTypeVariable createModeTypeVariable(Position pos, String name);

  ModeValueType createModeValueType(Mode mode);

  // TypeSystem Methods
  ClassType wrapperClassOfModeSubstPrimitive(ModeSubstPrimitiveType t);

  boolean isSubtypeModes(Mode lb, Mode ub);

  boolean isSupertypeModes(Mode lb, Mode ub);

  Type createModeSubst(Type bt, List<Mode> modeTypes);
}