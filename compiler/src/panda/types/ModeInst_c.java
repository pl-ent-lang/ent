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

public class ModeInst_c implements ModeInst {
  private Map<ModeTypeVariable, Type> modeTypeMap;
  private PandaTypeSystem typeSystem;

  public ModeInst_c(PandaTypeSystem typeSystem, 
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

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ModeInst)) {
      return false;
    }
    Map<ModeTypeVariable, Type> om = ((ModeInst) o).modeTypeMap();
    for (Map.Entry<ModeTypeVariable, Type> e : this.modeTypeMap().entrySet()) {
      Type ot = om.get(e.getKey());
      if (ot == null || !e.getValue().typeEqualsImpl(ot)) {
        return false;
      }
    }
    return true;
  }

  // Business Methods
  public Type instType(Type type) {
    if (type instanceof PandaType) {
      return this.instPandaType((PandaType)type);
    }

    if (type instanceof ModeTypeVariable) {
      return this.instModeTypeVariable((ModeTypeVariable)type);
    }

    return type;
  }

  public Type instPandaType(PandaType type) {
    PandaType inst = type.deepCopy();
    Type bt = this.instType(inst.baseType());
    Type mt = this.instType(inst.modeType());
    inst.baseType(bt);
    inst.modeType(mt);
    return inst;
  }

  public Type instModeTypeVariable(ModeTypeVariable modeTypeVar) {
    return this.modeTypeMap().get(modeTypeVar);
  }

  // TODO : Change this to the hook style Impl
  public ModeInstClassType instClassType(ModeSubstParsedClassType classType) {
    return new ModeInstClassType_c(classType, this);
  }

  public ReferenceType instContainer(MemberInstance memberInst) {
    return this.instType(memberInst.container()).toReference();
  }

  public <T extends FieldInstance> T instField(T fieldInst) {
    ReferenceType container = this.instContainer(fieldInst);
    Type fieldType = this.instType(fieldInst.type());
    T instFieldInst = (T) fieldInst.copy();
    instFieldInst.setType(fieldType);
    instFieldInst.setContainer(container);
    return instFieldInst;
  }

  public <T extends MethodInstance> T instMethod(T methodInst) {
    ReferenceType container = this.instContainer(methodInst);
    Type returnType = this.instType(methodInst.returnType());
    List<? extends Type> formalTypes = this.instTypeList(methodInst.formalTypes());
    List<? extends Type> throwTypes = this.instTypeList(methodInst.formalTypes());
    T instMethodInst = (T) methodInst.copy();
    instMethodInst.setReturnType(returnType);
    instMethodInst.setFormalTypes(formalTypes);
    instMethodInst.setThrowTypes(throwTypes);
    instMethodInst.setContainer(container);
    return instMethodInst;
  }

  public <T extends ConstructorInstance> T instConstructor(T constructorInst) {
    ReferenceType container = this.instContainer(constructorInst);
    List<? extends Type> formalTypes = 
      this.instTypeList(constructorInst.formalTypes());
    List<? extends Type> throwTypes = 
      this.instTypeList(constructorInst.formalTypes());
    T instMethodInst = (T) constructorInst.copy();
    instMethodInst.setFormalTypes(formalTypes);
    instMethodInst.setThrowTypes(throwTypes);
    instMethodInst.setContainer(container);
    return instMethodInst;
  }

  public <T extends Type> List<T> instTypeList(List<T> list) {
    List<T> instList = new ArrayList<>();
    for (T t : list) {
      instList.add((T)this.instType(t));
    }
    return instList;
  }

  public <T extends FieldInstance> List<T> instFieldList(List<T> list) {
    List<T> instList = new ArrayList<>();
    for (T t : list) {
      instList.add(this.instField(t));
    }
    return instList;
  }

  public <T extends MethodInstance> List<T> instMethodList(List<T> list) {
    List<T> instList = new ArrayList<>();
    for (T t : list) {
      instList.add(this.instMethod(t));
    }
    return instList;
  } 

  public <T extends ConstructorInstance> List<T> instConstructorList(List<T> list) {
    List<T> instList = new ArrayList<>();
    for (T t : list) {
      instList.add(this.instConstructor(t));
    }
    return instList;
  }

}
