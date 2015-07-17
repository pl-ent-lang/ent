package panda.types;

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

public class ModeSubstParsedClassType_c extends ModeSubstClassType_c implements ModeSubstParsedClassType {

  public ModeSubstParsedClassType_c(PandaParsedClassType baseType, List<Type> modeTypeArgs) {
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
    return 
      new ModeSubstParsedClassType_c((PandaParsedClassType) this.baseType(),
                                     new ArrayList<Type>(this.modeTypeArgs()));
  }

  // PandaParsedClassType Methods
  public List<ModeTypeVariable> modeTypeVars() {
    return ((PandaParsedClassType) this.baseType()).modeTypeVars();
  }

  public void modeTypeVars(List<ModeTypeVariable> modeTypeVars) {
    ((PandaParsedClassType) this.baseType()).modeTypeVars(modeTypeVars);
  }

  public boolean hasAttribute() {
    return ((PandaParsedClassType) this.baseType()).hasAttribute();
  }

  public AttributeInstance attributeInstance() {
    return ((PandaParsedClassType) this.baseType()).attributeInstance();
  }

  public void attributeInstance(AttributeInstance attributeInstance) {
    ((PandaParsedClassType) this.baseType()).attributeInstance(attributeInstance);
  } 


  public boolean hasCopy() {
    return ((PandaParsedClassType) this.baseType()).hasCopy();
  }

  // JL5ParsedClassType Methods
  @Override
  public void addEnumConstant(EnumInstance ei) {
    ((PandaParsedClassType) this.baseType()).addEnumConstant(ei);
  }

  @Override
  public List<EnumInstance> enumConstants() {
    return ((PandaParsedClassType) this.baseType()).enumConstants();
  }

  @Override
  public EnumInstance enumConstantNamed(String name) {
    return ((PandaParsedClassType) this.baseType()).enumConstantNamed(name);
  }

  @Override
  public AnnotationTypeElemInstance annotationElemNamed(String name) {
    return ((PandaParsedClassType) this.baseType()).annotationElemNamed(name);
  }

  @Override
  public void addAnnotationElem(AnnotationTypeElemInstance ai) {
    ((PandaParsedClassType) this.baseType()).addAnnotationElem(ai);
  }

  @Override
  public List<AnnotationTypeElemInstance> annotationElems() {
    return ((PandaParsedClassType) this.baseType()).annotationElems();
  }

  @Override
  public List<? extends JL5MethodInstance> methods(JL5MethodInstance mi) {
    return ((PandaParsedClassType) this.baseType()).methods(mi);
  }

  @Override
  public PClass<TypeVariable, ReferenceType> pclass() {
    return ((PandaParsedClassType) this.baseType()).pclass();
  }

  @Override
  public void setPClass(PClass<TypeVariable, ReferenceType> pc) {
    ((PandaParsedClassType) this.baseType()).setPClass(pc);
  }

  @Override
  public void setTypeVariables(List<TypeVariable> typeVars) {
    ((PandaParsedClassType) this.baseType()).setTypeVariables(typeVars);
  }

  @Override
  public List<TypeVariable> typeVariables() {
    return ((PandaParsedClassType) this.baseType()).typeVariables();
  }

  @Override
  public JL5Subst erasureSubst() {
    return ((PandaParsedClassType) this.baseType()).erasureSubst();
  }

  @Override
  public void printNoParams(CodeWriter w) {
    ((PandaParsedClassType) this.baseType()).printNoParams(w);
  }

  @Override
  public String toStringNoParams() {
    return ((PandaParsedClassType) this.baseType()).toStringNoParams();
  }

  @Override
  public boolean isRawClass() {
    return ((PandaParsedClassType) this.baseType()).isRawClass();
  }

  @Override
  public String translateAsReceiver(Resolver c) {
    return ((PandaParsedClassType) this.baseType()).translateAsReceiver(c);
  }

  @Override
  public Annotations annotations() {
    return ((PandaParsedClassType) this.baseType()).annotations();
  }

