package panda.types;

import panda.ast.*;
import panda.types.reflect.*;

import polyglot.ast.*;
import polyglot.frontend.*;
import polyglot.types.*;
import polyglot.types.reflect.*;
import polyglot.util.*;

import polyglot.ext.param.types.*;
import polyglot.ext.jl5.types.*;
import polyglot.ext.jl5.types.inference.*;
import polyglot.ext.jl7.types.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class PandaTypeSystem_c extends JL7TypeSystem_c implements PandaTypeSystem { 
  private Map<String, ModeType> createdModeTypes = new HashMap<>();

  private ModeType WildcardModeType;
  private ModeType DynamicModeType;
  private ModeType BottomModeType;

  private ModeSubstEngine substEngine = new ModeSubstEngine(this);
  
  public PandaTypeSystem_c() {
    // Setup both the bottom and dynamic mode type instances
    this.BottomModeType = this.createModeType("_");
    this.WildcardModeType = this.createModeType("*");
    this.DynamicModeType = this.createModeType("?");
  } 

  // Property Methods
  public Map<String, ModeType> createdModeTypes() {
    return this.createdModeTypes;
  } 

  public ModeType BottomModeType() {
    return this.BottomModeType;
  }

  public ModeType WildcardModeType() {
    return this.WildcardModeType;
  }

  public ModeType DynamicModeType() {
    return this.DynamicModeType;
  }

  private ModeSubstEngine substEngine() {
    return this.substEngine;
  }

  private ClassType ModeSubstObject = null;

  public ClassType ModeSubstObject() {
    if (this.ModeSubstObject == null) {
      this.ModeSubstObject =
        (ClassType) this.createModeSubst(
          this.Object(),
          Arrays.<Type>asList(this.WildcardModeType())
          );
    }
    return this.ModeSubstObject;
  }

  // Factory Methods / Previous TypeSystem Methods
  /*
  @Override
  public ClassFileLazyClassInitializer classFileLazyClassInitializer(ClassFile clazz) { 
    return new PandaClassFileLazyClassInitializer(clazz, this);
  }
  */

  @Override
  public JL5ConstructorInstance constructorInstance(Position pos,
          ClassType container, Flags flags, List<? extends Type> argTypes,
          List<? extends Type> excTypes, List<TypeVariable> typeParams) {
      assert_(container);
      assert_(argTypes);
      assert_(excTypes);
      assert_(typeParams);
      return new PandaConstructorInstance_c(this,
                                            pos,
                                            container,
                                            flags,
                                            argTypes,
                                            excTypes,
                                            typeParams,
                                            null);
    }


  @Override
  public ParsedClassType createClassType(LazyClassInitializer init, 
                                         Source fromSource) {
    return new PandaParsedClassType_c(this, init, fromSource);
  }

  @Override
  public DiamondType diamondType(Position pos, JL5ParsedClassType base) {
    return new PandaDiamondType_c(pos, base);
  }

  private boolean isSpecialModeSubstCase(Type l, Type u) {
    return (!(l instanceof ModeSubstType) && u instanceof ModeSubstType);
  }

  @Override
  public boolean typeEquals(Type l, Type u) {
    if (panda.Main.PDEBUG) {
      System.out.println("PandaTypeSystem::typeEquals(" + l + ", " + u + ")");
      System.out.println("PandaTypeSystem::typeEquals-Class(" + l.getClass() + ", " + u.getClass() + ")");
    }

    if (super.typeEquals(l, u)) {
      return true;
    }

    if (u instanceof ModeTypeVariable) {
      return this.typeEquals(l, ((ModeTypeVariable) u).lowerBound());
    }

    if (this.isSpecialModeSubstCase(l, u)) {
      return this.typeEquals(l, ((ModeSubstType) u).baseType());
    }

    return false;
  }

  @Override
  public boolean isSubtype(Type l, Type u) {
    if (panda.Main.PDEBUG) {
      System.out.println("PandaTypeSystem::isSubtype(" + l + ", " + u + ")");
      System.out.println("PandaTypeSystem::isSubtype-Class(" + l.getClass() + ", " + u.getClass() + ")");
    }

    if (super.isSubtype(l, u)) {
      return true;
    }

    // TODO : Special interception of the way type variables are handled.
    // Until we decide on strengthening/weaking for type variables, let
    // type variables that do not yet have a mode subst pass through to
    // an objec that does
    if (l instanceof TypeVariable && 
        !(l instanceof ModeSubstType) &&
        u instanceof ModeSubstType) {
      return this.isSubtype(l, ((ModeSubstType) u).baseType());
    }

    if (this.isSpecialModeSubstCase(l, u)) {
      return this.isSubtype(l, ((ModeSubstType) u).baseType());
    }

    return false;
  }


  @Override
  public boolean descendsFrom(Type l, Type u) {
    if (panda.Main.PDEBUG) {
      System.out.println("PandaTypeSystem::descendsFrom(" + l + ", " + u + ")");
      System.out.println("PandaTypeSystem::descendsFrom-Class(" + l.getClass() + ", " + u.getClass() + ")");
    }
    if (super.descendsFrom(l, u)) {
      return true;
    }

    if (u instanceof ModeTypeVariable) {
      return this.isSubtype(l, ((ModeTypeVariable) u).lowerBound());
    }

    /*
    if (this.isSpecialModeSubstCase(l, u)) {
      return this.descendsFrom(l, ((ModeSubstType) u).baseType());
    }
    */

    return false;
  }

  @Override
  public boolean isCastValid(Type l, Type u) {
    if (panda.Main.PDEBUG) {
      System.out.println("PandaTypeSystem::isCastValid(" + l + ", " + u + ")");
      System.out.println("PandaTypeSystem::isCastValid-Class(" + l.getClass() + ", " + u.getClass() + ")");
    }

    if (super.isCastValid(l, u)) {
      return true;
    }

    /*
    if (this.isSpecialModeSubstCase(l, u)) {
      return this.isCastValid(l, ((ModeSubstType) u).baseType());
    }
    */

    return false;
  }

  @Override
  public boolean isImplicitCastValid(Type l, Type u) {
    if (panda.Main.PDEBUG) {
      System.out.println("PandaTypeSystem::isImplicitCastValid(" + l + ", " + u + ")");
      System.out.println("PandaTypeSystem::isImplicitCastValid-Class(" + l.getClass() + ", " + u.getClass() + ")");
    }

    if (super.isImplicitCastValid(l, u)) {
      return true;
    }

    /*
    if (this.isSpecialModeSubstCase(l, u)) {
      return this.isImplicitCastValid(l, ((ModeSubstType) u).baseType());
    }
    */

    return false;
  }

  @Override
  public LinkedList<Type> isImplicitCastValidChain(Type l, Type u) {
    if (panda.Main.PDEBUG) {
      System.out.println("PandaTypeSystem::isImplicitCastValidChain(" + l + ", " + u + ")");
      System.out.println("PandaTypeSystem::isImplicitCastValidChain-Class(" + l.getClass() + ", " + u.getClass() + ")");
    }
    return super.isImplicitCastValidChain(l, u);
  }

  @Override
  public Context createContext() {
    return new PandaContext_c(PandaLang_c.instance, this);
  }

  private boolean inferModeTypeArg(List<ModeTypeVariable> mtVars,
                                   Type baset, 
                                   Type actt,
                                   Map<ModeTypeVariable, Type> mtMap) {
    if (!(baset instanceof ModeSubstType)) {
      return true;
    }

    // TODO : This is an O(n^2) lookup, keep a hash to avoid
    ModeSubstType st = (ModeSubstType) baset;
    for (Type t : st.modeTypeArgs()) {
      // The O(n) lookup from mtVars.contains
      if (t instanceof ModeTypeVariable && mtVars.contains(t)) {
        // This is a mode type var that is part of our inference, so infer it
        ModeTypeVariable mtVar = (ModeTypeVariable) t;

        // MODE_NOTE: If the actual type is not a mode subst type, throw in
        // wildcard for now.
        Type inft = null;
        if (actt instanceof ModeSubstType) {
          inft = ((ModeSubstType) actt).modeType();
        } else {
          inft = this.WildcardModeType();
        }

        if (mtMap.containsKey(mtVar)) {
          if (!mtMap.get(mtVar).typeEqualsImpl(inft)) {
            return false;
          }
        } else {
          mtMap.put(mtVar, inft);
        } 
      }
    }

    // Just like the opposite (in ModeSubst) we need to dip down in the subst
    // over the generic type
    Type actt1 = null;
    if (actt instanceof ModeSubstType) {
      actt1 = ((ModeSubstType) actt).baseType();
    } else {
      actt1 = actt;
    }

    if (st.baseType() instanceof SubstType && actt1 instanceof SubstType) {
      Type t1 = ((SubstType) st.baseType()).base();
      Type t2 = ((SubstType) actt1).base();

      if (!this.isCastValid(t1, t2)) {
        // Forget the inference, the type system will flag this as invalid
        return false;
      }

      Map<TypeVariable,ReferenceType> stSubsts = 
        ((SubstType) st.baseType()).subst().substitutions();
      Map<TypeVariable,ReferenceType> acttSubsts = 
        ((SubstType) actt1).subst().substitutions();

      JL5SubstClassType t3 = 
        this.findGenericSupertype((JL5ParsedClassType) t1, (ReferenceType) t2);
      Map<TypeVariable,TypeVariable> tvMap = new HashMap<>();
      if (t3 != null) {
        for (Map.Entry<TypeVariable,ReferenceType> e : t3.subst().substitutions().entrySet()) {
          tvMap.put(e.getKey(), (TypeVariable) e.getValue());
        }
      } else {
        for (Map.Entry<TypeVariable,ReferenceType> e : stSubsts.entrySet()) {
          tvMap.put(e.getKey(), e.getKey());
        }
      }

      for (Map.Entry<TypeVariable,TypeVariable> e : tvMap.entrySet()) {

        if (!acttSubsts.containsKey(e.getValue())) {
          // We cannot infer, the subst are not equal
          return false;
        }

        if (!this.inferModeTypeArg(mtVars,
                                   stSubsts.get(e.getKey()),
                                   acttSubsts.get(e.getValue()),
                                   mtMap)) {
          return false;
        }
      }
    } 

    return true;
  }

  public ModeSubst inferModeTypeArgs(PandaProcedureInstance pi,
                                      List <? extends Type> argTypes,
                                      Type expectedReturnType) {
    // Infer the mode type variable first, error if not possible
    Map<ModeTypeVariable,Type> mtMap = new HashMap<>();
    for (int i = 0; i < pi.formalTypes().size(); ++i) {
      if (!this.inferModeTypeArg(pi.modeTypeVars(),
                                 pi.formalTypes().get(i),
                                 argTypes.get(i),
                                 mtMap)) {
        return null;
      }
    }

    // Check return type as well
    if (expectedReturnType != null && 
        !this.inferModeTypeArg(pi.modeTypeVars(),
                               ((PandaMethodInstance) pi).returnType(),
                               expectedReturnType,
                               mtMap)) {
      return null;
    }


    ModeSubstClassType sct = (ModeSubstClassType) pi.container();
    ModeSubst subst = sct.modeSubst().deepCopy();
    subst.modeTypeMap().putAll(mtMap);

    // TODO : Not sure if this is right
    if (mtMap.isEmpty()) {
      return subst;
    }

    // We have our inferred map, now check that constraints over the procedures
    // mode type variables are satisfied

    mtMap = subst.modeTypeMap();
    for (ModeTypeVariable mtv : pi.modeTypeVars()) {
      Type sm = mtMap.get(mtv);

      // Check that all bounds are satisfied
      for (Type m : mtv.bounds()) {
        if (m instanceof ModeTypeVariable) {
          m = mtMap.get(m);
        }

        if (!this.isSubtype(sm, m)) {
          System.out.println("Attempting to subst with " + sm + " failing on contraint " + m);
          return null;
        }
      }
    }

    return subst; 
  }

  @Override
  public JL5ProcedureInstance callValid(JL5ProcedureInstance mi,
                                        List<? extends Type> argTypes,
                                        List<? extends ReferenceType> actualTypeArgs) {
    if (!(mi.container() instanceof ModeSubstType)) {
      // TODO : Just kick up to parent for now
      return super.callValid(mi, argTypes, actualTypeArgs);
    } 

    // Check from JL5TypeSystem::methodCallValid
    // Repeat it here to avoid some of the calls that could crash
    // our check
    if (argTypes.size() != mi.formalTypes().size()) {
      if (!(mi.isVariableArity() && argTypes.size() >= mi.formalTypes()
              .size() - 1)) {
        return null;
      }
    }

    PandaProcedureInstance pi = (PandaProcedureInstance) mi;

    ModeSubst subst = this.inferModeTypeArgs(pi, argTypes, null);
    if (subst == null) {
      // No matter what we should be able to create a valid subst,
      // null indicates error
      return null;
    }

    PandaProcedureInstance smi = (PandaProcedureInstance) subst.substProcedure(pi);

    // Let's perform a subst over modes, a hack for now, just prototyping
    // Can do a subst over the mi container type with a new type map
    return super.callValid(smi, argTypes, actualTypeArgs);
  }

  @Override
  public JL5MethodInstance methodCallValid(JL5MethodInstance mi, 
                                           String name,
                                           List<? extends Type> argTypes,
                                           List<? extends ReferenceType> actualTypeArgs,
                                           Type expectedReturnType) { 
    if (!(mi.container() instanceof ModeSubstType)) {
      // TODO : Just kick up to parent for now
      return super.methodCallValid(mi, name, argTypes, actualTypeArgs, expectedReturnType);
    }

    // Check from JL5TypeSystem::methodCallValid
    // Repeat it here to avoid some of the calls that could crash
    // our check
    if (argTypes.size() != mi.formalTypes().size()) {
      if (!(mi.isVariableArity() && argTypes.size() >= mi.formalTypes()
              .size() - 1)) {
        return null;
      }
    } 

    ModeSubst subst = this.inferModeTypeArgs((PandaProcedureInstance) mi, argTypes, expectedReturnType);
    if (subst == null) {
      // No matter what we should be able to create a valid subst,
      // null indicates error
      return null;
    }

    PandaMethodInstance smi = (PandaMethodInstance) subst.substMethod(mi);

    // Let's perform a subst over modes, a hack for now, just prototyping
    // Can do a subst over the mi container type with a new type map
    return super.methodCallValid(smi, name, argTypes, actualTypeArgs, expectedReturnType);
  }


  @Override
  public JL5MethodInstance methodInstance(Position pos, 
                                          ReferenceType container, 
                                          Flags flags, 
                                          Type returnType, 
                                          String name, 
                                          List<? extends Type> argTypes, 
                                          List<? extends Type> excTypes, 
                                          List<TypeVariable> typeParams) {
    return new PandaMethodInstance_c(this,
                                     pos,
                                     container,
                                     flags,
                                     returnType,
                                     name,
                                     argTypes,
                                     excTypes,
                                     typeParams,
                                     null);
  } 

  @Override
  public RawClass rawClass(JL5ParsedClassType base, Position pos) {
    if (!canBeRaw(base)) {
      throw new InternalCompilerError("Can only create a raw class with a parameterized class");
    }

    // TODO : I do not like putting the mode subst logic here for raw class types
    // but they are created not from walking the AST with a TypeNode but instead
    // through the type systems after a JL5SubstClassType has been created
    if (base instanceof ModeSubstParsedClassType) {
      ModeSubstParsedClassType st = (ModeSubstParsedClassType) base;
      PandaRawClass_c rc = 
        new PandaRawClass_c((PandaParsedClassType) st.baseType(), pos);
      return (RawClass) this.createModeSubst(rc, new ArrayList<Type>(st.modeTypeArgs()));
    } else {
      return new PandaRawClass_c(base, pos);
    }
  }

  @Override 
  protected Subst<TypeVariable, ReferenceType> substImpl(Map<TypeVariable, ? extends ReferenceType> substMap) {
    return new PandaSubst_c(this, substMap);
  }

  @Override
  public TypeVariable typeVariable(Position pos, String name, ReferenceType upperBound) {
    return new PandaTypeVariable_c(this, pos, name, upperBound);
  }

  @Override
  public ReferenceType intersectionType(Position pos, List<ReferenceType> types) {
    ReferenceType type = super.intersectionType(pos, types);
    if (type == this.Object()) {
      type = (ReferenceType) this.createModeSubst(
          this.Object(),
          Arrays.<Type>asList(this.WildcardModeType())
          );
    }
    return type;
  }


  // Panda TypeSystem Methods

  public AttributeInstance createAttributeInstance(Position pos, 
      ReferenceType container, 
      Flags flags) {
    return new AttributeInstance_c(this, pos, container, flags);
  }

  public CopyInstance createCopyInstance(Position pos, ReferenceType container) {
    return new CopyInstance_c(this, pos, container);
  }

  public McaseType createMcaseType(Type base) {
    McaseType mcaseType = new McaseType_c(this, base);
    return mcaseType;
  } 

  public ModeType createModeType(String mode) {
    if (this.createdModeTypes().containsKey(mode)) {
      return this.createdModeTypes().get(mode);
    }
    ModeType modeType = new ModeType_c(this, mode);
    this.createdModeTypes().put(mode, modeType);
    return modeType;
  } 

  public ModeOrderingInstance createModeOrderingInstance() {
    return new ModeOrderingInstance_c(this);
  }

  public ModeTypeVariable createModeTypeVariable(Position pos, String name) {
    return new ModeTypeVariable_c(this, pos, name);
  }

  public ModeValueType createModeValueType(Type mode) {
    return new ModeValueType_c(this, mode);
  }

  public Type createModeSubst(Type bt, List<Type> modeTypes) {
    return this.substEngine().createModeSubst(bt, modeTypes);
  }

  public ClassType wrapperClassOfModeSubstPrimitive(ModeSubstPrimitiveType t) {
    // We need to inject a boxed primitive type with a mode
    ClassType ct = this.wrapperClassOfPrimitive(t);
    return (ClassType) this.createModeSubst(ct, t.modeTypeArgs());
  }

}
