package ent.types;

import polyglot.frontend.Job;
import polyglot.types.ConstructorInstance;
import polyglot.types.ClassType;
import polyglot.types.ClassType_c;
import polyglot.types.Declaration;
import polyglot.types.Flags;
import polyglot.types.FieldInstance;
import polyglot.types.MethodInstance;
import polyglot.types.Package;
import polyglot.types.ReferenceType;
import polyglot.types.Resolver;
import polyglot.types.Type;

import polyglot.ext.jl5.types.Annotations;
import polyglot.ext.jl5.types.AnnotationTypeElemInstance;
import polyglot.ext.jl5.types.JL5ParsedClassType;
import polyglot.ext.jl5.types.JL5SubstClassType;
import polyglot.ext.jl5.types.EnumInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModeSubstRawClass_c extends ModeSubstClassType_c implements ModeSubstRawClass {

  public ModeSubstRawClass_c(EntRawClass baseType, List<Type> modeTypeArgs) {
    super(baseType, modeTypeArgs);
  }

  @Override
  public ModeSubstType deepCopy() {
    return new ModeSubstRawClass_c((EntRawClass)this.baseType(),
                                   new ArrayList<Type>(this.modeTypeArgs()));
  }

  // RawClass Methods

  @Override
  public boolean isRawClass() {
    return true;
  }

  @Override
  public JL5ParsedClassType base() {
    return ((EntRawClass)this.baseType()).base();
  }

  @Override
  public JL5SubstClassType erased() {
    System.out.println("Erased!");
    return ((EntRawClass)this.baseType()).erased();
  }

  @Override
  public List<EnumInstance> enumConstants() {
    return ((EntRawClass)this.baseType()).enumConstants();
  }

  @Override
  public Kind kind() {
    return ((EntRawClass)this.baseType()).kind();
  }

  @Override
  public ClassType outer() {
    return ((EntRawClass)this.baseType()).outer();
  }

  @Override
  public String toString() {
    return ((EntRawClass)this.baseType()).toString() + "@mode<" + this.modeType() + ">";
  }

  @Override
  public Package package_() {
    return ((EntRawClass)this.baseType()).package_();
  }

  @Override
  public Flags flags() {
    return ((EntRawClass)this.baseType()).flags();
  }

  @Override
  public List<? extends ConstructorInstance> constructors() {
    return ((EntRawClass)this.baseType()).constructors();
  }

  @Override
  public List<? extends ClassType> memberClasses() {
    return ((EntRawClass)this.baseType()).memberClasses();
  }

  @Override
  public List<? extends MethodInstance> methods() {
    return ((EntRawClass)this.baseType()).methods();
  }

  @Override
  public List<? extends FieldInstance> fields() {
    return ((EntRawClass)this.baseType()).fields();
  }

  @Override
  public List<? extends ReferenceType> interfaces() {
    return ((EntRawClass)this.baseType()).interfaces();
  }

  @Override
  public Type superType() {
    return ((EntRawClass)this.baseType()).superType();
  }

  @Override
  public Set<? extends Type> superclasses() {
    return ((EntRawClass)this.baseType()).superclasses();
  }

  @Override
  public String translateAsReceiver(Resolver c) {
    return ((EntRawClass)this.baseType()).translateAsReceiver(c);
  }

  @Override
  public boolean inStaticContext() {
    return ((EntRawClass)this.baseType()).inStaticContext();
  }

  @Override
  public void setFlags(Flags flags) {
    ((EntRawClass)this.baseType()).setFlags(flags);
  }

  @Override
  public void setContainer(ReferenceType container) {
    ((EntRawClass)this.baseType()).setContainer(container);
  }

  @Override
  public AnnotationTypeElemInstance annotationElemNamed(String name) {
    return ((EntRawClass)this.baseType()).annotationElemNamed(name);
  }

  @Override
  public List<AnnotationTypeElemInstance> annotationElems() {
    return ((EntRawClass)this.baseType()).annotationElems();
  }

  @Override
  public Annotations annotations() {
    return ((EntRawClass)this.baseType()).annotations();
  }
}