  @Override
  public void setAnnotations(Annotations annotations) {
    ((PandaParsedClassType) this.baseType()).setAnnotations(annotations);
  }

  @Override
  public boolean enumValueOfMethodNeeded() {
    return ((PandaParsedClassType) this.baseType()).enumValueOfMethodNeeded();
  }

  @Override
  public boolean enumValuesMethodNeeded() {
    return ((PandaParsedClassType) this.baseType()).enumValuesMethodNeeded();
  }

  @Override
  public boolean annotationsResolved() {
    return ((PandaParsedClassType) this.baseType()).annotationsResolved();
  }

  @Override
  public void setAnnotationsResolved(boolean annotationsResolved) {
    ((PandaParsedClassType) this.baseType())
      .setAnnotationsResolved(annotationsResolved);
  }

  @Override
  public Set<? extends Type> superclasses() {
    return ((PandaParsedClassType) this.baseType()).superclasses();
  }

  // ParsedClassType Methods
  @Override
  public LazyInitializer initializer() {
    return ((PandaParsedClassType) this.baseType()).initializer();
  }

  @Override
  public void setInitializer(LazyInitializer init) {
    ((PandaParsedClassType) this.baseType()).setInitializer(init);
  }

  @Override
  public Source fromSource() {
    return ((PandaParsedClassType) this.baseType()).fromSource();
  }

  @Override
  public Job job() {
    return ((PandaParsedClassType) this.baseType()).job();
  }

  @Override
  public void setJob(Job job) {
    ((PandaParsedClassType) this.baseType()).setJob(job);
  }

  @Override
  public Kind kind() {
    return ((PandaParsedClassType) this.baseType()).kind();
  }

  @Override
  public void inStaticContext(boolean inStaticContext) {
    ((PandaParsedClassType) this.baseType()).inStaticContext();
  }

  @Override
  public boolean inStaticContext() {
    return ((PandaParsedClassType) this.baseType()).inStaticContext();
  }

  @Override
  public ClassType outer() {
    return ((PandaParsedClassType) this.baseType()).outer();
  }

  @Override
  public String name() {
    String name = ((PandaParsedClassType) this.baseType()).name() + "@mode<";
    for (int i = 0; i < this.modeTypeArgs().size(); ++i) {
      name += this.modeTypeArgs().get(i);
      if (i+1 < this.modeTypeArgs().size()) {
        name += ",";
      }
    }
    name += ">";
    return name;
  }

  @Override
  public String toString() {
    String name = ((PandaParsedClassType) this.baseType()).toString() + "@mode<";
    for (int i = 0; i < this.modeTypeArgs().size(); ++i) {
      name += this.modeTypeArgs().get(i);
      if (i+1 < this.modeTypeArgs().size()) {
        name += ",";
      }
    }
    name += ">";
    return name;
  }

  @Override
  public Type superType() {
    return ((PandaParsedClassType) this.baseType()).superType();
  }

  @Override
  public Package package_() {
    return ((PandaParsedClassType) this.baseType()).package_();
  }

  @Override
  public Flags flags() {
    return ((PandaParsedClassType) this.baseType()).flags();
  }

  @Override
  public void setFlags(Flags flags) {
    ((PandaParsedClassType) this.baseType()).setFlags(flags); 
  }

  @Override
  public void flags(Flags flags) {
    ((PandaParsedClassType) this.baseType()).flags(flags);
  }

  @Override
  public void kind(Kind kind) {
    ((PandaParsedClassType) this.baseType()).kind(kind);
  }

  @Override
  public void outer(ClassType outer) {
    ((PandaParsedClassType) this.baseType()).outer(outer);
  }

  @Override
  public void setContainer(ReferenceType container) {
    ((PandaParsedClassType) this.baseType()).setContainer(container);
  }

  @Override
  public void name(String name) {
    ((PandaParsedClassType) this.baseType()).name(name);
  }

  @Override
  public void position(Position pos) {
    ((PandaParsedClassType) this.baseType()).position(pos);
  }

  @Override
  public void package_(Package p) {
    ((PandaParsedClassType) this.baseType()).package_(p);
  }

