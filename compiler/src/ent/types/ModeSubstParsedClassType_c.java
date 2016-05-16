package ent.types;

import polyglot.frontend.Job;
import polyglot.frontend.Source;
import polyglot.types.ClassType;
import polyglot.types.ConstructorInstance;
import polyglot.types.FieldInstance;
import polyglot.types.Flags;
import polyglot.types.LazyClassInitializer;
import polyglot.types.LazyInitializer;
import polyglot.types.MethodInstance;
import polyglot.types.Package;
import polyglot.types.ReferenceType;
import polyglot.types.Resolver;
import polyglot.types.Type;
import polyglot.types.Type_c;
import polyglot.util.CodeWriter;
import polyglot.util.Position;

import polyglot.ext.jl5.types.Annotations;
import polyglot.ext.jl5.types.AnnotationTypeElemInstance;
import polyglot.ext.jl5.types.EnumInstance;
import polyglot.ext.jl5.types.JL5MethodInstance;
import polyglot.ext.jl5.types.JL5Subst;
import polyglot.ext.jl5.types.TypeVariable;
import polyglot.ext.param.types.PClass;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class ModeSubstParsedClassType_c
    extends ModeSubstClassType_c implements ModeSubstParsedClassType {

  public ModeSubstParsedClassType_c(EntParsedClassType baseType, List<Type> modeTypeArgs) {
    super(baseType, modeTypeArgs);
  }

  // Subst caches
  protected transient List<? extends ReferenceType> interfaces = null;
  protected transient List<? extends FieldInstance> fields = null;
  protected transient List<? extends MethodInstance> methods = null;
  protected transient List<? extends ConstructorInstance> constructors = null;
  protected transient List<? extends ClassType> memberClasses = null;

  @Override
  public ModeSubstType deepCopy() {
    return new ModeSubstParsedClassType_c((EntParsedClassType)this.baseType(),
                                          new ArrayList<Type>(this.modeTypeArgs()));
  }

  // EntParsedClassType Methods
  public List<ModeTypeVariable> modeTypeVars() {
    return ((EntParsedClassType)this.baseType()).modeTypeVars();
  }

  public void modeTypeVars(List<ModeTypeVariable> modeTypeVars) {
    ((EntParsedClassType)this.baseType()).modeTypeVars(modeTypeVars);
  }

  public boolean hasAttribute() { return ((EntParsedClassType)this.baseType()).hasAttribute(); }

  public AttributeInstance attributeInstance() {
    return ((EntParsedClassType)this.baseType()).attributeInstance();
  }

  public void attributeInstance(AttributeInstance attributeInstance) {
    ((EntParsedClassType)this.baseType()).attributeInstance(attributeInstance);
  }

  public boolean hasCopy() { return ((EntParsedClassType)this.baseType()).hasCopy(); }

  // JL5ParsedClassType Methods
  @Override
  public void addEnumConstant(EnumInstance ei) {
    ((EntParsedClassType)this.baseType()).addEnumConstant(ei);
  }

  @Override
  public List<EnumInstance> enumConstants() {
    return ((EntParsedClassType)this.baseType()).enumConstants();
  }

  @Override
  public EnumInstance enumConstantNamed(String name) {
    return ((EntParsedClassType)this.baseType()).enumConstantNamed(name);
  }

  @Override
  public AnnotationTypeElemInstance annotationElemNamed(String name) {
    return ((EntParsedClassType)this.baseType()).annotationElemNamed(name);
  }

  @Override
  public void addAnnotationElem(AnnotationTypeElemInstance ai) {
    ((EntParsedClassType)this.baseType()).addAnnotationElem(ai);
  }

  @Override
  public List<AnnotationTypeElemInstance> annotationElems() {
    return ((EntParsedClassType)this.baseType()).annotationElems();
  }

  @Override
  public List<? extends JL5MethodInstance> methods(JL5MethodInstance mi) {
    return ((EntParsedClassType)this.baseType()).methods(mi);
  }

  @Override
  public PClass<TypeVariable, ReferenceType> pclass() {
    return ((EntParsedClassType)this.baseType()).pclass();
  }

  @Override
  public void setPClass(PClass<TypeVariable, ReferenceType> pc) {
    ((EntParsedClassType)this.baseType()).setPClass(pc);
  }

  @Override
  public void setTypeVariables(List<TypeVariable> typeVars) {
    ((EntParsedClassType)this.baseType()).setTypeVariables(typeVars);
  }

  @Override
  public List<TypeVariable> typeVariables() {
    return ((EntParsedClassType)this.baseType()).typeVariables();
  }

  @Override
  public JL5Subst erasureSubst() {
    return ((EntParsedClassType)this.baseType()).erasureSubst();
  }

  @Override
  public void printNoParams(CodeWriter w) {
    ((EntParsedClassType)this.baseType()).printNoParams(w);
  }

  @Override
  public String toStringNoParams() {
    return ((EntParsedClassType)this.baseType()).toStringNoParams();
  }

  @Override
  public boolean isRawClass() {
    return ((EntParsedClassType)this.baseType()).isRawClass();
  }

  @Override
  public String translateAsReceiver(Resolver c) {
    return ((EntParsedClassType)this.baseType()).translateAsReceiver(c);
  }

  @Override
  public Annotations annotations() {
    return ((EntParsedClassType)this.baseType()).annotations();
  }

  @Override
  public void setAnnotations(Annotations annotations) {
    ((EntParsedClassType)this.baseType()).setAnnotations(annotations);
  }

  @Override
  public boolean enumValueOfMethodNeeded() {
    return ((EntParsedClassType)this.baseType()).enumValueOfMethodNeeded();
  }

  @Override
  public boolean enumValuesMethodNeeded() {
    return ((EntParsedClassType)this.baseType()).enumValuesMethodNeeded();
  }

  @Override
  public boolean annotationsResolved() {
    return ((EntParsedClassType)this.baseType()).annotationsResolved();
  }

  @Override
  public void setAnnotationsResolved(boolean annotationsResolved) {
    ((EntParsedClassType)this.baseType()).setAnnotationsResolved(annotationsResolved);
  }

  @Override
  public Set<? extends Type> superclasses() {
    return ((EntParsedClassType)this.baseType()).superclasses();
  }

  // ParsedClassType Methods
  @Override
  public LazyInitializer initializer() {
    return ((EntParsedClassType)this.baseType()).initializer();
  }

  @Override
  public void setInitializer(LazyInitializer init) {
    ((EntParsedClassType)this.baseType()).setInitializer(init);
  }

  @Override
  public Source fromSource() {
    return ((EntParsedClassType)this.baseType()).fromSource();
  }

  @Override
  public Job job() {
    return ((EntParsedClassType)this.baseType()).job();
  }

  @Override
  public void setJob(Job job) {
    ((EntParsedClassType)this.baseType()).setJob(job);
  }

  @Override
  public Kind kind() {
    return ((EntParsedClassType)this.baseType()).kind();
  }

  @Override
  public void inStaticContext(boolean inStaticContext) {
    ((EntParsedClassType)this.baseType()).inStaticContext();
  }

  @Override
  public boolean inStaticContext() {
    return ((EntParsedClassType)this.baseType()).inStaticContext();
  }

  @Override
  public ClassType outer() {
    return ((EntParsedClassType)this.baseType()).outer();
  }

  @Override
  public String toString() {
    String name = ((EntParsedClassType)this.baseType()).toString() + "@mode<";
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
  public Type superType() {
    return ((EntParsedClassType)this.baseType()).superType();
  }

  @Override
  public Package package_() {
    return ((EntParsedClassType)this.baseType()).package_();
  }

  @Override
  public Flags flags() {
    return ((EntParsedClassType)this.baseType()).flags();
  }

  @Override
  public void setFlags(Flags flags) {
    ((EntParsedClassType)this.baseType()).setFlags(flags);
  }

  @Override
  public void flags(Flags flags) {
    ((EntParsedClassType)this.baseType()).flags(flags);
  }

  @Override
  public void kind(Kind kind) {
    ((EntParsedClassType)this.baseType()).kind(kind);
  }

  @Override
  public void outer(ClassType outer) {
    ((EntParsedClassType)this.baseType()).outer(outer);
  }

  @Override
  public void setContainer(ReferenceType container) {
    ((EntParsedClassType)this.baseType()).setContainer(container);
  }

  @Override
  public void name(String name) {
    ((EntParsedClassType)this.baseType()).name(name);
  }

  @Override
  public void position(Position pos) {
    ((EntParsedClassType)this.baseType()).position(pos);
  }

  @Override
  public void package_(Package p) {
    ((EntParsedClassType)this.baseType()).package_(p);
  }

  @Override
  public void superType(Type t) {
    ((EntParsedClassType)this.baseType()).superType(t);
  }

  @Override
  public void addInterface(ReferenceType t) {
    ((EntParsedClassType)this.baseType()).addInterface(t);
  }

  @Override
  public void addMethod(MethodInstance mi) {
    ((EntParsedClassType)this.baseType()).addMethod(mi);
  }

  @Override
  public void addConstructor(ConstructorInstance ci) {
    ((EntParsedClassType)this.baseType()).addConstructor(ci);
  }

  @Override
  public void addField(FieldInstance fi) {
    ((EntParsedClassType)this.baseType()).addField(fi);
  }

  @Override
  public void addMemberClass(ClassType t) {
    ((EntParsedClassType)this.baseType()).addMemberClass(t);
  }

  @Override
  public void setInterfaces(List<? extends ReferenceType> l) {
    ((EntParsedClassType)this.baseType()).setInterfaces(l);
  }

  @Override
  public void setMethods(List<? extends MethodInstance> l) {
    ((EntParsedClassType)this.baseType()).setMethods(l);
  }

  @Override
  public void setFields(List<? extends FieldInstance> l) {
    ((EntParsedClassType)this.baseType()).setFields(l);
  }

  @Override
  public void setConstructors(List<? extends ConstructorInstance> l) {
    ((EntParsedClassType)this.baseType()).setConstructors(l);
  }

  @Override
  public void setMemberClasses(List<? extends ClassType> l) {
    ((EntParsedClassType)this.baseType()).setMemberClasses(l);
  }

  @Override
  public boolean defaultConstructorNeeded() {
    return ((EntParsedClassType)this.baseType()).defaultConstructorNeeded();
  }

  @Override
  public List<? extends ConstructorInstance> constructors() {
    if (this.constructors == null) {
      this.constructors = this.modeSubst().substConstructorList(
          ((EntParsedClassType)this.baseType()).constructors());
    }
    return this.constructors;
  }

  @Override
  public List<? extends ClassType> memberClasses() {
    return ((EntParsedClassType)this.baseType()).memberClasses();
  }

  @Override
  public List<? extends MethodInstance> methods() {
    if (this.methods == null) {
      this.methods =
          this.modeSubst().substMethodList(((EntParsedClassType)this.baseType()).methods());
    }
    return this.methods;
  }

  @Override
  public List<? extends MethodInstance> methodsNamed(String name) {
    List<MethodInstance> methods = new LinkedList<>();
    for (MethodInstance mi : this.methods()) {
      if (mi.name().equals(name)) {
        methods.add(mi);
      }
    }
    return methods;
  }

  @Override
  public List<? extends FieldInstance> fields() {
    if (this.fields == null) {
      this.fields = this.modeSubst().substFieldList(((EntParsedClassType)this.baseType()).fields());
    }
    return this.fields;
  }

  @Override
  public FieldInstance fieldNamed(String name) {
    for (FieldInstance fi : this.fields()) {
      if (fi.name().equals(name)) {
        return fi;
      }
    }
    return null;
  }

  @Override
  public List<? extends ReferenceType> interfaces() {
    if (this.interfaces == null) {
      this.interfaces =
          this.modeSubst().substTypeList(((EntParsedClassType)this.baseType()).interfaces());
    }
    return this.interfaces;
  }

  @Override
  public boolean membersAdded() {
    return ((EntParsedClassType)this.baseType()).membersAdded();
  }

  @Override
  public void setMembersAdded(boolean membersAdded) {
    ((EntParsedClassType)this.baseType()).setMembersAdded(membersAdded);
  }

  @Override
  public void setSignaturesResolved(boolean signaturesDisambiguated) {
    ((EntParsedClassType)this.baseType()).setSignaturesResolved(signaturesDisambiguated);
  }

  @Override
  public boolean supertypesResolved() {
    return ((EntParsedClassType)this.baseType()).supertypesResolved();
  }

  @Override
  public void setSupertypesResolved(boolean supertypesResolved) {
    ((EntParsedClassType)this.baseType()).setSupertypesResolved(supertypesResolved);
  }

  @Override
  public int numSignaturesUnresolved() {
    return ((EntParsedClassType)this.baseType()).numSignaturesUnresolved();
  }

  @Override
  public boolean signaturesResolved() {
    return ((EntParsedClassType)this.baseType()).signaturesResolved();
  }

  @Override
  public void needSerialization(boolean b) {
    ((EntParsedClassType)this.baseType()).needSerialization(b);
  }

  @Override
  public boolean needSerialization() {
    return ((EntParsedClassType)this.baseType()).needSerialization();
  }

  /*
  @Override
  public LinkedList<Type> isImplicitCastValidChainImpl(Type toT) {
    EntTypeSystem ts = (EntTypeSystem) this.ts;
    // MODE-NOTE: FIXME Forwarding may not be the right solution here
    // May skirt around a lot of potential bugs
    if (!(toT instanceof ModeSubstType)) {
      return ts.isImplicitCastValidChain(this.baseType(), toT);
    }

    LinkedList<Type> chain = null;
    if (ts.isSubtype(this, toT)) {
      chain = new LinkedList<>();
      chain.add(this);
      chain.add(toT);
    }
    else if (toT.isPrimitive()) {
      // see if unboxing will let us cast to the primitive
      if (ts.primitiveTypeOfWrapper(this) != null) {
        chain =
          ts.isImplicitCastValidChain(
            ts.primitiveTypeOfWrapper(this),
            toT
            );
        if (chain != null) {
          chain.addFirst(this);
        }
      }
    }
    return chain;
  }
  */
}
