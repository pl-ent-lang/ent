package panda.types;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.types.Package;
import polyglot.util.*;
import polyglot.ext.jl7.types.*;

import java.util.List;
import java.util.Map;

public interface PandaTypeSystem extends JL7TypeSystem {
  // Property Methods
  Map<String, ModeType> createdModeTypes();

  ModeType WildcardModeType();
  ModeType BottomModeType();
  ModeType DynamicModeType();

  ClassType ModeSubstObject();

  void modesDeclPackage(Package pkg);
  Package modesDeclPackage();

  // Factory Methods 
  AttributeInstance createAttributeInstance(Position pos, ReferenceType container, Flags flags);

  CopyInstance createCopyInstance(Position pos, ReferenceType container);

  McaseType createMcaseType(Type base);

  ModeType createModeType(String mode);

  ModeOrderingInstance createModeOrderingInstance();

  ModeTypeVariable createModeTypeVariable(Position pos, String name);

  ModeValueType createModeValueType(Type mode);

  // TypeSystem Methods
  ClassType wrapperClassOfModeSubstPrimitive(ModeSubstPrimitiveType t);

  Type createModeSubst(Type bt, List<Type> modeTypes);

  ModeSubst inferModeTypeArgs(PandaProcedureInstance pi, List<? extends Type> argTypes, Type expectedReturnType);

  SemanticException checkModeSubst(PandaClassType baseT, List<Type> mtArgs);
}
