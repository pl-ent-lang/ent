package ent.types;

import polyglot.types.ArrayType;
import polyglot.types.Type;
import polyglot.types.MethodInstance;
import polyglot.types.FieldInstance;
import polyglot.types.ReferenceType;

import polyglot.ext.jl5.types.JL5ArrayType;
import polyglot.ext.jl5.types.EnumInstance;

import java.util.List;

public class ModeSubstArrayType_c extends ModeSubstReferenceType_c implements ModeSubstArrayType {

  public ModeSubstArrayType_c(JL5ArrayType baseType, List<Type> modeTypeArgs) {
    super(baseType, modeTypeArgs);
  }

  @Override
  public ModeSubstType deepCopy() {
    return new ModeSubstArrayType_c((JL5ArrayType)this.baseType(), this.modeTypeArgs());
  }

  // ArrayType Methods
  @Override
  public Type base() {
    return ((JL5ArrayType)this.baseType()).base();
  }

  @Override
  public ArrayType base(Type base) {
    return ((JL5ArrayType)this.baseType()).base(base);
  }

  @Override
  public Type ultimateBase() {
    return ((JL5ArrayType)this.baseType()).ultimateBase();
  }

  @Override
  public int dims() {
    return ((JL5ArrayType)this.baseType()).dims();
  }

  @Override
  public List<? extends MethodInstance> methods() {
    return ((JL5ArrayType)this.baseType()).methods();
  }

  @Override
  public List<? extends FieldInstance> fields() {
    return ((JL5ArrayType)this.baseType()).fields();
  }

  @Override
  public MethodInstance cloneMethod() {
    return ((JL5ArrayType)this.baseType()).cloneMethod();
  }

  @Override
  public FieldInstance fieldNamed(String name) {
    return ((JL5ArrayType)this.baseType()).fieldNamed(name);
  }

  @Override
  public FieldInstance lengthField() {
    return ((JL5ArrayType)this.baseType()).lengthField();
  }

  @Override
  public Type superType() {
    return ((JL5ArrayType)this.baseType()).superType();
  }

  @Override
  public List<? extends ReferenceType> interfaces() {
    return ((JL5ArrayType)this.baseType()).interfaces();
  }

  // JL5ArrayType Methods
  @Override
  public boolean isVarArg() {
    return ((JL5ArrayType)this.baseType()).isVarArg();
  }

  @Override
  public void setVarArg() {
    ((JL5ArrayType)this.baseType()).setVarArg();
  }

  // Inheritance Side-effect Methods
  @Override
  public EnumInstance enumConstantNamed(String name) {
    return null;
  }

  // Type Methods

  @Override
  public boolean isImplicitCastValidImpl(Type toT) {
    if (!(toT instanceof ModeSubstType)) {
      return this.ts.isImplicitCastValid(this.baseType(), toT);
    }
    ModeSubstType st = (ModeSubstType)toT;
    return this.ts.isImplicitCastValid(this.baseType(), st.baseType()) &&
        this.modeTypeArgsEquals(st);
  }

  @Override
  public boolean isCastValidImpl(Type toT) {
    if (!(toT instanceof ModeSubstType)) {
      return this.ts.isCastValid(this.baseType(), toT);
    }
    ModeSubstType st = (ModeSubstType)toT;
    return this.ts.isCastValid(this.baseType(), st.baseType()) && this.modeTypeArgsEquals(st);
  }
}
