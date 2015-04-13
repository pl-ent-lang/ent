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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;

public class PandaModeSubstClassType_c extends ClassType_c implements PandaModeSubstClassType {

  private PandaParsedClassType base;
  private PandaModeSubst subst;

  protected transient List<? extends ReferenceType> interfaces = null;
  protected transient List<? extends FieldInstance> fields = null;
  protected transient List<? extends MethodInstance> methods = null;
  protected transient List<? extends ConstructorInstance> constructors= null;
  protected transient List<? extends ClassType> memberClasses = null;

  public PandaModeSubstClassType_c(PandaTypeSystem ts, 
                                   Position pos, 
                                   PandaParsedClassType base, 
                                   PandaModeSubst subst) {
    super(ts, pos);
    this.base = base;
    this.subst = subst;
  }

  // Property Methods
  public PandaParsedClassType base() {
    return this.base;
  }

  public void base(PandaParsedClassType base) {
    this.base = base;
  }

  public PandaModeSubst subst() {
    return this.subst;
  }

  public void subst(PandaModeSubst subst) {
    this.subst = subst;
  }

  @Override
  public void print(CodeWriter w) {
    super.print(w);
  }

  /*
  @Override
  public boolean isCastValidImpl(Type toType) {
    // TODO : Must implement for type soundless
  }
  */

  @Override
  public String name() {
    return this.base().name();
  }

  @Override
  public boolean isImplicitCastValidImpl(Type toType) throws InternalCompilerError {
    throw new InternalCompilerError("Should not be called in JL5");
  }

  @Override
  public boolean descendsFromImpl(Type ancestor) {
    if (super.descendsFromImpl(ancestor)) {
      return true;
    }

    // TODO : Must implement with regard to mode substitions
    return false;
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

  @Override
  public Job job() {
      return null;
  }

  @Override
  public ClassType outer() {
    // TODO : Must implement for type soundless
    return ((ClassType) this.subst().substType(this.base().outer()));
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
    return this.subst().substType(this.base().superType());
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
      this.interfaces = deepCopy(this.base().interfaces());
      this.interfaces = this.subst().substTypeList(this.interfaces);
    }
    return this.interfaces;
  }

  @Override
  public List<? extends FieldInstance> fields() {
    if (this.fields == null) {
      this.fields = deepCopy(this.base().fields());
      this.fields = this.subst().substFieldList(this.fields);
    }
    return this.fields;
  }

  @Override
  public List<? extends MethodInstance> methods() {
    if (this.methods == null) {
      this.methods = deepCopy(this.base().methods());
      this.methods = this.subst().substMethodList(this.methods);
    }
    return this.methods;
  }


  @Override
  public List<? extends ConstructorInstance> constructors() {
    if (this.fields == null) {
      this.constructors = deepCopy(this.base().constructors());
      this.constructors = this.subst().substConstructorList(this.constructors);
    }
    return this.constructors;
  }

  @Override
  public List<? extends ClassType> memberClasses() {
    if (this.memberClasses == null) {
      this.memberClasses = deepCopy(this.base().memberClasses());
      this.memberClasses = this.subst().substTypeList(this.memberClasses);
    }
    return this.memberClasses;
  }



}
