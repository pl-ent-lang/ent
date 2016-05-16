package ent.types;

import java.util.List;

import polyglot.util.InternalCompilerError;
import polyglot.types.Type;
import polyglot.types.ReferenceType;
import polyglot.types.ConstructorInstance;
import polyglot.types.FieldInstance;
import polyglot.types.MethodInstance;
import polyglot.types.ProcedureInstance;
import polyglot.types.MemberInstance;

import polyglot.ext.param.types.SubstType;

import polyglot.ext.jl5.types.RawClass;
import polyglot.ext.jl5.types.TypeVariable;
import polyglot.ext.jl5.types.JL5ArrayType;
import polyglot.ext.jl5.types.JL5PrimitiveType;

import polyglot.ext.jl5.types.JL5ParsedClassType;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ModeSubst {
  private EntTypeSystem ts;
  private Type baseType;
  private Map<ModeTypeVariable, Type> modeTypeMap;

  public ModeSubst(Type bt, Map<ModeTypeVariable, Type> mtMap) {
    this.baseType = bt;
    this.modeTypeMap = mtMap;
  }

  public EntTypeSystem ts() { return (EntTypeSystem)this.baseType().typeSystem(); }

  // Property Methods
  public Type baseType() { return this.baseType; }

  public Map<ModeTypeVariable, Type> modeTypeMap() { return this.modeTypeMap; }

  public ModeSubst deepCopy() {
    return new ModeSubst(this.baseType(), new HashMap<ModeTypeVariable, Type>(this.modeTypeMap()));
  }

  public Type substType(Type t) {
    if (t instanceof ModeSubstType) {
      return this.substModeSubstType((ModeSubstType)t);
    }

    if (t instanceof ModeTypeVariable) {
      return this.substModeTypeVariable((ModeTypeVariable)t);
    }

    if (t instanceof SubstType) {
      return this.substSubstType((SubstType<TypeVariable, ReferenceType>)t);
    }

    return t;
  }

  public Type substModeSubstType(ModeSubstType type) {
    ModeSubstType subst = type.deepCopy();

    Type bt = this.substType(subst.baseType());
    List<Type> mtArgs = new ArrayList<>();
    for (Type t : subst.modeTypeArgs()) {
      mtArgs.add(this.substType(t));
    }

    subst.baseType(bt);
    subst.modeTypeArgs(mtArgs);

    return subst;
  }

  public Type substSubstType(SubstType<TypeVariable, ReferenceType> st) {
    // We have to do a mode subst over each of the subst in the substtype (ugly)
    Type base = st.base();
    Map<TypeVariable, ReferenceType> tsubst = st.subst().substitutions();
    Map<TypeVariable, ReferenceType> ntsubst = new HashMap<>();

    for (Map.Entry<TypeVariable, ReferenceType> e : tsubst.entrySet()) {
      ntsubst.put(e.getKey(), (ReferenceType)this.substType(e.getValue()));
    }

    return this.ts().subst(base, ntsubst);
  }

  public Type substModeTypeVariable(ModeTypeVariable mtVar) {
    // If not in the map, don't subst, just leave
    Type t = this.modeTypeMap().get(mtVar);
    if (t != null) {
      return t;
    } else {
      return mtVar;
    }
  }

  /*
  public ReferenceType substContainer(MemberInstance mi,
                                      Map<ModeTypeVariable, Type> mtMap) {
    return this.substType(mi.container(), mtMap).toReference();
  }
  */

  public <T extends FieldInstance> T substField(T fi) {
    // ReferenceType cont = this.substContainer(fi, mtMap);
    Type ft = this.substType(fi.type());
    T subst = (T)fi.copy();
    subst.setType(ft);
    subst.setContainer((ReferenceType)this.baseType());
    return subst;
  }

  public <T extends MethodInstance> T substMethod(T mi) {
    // ReferenceType cont = this.substContainer(mi, mtMap);
    Type retType = this.substType(mi.returnType());
    List<? extends Type> formalTypes = this.substTypeList(mi.formalTypes());
    List<? extends Type> throwTypes = this.substTypeList(mi.throwTypes());
    T subst = (T)mi.copy();
    subst.setReturnType(retType);
    subst.setFormalTypes(formalTypes);
    subst.setThrowTypes(throwTypes);
    subst.setContainer((ReferenceType)this.baseType());

    if (subst instanceof EntMethodInstance) {
      EntMethodInstance esubst = (EntMethodInstance)subst;
      esubst.baseInstance((EntMethodInstance)mi);
      if (esubst.overmode() != null) {
        esubst.overmode((ModeType)this.substType(esubst.overmode()));
      }
      esubst.modeTypeVars(this.substTypeList(esubst.modeTypeVars()));
    }

    return subst;
  }

  public <T extends ConstructorInstance> T substConstructor(T ci) {
    // ReferenceType cont = this.substContainer(ci, mtMap);
    List<? extends Type> formalTypes = this.substTypeList(ci.formalTypes());
    List<? extends Type> throwTypes = this.substTypeList(ci.throwTypes());
    T subst = (T)ci.copy();
    subst.setFormalTypes(formalTypes);
    subst.setThrowTypes(throwTypes);
    subst.setContainer((ReferenceType)this.baseType());

    if (subst instanceof EntConstructorInstance) {
      EntConstructorInstance esubst = (EntConstructorInstance)subst;
      esubst.baseInstance((EntConstructorInstance)ci);
      if (esubst.overmode() != null) {
        esubst.overmode((ModeType)this.substType(esubst.overmode()));
      }
    }

    return subst;
  }

  public <T extends ProcedureInstance> T substProcedure(T pi) {
    if (pi instanceof MethodInstance) {
      return (T)this.substMethod((MethodInstance)pi);
    } else {
      return (T)this.substConstructor((ConstructorInstance)pi);
    }
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
