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

  public boolean hasMcaseFields() {
    return ((PandaClassType) this.baseType()).hasMcaseFields();
  } 

  public void hasMcaseFields(boolean hasMcaseFields) {
    ((PandaClassType) this.baseType()).hasMcaseFields(hasMcaseFields);
  } 

  public boolean hasDynamicRecv() {
    return ((PandaClassType) this.baseType()).hasDynamicRecv();
  } 

  public boolean containsModeTypeVariable(ModeTypeVariable mt) {
    return ((PandaClassType) this.baseType()).containsModeTypeVariable(mt);
  }

  public boolean instancesNeedTypePreservation() {
    return ((PandaClassType) this.baseType()).instancesNeedTypePreservation();
  }

  public void instancesNeedTypePreservation(boolean needs) {
    ((PandaClassType) this.baseType()).instancesNeedTypePreservation(needs);
  }


  // ClassType Methods
  @Override
  public String name() {
    return ((PandaClassType) this.baseType()).name();
  }

  @Override
  public String fullName() {
    return ((PandaClassType) this.baseType()).fullName();
  }

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

  protected boolean isCastValidImplJL(Type toType) {
    if (!toType.isCanonical()) return false;
    if (!toType.isReference()) return false;

    if (toType.isArray()) {
      // From type is not an array, but to type is.  Check if the array
      // is a subtype of the from type.  This happens when from type
      // is java.lang.Object.
      return ts.isSubtype(toType, this);
    }

    // Both types should be classes now.
    if (!toType.isClass()) return false;

    // From and to are neither primitive nor an array. They are distinct.
    boolean fromInterface = flags().isInterface();
    boolean toInterface = toType.toClass().flags().isInterface();
    boolean fromFinal = flags().isFinal();
    boolean toFinal = toType.toClass().flags().isFinal();

    // This is taken from Section 5.5 of the JLS.
    if (!fromInterface) {
      // From is not an interface.
      if (!toInterface) {
        // Neither from nor to is an interface.
        return ts.isSubtype(this, toType) || ts.isSubtype(toType, this);
      }

      if (fromFinal) {
        // From is a final class, and to is an interface
        return ts.isSubtype(this, toType);
      }

      // From is a non-final class, and to is an interface.
      return true;
    } else {
      // From is an interface
      if (!toInterface && !toFinal) {
          // To is a non-final class.
          return true;
      }

      if (toFinal) {
          // To is a final class.
          return ts.isSubtype(toType, this);
      }

      // To and From are both interfaces.
      // If To and From contain methods with the same signature but
      // different return types, then a compile-time error occurs.
      Map<String, MethodInstance> signatureMap = new HashMap<>();
      List<ReferenceType> typeList = new LinkedList<>();
      typeList.add(this);
      typeList.add(toType.toClass());
      while (!typeList.isEmpty()) {
          ReferenceType type = typeList.remove(0);
          for (MethodInstance mi : type.methods()) {
              String signature = mi.signature();
              if (signatureMap.containsKey(signature)) {
                  MethodInstance mj = signatureMap.get(signature);
                  if (!ts.typeEquals(mi.returnType(), mj.returnType())) {
                      return false;
                  }
              }
              else signatureMap.put(signature, mi);
          }
          typeList.addAll(type.interfaces());
      }
      return true;
    }
  }

  @Override
  public boolean isCastValidImpl(Type toType) {
    if (this.isCastValidImplJL(toType)) {
      return true;
    }
    return (this.ts.isSubtype(this, toType) || 
            this.ts.isSubtype(toType, this));
  }


  @Override
  public boolean isImplicitCastValidImpl(Type toT) {
    if (!toT.isClass()) return false;

    if (!(toT instanceof ModeSubstType)) {
      return this.ts.isImplicitCastValid(this.baseType(), toT);
    } 

    ModeSubstType st = (ModeSubstType) toT;
    return this.ts.isSubtype(this.baseType(), st.baseType()) &&
           this.modeTypeArgsImplicit(st);
  }

  @Override
  public LinkedList<Type> isImplicitCastValidChainImpl(Type toT) {
    PandaTypeSystem ts = (PandaTypeSystem) this.ts;

    // MODE-NOTE: FIXME Forwarding may not be the right solution here
    // May skirt around a lot of potential bugs
    if (!(toT instanceof ModeSubstType)) {
      return ts.isImplicitCastValidChain(this.baseType(), toT);
    } 

    LinkedList<Type> chain = null;
    ModeSubstType st = (ModeSubstType) toT;

    if (ts.isSubtype(this.baseType(), st.baseType()) &&
        this.modeTypeArgsImplicit(st)) {
      chain = new LinkedList<>();
      chain.add(this);
      chain.add(toT);
    } else if (toT.isPrimitive()) { 
      // MODE-NOTE: FIXME This is going to pop up as a bug eventually
      ModeSubstPrimitiveType pt = (ModeSubstPrimitiveType) toT.toPrimitive();
      ClassType wrapperType = ts.wrapperClassOfModeSubstPrimitive(pt); 
      chain = ts.isImplicitCastValidChain(this, wrapperType);
      if (chain != null) {
        chain.addLast(toT);
      }
    }
    return chain;
  }


}
