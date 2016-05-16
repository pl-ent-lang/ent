package ent.types;

import polyglot.types.*;
import polyglot.util.*;

import polyglot.ext.jl5.types.*;
import polyglot.ext.jl7.types.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ModeSubstEngine {
  private Map<Type, Map<List<Type>, Type>> substCache = new HashMap<>();
  private EntTypeSystem typeSystem;

  public ModeSubstEngine(EntTypeSystem typeSystem) { this.typeSystem = typeSystem; }

  private EntTypeSystem typeSystem() { return this.typeSystem; }

  public Type createModeSubst(Type t, List<Type> mtArgs) throws InternalCompilerError {
    Map<List<Type>, Type> cached = this.substCache.get(t);
    if (cached != null && cached.containsKey(mtArgs)) {
      return cached.get(mtArgs);
    }

    Type newModeSubst = this.createModeSubstNocache(t, mtArgs);
    if (cached == null) {
      Map<List<Type>, Type> newSubsts = new HashMap<>();
      this.substCache.put(t, newSubsts);
    }

    this.substCache.get(t).put(mtArgs, newModeSubst);
    return newModeSubst;
  }

  public Type createModeSubstNocache(Type t, List<Type> mtArgs) throws InternalCompilerError {
    if (t instanceof TypeVariable) {
      return t;
    }

    if (t instanceof EntClassType) {
      return this.createModeSubstClass(t, mtArgs);
    } else {
      return this.createModeSubstType(t, mtArgs);
    }
  }

  public Type createModeSubstClass(Type t, List<Type> mtArgs) {
    // Build the subst object for all class types
    if (t instanceof EntParsedClassType) {
      return new ModeSubstParsedClassType_c((EntParsedClassType)t, mtArgs);

    } else if (t instanceof EntRawClass) {
      return new ModeSubstRawClass_c((EntRawClass)t, mtArgs);

    } else if (t instanceof EntSubstClassType) {
      return new ModeSubstSubstClassType_c((EntSubstClassType)t, mtArgs);

    } else if (t instanceof EntDiamondType) {
      return new ModeSubstDiamondType_c((DiamondType)t, mtArgs);

    } else {
      // Not yet impl
      throw new InternalCompilerError("Implement " + t.getClass() + "!");
    }
  }

  public Type createModeSubstType(Type t, List<Type> mtArgs) {
    if (t instanceof JL5PrimitiveType) {
      return new ModeSubstPrimitiveType_c((JL5PrimitiveType)t, mtArgs);

    } else if (t instanceof JL5ArrayType) {
      return new ModeSubstArrayType_c((JL5ArrayType)t, mtArgs);

    } else if (t instanceof McaseType) {
      return new ModeSubstMcaseType_c((McaseType)t, mtArgs);

    } else if (t instanceof NullType) {
      return new ModeSubstNullType_c((NullType)t, mtArgs);

    } else {
      // Not yet impl
      throw new InternalCompilerError("Implement " + t.getClass() + "!");
    }
  }
}
