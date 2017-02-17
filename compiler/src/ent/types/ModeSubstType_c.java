package ent.types;

import polyglot.types.*;
import polyglot.types.Package;
import polyglot.util.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public abstract class ModeSubstType_c extends Type_c implements ModeSubstType {

  protected Type baseType;
  protected List<Type> modeTypeArgs;

  public ModeSubstType_c(Type baseType, List<Type> modeTypeArgs) {
    super(baseType.typeSystem(), baseType.position());
    this.baseType = baseType;
    this.modeTypeArgs = modeTypeArgs;
  }

  // ModeSubstType Methods
  public Type baseType() { return (Type)this.baseType; }

  public void baseType(Type baseType) { this.baseType = baseType; }

  public List<Type> modeTypeArgs() { return this.modeTypeArgs; }

  public void modeTypeArgs(List<Type> modeTypeArgs) { this.modeTypeArgs = modeTypeArgs; }

  public Type modeType() { return this.modeTypeArgs().get(0); }

  public void modeType(Type modeType) { this.modeTypeArgs().set(0, modeType); }

  public abstract ModeSubstType deepCopy();

  @SuppressWarnings("unused") private static final long readObjectVersionUID = 1L;

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject();

    if (in instanceof TypeInputStream) {
      TypeInputStream tin = (TypeInputStream)in;
      EntTypeSystem ts = (EntTypeSystem)tin.getTypeSystem();

      // TODO : This is a total deserialization hack, but needs to be used until
      // there is time to site down and serialize / deserialize proplery.

      List<Type> fixed = new ArrayList<>();
      for (Type t : this.modeTypeArgs()) {
        ModeType mt = (ModeType)t;
        if (mt.name().equals("*")) {
          fixed.add(ts.WildcardModeType());
        } else if (mt.name().equals("?")) {
          fixed.add(ts.DynamicModeType());
        } else {
          fixed.add(mt);
        }
      }

      this.modeTypeArgs(fixed);
    }
  }

  // Type Methods
  @Override
  public String toString() {
    return this.baseType() + "@mode<" + this.modeType() + ">";
  }

  @Override
  public String translate(Resolver c) {
    return this.baseType.translate(c);
  }

  @Override
  public boolean isType() {
    return this.baseType.isType();
  }

  @Override
  public boolean isPackage() {
    return this.baseType.isPackage();
  }

  @Override
  public Type toType() {
    return this.baseType.toType();
  }

  @Override
  public Package toPackage() {
    return this.baseType.toPackage();
  }

  @Override
  public boolean isCanonical() {
    return this.baseType.isCanonical();
  }

  @Override
  public boolean isPrimitive() {
    return this.baseType.isPrimitive();
  }

  @Override
  public boolean isNumeric() {
    return this.baseType.isNumeric();
  }

  @Override
  public boolean isIntOrLess() {
    return this.baseType.isIntOrLess();
  }

  @Override
  public boolean isLongOrLess() {
    return this.baseType.isLongOrLess();
  }

  @Override
  public boolean isVoid() {
    return this.baseType.isVoid();
  }

  @Override
  public boolean isBoolean() {
    return this.baseType.isBoolean();
  }

  @Override
  public boolean isChar() {
    return this.baseType.isChar();
  }

  @Override
  public boolean isByte() {
    return this.baseType.isByte();
  }

  @Override
  public boolean isShort() {
    return this.baseType.isShort();
  }

  @Override
  public boolean isInt() {
    return this.baseType.isInt();
  }

  @Override
  public boolean isLong() {
    return this.baseType.isLong();
  }

  @Override
  public boolean isFloat() {
    return this.baseType.isFloat();
  }

  @Override
  public boolean isDouble() {
    return this.baseType.isDouble();
  }

  @Override
  public boolean isReference() {
    return this.baseType.isReference();
  }

  @Override
  public boolean isNull() {
    return this.baseType.isNull();
  }

  @Override
  public boolean isClass() {
    return this.baseType.isClass();
  }

  @Override
  public boolean isArray() {
    return this.baseType.isArray();
  }

  @Override
  public boolean isThrowable() {
    return this.baseType.isThrowable();
  }

  @Override
  public boolean isUncheckedException() {
    return this.baseType.isUncheckedException();
  }

  // MODE-NOTE: It was an oversight to not inject a mode
  // subst type for these methods.
  @Override
  public ClassType toClass() {
    EntTypeSystem ts = (EntTypeSystem)this.ts;
    return (ClassType)ts.createModeSubst(this.baseType.toClass(),
                                         Arrays.<Type>asList(ts.WildcardModeType()));
  }

  @Override
  public NullType toNull() {
    EntTypeSystem ts = (EntTypeSystem)this.ts;
    return (NullType)ts.createModeSubst(this.baseType.toNull(),
                                        Arrays.<Type>asList(ts.WildcardModeType()));
  }

  @Override
  public ReferenceType toReference() {
    EntTypeSystem ts = (EntTypeSystem)this.ts;

    return (ReferenceType)ts.createModeSubst(this.baseType.toReference(),
                                             new ArrayList<>(this.modeTypeArgs()));
  }

  @Override
  public PrimitiveType toPrimitive() {
    EntTypeSystem ts = (EntTypeSystem)this.ts;
    return (PrimitiveType)ts.createModeSubst(this.baseType.toPrimitive(),
                                             Arrays.<Type>asList(ts.WildcardModeType()));
  }

  @Override
  public ArrayType toArray() {
    EntTypeSystem ts = (EntTypeSystem)this.ts;
    return (ArrayType)ts.createModeSubst(this.baseType.toArray(),
                                         Arrays.<Type>asList(ts.WildcardModeType()));
  }

  @Override
  public ArrayType arrayOf(int dims) {
    return this.baseType.arrayOf(dims);
  }

  // OPTIMIZATION-TODO : Aux method, if you get the ModeSubst object workign better this should
  // be a constant compare (intern substs);
  protected boolean modeTypeArgsEquals(ModeSubstType ot) {
    if (this.modeTypeArgs().size() != ot.modeTypeArgs().size()) {
      return false;
    }
    for (int i = 0; i < this.modeTypeArgs().size(); ++i) {
      if (!this.ts.typeEquals(this.modeTypeArgs().get(i), ot.modeTypeArgs().get(i))) {
        return false;
      }
    }
    return true;
  }

  protected boolean modeTypeArgsDescends(ModeSubstType ot) {
    if (this.modeTypeArgs().size() != ot.modeTypeArgs().size()) {
      return false;
    }
    for (int i = 0; i < this.modeTypeArgs().size(); ++i) {
      if (!this.ts.descendsFrom(this.modeTypeArgs().get(i), ot.modeTypeArgs().get(i))) {
        return false;
      }
    }
    return true;
  }

  protected boolean modeTypeArgsImplicit(ModeSubstType ot) {
    if (this.modeTypeArgs().size() != ot.modeTypeArgs().size()) {
      return false;
    }
    for (int i = 0; i < this.modeTypeArgs().size(); ++i) {
      if (!this.ts.isImplicitCastValid(this.modeTypeArgs().get(i), ot.modeTypeArgs().get(i))) {
        return false;
      }
    }
    return true;
  }

  protected boolean modeTypeArgsCast(ModeSubstType ot) {
    if (this.modeTypeArgs().size() != ot.modeTypeArgs().size()) {
      return false;
    }
    for (int i = 0; i < this.modeTypeArgs().size(); ++i) {
      if (!this.ts.isCastValid(this.modeTypeArgs().get(i), ot.modeTypeArgs().get(i))) {
        return false;
      }
    }
    return true;
  }


  // MODE-NOTE:
  // hashCode and equalsImpl are very tricky right now.
  // if we take the approach of stripping mode types when comparing
  // to non mode type substituted types then we have to loosen what
  // it means for be "equal" (not typeEqual).
  //
  // Object@mode<..> equals Object
  // Object@mode<low> does not equal Object@mode<high>
  //
  // Hash code is created based on our base type, not as big of a deal,
  // just could cause performance issues.
  @Override
  public int hashCode() {
    return this.baseType().hashCode();
  }

  // FIXME: See Stringer.java to catch bug
  @Override
  public boolean equalsImpl(TypeObject o) {
    if (!(o instanceof ModeSubstType)) {
      return false;
    }

    ModeSubstType st = (ModeSubstType)o;
    return this.baseType().equals(st.baseType()) && this.modeTypeArgsEquals(st);
  }

  @Override
  public boolean typeEqualsImpl(Type ansT) {
    if (!(ansT instanceof ModeSubstType)) {
      return this.ts.typeEquals(this.baseType(), ansT);
    }

    ModeSubstType st = (ModeSubstType)ansT;
    return this.ts.typeEquals(this.baseType(), st.baseType()) && this.modeTypeArgsEquals(st);
  }

  @Override
  public boolean isSubtypeImpl(Type ansT) {
    return this.ts.typeEquals(this, ansT) || this.ts.descendsFrom(this, ansT);
  }

  @Override
  public boolean descendsFromImpl(Type ansT) {
    if (!(ansT instanceof ModeSubstType)) {
      return this.ts.descendsFrom(this.baseType(), ansT);
    }

    ModeSubstType st = (ModeSubstType)ansT;

    // 3 Cases for descends
    //  1. Base descends, mode types are equal
    //  2. Base descends, mode types descend
    //  3. Base are equal, mode type descend
    //  TODO : Refactor and then optimize this code
    boolean descends = this.ts.descendsFrom(this.baseType(), st.baseType());
    if ((descends && this.modeTypeArgsEquals(st)) || (descends && this.modeTypeArgsDescends(st))) {
      return true;
    }

    return this.ts.typeEquals(this.baseType(), st.baseType()) && this.modeTypeArgsDescends(st);
  }

  @Override
  public boolean isCastValidImpl(Type toT) {
    if (!(toT instanceof ModeSubstType)) {
      return this.ts.isCastValid(this.baseType(), toT);
    }

    ModeSubstType st = (ModeSubstType)toT;
    
    // TODO: Special case for null
    if (st.baseType().isNull()) {
      return this.ts.isCastValid(this.baseType(), st.baseType());
    } else {
      return this.ts.isCastValid(this.baseType(), st.baseType()) && this.modeTypeArgsCast(st);
    }
  }

  @Override
  public boolean isImplicitCastValidImpl(Type toT) {
    if (!(toT instanceof ModeSubstType)) {
      return this.ts.isImplicitCastValid(this.baseType(), toT);
    }

    ModeSubstType st = (ModeSubstType)toT;
    return this.ts.isImplicitCastValid(this.baseType(), st.baseType()) &&
        this.modeTypeArgsImplicit(st);
  }

  /*
  @Override
  public boolean descendsFromImpl(Type ansT) {
    if (!(ansT instanceof ModeSubstType)) {
      System.out.println("Types: " + this + " " + ansT);
      System.out.println("Classes: " + this.getClass() + " " + ansT.getClass());
      throw new InternalCompilerError(
          "unexpected non mode substituted type " + ansT + ". typeEquals should allow the special
  case.");
    }

    ModeSubstType st = (ModeSubstType) ansT;
    return this.ts.descendsFrom(this.baseType(), st.baseType()) &&
           this.modeTypeArgsDescends(st);
  }

  @Override
  public boolean isCastValidImpl(Type toT) {
    if (!(toT instanceof ModeSubstType)) {
      System.out.println("Types: " + this + " " + toT);
      System.out.println("Classes: " + this.getClass() + " " + toT.getClass());
      throw new InternalCompilerError(
          "unexpected non mode substituted type " + toT + " from " + this + ". typeEquals should
  allow the special case.");
    }

    ModeSubstType st = (ModeSubstType) toT;
    return this.ts.isCastValid(this.baseType(), st.baseType()) &&
           this.modeTypeArgsEquals(st);
  }

  @Override
  public boolean isImplicitCastValidImpl(Type toT) {
    if (!(toT instanceof ModeSubstType)) {
      System.out.println("Types: " + this + " " + toT);
      System.out.println("Classes: " + this.getClass() + " " + toT.getClass());
      throw new InternalCompilerError(
          "unexpected non mode substituted type " + toT + ". typeEquals should allow the special
  case.");
    }

    ModeSubstType st = (ModeSubstType) toT;
    return this.ts.isImplicitCastValid(this.baseType(), st.baseType()) &&
           this.modeTypeArgsImplicit(st);
  }
  */

  @Override
  public boolean numericConversionValidImpl(Object value) {
    return this.ts.numericConversionValid(this.baseType(), value);
  }

  @Override
  public String argsAsString() {
    String args = "@mode<";
    for (Type t : this.modeTypeArgs()) {
      args += t.toString() + ",";
    }
    args = args.substring(0, args.length() - 1);
    args += ">";
    return args;
  }
}
