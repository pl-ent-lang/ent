package panda.types;

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

public class ModeSubstSubstClassType_c extends ModeSubstClassType_c implements ModeSubstSubstClassType {

  public ModeSubstSubstClassType_c(PandaSubstClassType baseType, List<Type> modeTypeArgs) {
    super(baseType, modeTypeArgs);
  }

  protected transient List<? extends ReferenceType> interfaces = null;
  protected transient List<? extends FieldInstance> fields = null;
  protected transient List<? extends MethodInstance> methods = null;
  protected transient List<? extends ConstructorInstance> constructors = null;
  protected transient List<? extends ClassType> memberClasses = null;

  @Override
  public ModeSubstType deepCopy() {
    return 
      new ModeSubstSubstClassType_c((PandaSubstClassType) this.baseType(),
                                     new ArrayList<Type>(this.modeTypeArgs()));
      
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
    JL5ParsedClassType base = ((PandaSubstClassType) this.baseType()).base();
    PandaTypeSystem ts = (PandaTypeSystem) this.ts;
    return (PandaParsedClassType) ts.createModeSubst(base, this.modeTypeArgs());
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
  public String toString() {
    String name = ((PandaSubstClassType) this.baseType()).toString() + "@mode<";
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
    if (this.interfaces == null) {
      this.interfaces = this.modeSubst().substTypeList(((PandaSubstClassType) this.baseType()).interfaces());
    }
    return this.interfaces;
  }

  @Override
  public List<? extends FieldInstance> fields() {
    if (this.fields == null) {
      this.fields = this.modeSubst().substFieldList(((PandaSubstClassType) this.baseType()).fields());
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
      this.methods = this.modeSubst().substMethodList(((PandaSubstClassType) this.baseType()).methods());
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
      this.constructors = ((PandaSubstClassType) this.baseType()).constructors();
      this.constructors = this.modeSubst().substConstructorList(this.constructors);
    }
    return this.constructors;
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

  private boolean hasWildCardArg() {
    JL5ParsedClassType b = (JL5ParsedClassType) this.base();

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
  @Override
  public boolean descendsFromImpl(Type ansT) {
    if (super.descendsFromImpl(ansT)) {
      return true;
    }

    // HACK to get a compile, revisit
    if (ansT.isNull()) {
      return false;
    }

    PandaTypeSystem ts = (PandaTypeSystem) this.ts;

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

}
