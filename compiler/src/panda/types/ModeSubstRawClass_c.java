package panda.types;

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

public class ModeSubstRawClass_c extends ModeSubstClassType_c implements ModeSubstRawClass{

  public ModeSubstRawClass_c(PandaRawClass baseType, List<Type> modeTypeArgs) {
    super(baseType, modeTypeArgs);
  }

  @Override
  public ModeSubstType deepCopy() {
    return new ModeSubstRawClass_c((PandaRawClass) this.baseType(),
                                   new ArrayList<Type>(this.modeTypeArgs()));
  }

  // RawClass Methods
  
  @Override
  public boolean isRawClass() {
    return true;
  }

  @Override
  public JL5ParsedClassType base() {
    System.out.println("Based!");
    return ((PandaRawClass) this.baseType()).base();
  }

  @Override
  public JL5SubstClassType erased() {
    System.out.println("Erased!");
    return ((PandaRawClass) this.baseType()).erased();
  }
                           
  @Override
  public List<EnumInstance> enumConstants() {
    return ((PandaRawClass) this.baseType()).enumConstants();
  }

  /*
  @Override
  public Job job() {
    return null;
  }
  */

  @Override
  public Kind kind() {
    return ((PandaRawClass) this.baseType()).kind();
  }

  @Override
  public ClassType outer() {
    return ((PandaRawClass) this.baseType()).outer();
  }

  @Override
  public String name() {
    return ((PandaRawClass) this.baseType()).name() + "@mode<" + this.modeType() + ">";
  }

  @Override
  public String toString() {
    return this.name();
  }

  @Override
  public Package package_() {
    return ((PandaRawClass) this.baseType()).package_();
  }

  @Override
  public Flags flags() {
    return ((PandaRawClass) this.baseType()).flags();
  }

  @Override
  public List<? extends ConstructorInstance> constructors() {
    return ((PandaRawClass) this.baseType()).constructors();
  }

  @Override
  public List<? extends ClassType> memberClasses() {
    return ((PandaRawClass) this.baseType()).memberClasses();
  }

  @Override
  public List<? extends MethodInstance> methods() {
    return ((PandaRawClass) this.baseType()).methods();
  }

  @Override
  public List<? extends FieldInstance> fields() {
    return ((PandaRawClass) this.baseType()).fields();
  }

  @Override
  public List<? extends ReferenceType> interfaces() {
    return ((PandaRawClass) this.baseType()).interfaces();
  }

  @Override
  public Type superType() {
    return ((PandaRawClass) this.baseType()).superType();
  }

  @Override
  public Set<? extends Type> superclasses() {
    return ((PandaRawClass) this.baseType()).superclasses();
  }

  @Override
  public String translateAsReceiver(Resolver c) {
    return ((PandaRawClass) this.baseType()).translateAsReceiver(c);
  }

  @Override
  public boolean inStaticContext() {
    return ((PandaRawClass) this.baseType()).inStaticContext();
  }

  @Override
  public void setFlags(Flags flags) {
    ((PandaRawClass) this.baseType()).setFlags(flags);
  }

  @Override
  public void setContainer(ReferenceType container) {
    ((PandaRawClass) this.baseType()).setContainer(container);
  }

  @Override
  public AnnotationTypeElemInstance annotationElemNamed(String name) {
    return ((PandaRawClass) this.baseType()).annotationElemNamed(name);
  }

  @Override
  public List<AnnotationTypeElemInstance> annotationElems() {
    return ((PandaRawClass) this.baseType()).annotationElems();
  }

  @Override
  public Annotations annotations() {
    return ((PandaRawClass) this.baseType()).annotations();
  }

}
