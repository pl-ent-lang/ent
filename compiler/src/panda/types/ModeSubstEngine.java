package panda.types;

import polyglot.util.InternalCompilerError;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.ReferenceType;
import polyglot.types.ConstructorInstance;
import polyglot.types.FieldInstance;
import polyglot.types.MethodInstance;
import polyglot.types.MemberInstance; 

import polyglot.ext.jl5.types.RawClass;
import polyglot.ext.jl5.types.TypeVariable;
import polyglot.ext.jl5.types.JL5ArrayType;
import polyglot.ext.jl5.types.JL5PrimitiveType;

import polyglot.ext.jl5.types.JL5ParsedClassType;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ModeSubstEngine { 
  private Map<Type,Map<Type, Type>> substCache = new HashMap<>();
  private PandaTypeSystem typeSystem;

  public ModeSubstEngine(PandaTypeSystem typeSystem) {
    this.typeSystem = typeSystem;
  }

  private PandaTypeSystem typeSystem() {
    return this.typeSystem;
  }

  private Map<Type,Map<Type, Type>> substCache() {
    return this.substCache;
  }

  public boolean containsSubst(Type t, Type mt) {
    Type st = this.getSubst(t, mt);
    return !(st == null);
  }

  private Type getSubst(Type t, Type mt) {
    Map<Type, Type> typeMap = this.substCache().get(t);
    if (typeMap == null) {
      return null;
    }
    return typeMap.get(mt);
  }

  private void putSubst(Type t, Type mt, Type st) {
    Map<Type, Type> typeMap = this.substCache().get(t);
    if (typeMap == null) {
      typeMap = new HashMap<Type, Type>();
      this.substCache().put(t, typeMap);
    }
    typeMap.put(mt, st);
  }

  /*
  public Type subst(Type bt, List<Type> mtArgs) throws InternalCompilerError {
    //Type st = this.getSubst(t, mt);
    //if (st != null) {
    //return st;
    //}

    //Type st = uncachedSubst(bt, mtArgs);
    //this.putSubst(t, mt, st);

    return this.substType(
  }
  */

  /*
  private Map<ModeTypeVariable, Type> buildModeTypeMap(ClassType ct, 
                                                       List<Type> mtArgs) {
    Map<ModeTypeVariable, Type> mtMap = new HashMap<ModeTypeVariable, Type>();
    List<ModeTypeVariable> 
    for (int i = 0; i < mtArgs.size(); ++i) {
      mtMap.put(
    }
  }

  public Type substClassType(Type t, List<Type> mtArgs) {
    PandaClassType ct = (PandaClassType) t;
  }
  */

  public Type createModeSubst(Type t, List<Type> mtArgs) throws InternalCompilerError {
    if (t instanceof TypeVariable) {
      return t;
    }

    if (t instanceof PandaClassType) {
      return this.createModeSubstClass(t, mtArgs);
    } else {
      return this.createModeSubstType(t, mtArgs);
    } 
  } 

  public boolean modeSubstSatisfiesConstraints(PandaClassType t, List<Type> mtArgs) {
    Map<ModeTypeVariable, Type> mtMap = new HashMap<>();
    List<ModeTypeVariable> mtVars = t.modeTypeVars();
    for (int i = 0; i < mtVars.size(); ++i) {
      Type sm = mtArgs.get(i);

      // All constraints must be satisfied (all upper bounds)
      for (Type vm : mtVars.get(i).bounds()) {
        if (vm instanceof ModeTypeVariable) {
          vm = mtMap.get(vm);
        }

        if (!this.typeSystem().isSubtype(sm, vm) && sm != this.typeSystem().DynamicModeType()) {
          System.out.println("Attempting to subst with " + sm + " failing on contraint " + vm);
          return false;
        }
      }

      mtMap.put(mtVars.get(i), sm);
    }
    return true;
  }
  
  public Type createModeSubstClass(Type t, List<Type> mtArgs) {
    // Check that a subst satisfies the contraints on the mode type variables,
    // otherwise flag an error
    if (!this.modeSubstSatisfiesConstraints((PandaClassType) t, mtArgs)) {
      return null;
      //throw new SemanticException("Substitution unable to satisfy bounds for " + t);
    }

    // Build the subst object for all class types
    if (t instanceof PandaParsedClassType) {
      return new ModeSubstParsedClassType_c((PandaParsedClassType) t, mtArgs);

    } else if (t instanceof PandaRawClass) {
      return new ModeSubstRawClass_c((PandaRawClass) t, mtArgs);

    } else if (t instanceof PandaSubstClassType) {
      return new ModeSubstSubstClassType_c((PandaSubstClassType) t, mtArgs);

    } else {
      // Not yet impl
      throw new InternalCompilerError("Implement " + t.getClass() + "!"); 
    } 
  }

  public Type createModeSubstType(Type t, List<Type> mtArgs) {
    if (t instanceof JL5PrimitiveType) {
      return new ModeSubstPrimitiveType_c((JL5PrimitiveType) t, mtArgs);

    } else if (t instanceof JL5ArrayType) {
      return new ModeSubstArrayType_c((JL5ArrayType) t, mtArgs);

    } else {
      // Not yet impl
      throw new InternalCompilerError("Implement " + t.getClass() + "!"); 
    }
  }
}
