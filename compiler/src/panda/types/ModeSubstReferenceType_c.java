package panda.types;

import polyglot.types.FieldInstance;
import polyglot.types.MemberInstance;
import polyglot.types.MethodInstance;
import polyglot.types.ReferenceType;
import polyglot.types.Type;

import polyglot.ext.jl5.types.EnumInstance;
import polyglot.ext.jl5.types.JL5ReferenceType;

import java.util.ArrayList;
import java.util.List;

public abstract class ModeSubstReferenceType_c extends ModeSubstType_c implements ModeSubstReferenceType {

  public ModeSubstReferenceType_c(Type baseType, List<Mode> modeTypeArgs) {
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
  public boolean descendsFromImpl(Type ancestor) {
    // TODO : We will let types that have not be subst with a mode
    // "see through" and check for equality for now and flag a
    // warning.

    if (!(ancestor instanceof ModeSubstType)) {
      System.out.println("WARNING: descendsFromImpl --- " + this + " -- " + ancestor);
      System.out.println("         classes          --- " + this.getClass() + " -- " + ancestor.getClass());
      return this.ts.descendsFrom(this.baseType(), ancestor);
    } 

    // TODO : For now, force mode types to be the same, ad in proper subtyping
    // later
    ModeSubstType p = (ModeSubstType) ancestor;
    return this.ts.descendsFrom(this.baseType(), p.baseType()) &&
           this.ts.typeEquals(this.modeType(), p.modeType());
  }

  @Override
  public boolean isImplicitCastValidImpl(Type toType) {
    // TODO : For now, force mode types to be the same, ad in proper subtyping
    // later
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
