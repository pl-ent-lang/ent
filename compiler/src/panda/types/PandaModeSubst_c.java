package panda.types;

import polyglot.types.Type;
import polyglot.types.ReferenceType;
import polyglot.types.ConstructorInstance;
import polyglot.types.FieldInstance;
import polyglot.types.MethodInstance;
import polyglot.types.MemberInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PandaModeSubst_c implements PandaModeSubst {
  private Map<ModeTypeVariable, Type> modeTypeMap;
  private PandaTypeSystem typeSystem;

  public PandaModeSubst_c(PandaTypeSystem typeSystem, 
                          Map<ModeTypeVariable, Type> modeTypeMap) {
    this.typeSystem = typeSystem;
    this.modeTypeMap = modeTypeMap;
  }

  // Property Methods
  public PandaTypeSystem typeSystem() {
    return this.typeSystem;
  }

  public void typeSystem(PandaTypeSystem typeSystem) {
    this.typeSystem = typeSystem;
  }

  public Map<ModeTypeVariable, Type> modeTypeMap() {
    return this.modeTypeMap;
  }

  public void modeTypeMap(Map<ModeTypeVariable, Type> modeTypeMap) {
    this.modeTypeMap = modeTypeMap;
  }

  // Business Methods
  public Type substType(Type type) {
    if (type instanceof PandaParsedClassType) {
      return this.substClassType((PandaParsedClassType)type);
    } 
    
    if (type instanceof ModeTypeVariable) {
      return this.substModeTypeVariable((ModeTypeVariable)type);
    }

    return type;
  }

  public Type substModeTypeVariable(ModeTypeVariable modeTypeVar) {
    return this.modeTypeMap().get(modeTypeVar);
  }

  // TODO : Change this to the hook style Impl
  public PandaModeSubstClassType substClassType(PandaParsedClassType classType) {
    return new PandaModeSubstClassType_c(this.typeSystem, 
                                         classType.position(), 
                                         classType, 
                                         this);
  }

  public ReferenceType substContainer(MemberInstance memberInst) {
    return this.substType(memberInst.container()).toReference();
  }

  public <T extends FieldInstance> T substField(T fieldInst) {
    ReferenceType container = this.substContainer(fieldInst);
    Type fieldType = this.substType(fieldInst.type());
    T substFieldInst = (T) fieldInst.copy();
    substFieldInst.setType(fieldType);
    substFieldInst.setContainer(container);
    return substFieldInst;
  }

  public <T extends MethodInstance> T substMethod(T methodInst) {
    ReferenceType container = this.substContainer(methodInst);
    Type returnType = this.substType(methodInst.returnType());
    List<? extends Type> formalTypes = this.substTypeList(methodInst.formalTypes());
    List<? extends Type> throwTypes = this.substTypeList(methodInst.formalTypes());
    T substMethodInst = (T) methodInst.copy();
    substMethodInst.setReturnType(returnType);
    substMethodInst.setFormalTypes(formalTypes);
    substMethodInst.setThrowTypes(throwTypes);
    substMethodInst.setContainer(container);
    return substMethodInst;
  }

  public <T extends ConstructorInstance> T substConstructor(T constructorInst) {
    ReferenceType container = this.substContainer(constructorInst);
    List<? extends Type> formalTypes = 
      this.substTypeList(constructorInst.formalTypes());
    List<? extends Type> throwTypes = 
      this.substTypeList(constructorInst.formalTypes());
    T substMethodInst = (T) constructorInst.copy();
    substMethodInst.setFormalTypes(formalTypes);
    substMethodInst.setThrowTypes(throwTypes);
    substMethodInst.setContainer(container);
    return substMethodInst;
  }

  public <T extends Type> List<T> substTypeList(List<T> list) {
    List<T> substList = new ArrayList<>();
    for (T t : list) {
      substList.add((T)this.substType(t));
    }
    return substList;
  }

  public <T extends FieldInstance> List<T> substFieldList(List<T> list) {
    List<T> substList = new ArrayList<>();
    for (T t : list) {
      substList.add(this.substField(t));
    }
    return substList;
  }

  public <T extends MethodInstance> List<T> substMethodList(List<T> list) {
    List<T> substList = new ArrayList<>();
    for (T t : list) {
      substList.add(this.substMethod(t));
    }
    return substList;
  } 

  public <T extends ConstructorInstance> List<T> substConstructorList(List<T> list) {
    List<T> substList = new ArrayList<>();
    for (T t : list) {
      substList.add(this.substConstructor(t));
    }
    return substList;
  }

}
