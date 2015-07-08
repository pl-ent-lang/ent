package panda.types;

import polyglot.types.*;
import polyglot.util.*;

import polyglot.ext.jl5.types.*;

import java.util.ArrayList;
import java.util.List;

public abstract class ModeSubstReferenceType_c extends ModeSubstType_c implements ModeSubstReferenceType {

  public ModeSubstReferenceType_c(Type baseType, List<Type> modeTypeArgs) {
    super(baseType, modeTypeArgs);
  } 

  // ReferenceType Methods
  @Override
  public boolean isReference() {
    return true;
  }

  @Override
  public ReferenceType toReference() {
    return this;
  }

  @Override
  public List<? extends MemberInstance> members() {
    List<MemberInstance> l = new ArrayList<>();
    l.addAll(methods());
    l.addAll(fields());
    return l;
  }

  @Override
  public abstract List<? extends MethodInstance> methods();

  @Override
  public abstract List<? extends FieldInstance> fields();

  @Override
  public abstract Type superType();

  @Override
  public abstract List<? extends ReferenceType> interfaces();

  @Override
  public final boolean hasMethod(MethodInstance mi) {
    return ((ReferenceType) this.baseType()).hasMethod(mi);
  }

  @Override
  public final boolean hasMethodImpl(MethodInstance mi) {
    return ((ReferenceType) this.baseType()).hasMethodImpl(mi);
  }

  @Override
  public boolean typeEqualsImpl(Type ancestor) {
    if (!(ancestor instanceof ModeSubstType)) {
      throw new InternalCompilerError(
          "mode subst did not occur - comparing " + this + " -- " + ancestor);
    } 

    // TODO : For now, force mode types to be the same, ad in proper subtyping
    // later
    ModeSubstType p = (ModeSubstType) ancestor;
    return this.ts.typeEquals(this.baseType(), p.baseType()) && this.modeTypeArgsEquals(p);
  }


  @Override
  public boolean descendsFromImpl(Type ancestor) {
    if (!(ancestor instanceof ModeSubstType)) {
      throw new InternalCompilerError(
          "mode subst did not occur - comparing " + this + " -- " + ancestor);
    } 

    // TODO : For now, force mode types to be the same, ad in proper subtyping
    // later
    ModeSubstType p = (ModeSubstType) ancestor;
    return this.ts.descendsFrom(this.baseType(), p.baseType()) && this.modeTypeArgsEquals(p);
  }

  private boolean modeTypeArgsEquals(ModeSubstType ot) {
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

  @Override
  public boolean isCastValidImpl(Type toType) {
    // TODO : For now, force mode types to be the same, ad in proper subtyping
    // later
    if (!(toType instanceof ModeSubstType)) {
      throw new InternalCompilerError(
          "mode subst did not occur - comparing " + this + " -- " + toType);
    } 

    ModeSubstType p = (ModeSubstType) toType;
    return (this.ts.isSubtype(this.baseType(), p.baseType()) ||
            this.ts.isSubtype(p.baseType(), this.baseType())) &&
           this.ts.typeEquals(this.modeType(), p.modeType());
  }


  @Override
  public boolean isImplicitCastValidImpl(Type toType) {
    // TODO : For now, force mode types to be the same, ad in proper subtyping
    // later
    if (!(toType instanceof ModeSubstType)) {
      throw new InternalCompilerError(
          "mode subst did not occur - comparing " + this + " -- " + toType);
    } 

    ModeSubstType p = (ModeSubstType) toType;
    return this.ts.isSubtype(this.baseType(), p.baseType()) &&
           this.ts.typeEquals(this.modeType(), p.modeType());
  }

  @Override
  public List<? extends MethodInstance> methodsNamed(String name) {
    return ((ReferenceType) this.baseType()).methodsNamed(name);
  }

  @Override
  public List<? extends MethodInstance> methods(String name, 
                                                List<? extends Type> argTypes) {
    return ((ReferenceType) this.baseType()).methods(name, argTypes);
  }

  // JL5ReferenceType Methods
  public abstract EnumInstance enumConstantNamed(String name);


}
