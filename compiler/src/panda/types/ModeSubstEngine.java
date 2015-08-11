package panda.types;

import polyglot.types.*;
import polyglot.util.*;

import polyglot.ext.jl5.types.*;
import polyglot.ext.jl7.types.*;

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

  public boolean modeSubstSatisfiesConstraints(PandaClassType baseT, List<Type> mtArgs) {
    Map<ModeTypeVariable, Type> mtMap = new HashMap<>();
    List<ModeTypeVariable> baseMtVars = baseT.modeTypeVars();

    if (baseMtVars.size() != mtArgs.size()) {
      return false;
    }

    for (int i = 0; i < baseMtVars.size(); ++i) {
      Type mtArg = mtArgs.get(i);

      //System.out.format("Checking arg %s on var %s\n", mtArg, baseMtVars.get(i));

      // All constraints must be satisfied (all upper bounds)
      for (Type bound : baseMtVars.get(i).bounds()) {
        if (bound instanceof ModeTypeVariable) {
          bound = mtMap.get(bound);
        }

        // Strengthing this restriction, can not blindly dump a DynamicModeType, must be a subtype
        // of constraints
        //if (!this.typeSystem().isSubtype(mtArg, bound) && mtArg != this.typeSystem().DynamicModeType()) {

        if (this.typeSystem().isSubtype(mtArg, bound)) {
          continue;
        }

        if (this.typeSystem().DynamicModeType() == mtArg && baseMtVars.get(i).isDynRecvr()) {
          continue;
        }
        
        return false;
      }

      if (!baseMtVars.get(i).isDynRecvr()) {
        mtMap.put(baseMtVars.get(i), mtArg);
      } else {
        mtMap.put(baseMtVars.get(i), baseMtVars.get(i).upperBound());
      }
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

    } else if (t instanceof PandaDiamondType) {
      return new ModeSubstDiamondType_c((DiamondType) t, mtArgs);

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

    } else if (t instanceof McaseType) {
      return new ModeSubstMcaseType_c((McaseType) t, mtArgs);
    
    } else if (t instanceof NullType) {
      return new ModeSubstNullType_c((NullType) t, mtArgs);

    } else {
      // Not yet impl
      throw new InternalCompilerError("Implement " + t.getClass() + "!"); 
    }
  }
}
