package ent.types;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.types.Package;
import polyglot.util.*;
import polyglot.ext.jl7.types.*;

import java.util.List;
import java.util.Map;

public interface EntTypeSystem extends JL7TypeSystem {
  // Property Methods
  Map<String, ModeType> createdModeTypes();

  ModeType WildcardModeType();
  ModeType BottomModeType();
  ModeType BottomUserModeType();
  ModeType DynamicModeType();

  ClassType ModeSubstObject();

  void modesDeclPackage(Package pkg);
  Package modesDeclPackage();

  // Factory Methods
  AttributeInstance createAttributeInstance(Position pos, Flags flags);

  CopyInstance createCopyInstance(Position pos, ReferenceType container);

  McaseType createMcaseType(Type base);

  ModeType createModeType(String mode);

  ModeOrderingInstance createModeOrderingInstance();

  ModeTypeVariable createModeTypeVariable(Position pos, String name);
  ModeTypeVariable createBoundedExistential(Position pos);
  ModeTypeVariable createWildcardModeTypeVariable(Position pos, String name);

  ModeValueType createModeValueType(Type mode);

  // TypeSystem Methods
  ClassType wrapperClassOfModeSubstPrimitive(ModeSubstPrimitiveType t);

  Type createModeSubst(Type bt, List<Type> modeTypes);

  ModeSubst inferModeTypeArgs(EntProcedureInstance pi,
                              List<? extends Type> argTypes,
                              Type expectedReturnType);

  SemanticException checkModeSubst(EntClassType baseT, List<Type> mtArgs);

  EntProcedureInstance callValid(EntProcedureInstance mi,
                                 List<? extends Type> argTypes,
                                 List<? extends ReferenceType> actualTypeArgs,
                                 Type expectedReturnType,
                                 List<ModeType> actualModeTypeArgs);

  MethodInstance findMethod(ReferenceType container,
                            String name,
                            List<? extends Type> argTypes,
                            List<? extends ReferenceType> typeArgs,
                            ClassType currClass,
                            Type expectedReturnType,
                            boolean fromClient,
                            List<ModeType> actualModeTypeArgs) throws SemanticException;

  EntMethodInstance methodCallValid(EntMethodInstance mi,
                                    String name,
                                    List<? extends Type> argTypes,
                                    List<? extends ReferenceType> actualTypeArgs,
                                    Type expectedReturnType,
                                    List<ModeType> actualModeTypeArgs);
}
