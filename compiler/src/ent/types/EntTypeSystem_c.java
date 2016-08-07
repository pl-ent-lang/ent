package ent.types;

import ent.ast.*;
import ent.runtime.*;
import polyglot.types.Package;
import ent.types.reflect.*;
import ent.types.inference.*;

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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class EntTypeSystem_c extends JL7TypeSystem_c implements EntTypeSystem {
  private Map<String, ModeType> createdModeTypes = new HashMap<>();

  public static int ORIG = -1;

  private ModeType WildcardModeType;
  private ModeType DynamicModeType;
  private ModeType BottomModeType;

  private ModeSubstEngine substEngine = new ModeSubstEngine(this);

  private Package modesDeclPkg;

  public EntTypeSystem_c() {
    // Setup both the bottom and dynamic mode type instances
    this.BottomModeType = this.createModeType("_");
    this.BottomModeType.uniqueId(ENT_Modes.FREE_MODE);
    this.WildcardModeType = this.createModeType("*");
    this.WildcardModeType.uniqueId(ENT_Modes.WILDCARD_MODE);

    ORIG = System.identityHashCode(this.WildcardModeType);

    this.DynamicModeType = this.createModeType("?");
    this.DynamicModeType.uniqueId(ENT_Modes.DYNAMIC_MODE);
  }

  // Property Methods
  public Map<String, ModeType> createdModeTypes() { return this.createdModeTypes; }

  public ModeType BottomModeType() { return this.BottomModeType; }

  public ModeType BottomUserModeType() { return (ModeType)this.BottomModeType.superType(); }

  public ModeType WildcardModeType() { return this.WildcardModeType; }

  public ModeType DynamicModeType() { return this.DynamicModeType; }

  private ModeSubstEngine substEngine() { return this.substEngine; }

  private ClassType ModeSubstObject = null;

  public ClassType ModeSubstObject() {
    if (this.ModeSubstObject == null) {
      this.ModeSubstObject = (ClassType)this.createModeSubst(
          this.Object(), Arrays.<Type>asList(this.WildcardModeType()));
    }
    return this.ModeSubstObject;
  }

  public void modesDeclPackage(Package pkg) { this.modesDeclPkg = pkg; }

  public Package modesDeclPackage() { return this.modesDeclPkg; }

  // Factory Methods / Previous TypeSystem Methods
  /*
  @Override
  public ClassFileLazyClassInitializer classFileLazyClassInitializer(ClassFile clazz) {
    return new EntClassFileLazyClassInitializer(clazz, this);
  }
  */

  @Override
  public Flags legalFieldFlags() {
    return EntFlags.setModesafe(super.legalFieldFlags());
  }

  @Override
  public Flags legalMethodFlags() {
    return EntFlags.setModesafe(super.legalMethodFlags());
  }

  @Override
  public Flags legalAbstractMethodFlags() {
    return EntFlags.setModesafe(super.legalAbstractMethodFlags());
  }

  @Override
  public JL5ConstructorInstance constructorInstance(Position pos,
                                                    ClassType container,
                                                    Flags flags,
                                                    List<? extends Type> argTypes,
                                                    List<? extends Type> excTypes,
                                                    List<TypeVariable> typeParams) {
    assert_(container);
    assert_(argTypes);
    assert_(excTypes);
    assert_(typeParams);
    return new EntConstructorInstance_c(
        this, pos, container, flags, argTypes, excTypes, typeParams, null);
  }

  @Override
  public ParsedClassType createClassType(LazyClassInitializer init, Source fromSource) {
    return new EntParsedClassType_c(this, init, fromSource);
  }

  @Override
  public DiamondType diamondType(Position pos, JL5ParsedClassType base) {
    return new EntDiamondType_c(pos, base);
  }

  private boolean isSpecialModeSubstCase(Type l, Type u) {
    return (!(l instanceof ModeSubstType) && u instanceof ModeSubstType);
  }

  @Override
  public boolean typeEquals(Type l, Type u) {
    if (super.typeEquals(l, u)) {
      return true;
    }

    if (u instanceof ModeTypeVariable) {
      return this.typeEquals(l, ((ModeTypeVariable)u).lowerBound());
    }

    if (this.isSpecialModeSubstCase(l, u)) {
      return this.typeEquals(l, ((ModeSubstType)u).baseType());
    }

    return false;
  }

  @Override
  public boolean isSubtype(Type l, Type u) {
    if (super.isSubtype(l, u)) {
      return true;
    }

    // TODO : Special interception of the way type variables are handled.
    // Until we decide on strengthening/weaking for type variables, let
    // type variables that do not yet have a mode subst pass through to
    // an objec that does
    if (l instanceof TypeVariable && !(l instanceof ModeSubstType) && u instanceof ModeSubstType) {
      return this.isSubtype(l, ((ModeSubstType)u).baseType());
    }

    if (this.isSpecialModeSubstCase(l, u)) {
      return this.isSubtype(l, ((ModeSubstType)u).baseType());
    }

    return false;
  }

  @Override
  public boolean descendsFrom(Type l, Type u) {
    if (super.descendsFrom(l, u)) {
      return true;
    }

    if (this.isSpecialModeSubstCase(l, u)) {
      return this.descendsFrom(l, ((ModeSubstType)u).baseType());
    }

    if (!(l instanceof ModeTypeVariable) && u instanceof ModeTypeVariable) {
      // System.out.format("%s <: %s\n", l, u);
      ModeTypeVariable utv = (ModeTypeVariable)u;
      // if (utv.lowerBound() == null) {
      //  return false;
      //}
      // System.out.format("%s <: %s: %b\n", l, utv.lowerBound(), this.isSubtype(l,
      // utv.lowerBound()));
      return this.isSubtype(l, utv.lowerBound());

      // this.isSubtype(utv.lowerBound(), l) &&
      // this.isSubtype(l, utv.upperBound());
    }

    if (l instanceof ModeTypeVariable && u instanceof ModeTypeVariable) {
      // System.out.format("Type Variable: %s <: %s\n", l, u);
      ModeTypeVariable ltv = (ModeTypeVariable)l;
      ModeTypeVariable utv = (ModeTypeVariable)u;
      // if (utv.lowerBound() == null) {
      //  return ltv.descendsFromImpl(utv);
      //}

      return this.isSubtype(ltv.upperBound(), utv.lowerBound());
      // CRUNCH-HACK: Come back to fix, figure out the right subtyping
      /*
      return this.isSubtype(ltv.upperBound(), utv.lowerBound()) ||
        (this.isSubtype(utv.lowerBound(), ltv.lowerBound()) &&
         this.isSubtype(ltv.upperBound(), utv.upperBound()));
      */
    }

    return false;
  }

  @Override
  public boolean isCastValid(Type l, Type u) {
    if (super.isCastValid(l, u)) {
      return true;
    }

    if (this.isSpecialModeSubstCase(l, u)) {
      return this.isCastValid(l, ((ModeSubstType)u).baseType());
    }

    return false;
  }

  @Override
  public boolean isImplicitCastValid(Type l, Type u) {
    if (super.isImplicitCastValid(l, u)) {
      return true;
    }

    if (this.isSpecialModeSubstCase(l, u)) {
      return this.isImplicitCastValid(l, ((ModeSubstType)u).baseType());
    }

    if (l instanceof ModeTypeVariable && u instanceof ModeTypeVariable) {
      ModeTypeVariable lmt = (ModeTypeVariable)l;
      ModeTypeVariable umt = (ModeTypeVariable)u;
      return this.isImplicitCastValid(lmt.lowerBound(), umt.lowerBound()) &&
          this.isImplicitCastValid(lmt.upperBound(), umt.upperBound());
    }

    if (u instanceof ModeTypeVariable) {
      return this.isImplicitCastValid(l, ((ModeTypeVariable)u).lowerBound());
    }

    return false;
  }

  @Override
  public LinkedList<Type> isImplicitCastValidChain(Type l, Type u) {
    return super.isImplicitCastValidChain(l, u);
  }

  @Override
  public Context createContext() {
    return new EntContext_c(EntLang_c.instance, this);
  }

  private boolean inferModeTypeArg(List<ModeTypeVariable> mtVars,
                                   Type baseT,
                                   Type actT,
                                   Map<ModeTypeVariable, Type> mtMap) {
    if (!(baseT instanceof ModeSubstType)) {
      return true;
    }

    // TODO : This is an O(n^2) lookup, keep a hash to avoid
    ModeSubstType st = (ModeSubstType)baseT;
    for (int i = 0; i < st.modeTypeArgs().size(); ++i) {
      Type t = st.modeTypeArgs().get(i);
      // The O(n) lookup from mtVars.contains
      if (t instanceof ModeTypeVariable && mtVars.contains(t)) {
        // This is a mode type var that is part of our inference, so infer it
        ModeTypeVariable mtVar = (ModeTypeVariable)t;

        // MODE-NOTE: If the actual type is not a mode subst type, throw in
        // wildcard for now.
        Type infT = null;
        if (actT instanceof ModeSubstType) {
          infT = ((ModeSubstType)actT).modeTypeArgs().get(i);
        } else {
          infT = this.WildcardModeType();
        }

        if (mtMap.containsKey(mtVar)) {
          if (!mtMap.get(mtVar).typeEqualsImpl(infT)) {
            return false;
          }
        } else {
          mtMap.put(mtVar, infT);
        }
      }
    }

    // Just like the opposite (in ModeSubst) we need to dip down in the subst
    // over the generic type
    Type actT1 = null;
    if (actT instanceof ModeSubstType) {
      actT1 = ((ModeSubstType)actT).baseType();
    } else {
      actT1 = actT;
    }

    if (st.baseType() instanceof SubstType && actT1 instanceof SubstType) {
      Type t1 = ((SubstType)st.baseType()).base();
      Type t2 = ((SubstType)actT1).base();

      if (!this.isCastValid(t1, t2)) {
        // Forget the inference, the type system will flag this as invalid
        return false;
      }

      Map<TypeVariable, ReferenceType> stSubsts =
          ((SubstType)st.baseType()).subst().substitutions();
      Map<TypeVariable, ReferenceType> actTSubsts = ((SubstType)actT1).subst().substitutions();

      JL5SubstClassType t3 = this.findGenericSupertype((JL5ParsedClassType)t1, (ReferenceType)t2);

      Map<TypeVariable, TypeVariable> tvMap = new HashMap<>();
      if (t3 != null) {
        for (Map.Entry<TypeVariable, ReferenceType> e : t3.subst().substitutions().entrySet()) {
          tvMap.put(e.getKey(), (TypeVariable)e.getValue());
        }
      } else {
        for (Map.Entry<TypeVariable, ReferenceType> e : stSubsts.entrySet()) {
          tvMap.put(e.getKey(), e.getKey());
        }
      }

      // BUG18 : This is not a proper comparison (only need a castValid, not typeEquals),
      // as such the type variables are not equal. Need to compare the type arguments of
      // the two subst types linearly (i = 0, i = 1) etc.
      for (Map.Entry<TypeVariable, TypeVariable> e : tvMap.entrySet()) {
        if (!actTSubsts.containsKey(e.getValue())) {
          // We cannot infer, the subst are not equal
          return false;
        }

        if (!this.inferModeTypeArg(
                mtVars, stSubsts.get(e.getKey()), actTSubsts.get(e.getValue()), mtMap)) {
          return false;
        }
      }
    }

    return true;
  }

  public ModeSubst inferModeTypeArgs(EntProcedureInstance pi,
                                     List<? extends Type> argTypes,
                                     Type expectedReturnType) {
    // TODO : Need to handle variable args for inference (see how JL5 does it)
    // Infer the mode type variable first, error if not possible

    Map<ModeTypeVariable, Type> mtMap = new HashMap<>();
    for (int i = 0; i < pi.formalTypes().size(); ++i) {
      if (!this.inferModeTypeArg(
              pi.modeTypeVars(), pi.formalTypes().get(i), argTypes.get(i), mtMap)) {
        return null;
      }
    }

    // Check return type as well
    if (expectedReturnType != null &&
        !this.inferModeTypeArg(
            pi.modeTypeVars(), ((EntMethodInstance)pi).returnType(), expectedReturnType, mtMap)) {
      return null;
    }

    ModeSubstClassType sct = (ModeSubstClassType)pi.container();
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
      for (Type m : mtv.upperBounds()) {
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

  public SemanticException checkModeSubst(EntClassType baseT, List<Type> mtArgs) {
    Map<ModeTypeVariable, Type> mtMap = new HashMap<>();
    List<ModeTypeVariable> baseMtVars = baseT.modeTypeVars();

    for (int i = 0; i < baseMtVars.size(); ++i) {
      if (!baseMtVars.get(i).upperBound().isCanonical()) {
        // Need to disambig
        throw new SchedulerException();
      }

      Type mtArg = mtArgs.get(i);

      // 1. MtVar is a dynRecv with a supplied ? type.
      if (baseMtVars.get(i).isDynRecvr() && this.typeEquals(this.DynamicModeType(), mtArg)) {
        mtMap.put(baseMtVars.get(i), baseMtVars.get(i).upperBound());
        continue;
      }

      // 2. If we are dynamic with no bounds, this will error.
      if (baseMtVars.get(i).upperBounds().size() == 0 && this.DynamicModeType() == mtArg) {
        return new SemanticException(baseMtVars.get(i) + " cannot receive the dynamic mode type.");
      }

      // 3. Otherwise, all bounds must be satisfied
      for (Type bound : baseMtVars.get(i).upperBounds()) {
        if (bound instanceof ModeTypeVariable) {
          bound = mtMap.get(bound);
        }

        if (!this.isSubtype(mtArg, bound)) {
          return new SemanticException("cannot satisfy constraint: " + mtArg + "<=" + bound);
        }
      }

      // 4. Check lower bound
      Type lb = baseMtVars.get(i).lowerBound();
      if (lb instanceof ModeTypeVariable) {
        lb = mtMap.get(lb);
      }
      if (lb != null && !this.isSubtype(lb, mtArg)) {
        return new SemanticException("cannot satisfy constraint: " + lb + "<=" + mtArg);
      }

      mtMap.put(baseMtVars.get(i), mtArg);
    }
    return null;
  }

  @Override
  public JL5ProcedureInstance callValid(JL5ProcedureInstance mi,
                                        List<? extends Type> argTypes,
                                        List<? extends ReferenceType> actualTypeArgs,
                                        Type expectedReturnType) {
    return this.callValid(
        (EntProcedureInstance)mi, argTypes, actualTypeArgs, expectedReturnType, null);
  }

  @Override
  public EntProcedureInstance callValid(EntProcedureInstance mi,
                                        List<? extends Type> argTypes,
                                        List<? extends ReferenceType> actualTypeArgs,
                                        Type expectedReturnType,
                                        List<ModeType> actualModeTypeArgs) {

    if (!(mi.container() instanceof ModeSubstType)) {
      // MODE-NOTE : Need to return to address this. ModeSubstType/Type issue.
      // Should the container always be a ModeSubstType?
      return (EntProcedureInstance)super.callValid(
          mi, argTypes, actualTypeArgs, expectedReturnType);
    }

    // Check from JL5TypeSystem::methodCallValid
    // Repeat it here to avoid some of the calls that could crash
    // our check
    if (argTypes.size() != mi.formalTypes().size()) {
      if (!(mi.isVariableArity() && argTypes.size() >= mi.formalTypes().size() - 1)) {
        return null;
      } else {
        return (EntProcedureInstance)super.callValid(
            mi, argTypes, actualTypeArgs, expectedReturnType);
      }
    }

    EntProcedureInstance pi = (EntProcedureInstance)mi;

    if (actualModeTypeArgs != null) {
      // We should be checked from EntCallExt
      Map<ModeTypeVariable, Type> mtMap = new HashMap<>();
      List<ModeTypeVariable> modeTypeVars = pi.modeTypeVars();
      for (int i = 0; i < pi.modeTypeVars().size(); i++) {
        mtMap.put(pi.modeTypeVars().get(i), actualModeTypeArgs.get(i));
      }

      ModeSubstClassType sct = (ModeSubstClassType)pi.container();
      ModeSubst subst = sct.modeSubst().deepCopy();
      subst.modeTypeMap().putAll(mtMap);

      pi = (EntProcedureInstance)subst.substProcedure(pi);
    }

    // Let's perform a subst over modes, a hack for now, just prototyping
    // Can do a subst over the mi container type with a new type map
    return (EntProcedureInstance)super.callValid(pi, argTypes, actualTypeArgs);
  }

  @Override
  public MethodInstance findMethod(ReferenceType container,
                                   String name,
                                   List<? extends Type> argTypes,
                                   List<? extends ReferenceType> typeArgs,
                                   ClassType currClass,
                                   Type expectedReturnType,
                                   boolean fromClient) throws SemanticException {
    return this.findMethod(
        container, name, argTypes, typeArgs, currClass, expectedReturnType, fromClient, null);
  }

  public MethodInstance findMethod(ReferenceType container,
                                   String name,
                                   List<? extends Type> argTypes,
                                   List<? extends ReferenceType> typeArgs,
                                   ClassType currClass,
                                   Type expectedReturnType,
                                   boolean fromClient,
                                   List<ModeType> actualModeTypeArgs) throws SemanticException {
    assert_(container);
    assert_(argTypes);

    List<? extends MethodInstance> acceptable = findAcceptableMethods(container,
                                                                      name,
                                                                      argTypes,
                                                                      typeArgs,
                                                                      currClass,
                                                                      expectedReturnType,
                                                                      fromClient,
                                                                      actualModeTypeArgs);

    if (acceptable.size() == 0) {
      throw new NoMemberException(NoMemberException.METHOD,
                                  "No valid method call found for " + name + "(" +
                                      listToString(argTypes) + ")"
                                      +
                                      " in " + container + ".");
    }

    Collection<? extends MethodInstance> maximal = findMostSpecificProcedures(acceptable);

    if (maximal.size() > 1) {
      StringBuffer sb = new StringBuffer();
      for (Iterator<? extends MethodInstance> i = maximal.iterator(); i.hasNext();) {
        MethodInstance ma = i.next();
        sb.append(ma.returnType());
        sb.append(" ");
        sb.append(ma.container());
        sb.append(".");
        sb.append(ma.signature());
        if (i.hasNext()) {
          if (maximal.size() == 2) {
            sb.append(" and ");
          } else {
            sb.append(", ");
          }
        }
      }

      throw new SemanticException("Reference to " + name +
                                  " is ambiguous, multiple methods match: " + sb.toString());
    }

    MethodInstance mi = maximal.iterator().next();

    return mi;
  }

  @Override
  protected List<? extends MethodInstance>
  findAcceptableMethods(ReferenceType container,
                        String name,
                        List<? extends Type> argTypes,
                        List<? extends ReferenceType> actualTypeArgs,
                        ClassType currClass,
                        Type expectedReturnType,
                        boolean fromClient) throws SemanticException {
    return this.findAcceptableMethods(
        container, name, argTypes, actualTypeArgs, currClass, expectedReturnType, fromClient, null);
  }

  protected List<? extends MethodInstance>
  findAcceptableMethods(ReferenceType container,
                        String name,
                        List<? extends Type> argTypes,
                        List<? extends ReferenceType> actualTypeArgs,
                        ClassType currClass,
                        Type expectedReturnType,
                        boolean fromClient,
                        List<ModeType> actualModeTypeArgs) throws SemanticException {
    assert_(container);
    assert_(argTypes);

    // apply capture conversion to container
    container = (ReferenceType)applyCaptureConversion(container, container.position());

    SemanticException error = null;

    // List of methods accessible from curClass that have valid method
    // calls without boxing/unboxing conversion or variable arity and
    // are not overridden by an unaccessible method
    List<MethodInstance> phase1methods = new ArrayList<>();
    // List of methods accessible from curClass that have a valid method
    // call relying on boxing/unboxing conversion
    List<MethodInstance> phase2methods = new ArrayList<>();
    // List of methods accessible from curClass that have a valid method
    // call relying on boxing/unboxing conversion and variable arity
    List<MethodInstance> phase3methods = new ArrayList<>();

    // A list of unacceptable methods, where the method call is valid, but
    // the method is not accessible. This list is needed to make sure that
    // the acceptable methods are not overridden by an unacceptable method.
    List<MethodInstance> inaccessible = new ArrayList<>();

    // A set of all the methods that methods in phase[123]methods override.
    // Used to make sure we don't mistakenly add in overridden methods
    // (since overridden methods aren't inherited from superclasses).
    Set<MethodInstance> phase1overridden = new HashSet<>();
    Set<MethodInstance> phase2overridden = new HashSet<>();
    Set<MethodInstance> phase3overridden = new HashSet<>();

    Set<Type> visitedTypes = new HashSet<>();

    LinkedList<Type> typeQueue = new LinkedList<>();
    typeQueue.addLast(container);

    //        System.err.println("JL5TS: findAcceptableMethods for " + name + " in " + container);
    while (!typeQueue.isEmpty()) {
      Type type = typeQueue.remove();

      //            System.err.println("   looking at type " + type + " " + type.getClass());
      // Make sure each type is considered only once
      if (visitedTypes.contains(type))
        continue;
      visitedTypes.add(type);

      if (!type.isReference()) {
        throw new SemanticException("Cannot call method in "
                                    +
                                    " non-reference type " + type + ".");
      }

      @SuppressWarnings("unchecked")
      List<JL5MethodInstance> methods = (List<JL5MethodInstance>)type.toReference().methods();
      for (JL5MethodInstance mi : methods) {
        // Method name must match
        if (!mi.name().equals(name))
          continue;
        //                System.err.println("      checking " + mi);

        JL5MethodInstance substMi = methodCallValid((EntMethodInstance)mi,
                                                    name,
                                                    argTypes,
                                                    actualTypeArgs,
                                                    expectedReturnType,
                                                    actualModeTypeArgs);
        JL5MethodInstance origMi = mi;

        if (substMi != null) {
          mi = substMi;

          if (isMember(mi, container.toReference()) &&
              isAccessible(mi, container, currClass, fromClient)) {
            if (varArgsRequired(mi)) {
              if (!phase3overridden.contains(mi) && !phase3overridden.contains(origMi)) {
                phase3overridden.addAll(mi.implemented());
                phase3overridden.addAll(origMi.implemented());
                phase3methods.removeAll(mi.implemented());
                phase3methods.removeAll(origMi.implemented());
                phase3methods.add(mi);
              }
            } else if (boxingRequired(mi, argTypes)) {
              if (!phase2overridden.contains(mi) && !phase2overridden.contains(origMi)) {
                phase2overridden.addAll(mi.implemented());
                phase2overridden.addAll(origMi.implemented());
                phase2methods.removeAll(mi.implemented());
                phase2methods.removeAll(origMi.implemented());
                phase2methods.add(mi);
              }
            } else {
              if (!phase1overridden.contains(mi) && !phase1overridden.contains(origMi)) {
                phase1overridden.addAll(mi.implemented());
                phase1overridden.addAll(origMi.implemented());
                phase1methods.removeAll(mi.implemented());
                phase1methods.removeAll(origMi.implemented());
                phase1methods.add(mi);
              }
            }
          } else {
            // method call is valid but the method is unaccessible
            inaccessible.add(mi);
            if (error == null) {
              error = new NoMemberException(NoMemberException.METHOD,
                                            "(3) Method " + mi.signature() + " in " + container +
                                                " is inaccessible.");
            }
          }
        } else {
          if (error == null) {
            error = new NoMemberException(NoMemberException.METHOD,
                                          "Method " + mi.signature() + " in " + container +
                                              " cannot be called with arguments "
                                              +
                                              "(" + listToString(argTypes) + ").");
          }
        }
      }

      if (type instanceof JL5ClassType) {
        for (Type superT : ((JL5ClassType)type).superclasses()) {
          if (superT != null && superT.isReference()) {
            typeQueue.addLast(superT.toReference());
          }
        }
      } else {
        Type superT = type.toReference().superType();
        if (superT != null && superT.isReference()) {
          typeQueue.addLast(superT.toReference());
        }
      }

      typeQueue.addAll(type.toReference().interfaces());
    }

    if (error == null) {
      error = new NoMemberException(NoMemberException.METHOD,
                                    "No valid method call found for " + name + "(" +
                                        listToString(argTypes) + ")"
                                        +
                                        " in " + container + ".");
    }

    // remove any methods that are overridden by an inaccessible method
    for (MethodInstance mi : inaccessible) {
      phase1methods.removeAll(mi.overrides());
      phase2methods.removeAll(mi.overrides());
      phase3methods.removeAll(mi.overrides());
    }

    //        System.err.println("        phase1overridden is    " + phase1overridden);
    //        System.err.println("        phase2overridden is    " + phase2overridden);
    //        System.err.println("        phase3overridden is    " + phase3overridden);

    if (!phase1methods.isEmpty())
      return phase1methods;
    if (!phase2methods.isEmpty())
      return phase2methods;
    if (!phase3methods.isEmpty())
      return phase3methods;

    // No acceptable accessible methods were found

    throw error;
  }

  @Override
  public JL5MethodInstance methodCallValid(JL5MethodInstance mi,
                                           String name,
                                           List<? extends Type> argTypes,
                                           List<? extends ReferenceType> actualTypeArgs,
                                           Type expectedReturnType) {
    return this.methodCallValid(
        (EntMethodInstance)mi, name, argTypes, actualTypeArgs, expectedReturnType, null);
  }

  @Override
  public EntMethodInstance methodCallValid(EntMethodInstance mi,
                                           String name,
                                           List<? extends Type> argTypes,
                                           List<? extends ReferenceType> actualTypeArgs,
                                           Type expectedReturnType,
                                           List<ModeType> actualModeTypeArgs) {
    /*
    if (!(mi.container() instanceof ModeSubstType)) {
      System.err.format("Container not subst!\n");
      // MODE-NOTE : Need to return to address this. ModeSubstType/Type issue.
      // Should the container always be a ModeSubstType?
      return (EntMethodInstance) super.methodCallValid(mi, name, argTypes, actualTypeArgs,
    expectedReturnType);
    }
    */

    // Check from JL5TypeSystem::methodCallValid
    // Repeat it here to avoid some of the calls that could crash
    // our check
    if (argTypes.size() != mi.formalTypes().size()) {
      if (!(mi.isVariableArity() && argTypes.size() >= mi.formalTypes().size() - 1)) {
        return null;
      } else {
        // Don't handle varagrs yet,
        return (EntMethodInstance)super.methodCallValid(
            mi, name, argTypes, actualTypeArgs, expectedReturnType);
      }
    }

    EntProcedureInstance pi = (EntProcedureInstance)mi;
    if (actualModeTypeArgs != null) {
      Map<ModeTypeVariable, Type> mtMap = new HashMap<>();
      List<ModeTypeVariable> modeTypeVars = pi.modeTypeVars();
      for (int i = 0; i < pi.modeTypeVars().size(); i++) {
        mtMap.put(pi.modeTypeVars().get(i), actualModeTypeArgs.get(i));
      }

      ModeSubst subst = null;
      if (pi.container() instanceof ModeSubstClassType) {
        ModeSubstClassType sct = (ModeSubstClassType)pi.container();
        subst = sct.modeSubst().deepCopy();
      } else {
        List<Type> newActualModeTypeArgs = new ArrayList<>();
        for (ModeType mt : actualModeTypeArgs) {
          newActualModeTypeArgs.add((Type)mt);
        }
        ModeSubstClassType mst =
            (ModeSubstClassType)this.createModeSubst(pi.container(), newActualModeTypeArgs);
        subst = mst.modeSubst();
      }
      subst.modeTypeMap().putAll(mtMap);

      // Check that constraints are handled
      mtMap = subst.modeTypeMap();
      for (ModeTypeVariable mtv : pi.modeTypeVars()) {
        Type sm = mtMap.get(mtv);

        // Check that all bounds are satisfied
        for (Type m : mtv.upperBounds()) {
          if (m instanceof ModeTypeVariable) {
            m = mtMap.get(m);
          }

          if (!this.isSubtype(sm, m)) {
            return null;
          }
        }
      }

      pi = (EntProcedureInstance)subst.substProcedure(pi);
    }

    // Let's perform a subst over modes, a hack for now, just prototyping
    // Can do a subst over the mi container type with a new type map
    return (EntMethodInstance)super.methodCallValid(
        (JL5MethodInstance)pi, name, argTypes, actualTypeArgs, expectedReturnType);
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
    return new EntMethodInstance_c(
        this, pos, container, flags, returnType, name, argTypes, excTypes, typeParams, null);
  }

  @Override
  public RawClass rawClass(JL5ParsedClassType base, Position pos) {
    if (!canBeRaw(base)) {
      throw new InternalCompilerError("Can only create a raw class with a parameterized class");
    }

    // The ModeSubstType creation for RawClasses is here because a RawClass
    // is created not from walking the AST with a TypeNode but instead
    // through the type systems after a JL5SubstClassType has been created.
    if (base instanceof ModeSubstParsedClassType) {
      ModeSubstParsedClassType st = (ModeSubstParsedClassType)base;
      EntRawClass_c rc = new EntRawClass_c((EntParsedClassType)st.baseType(), pos);
      return (RawClass)this.createModeSubst(rc, new ArrayList<Type>(st.modeTypeArgs()));
    } else {
      return new EntRawClass_c(base, pos);
    }
  }

  @Override
  protected Subst<TypeVariable, ReferenceType>
  substImpl(Map<TypeVariable, ? extends ReferenceType> substMap) {
    return new EntSubst_c(this, substMap);
  }

  @Override
  public TypeVariable typeVariable(Position pos, String name, ReferenceType upperBound) {
    return new EntTypeVariable_c(this, pos, name, upperBound);
  }

  @Override
  public ReferenceType intersectionType(Position pos, List<ReferenceType> types) {
    ReferenceType type = super.intersectionType(pos, types);
    if (type == this.Object()) {
      type = (ReferenceType)this.createModeSubst(this.Object(),
                                                 Arrays.<Type>asList(this.WildcardModeType()));
    }
    return type;
  }

  @Override
  protected InferenceSolver inferenceSolver(JL5ProcedureInstance pi,
                                            List<? extends Type> argTypes) {
    return new EntInferenceSolver_c(pi, argTypes, this);
  }

  // MODE-NOTE: We need to figure out what a generic supertype of a mode subst
  // type is.
  @Override
  public JL5SubstClassType findGenericSupertype(JL5ParsedClassType base, ReferenceType sub) {
    JL5SubstClassType t = super.findGenericSupertype(base, sub);
    if (t == null) {
      return null;
    }
    if (base instanceof ModeSubstType) {
      t = (JL5SubstClassType)this.createModeSubst(
          t, new ArrayList<>(((ModeSubstType)base).modeTypeArgs()));
    }
    return t;
  }

  protected boolean
  hasSameSignature(JL5ProcedureInstance mi, JL5ProcedureInstance mj, boolean eraseMj) {
    // JLS 3rd Ed. | 8.4.2
    // Two methods have the same signature if they have the same name
    // and argument types.
    // Two methods have the same argument types if all of the following hold:
    // - They have same number of formal parameters
    // - They have same number of type parameters
    // - After renaming type parameters to match, the bounds of type
    //   variables and argument types are the same.
    if (mi instanceof JL5MethodInstance && mj instanceof JL5MethodInstance) {
      if (!((JL5MethodInstance)mi).name().equals(((JL5MethodInstance)mj).name())) {
        return false;
      }
    }
    if (mi.formalTypes().size() != mj.formalTypes().size()) {
      return false;
    }
    if (eraseMj && !mi.typeParams().isEmpty()) {
      // we are erasing mj, so it has no type parameters.
      // so mi better have no type parameters
      return false;
    } else if (!eraseMj && mi.typeParams().size() != mj.typeParams().size()) {
      // we are not erasing mj, so it and mi better
      // have the same number of type parameters.
      return false;
    }

    // replace the type variables of mj with the type variables of mi
    if (!eraseMj && !mi.typeParams().isEmpty()) {
      Map<TypeVariable, ReferenceType> substm = new LinkedHashMap<>();
      for (int i = 0; i < mi.typeParams().size(); i++) {
        substm.put(mj.typeParams().get(i), mi.typeParams().get(i));
      }
      Subst<TypeVariable, ReferenceType> subst = this.subst(substm);

      // Check that bounds of type variables match
      for (Iterator<? extends TypeVariable> typesi = mi.typeParams().iterator(),
                                            typesj = mj.typeParams().iterator();
           typesi.hasNext();) {
        TypeVariable ti = typesi.next();
        TypeVariable tj = typesj.next();
        if (!ti.upperBound().equals(subst.substType(tj.upperBound())))
          return false;
      }

      if (mj instanceof JL5MethodInstance)
        mj = subst.substMethod((JL5MethodInstance)mj);
      else
        mj = subst.substConstructor((JL5ConstructorInstance)mj);
    }

    // Check that the argument types match
    for (Iterator<? extends Type> typesi = mi.formalTypes().iterator(),
                                  typesj = mj.formalTypes().iterator();
         typesi.hasNext();) {
      Type ti = typesi.next();
      Type tj = typesj.next();
      if (eraseMj) {
        tj = this.erasureType(tj);
      }
      if (!ti.typeEquals(tj)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean areReturnTypeSubstitutable(Type ri, Type rj) {
    if (ri.isPrimitive()) {
      return ri.typeEquals(rj);
    } else if (ri.isReference()) {
      return ri.isSubtype(rj) || isUncheckedConversion(ri, rj) ||
          ri.isSubtype(this.erasureType(rj));
    } else if (ri.isVoid()) {
      return rj.isVoid();
    } else {
      throw new InternalCompilerError("Unexpected return type: " + ri);
    }
  }

  @Override
  public void checkClassConformance(ClassType ct) throws SemanticException {
    EntClassType pct = (EntClassType)ct;
    List<ReferenceType> superInterfaces = abstractSuperInterfaces(ct);
    for (ReferenceType rt : superInterfaces) {
      if (this.typeEquals(pct, rt)) {
        continue;
      }
      if (((EntClassType)rt).hasDynamicRecv() && pct.attributeInstance() == null &&
          !pct.flags().isAbstract()) {
        throw new SemanticException(rt + " requires an attributor. " + ct +
                                    " must implement an attributor.");
      }
    }
    super.checkClassConformance(ct);
  }

  @Override
  public PrimitiveType primitiveTypeOfWrapper(Type l) {
    if (!(l instanceof ModeSubstType)) {
      return super.primitiveTypeOfWrapper(l);
    }

    ModeSubstType st = (ModeSubstType)l;
    PrimitiveType pt = super.primitiveTypeOfWrapper(st.baseType());
    if (pt == null) {
      return null;
    }
    return (PrimitiveType)this.createModeSubst(pt, new ArrayList<Type>(st.modeTypeArgs()));
  }

  @Override
  public LubType lub(Position pos, List<ReferenceType> us) {
    return new EntLubType_c(this, pos, us);
  }

  @Override
  public EntFieldInstance
  fieldInstance(Position pos, ReferenceType container, Flags flags, Type type, String name) {
    assert_(container);
    assert_(type);
    return new EntFieldInstance_c(this, pos, container, flags, type, name);
  }

  @Override
  public boolean isValidAnnotationValueType(Type t) {
    // must be one of primitive, String, Class, enum, annotation or
    // array of one of these
    if (t.isPrimitive())
      return true;
    if (t.isClass()) {
      if (JL5Flags.isEnum(t.toClass().flags()) || JL5Flags.isAnnotation(t.toClass().flags()) ||
          String().typeEquals(t) || Class().typeEquals(t)) {
        return true;
      }
    }
    // XXX More elegant way to check that t is a parameterized invocation of Class?
    // See JLS 3rd ed. 9.6, clarified in JLS SE7 ed. 9.6.1
    if (erasureType(Class()).equals(erasureType(t))) {
      return true;
    }
    if (t.isArray()) {
      return isValidAnnotationValueType(t.toArray().base());
    }
    return false;
  }

  protected boolean isCastValidFromInterface(ClassType fromType, Type toType) {
    // If T is an array type, then T must implement S, or a compile-time error occurs
    // This is handled in the super class.

    if (toType.isClass() && toType.toClass().flags().isFinal()) {
      // toType is final.
      if (fromType instanceof RawClass || fromType instanceof JL5SubstClassType) {
        // S is either a parameterized type that is an invocation of some generic type declaration
        // G, or a raw type corresponding to a generic type declaration G.
        // Then there must exist a supertype X of T, such that X is an invocation of G, or a
        // compile-time error occurs.
        JL5ParsedClassType baseClass;
        if (fromType instanceof RawClass) {
          baseClass = ((RawClass)fromType).base();
        } else {
          baseClass = ((JL5SubstClassType)fromType).base();
        }

        // ANTHONY - Total hack, but I can't fix polyglot right now.
        if (toType.toString().contains("Pair")) {
          return true;
        }

        JL5SubstClassType x = findGenericSupertype(baseClass, toType.toReference());
        if (x == null) {
          return false;
        }

        // Furthermore, if S and X are provably distinct parameterized types then a compile-time
        // error occurs.
        if (fromType instanceof JL5SubstClassType &&
            areProvablyDistinct((JL5SubstClassType)fromType, x)) {
          return false;
        }
      } else {
        // S is not a parameterized type or a raw type, and T is final
        // Then T must implement S, and the cast is statically known to be correct, or a
        // compile-time error occurs.
        if (!isSubtype(toType, fromType)) {
          // XXX this takes care that T must implement S. Not sure why there is a requirement for
          // the cast to statically known to be correct. That would seem to imply that fromType is a
          // subtype of toType?!
          return false;
        }
      }
      return true;

    } else {
      // T is a type that is not final (�8.1.1), and S is an interface
      // if there exists a supertype X of T, and a supertype Y of S, such that both X and Y are
      // provably distinct parameterized types,
      // and that the erasures of X and Y are the same, a compile-time error occurs.
      // Go through the supertypes of each.
      List<ReferenceType> allY = allAncestorsOf(fromType.toReference());
      List<ReferenceType> allX = allAncestorsOf(toType.toReference());

      for (ReferenceType y : allY) {
        for (ReferenceType x : allX) {
          if (x instanceof JL5SubstClassType && y instanceof JL5SubstClassType &&
              areProvablyDistinct((JL5SubstClassType)x, (JL5SubstClassType)y) &&
              erasureType(x).equals(erasureType(y))) {
            return false;
          }
        }
      }

      // Otherwise, the cast is always legal at compile time (because even if T does not implement
      // S, a subclass of T might).
      return true;
    }
  }

  private static boolean areProvablyDistinct(JL5SubstClassType s, JL5SubstClassType t) {
    // See JLS 3rd ed 4.5
    // Distinct if either (1) They are invocations of distinct generic type declarations.
    // or (2) Any of their type arguments are provably distinct
    JL5SubstClassType x = s;
    JL5SubstClassType y = t;

    TypeSystem ts = x.typeSystem();

    if (!ts.typeEquals(x.base(), y.base())) {
      return true;
    }
    List<ReferenceType> xActuals = x.actuals();
    List<ReferenceType> yActuals = y.actuals();
    if (xActuals.size() != yActuals.size()) {
      return true;
    }
    for (int i = 0; i < xActuals.size(); i++) {
      if (areTypArgsProvablyDistinct(xActuals.get(i), xActuals.get(i))) {
        return true;
      }
    }
    return false;
  }

  private static boolean areTypArgsProvablyDistinct(ReferenceType s, ReferenceType t) {
    // JLS 3rd ed 4.5. "Two type arguments are provably distinct if
    // neither of the arguments is a type variable or wildcard, and
    // the two arguments are not the same type."
    return !(s instanceof TypeVariable) && !(t instanceof TypeVariable) &&
        !(s instanceof WildCardType) && !(t instanceof WildCardType) && !s.equals(t);
  }

  @Override
  public AnnotationTypeElemInstance annotationElemInstance(Position pos,
                                                           ClassType ct,
                                                           Flags f,
                                                           Type type,
                                                           java.lang.String name,
                                                           boolean hasDefault) {
    assert_(ct);
    assert_(type);
    return new EntAnnotationTypeElemInstance_c(this, pos, ct, f, type, name, hasDefault);
  }

  // Ent TypeSystem Methods

  public AttributeInstance
  createAttributeInstance(Position pos, Flags flags) {
    return new AttributeInstance_c(this, pos, flags);
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
    return new ModeTypeVariable_c(this, pos, name, false);
  }

  public ModeTypeVariable createBoundedExistential(Position pos) {
    return new ModeTypeVariable_c(this, pos, "_", true);
  }

  public ModeTypeVariable createWildcardModeTypeVariable(Position pos, String name) {
    ModeTypeVariable mtv = new ModeTypeVariable_c(this, pos, name, false);
    List<Type> bounds = new ArrayList<>();
    bounds.add(this.WildcardModeType());
    mtv.lowerBounds(bounds);
    mtv.upperBounds(bounds);
    mtv.inferBounds();
    return mtv;
  }

  public ModeValueType createModeValueType(Type mode) { return new ModeValueType_c(this, mode); }

  public Type createModeSubst(Type bt, List<Type> modeTypes) {
    return this.substEngine().createModeSubst(bt, modeTypes);
  }

  public ClassType wrapperClassOfModeSubstPrimitive(ModeSubstPrimitiveType t) {
    // We need to inject a boxed primitive type with a mode
    ClassType ct = this.wrapperClassOfPrimitive(t);
    return (ClassType)this.createModeSubst(ct, t.modeTypeArgs());
  }
}
