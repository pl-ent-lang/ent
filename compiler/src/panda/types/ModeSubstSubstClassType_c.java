package panda.types;

import polyglot.types.ClassType;
import polyglot.types.ConstructorInstance;
import polyglot.types.FieldInstance;
import polyglot.types.Flags;
import polyglot.types.MethodInstance;
import polyglot.types.Package;
import polyglot.types.ReferenceType;
import polyglot.types.Resolver;
import polyglot.types.Type;
import polyglot.util.CodeWriter;

import polyglot.ext.param.types.PClass;
import polyglot.ext.param.types.Subst;

import polyglot.ext.jl5.types.Annotations;
import polyglot.ext.jl5.types.AnnotationTypeElemInstance;
import polyglot.ext.jl5.types.EnumInstance;
import polyglot.ext.jl5.types.TypeVariable;
import polyglot.ext.jl5.types.JL5ParsedClassType;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

public class ModeSubstSubstClassType_c extends ModeSubstClassType_c implements ModeSubstSubstClassType {

  public ModeSubstSubstClassType_c(PandaSubstClassType baseType,
                                   Type modeType) {
    super(baseType, modeType);
  }

  @Override
  public PandaType deepCopy() {
    return 
      new ModeSubstSubstClassType_c((PandaSubstClassType) this.baseType(),
                                     this.modeType());
  }

  @Override
  public Iterator<Entry<TypeVariable, ReferenceType>> entries() {
    return ((PandaSubstClassType) this.baseType()).entries();
  }

  // JL5SubstType Methods
  @Override
  public PClass<TypeVariable, ReferenceType> instantiatedFrom() {
    return ((PandaSubstClassType) this.baseType()).instantiatedFrom();
  }

  @Override
  public Subst<TypeVariable, ReferenceType> subst() {
    return ((PandaSubstClassType) this.baseType()).subst();
  }

  @Override
  public List<ReferenceType> actuals() {
    return ((PandaSubstClassType) this.baseType()).actuals();
  }

  // JL5ClassType
  @Override
  public EnumInstance enumConstantNamed(String name) {
    return ((PandaSubstClassType) this.baseType()).enumConstantNamed(name);
  }

  @Override
  public List<EnumInstance> enumConstants() {
    return ((PandaSubstClassType) this.baseType()).enumConstants();
  }

  @Override
  public AnnotationTypeElemInstance annotationElemNamed(String name) {
    return ((PandaSubstClassType) this.baseType()).annotationElemNamed(name);
  }

  @Override
  public List<AnnotationTypeElemInstance> annotationElems() {
    return ((PandaSubstClassType) this.baseType()).annotationElems();
  }

  @Override
  public void printParams(CodeWriter w) {
    ((PandaSubstClassType) this.baseType()).printParams(w);
  }

  @Override
  public boolean isRawClass() {
    return false;
  }

  // TODO : What is the right way to handle this situation? We need to see through
  // to the base type, but it needs to be subst for the mode.

  @Override
  public JL5ParsedClassType base() {
    //return ((PandaSubstClassType) this.baseType()).base();
    JL5ParsedClassType base = ((PandaSubstClassType) this.baseType()).base();
    PandaTypeSystem ts = (PandaTypeSystem) this.ts;
    return (PandaParsedClassType) ts.substModeType(base, this.modeType());
  }

  @Override
  public String translateAsReceiver(Resolver c) {
    return ((PandaSubstClassType) this.baseType()).translateAsReceiver(c);
  }

  @Override
  public ClassType outer() {
    return ((PandaSubstClassType) this.baseType()).outer();
  }

  @Override
  public Annotations annotations() {
    return ((PandaSubstClassType) this.baseType()).annotations();
  }

  @Override
  public Set<? extends Type> superclasses() {
    return ((PandaSubstClassType) this.baseType()).superclasses();
  }

  @Override
  public String fullName() {
    return ((PandaSubstClassType) this.baseType()).fullName();
  }

  @Override
  public String name() {
    return ((PandaSubstClassType) this.baseType()).name() + "@mode<" + this.modeType() + ">";
  }

  @Override
  public String toString() {
    return this.name();
  }

  @Override
  public Flags flags() {
    return ((PandaSubstClassType) this.baseType()).flags();
  }

  @Override
  public Package package_() {
    return ((PandaSubstClassType) this.baseType()).package_();
  }

  @Override
  public Type superType() {
    return ((PandaSubstClassType) this.baseType()).superType();
  }

  @Override
  public List<? extends ReferenceType> interfaces() {
    return ((PandaSubstClassType) this.baseType()).interfaces();
  }

  @Override
  public List<? extends FieldInstance> fields() {
    return ((PandaSubstClassType) this.baseType()).fields();
  }

  @Override
  public List<? extends MethodInstance> methods() {
    return ((PandaSubstClassType) this.baseType()).methods();
  }

  @Override
  public List<? extends ConstructorInstance> constructors() {
    return ((PandaSubstClassType) this.baseType()).constructors();
  }

  @Override
  public List<? extends ClassType> memberClasses() {
    return ((PandaSubstClassType) this.baseType()).memberClasses();
  }

  @Override
  public ClassType.Kind kind() {
    return ((PandaSubstClassType) this.baseType()).kind();
  }

  @Override
  public boolean inStaticContext() {
    return ((PandaSubstClassType) this.baseType()).inStaticContext();
  }

  @Override
  public void setFlags(Flags flags) {
    ((PandaSubstClassType) this.baseType()).setFlags(flags);
  }

  @Override
  public void setContainer(ReferenceType container) {
    ((PandaSubstClassType) this.baseType()).setContainer(container);
  }

}
