package ent.types;

import polyglot.types.*;
import polyglot.types.Package;
import polyglot.util.*;

import polyglot.ext.param.types.*;

import polyglot.ext.jl5.types.*;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

public class ModeSubstSubstClassType_c
    extends ModeSubstClassType_c implements ModeSubstSubstClassType {

  public ModeSubstSubstClassType_c(EntSubstClassType baseType, List<Type> modeTypeArgs) {
    super(baseType, modeTypeArgs);
  }

  protected transient List<? extends ReferenceType> interfaces = null;
  protected transient List<? extends FieldInstance> fields = null;
  protected transient List<? extends MethodInstance> methods = null;
  protected transient List<? extends ConstructorInstance> constructors = null;
  protected transient List<? extends ClassType> memberClasses = null;

  @Override
  public ModeSubstType deepCopy() {
    return new ModeSubstSubstClassType_c((EntSubstClassType)this.baseType(),
                                         new ArrayList<Type>(this.modeTypeArgs()));
  }

  @Override
  public Iterator<Entry<TypeVariable, ReferenceType>> entries() {
    return ((EntSubstClassType)this.baseType()).entries();
  }

  // JL5SubstType Methods
  @Override
  public PClass<TypeVariable, ReferenceType> instantiatedFrom() {
    return ((EntSubstClassType)this.baseType()).instantiatedFrom();
  }

  @Override
  public Subst<TypeVariable, ReferenceType> subst() {
    return ((EntSubstClassType)this.baseType()).subst();
  }

  @Override
  public List<ReferenceType> actuals() {
    return ((EntSubstClassType)this.baseType()).actuals();
  }

  // JL5ClassType
  @Override
  public EnumInstance enumConstantNamed(String name) {
    return ((EntSubstClassType)this.baseType()).enumConstantNamed(name);
  }

  @Override
  public List<EnumInstance> enumConstants() {
    return ((EntSubstClassType)this.baseType()).enumConstants();
  }

  @Override
  public AnnotationTypeElemInstance annotationElemNamed(String name) {
    return ((EntSubstClassType)this.baseType()).annotationElemNamed(name);
  }

  @Override
  public List<AnnotationTypeElemInstance> annotationElems() {
    return ((EntSubstClassType)this.baseType()).annotationElems();
  }

  @Override
  public void printParams(CodeWriter w) {
    ((EntSubstClassType)this.baseType()).printParams(w);
  }

  @Override
  public boolean isRawClass() {
    return false;
  }

  // TODO : What is the right way to handle this situation? We need to see through
  // to the base type, but it needs to be subst for the mode.

  @Override
  public JL5ParsedClassType base() {
    JL5ParsedClassType base = ((EntSubstClassType)this.baseType()).base();
    EntTypeSystem ts = (EntTypeSystem)this.ts;

    EntParsedClassType ps = (EntParsedClassType)ts.createModeSubst(base, this.modeTypeArgs());

    return base;
  }

  @Override
  public String translateAsReceiver(Resolver c) {
    return ((EntSubstClassType)this.baseType()).translateAsReceiver(c);
  }

  @Override
  public ClassType outer() {
    return ((EntSubstClassType)this.baseType()).outer();
  }

  @Override
  public Annotations annotations() {
    return ((EntSubstClassType)this.baseType()).annotations();
  }

  @Override
  public Set<? extends Type> superclasses() {
    return ((EntSubstClassType)this.baseType()).superclasses();
  }

  @Override
  public String fullName() {
    return ((EntSubstClassType)this.baseType()).fullName();
  }

  @Override
  public String toString() {
    String name = ((EntSubstClassType)this.baseType()).toString() + "@mode<";
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
  public Flags flags() {
    return ((EntSubstClassType)this.baseType()).flags();
  }

  @Override
  public Package package_() {
    return ((EntSubstClassType)this.baseType()).package_();
  }

  @Override
  public Type superType() {
    return ((EntSubstClassType)this.baseType()).superType();
  }

  @Override
  public List<? extends ReferenceType> interfaces() {
    if (this.interfaces == null) {
      this.interfaces =
          this.modeSubst().substTypeList(((EntSubstClassType)this.baseType()).interfaces());
    }
    return this.interfaces;
  }

  @Override
  public List<? extends FieldInstance> fields() {
    if (this.fields == null) {
      this.fields = this.modeSubst().substFieldList(((EntSubstClassType)this.baseType()).fields());
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
  public List<? extends MethodInstance> methods() {
    if (this.methods == null) {
      this.methods =
          this.modeSubst().substMethodList(((EntSubstClassType)this.baseType()).methods());
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
  public List<? extends ConstructorInstance> constructors() {
    if (this.constructors == null) {
      this.constructors = ((EntSubstClassType)this.baseType()).constructors();
      this.constructors = this.modeSubst().substConstructorList(this.constructors);
    }
    return this.constructors;
  }

  @Override
  public List<? extends ClassType> memberClasses() {
    return ((EntSubstClassType)this.baseType()).memberClasses();
  }

  @Override
  public ClassType.Kind kind() {
    return ((EntSubstClassType)this.baseType()).kind();
  }

  @Override
  public boolean inStaticContext() {
    return ((EntSubstClassType)this.baseType()).inStaticContext();
  }

  @Override
  public void setFlags(Flags flags) {
    ((EntSubstClassType)this.baseType()).setFlags(flags);
  }

  @Override
  public void setContainer(ReferenceType container) {
    ((EntSubstClassType)this.baseType()).setContainer(container);
  }

  private boolean hasWildCardArg() {
    JL5ParsedClassType b = (JL5ParsedClassType)this.base();

    for (TypeVariable t : b.typeVariables()) {
      Type substType = this.subst().substType(t);
      if (substType instanceof WildCardType &&
          !(substType instanceof CaptureConvertedWildCardType)) {
        return true;
      }
    }
    return false;
  }

  // Type Methods
  /*
  @Override
  public boolean descendsFromImpl(Type ansT) {
    if (super.descendsFromImpl(ansT)) {
      return true;
    }

    // HACK to get a compile, revisit
    if (ansT.isNull()) {
      return false;
    }

    EntTypeSystem ts = (EntTypeSystem) this.ts;

    if (this.hasWildCardArg()) {
      Type captured;
      try {

        // MODE-NODE: This needs to be fixed, is a simple hack to get around an infinite loop
        captured = ts.applyCaptureConversion(this, null);
        if (captured instanceof ModeSubstType) {
          if (ts.descendsFrom(this.baseType(), ((ModeSubstType) captured).baseType())) {
            return true;
          }
        } else {
          // Note: we want descendsFrom, not isSubtype, since the direct ancestors of this class
          // are the direct ancestors of captured, but not captured itself.
          if (ts.descendsFrom(captured, ansT)) {
            return true;
          }
        }
      }
      catch (SemanticException e) {
        // nope, can't apply capture conversion.
      }
    }

    if (ansT instanceof RawClass) {
      // TODO : We inject a mode subst when our base is requested, take that into account
      // for the check.
      //
      // Revist exactly why we do this
      ModeSubstType st = (ModeSubstType) this.base();
      RawClass rc = (RawClass) ansT;
      if (st.baseType().equals(rc.base())) {
        return true;
      }
    }
    if (ansT instanceof JL5SubstClassType) {
      JL5SubstClassType anc = (JL5SubstClassType) ansT;
      if (this.base().typeEquals(anc.base())) {
        // same base. check the params
        // go through each type variable, and check containment
        boolean allContained = true;
        for (TypeVariable tv : ts.classAndEnclosingTypeVariables(base())) {
          Type ti = this.subst().substType(tv);
          Type si = anc.subst().substType(tv);

          if (!ts.isContained(ti, si)) {
            allContained = false;
            break;
          }
        }
        if (allContained) {
          return true;
        }
      }
    }
    return false;
  }
  */
}
