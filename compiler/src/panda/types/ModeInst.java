package panda.types;

import polyglot.types.Type;
import polyglot.types.ReferenceType;
import polyglot.types.ConstructorInstance;
import polyglot.types.FieldInstance;
import polyglot.types.MethodInstance;
import polyglot.types.MemberInstance;

import java.util.List;
import java.util.Map;

public interface ModeInst {

  // Property Methods
  Map<ModeTypeVariable, Type> modeTypeMap();
  void modeTypeMap(Map<ModeTypeVariable, Type> modeTypeMap);

  Type instType(Type type);
  ModeInstClassType instClassType(ModeSubstParsedClassType classType);

  <T extends FieldInstance> T instField(T fieldInst);
  <T extends MethodInstance> T instMethod(T methodInst);
  <T extends ConstructorInstance> T instConstructor(T constructorInst);
  <T extends Type> List<T> instTypeList(List<T> list);
  <T extends FieldInstance> List<T> instFieldList(List<T> list);
  <T extends MethodInstance> List<T> instMethodList(List<T> list);
  <T extends ConstructorInstance> List<T> instConstructorList(List<T> list);

}
