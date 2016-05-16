package ent.types;

import polyglot.types.*;
import polyglot.types.Package;
import polyglot.util.*;

import polyglot.ext.jl5.types.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public abstract class ModeSubstClassType_c
    extends ModeSubstReferenceType_c implements ModeSubstClassType {

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

  // EntClassType Methods
  @Override
  public List<ModeTypeVariable> modeTypeVars() {
    return ((EntClassType)this.baseType()).modeTypeVars();
  }

  @Override
  public void modeTypeVars(List<ModeTypeVariable> modeTypeVars) {
    ((EntClassType)this.baseType()).modeTypeVars(modeTypeVars);
  }

  @Override
  public boolean isImplicitModeTypeVar() {
    return ((EntClassType)this.baseType()).isImplicitModeTypeVar();
  }

  public AttributeInstance attributeInstance() {
    return ((EntClassType)this.baseType()).attributeInstance();
  }

  public void attributeInstance(AttributeInstance attributeInstance) {
    ((EntClassType)this.baseType()).attributeInstance(attributeInstance);
  }

  public boolean hasAttribute() { return ((EntClassType)this.baseType()).hasAttribute(); }

  public CopyInstance copyInstance() { return ((EntClassType)this.baseType()).copyInstance(); }

  public void copyInstance(CopyInstance copyInstance) {
    ((EntClassType)this.baseType()).copyInstance(copyInstance);
  }

  public boolean hasCopy() { return ((EntClassType)this.baseType()).hasCopy(); }

  public boolean hasMcaseFields() { return ((EntClassType)this.baseType()).hasMcaseFields(); }

  public void hasMcaseFields(boolean hasMcaseFields) {
    ((EntClassType)this.baseType()).hasMcaseFields(hasMcaseFields);
  }

  public boolean hasDynamicRecv() { return ((EntClassType)this.baseType()).hasDynamicRecv(); }

  public boolean containsModeTypeVariable(ModeTypeVariable mt) {
    return ((EntClassType)this.baseType()).containsModeTypeVariable(mt);
  }

  public boolean instancesNeedTypePreservation() {
    return ((EntClassType)this.baseType()).instancesNeedTypePreservation();
  }

  public void instancesNeedTypePreservation(boolean needs) {
    ((EntClassType)this.baseType()).instancesNeedTypePreservation(needs);
  }

  // ClassType Methods
  @Override
  public String name() {
    return ((EntClassType)this.baseType()).name();
  }

  @Override
  public String fullName() {
    return ((EntClassType)this.baseType()).fullName();
  }

  @Override
  public Resolver resolver() {
    return ((JL5ClassType)this.baseType()).resolver();
  }

  // TODO : Be careful, a copy on this object may need to copy the subst type
  @Override
  public ClassType_c copy() {
    return (ClassType_c)((JL5ClassType)this.baseType()).copy();
  }

  @Override
  public Declaration declaration() {
    return ((JL5ClassType)this.baseType()).declaration();
  }

  @Override
  public void setDeclaration(Declaration decl) {
    ((JL5ClassType)this.baseType()).setDeclaration(decl);
  }

  @Override
  public ReferenceType container() {
    return ((JL5ClassType)this.baseType()).container();
  }

  @Override
  public boolean isTopLevel() {
    return ((JL5ClassType)this.baseType()).isTopLevel();
  }

  @Override
  public boolean isMember() {
    return ((JL5ClassType)this.baseType()).isMember();
  }

  @Override
  public boolean isLocal() {
    return ((JL5ClassType)this.baseType()).isLocal();
  }

  @Override
  public boolean isAnonymous() {
    return ((JL5ClassType)this.baseType()).isAnonymous();
  }

  @Override
  public boolean isInner() {
    return ((JL5ClassType)this.baseType()).isInner();
  }

  @Override
  public boolean isNested() {
    return ((JL5ClassType)this.baseType()).isNested();
  }

  @Override
  public boolean isInnerClass() {
    return ((JL5ClassType)this.baseType()).isInnerClass();
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

  @Override public abstract Flags flags();

  @Override public abstract List<? extends ConstructorInstance> constructors();

  @Override public abstract List<? extends ClassType> memberClasses();

  @Override public abstract List<? extends MethodInstance> methods();

  @Override public abstract List<? extends FieldInstance> fields();

  @Override public abstract List<? extends ReferenceType> interfaces();

  @Override public abstract Type superType();

  @Override public abstract Package package_();

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
    return ((JL5ClassType)this.baseType()).isThrowable();
  }

  @Override
  public boolean isUncheckedException() {
    return ((JL5ClassType)this.baseType()).isUncheckedException();
  }

  @Override
  public final boolean isEnclosed(ClassType maybe_outer) {
    return ((JL5ClassType)this.baseType()).isEnclosed(maybe_outer);
  }

  @Override
  public final boolean hasEnclosingInstance(ClassType encl) {
    return ((JL5ClassType)this.baseType()).hasEnclosingInstance(encl);
  }

  @Override
  public boolean isEnclosedImpl(ClassType maybe_outer) {
    return ((JL5ClassType)this.baseType()).isEnclosedImpl(maybe_outer);
  }

  @Override
  public boolean hasEnclosingInstanceImpl(ClassType encl) {
    return ((JL5ClassType)this.baseType()).hasEnclosingInstanceImpl(encl);
  }

  // JL5ClassType Methods
  @Override
  public EnumInstance enumConstantNamed(String name) {
    return ((JL5ClassType)this.baseType()).enumConstantNamed(name);
  }

  @Override
  public LinkedList<Type> isImplicitCastValidChainImpl(Type toT) {
    EntTypeSystem ts = (EntTypeSystem)this.ts;

    // MODE-NOTE: FIXME Forwarding may not be the right solution here
    // May skirt around a lot of potential bugs
    if (!(toT instanceof ModeSubstType)) {
      return ts.isImplicitCastValidChain(this.baseType(), toT);
    }

    LinkedList<Type> chain = null;
    ModeSubstType st = (ModeSubstType)toT;

    if (ts.isSubtype(this, toT)) {
      chain = new LinkedList<>();
      chain.add(this);
      chain.add(toT);
    } else if (toT.isPrimitive()) {
      // MODE-NOTE: FIXME This is going to pop up as a bug eventually
      // Compare witll all isImplicitCastValidChain to pick the right one
      if (ts.primitiveTypeOfWrapper(this) != null) {
        chain = ts.isImplicitCastValidChain(ts.primitiveTypeOfWrapper(this), toT);
      }
      if (chain != null) {
        chain.addLast(toT);
      }
    }
    return chain;
  }
}
