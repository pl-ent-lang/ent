package ent.types;

import polyglot.types.ClassType;
import polyglot.types.FieldInstance;
import polyglot.types.MethodInstance;
import polyglot.types.ReferenceType;
import polyglot.types.Resolver;
import polyglot.types.Type;

import polyglot.ext.jl5.types.EnumInstance;
import polyglot.ext.jl5.types.JL5ProcedureInstance;
import polyglot.ext.jl5.types.TypeVariable;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class ModeSubstTypeVariable_c
    extends ModeSubstReferenceType_c implements ModeSubstTypeVariable {

  public ModeSubstTypeVariable_c(Type baseType, List<Type> modeTypeArgs) {
    super(baseType, modeTypeArgs);
  }

  @Override
  public ModeSubstType deepCopy() {
    return new ModeSubstTypeVariable_c(this.baseType(), new ArrayList<Type>(this.modeTypeArgs()));
  }

  // TypeVariable Methods
  @Override
  public void setDeclaringProcedure(JL5ProcedureInstance pi) {
    ((TypeVariable)this.baseType()).setDeclaringProcedure(pi);
  }

  @Override
  public void setDeclaringClass(ClassType ct) {
    ((TypeVariable)this.baseType()).setDeclaringClass(ct);
  }

  @Override
  public void setSyntheticOrigin() {
    ((TypeVariable)this.baseType()).setSyntheticOrigin();
  }

  @Override
  public TVarDecl declaredIn() {
    return ((TypeVariable)this.baseType()).declaredIn();
  }

  @Override
  public ClassType declaringClass() {
    return ((TypeVariable)this.baseType()).declaringClass();
  }

  @Override
  public JL5ProcedureInstance declaringProcedure() {
    return ((TypeVariable)this.baseType()).declaringProcedure();
  }

  @Override
  public String name() {
    return ((TypeVariable)this.baseType()).name() + "@mode<" + this.modeType() + ">";
  }

  @Override
  public String fullName() {
    return this.name();
  }

  @Override
  public boolean isCanonical() {
    return true;
  }

  @Override
  public List<? extends MethodInstance> methods() {
    return Collections.emptyList();
  }

  @Override
  public List<? extends FieldInstance> fields() {
    return Collections.emptyList();
  }

  @Override
  public FieldInstance fieldNamed(String name) {
    for (FieldInstance fi : fields()) {
      if (fi.name().equals(name)) {
        return fi;
      }
    }
    return null;
  }

  @Override
  public List<? extends ReferenceType> interfaces() {
    return Collections.emptyList();
  }

  @Override
  public ReferenceType erasureType() {
    return ((TypeVariable)this.baseType()).erasureType();
  }

  @Override
  public Type superType() {
    return ((TypeVariable)this.baseType()).superType();
  }

  @Override
  public String translate(Resolver c) {
    return ((TypeVariable)this.baseType()).translate(c);
  }

  @Override
  public boolean hasLowerBound() {
    return ((TypeVariable)this.baseType()).hasLowerBound();
  }

  @Override
  public ReferenceType lowerBound() {
    return ((TypeVariable)this.baseType()).lowerBound();
  }

  @Override
  public void setLowerBound(ReferenceType lowerBound) {
    ((TypeVariable)this.baseType()).setLowerBound(lowerBound);
  }

  @Override
  public ReferenceType upperBound() {
    return ((TypeVariable)this.baseType()).upperBound();
  }

  @Override
  public void setUpperBound(ReferenceType upperBound) {
    ((TypeVariable)this.baseType()).setUpperBound(upperBound);
  }

  @Override
  public TypeVariable upperBound(ReferenceType upperBound) {
    return ((TypeVariable)this.baseType()).upperBound(upperBound);
  }

  // JL5ClassType Methods
  @Override
  public EnumInstance enumConstantNamed(String name) {
    return null;
  }
}