  @Override
  public void superType(Type t) {
    ((PandaParsedClassType) this.baseType()).superType(t);
  }

  @Override
  public void addInterface(ReferenceType t) {
    ((PandaParsedClassType) this.baseType()).addInterface(t);
  }

  @Override
  public void addMethod(MethodInstance mi) {
    ((PandaParsedClassType) this.baseType()).addMethod(mi);
  }

  @Override
  public void addConstructor(ConstructorInstance ci) {
    ((PandaParsedClassType) this.baseType()).addConstructor(ci);
  }

  @Override
  public void addField(FieldInstance fi) {
    ((PandaParsedClassType) this.baseType()).addField(fi);
  }

  @Override
  public void addMemberClass(ClassType t) {
    ((PandaParsedClassType) this.baseType()).addMemberClass(t);
  }

  @Override
  public void setInterfaces(List<? extends ReferenceType> l) {
    ((PandaParsedClassType) this.baseType()).setInterfaces(l);
  }

  @Override
  public void setMethods(List<? extends MethodInstance> l) {
    ((PandaParsedClassType) this.baseType()).setMethods(l);
  }

  @Override
  public void setFields(List<? extends FieldInstance> l) {
    ((PandaParsedClassType) this.baseType()).setFields(l);
  }

  @Override
  public void setConstructors(List<? extends ConstructorInstance> l) {
    ((PandaParsedClassType) this.baseType()).setConstructors(l);
  }

  @Override
  public void setMemberClasses(List<? extends ClassType> l) {
    ((PandaParsedClassType) this.baseType()).setMemberClasses(l);
  }

  @Override
  public boolean defaultConstructorNeeded() {
    return ((PandaParsedClassType) this.baseType()).defaultConstructorNeeded();
  }

  @Override
  public List<? extends ConstructorInstance> constructors() {
    if (this.constructors == null) {
      this.constructors = this.modeSubst().substConstructorList(((PandaParsedClassType) this.baseType()).constructors());
    }
    return this.constructors;
  }

  @Override
  public List<? extends ClassType> memberClasses() {
    return ((PandaParsedClassType) this.baseType()).memberClasses();
  }

  @Override
  public List<? extends MethodInstance> methods() {
    if (this.methods == null) {
      this.methods = this.modeSubst().substMethodList(((PandaParsedClassType) this.baseType()).methods());
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
      this.fields = this.modeSubst().substFieldList(((PandaParsedClassType) this.baseType()).fields());
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
      this.interfaces = this.modeSubst().substTypeList(((PandaParsedClassType) this.baseType()).interfaces());
    }
    return this.interfaces;
  }

  @Override
  public boolean membersAdded() {
    return ((PandaParsedClassType) this.baseType()).membersAdded();
  }

  @Override
  public void setMembersAdded(boolean membersAdded) {
    ((PandaParsedClassType) this.baseType()).setMembersAdded(membersAdded);
  }

  @Override
  public void setSignaturesResolved(boolean signaturesDisambiguated) {
    ((PandaParsedClassType) this.baseType())
      .setSignaturesResolved(signaturesDisambiguated);
  }

  @Override
  public boolean supertypesResolved() {
    return ((PandaParsedClassType) this.baseType()).supertypesResolved();
  }

  @Override
  public void setSupertypesResolved(boolean supertypesResolved) {
    ((PandaParsedClassType) this.baseType())
      .setSupertypesResolved(supertypesResolved);
  }

  @Override
  public int numSignaturesUnresolved() {
    return ((PandaParsedClassType) this.baseType()).numSignaturesUnresolved();
  }

  @Override
  public boolean signaturesResolved() {
    return ((PandaParsedClassType) this.baseType()).signaturesResolved();
  }

  @Override
  public void needSerialization(boolean b) {
    ((PandaParsedClassType) this.baseType()).needSerialization(b);
  }

  @Override
  public boolean needSerialization() {
    return ((PandaParsedClassType) this.baseType()).needSerialization();
  }

}


