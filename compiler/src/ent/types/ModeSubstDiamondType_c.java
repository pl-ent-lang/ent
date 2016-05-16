package ent.types;

import polyglot.types.*;
import polyglot.types.Package;
import polyglot.ext.jl5.types.*;
import polyglot.ext.jl7.types.*;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;

public class ModeSubstDiamondType_c extends ModeSubstClassType_c implements ModeSubstDiamondType {

  public ModeSubstDiamondType_c(DiamondType baseType, List<Type> modeTypeArgs) {
    super(baseType, modeTypeArgs);
  }

  @Override
  public ModeSubstType deepCopy() {
    return new ModeSubstDiamondType_c((DiamondType)this.baseType(),
                                      new ArrayList<Type>(this.modeTypeArgs()));
  }

  // DiamondType Methods
  @Override
  public JL5ParsedClassType base() {
    return ((DiamondType)this.baseType()).base();
  }

  @Override
  public JL5SubstClassType inferred() {
    return ((DiamondType)this.baseType()).inferred();
  }

  @Override
  public void inferred(JL5SubstClassType inferred) {
    ((DiamondType)this.baseType()).inferred(inferred);
  }

  // ClassType Methods
  @Override
  public Package package_() {
    return ((DiamondType)this.baseType()).package_();
  }

  @Override
  public Flags flags() {
    return ((DiamondType)this.baseType()).flags();
  }

  @Override
  public List<? extends ConstructorInstance> constructors() {
    return ((DiamondType)this.baseType()).constructors();
  }

  @Override
  public List<? extends ClassType> memberClasses() {
    return ((DiamondType)this.baseType()).memberClasses();
  }

  @Override
  public List<? extends MethodInstance> methods() {
    return ((DiamondType)this.baseType()).methods();
  }

  @Override
  public List<? extends FieldInstance> fields() {
    return ((DiamondType)this.baseType()).fields();
  }

  @Override
  public List<? extends ReferenceType> interfaces() {
    return ((DiamondType)this.baseType()).interfaces();
  }

  @Override
  public Type superType() {
    return ((DiamondType)this.baseType()).superType();
  }

  // JL5ClassType Methods
  @Override
  public Set<? extends Type> superclasses() {
    return ((DiamondType)this.baseType()).superclasses();
  }

  @Override
  public Annotations annotations() {
    return ((DiamondType)this.baseType()).annotations();
  }

  @Override
  public String translateAsReceiver(Resolver c) {
    return ((DiamondType)this.baseType()).translateAsReceiver(c);
  }

  @Override
  public AnnotationTypeElemInstance annotationElemNamed(String name) {
    return ((DiamondType)this.baseType()).annotationElemNamed(name);
  }

  @Override
  public List<AnnotationTypeElemInstance> annotationElems() {
    return ((DiamondType)this.baseType()).annotationElems();
  }

  @Override
  public List<EnumInstance> enumConstants() {
    return ((DiamondType)this.baseType()).enumConstants();
  }

  @Override
  public boolean isRawClass() {
    return ((DiamondType)this.baseType()).isRawClass();
  }

  @Override
  public ClassType outer() {
    return ((DiamondType)this.baseType()).outer();
  }

  @Override
  public boolean inStaticContext() {
    return ((DiamondType)this.baseType()).inStaticContext();
  }

  @Override
  public Kind kind() {
    return ((DiamondType)this.baseType()).kind();
  }

  @Override
  public void setContainer(ReferenceType container) {
    ((DiamondType)this.baseType()).setContainer(container);
  }

  @Override
  public String toString() {
    String name = ((DiamondType)this.baseType()).toString() + "@mode<";
    for (int i = 0; i < this.modeTypeArgs().size(); ++i) {
      name += this.modeTypeArgs().get(i);
      if (i + 1 < this.modeTypeArgs().size()) {
        name += ",";
      }
    }
    name += ">";
    return name;
  }

  @Override
  public void setFlags(Flags flags) {
    ((DiamondType)this.baseType()).setFlags(flags);
  }

  // Type Methods
  @Override
  public LinkedList<Type> isImplicitCastValidChainImpl(Type toT) {
    EntTypeSystem ts = (EntTypeSystem)this.ts;
    // MODE-NOTE: This breaks out of our mode subst types
    return ts.isImplicitCastValidChain(this.inferred(), toT);
  }
}
