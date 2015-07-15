package panda.types;

import polyglot.types.*;
import polyglot.types.Package;
import polyglot.util.*;

import polyglot.ext.jl5.types.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public abstract class ModeSubstClassType_c extends ModeSubstReferenceType_c implements ModeSubstClassType {

  private transient ModeSubst modeSubst = null;

  public ModeSubstClassType_c(ClassType baseType, List<Type> modeTypeArgs) {
    super(baseType, modeTypeArgs);
  }

  @Override
  public void modeTypeArgs(List<Type> modeTypeArgs) {
    this.modeTypeArgs = modeTypeArgs;
    this.modeSubst = null;
  } 

  public ModeSubst modeSubst() {
    if (this.modeSubst == null) {
      Map<ModeTypeVariable, Type> mtMap = new HashMap<>();
      for (int i = 0; i < this.modeTypeVars().size(); ++i) {
        mtMap.put(this.modeTypeVars().get(i), this.modeTypeArgs().get(i));
      }
      this.modeSubst = new ModeSubst(this, mtMap);
    }
    return this.modeSubst;
  }

  // PandaClassType Methods
  @Override
  public List<ModeTypeVariable> modeTypeVars() {
    return ((PandaClassType) this.baseType()).modeTypeVars();
  }
  
  @Override
  public void modeTypeVars(List<ModeTypeVariable> modeTypeVars) {
    ((PandaClassType) this.baseType()).modeTypeVars(modeTypeVars);
  } 

  @Override
  public boolean isImplicitModeTypeVar() {
    return ((PandaClassType) this.baseType()).isImplicitModeTypeVar();
  }

  public AttributeInstance attributeInstance() {
    return ((PandaClassType) this.baseType()).attributeInstance();
  }

  public void attributeInstance(AttributeInstance attributeInstance) {
    ((PandaClassType) this.baseType()).attributeInstance(attributeInstance);
  } 

  public boolean hasAttribute() {
    return ((PandaClassType) this.baseType()).hasAttribute();
  }

  public CopyInstance copyInstance() {
    return ((PandaClassType) this.baseType()).copyInstance();
  }

  public void copyInstance(CopyInstance copyInstance) {
    ((PandaClassType) this.baseType()).copyInstance(copyInstance);
  } 

  public boolean hasCopy() {
    return ((PandaClassType) this.baseType()).hasCopy();
  }

  // ClassType Methods
  @Override
  public Resolver resolver() {
    return ((JL5ClassType) this.baseType()).resolver();
  }

  // TODO : Be careful, a copy on this object may need to copy the subst type
  @Override
  public ClassType_c copy() {
    return (ClassType_c) ((JL5ClassType) this.baseType()).copy();
  }

  @Override
  public Declaration declaration() {
    return ((JL5ClassType) this.baseType()).declaration();
  }

  @Override
  public void setDeclaration(Declaration decl) {
    ((JL5ClassType) this.baseType()).setDeclaration(decl);
  }

  @Override
  public ReferenceType container() {
    return ((JL5ClassType) this.baseType()).container();
  }

  @Override
  public String fullName() {
    return ((JL5ClassType) this.baseType()).fullName();
  }

  @Override
  public boolean isTopLevel() {
    return ((JL5ClassType) this.baseType()).isTopLevel();
  }

  @Override
  public boolean isMember() {
    return ((JL5ClassType) this.baseType()).isMember();
  }

  @Override
  public boolean isLocal() {
    return ((JL5ClassType) this.baseType()).isLocal();
  }

  @Override
  public boolean isAnonymous() {
    return ((JL5ClassType) this.baseType()).isAnonymous();
  }

  @Override
  public boolean isInner() {
    return ((JL5ClassType) this.baseType()).isInner();
  }

  @Override
  public boolean isNested() {
    return ((JL5ClassType) this.baseType()).isNested();
  }

  @Override
  public boolean isInnerClass() {
    return ((JL5ClassType) this.baseType()).isInnerClass();
  }

  @Override
  public boolean isCanonical() {
    return true;
  }

  @Override
  public boolean isClass() {
    return true;
  }

  @Override
  public ClassType toClass() {
    return this;
  }

  @Override
  public abstract Flags flags();

  @Override
  public abstract List<? extends ConstructorInstance> constructors();

  @Override
  public abstract List<? extends ClassType> memberClasses();

  @Override
  public abstract List<? extends MethodInstance> methods();

  @Override
  public abstract List<? extends FieldInstance> fields();

  @Override
  public abstract List<? extends ReferenceType> interfaces();

  @Override
  public abstract Type superType();

  @Override
  public abstract Package package_();

  @Override
  public List<? extends MemberInstance> members() {
    List<MemberInstance> l = new LinkedList<>();
    l.addAll(this.methods());
    l.addAll(this.fields());
    l.addAll(this.constructors());
    l.addAll(this.memberClasses());
    return l;
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
  public ClassType memberClassNamed(String name) {
    for (ClassType t : this.memberClasses()) {
      if (t.name().equals(name)) {
        return t;
      }
    }
    return null;
  }


  @Override
  public boolean isThrowable() {
    return ((JL5ClassType) this.baseType()).isThrowable();
  }

  @Override
  public boolean isUncheckedException() {
    return ((JL5ClassType) this.baseType()).isUncheckedException();
  }

  @Override
  public final boolean isEnclosed(ClassType maybe_outer) {
    return ((JL5ClassType) this.baseType()).isEnclosed(maybe_outer);
  }

  @Override
  public final boolean hasEnclosingInstance(ClassType encl) {
    return ((JL5ClassType) this.baseType()).hasEnclosingInstance(encl);
  }

  @Override
  public boolean isEnclosedImpl(ClassType maybe_outer) {
    return ((JL5ClassType) this.baseType()).isEnclosedImpl(maybe_outer);
  }

  @Override
  public boolean hasEnclosingInstanceImpl(ClassType encl) {
    return ((JL5ClassType) this.baseType()).hasEnclosingInstanceImpl(encl);
  }

  // JL5ClassType Methods
  @Override
  public EnumInstance enumConstantNamed(String name) {
    return ((JL5ClassType) this.baseType()).enumConstantNamed(name);
  }

  // Type Methods
  @Override
  public boolean descendsFromImpl(Type ansT) {
    PandaTypeSystem ts = (PandaTypeSystem) this.ts;

    if (!ansT.isCanonical() || ansT.isNull() || ts.typeEquals(this, ansT)) {
      return false;
    }

    if (ts.typeEquals(ansT, ts.ModeSubstObject())) {
      return true;
    }

      // Check subtype relation for classes.
    if (!flags().isInterface()) {
      if (ts.typeEquals(this, ts.ModeSubstObject())) {
        return false;
      }

      if (superType() == null) {
        return false;
      }

      if (ts.isSubtype(superType(), ansT)) {
        return true;
      }
    }

    // Next check interfaces.
    for (Type parentType : interfaces()) {
      if (ts.isSubtype(parentType, ansT)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean isImplicitCastValidImpl(Type toT) {
    if (!toT.isClass()) return false;
    return this.ts.isSubtype(this, toT);
  }

  @Override
  public LinkedList<Type> isImplicitCastValidChainImpl(Type toType) {
    PandaTypeSystem ts = (PandaTypeSystem) this.ts;
    LinkedList<Type> chain = null;
    if (ts.isSubtype(this, toType)) {
      chain = new LinkedList<>();
      chain.add(this);
      chain.add(toType);
    } else if (toType().isPrimitive()) { 
      ModeSubstPrimitiveType pt = (ModeSubstPrimitiveType) toType.toPrimitive();
      ClassType wrapperType = ts.wrapperClassOfModeSubstPrimitive(pt); 
      chain = ts.isImplicitCastValidChain(this, wrapperType);
      if (chain != null) {
        chain.addLast(toType);
      }
    }
    return chain;
  }


}
