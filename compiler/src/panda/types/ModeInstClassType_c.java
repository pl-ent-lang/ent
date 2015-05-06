package panda.types;

import polyglot.frontend.Job;
import polyglot.types.ClassType_c;
import polyglot.types.ClassType;
import polyglot.types.FieldInstance;
import polyglot.types.Flags;
import polyglot.types.MethodInstance;
import polyglot.types.ConstructorInstance;
import polyglot.types.Package;
import polyglot.types.ReferenceType;
import polyglot.types.ReferenceType_c;
import polyglot.types.Resolver;
import polyglot.types.Type;
import polyglot.types.TypeObject;
import polyglot.util.CodeWriter;
import polyglot.util.Copy;
import polyglot.util.Position;
import polyglot.util.InternalCompilerError;

import polyglot.ext.param.types.SubstClassType_c;

import polyglot.ext.jl5.types.Annotations;
import polyglot.ext.jl5.types.AnnotationTypeElemInstance;
import polyglot.ext.jl5.types.EnumInstance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ModeInstClassType_c extends ModeSubstClassType_c implements ModeInstClassType {

  private ModeSubstParsedClassType base;
  private ModeInst inst;

  protected transient List<? extends ReferenceType> interfaces = null;
  protected transient List<? extends FieldInstance> fields = null;
  protected transient List<? extends MethodInstance> methods = null;
  protected transient List<? extends ConstructorInstance> constructors= null;
  protected transient List<? extends ClassType> memberClasses = null;

  // TODO : Right now a ModeInstClassType sees through to it's base type
  public ModeInstClassType_c(ModeSubstParsedClassType base, 
                             ModeInst inst) {
    super(base, base.modeType());
    this.base = base;
    this.inst = inst;
  }

  // Property Methods
  public ModeSubstParsedClassType base() {
    return this.base;
  }

  public void base(ModeSubstParsedClassType base) {
    this.base = base;
  }

  public ModeInst inst() {
    return this.inst;
  }

  public void inst(ModeInst inst) {
    this.inst = inst;
  }

  @Override
  public PandaType deepCopy() {
    return new ModeInstClassType_c(this.base(), this.inst());
  }

  @Override
  public boolean typeEqualsImpl(Type t) {
    if (!super.typeEqualsImpl(t)) {
      return false;
    }
               
    ModeInstClassType ic = (ModeInstClassType) t;
    return this.inst().equals(ic.inst());
  }

  @Override
  public void print(CodeWriter w) {
    super.print(w);
  }

  @Override
  public String name() {
    PandaType p = (PandaType) this.base();
    PandaParsedClassType pc = (PandaParsedClassType) p;
    String n = p.baseType() + "@mode<" + p.modeType();
    for (ModeTypeVariable m : pc.modeTypeVariables()) {
      n += "," + this.inst.modeTypeMap().get(m);
    }
    n += ">";
    return n;
  }

  @Override
  public String toString() {
    return this.name();
  }

  @Override
  public boolean isImplicitCastValidImpl(Type toType) throws InternalCompilerError {
    // TODO : Send this to the base type stuff for now
    // NOTE : JL5 errors if this method is called, why?
    throw new InternalCompilerError("Should not be called in JL5");
    //return this.ts.isImplicitCastValid(this.base(), toType);
  }

  @Override
  public boolean descendsFromImpl(Type ancestor) {
    // TODO : Send this to the base type stuff for now
    return this.ts.descendsFrom(this.base(), ancestor);
  }

  @Override
  public boolean inStaticContext() {
    return this.base().inStaticContext();
  }

  @Override 
  public String translate(Resolver c) {
    return this.base().translate(c);
  }

  @Override
  public ClassType.Kind kind() {
    return this.base().kind();
  }

  @Override
  public void setFlags(Flags flags) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setContainer(ReferenceType container) {
    throw new UnsupportedOperationException();
  }

  /*
  @Override
  public Job job() {
      return null;
  }
  */

  @Override
  public ClassType outer() {
    // TODO : Must implement for type soundless
    return ((ClassType) this.inst().instType(this.base().outer()));
  }

  /*
  @Override
  public boolean isEnclosedImpl(ClassType maybeOuter) {
    // TODO : Must implement for type soundless
  }
  */
  
  // ClassType_c methods that need to "intercept" and perform a substition
  // on the base type.
  @Override 
  public Type superType() {
    return this.inst().instType(this.base().superType());
  }

  @Override
  public Package package_() {
      return base.package_();
  }

  @Override
  public Flags flags() {
    return base.flags();
  }

  protected <T extends TypeObject> List<T> deepCopy(List<T> src) {
    List<T> dst = new ArrayList<>(src.size());
    for (T t : src) {
      dst.add(Copy.Util.copy(t));
    }
    return dst;
  }

  @Override
  public List<? extends ReferenceType> interfaces() {
    if (this.interfaces == null) {
      this.interfaces = this.deepCopy(this.base().interfaces());
      this.interfaces = this.inst().instTypeList(this.interfaces);
    }
    return this.interfaces;
  }

  @Override
  public List<? extends FieldInstance> fields() {
    if (this.fields == null) {
      this.fields = this.deepCopy(this.base().fields());

      // NOTE: Just testing out some inst prototyping
      for (FieldInstance fi : this.fields) {
        fi.setContainer(this);
      }

      this.fields = this.inst().instFieldList(this.fields);
    }
    return this.fields;
  }

  @Override
  public List<? extends MethodInstance> methods() {
    if (this.methods == null) {
      this.methods = this.deepCopy(this.base().methods());

      // NOTE: Just testing out some inst prototyping
      for (MethodInstance mi : this.methods) {
        mi.setContainer(this);
      } 

      this.methods = this.inst().instMethodList(this.methods);
    }
    return this.methods;
  }


  @Override
  public List<? extends ConstructorInstance> constructors() {
    if (this.fields == null) {
      this.constructors = this.deepCopy(this.base().constructors());
      this.constructors = this.inst().instConstructorList(this.constructors);
    }
    return this.constructors;
  }

  @Override
  public List<? extends ClassType> memberClasses() {
    if (this.memberClasses == null) {
      this.memberClasses = this.deepCopy(this.base().memberClasses());
      this.memberClasses = this.inst().instTypeList(this.memberClasses);
    }
    return this.memberClasses;
  }

  // JL5Class Methods 
  @Override
  public Set<? extends Type> superclasses() {
    return this.base().superclasses();
  }

  @Override
  public List<EnumInstance> enumConstants() {
    return this.base().enumConstants();
  }

  @Override
  public EnumInstance enumConstantNamed(String name) {
    return this.base().enumConstantNamed(name);
  }

  @Override
  public AnnotationTypeElemInstance annotationElemNamed(String name) {
    return this.base().annotationElemNamed(name);
  }

  @Override
  public List<AnnotationTypeElemInstance> annotationElems() {
    return this.base().annotationElems();
  }

  @Override
  public boolean isRawClass() {
    return this.base().isRawClass();
  }

  @Override
  public String translateAsReceiver(Resolver c) {
    return this.base().translateAsReceiver(c);
  }

  @Override
  public Annotations annotations() {
    return this.base().annotations();
  }


}
