package panda.types;

import polyglot.types.Type;
import polyglot.types.ReferenceType;
import polyglot.types.ConstructorInstance;
import polyglot.types.FieldInstance;
import polyglot.types.MethodInstance;
import polyglot.types.MemberInstance;

import java.util.List;
import java.util.Map;

public interface PandaModeSubst {

  // Property Methods
  Map<ModeTypeVariable, Type> modeTypeMap();
  void modeTypeMap(Map<ModeTypeVariable, Type> modeTypeMap);

  Type substType(Type type);
  PandaModeSubstClassType substClassType(PandaParsedClassType classType);
  <T extends FieldInstance> T substField(T fieldInst);
  <T extends MethodInstance> T substMethod(T methodInst);
  <T extends ConstructorInstance> T substConstructor(T constructorInst);
  <T extends Type> List<T> substTypeList(List<T> list);
  <T extends FieldInstance> List<T> substFieldList(List<T> list);
  <T extends MethodInstance> List<T> substMethodList(List<T> list);
  <T extends ConstructorInstance> List<T> substConstructorList(List<T> list);

}
