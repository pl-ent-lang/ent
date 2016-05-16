package ent.types;

import polyglot.types.*;
import polyglot.util.*;

import polyglot.ext.jl5.types.*;

import java.util.ArrayList;
import java.util.List;

public abstract class ModeSubstReferenceType_c
    extends ModeSubstType_c implements ModeSubstReferenceType {

  public ModeSubstReferenceType_c(Type baseType, List<Type> modeTypeArgs) {
    super(baseType, modeTypeArgs);
  }

  @Override
  public List<? extends MemberInstance> members() {
    List<MemberInstance> l = new ArrayList<>();
    l.addAll(methods());
    l.addAll(fields());
    return l;
  }

  @Override public abstract List<? extends MethodInstance> methods();

  @Override public abstract List<? extends FieldInstance> fields();

  @Override public abstract Type superType();

  @Override public abstract List<? extends ReferenceType> interfaces();

  @Override
  public final boolean hasMethod(MethodInstance mi) {
    return ((ReferenceType)this.baseType()).hasMethod(mi);
  }

  @Override
  public final boolean hasMethodImpl(MethodInstance mi) {
    return ((ReferenceType)this.baseType()).hasMethodImpl(mi);
  }

  @Override
  public List<? extends MethodInstance> methodsNamed(String name) {
    return ((ReferenceType)this.baseType()).methodsNamed(name);
  }

  @Override
  public List<? extends MethodInstance> methods(String name, List<? extends Type> argTypes) {
    return ((ReferenceType)this.baseType()).methods(name, argTypes);
  }

  // JL5ReferenceType Methods
  public abstract EnumInstance enumConstantNamed(String name);
}
