package ent.types;

import polyglot.types.Flags;
import polyglot.types.ReferenceType;
import polyglot.types.Type;
import polyglot.util.Position;

import polyglot.ext.jl5.types.JL5MethodInstance;
import polyglot.ext.jl5.types.JL5MethodInstance_c;
import polyglot.ext.jl5.types.TypeVariable;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class EntMethodInstance_c extends JL5MethodInstance_c implements EntMethodInstance {

  private List<ModeTypeVariable> modeTypeVars;
  private EntProcedureInstance baseInstance;
  private ModeType overmode;
  private AttributeInstance attrInstance;

  public EntMethodInstance_c(EntTypeSystem ts,
                             Position pos,
                             ReferenceType container,
                             Flags flags,
                             Type returnType,
                             String name,
                             List<? extends Type> argTypes,
                             List<? extends Type> excTypes,
                             List<? extends TypeVariable> typeParams,
                             List<ModeTypeVariable> modeTypeVars) {
    super(ts, pos, container, flags, returnType, name, argTypes, excTypes, typeParams);
    this.modeTypeVars(modeTypeVars);
    this.overmode(overmode);
    this.baseInstance = null;
  }

  public List<ModeTypeVariable> modeTypeVars() { return this.modeTypeVars; }

  public void modeTypeVars(List<ModeTypeVariable> modeTypeVars) {
    if (modeTypeVars != null) {
      this.modeTypeVars = modeTypeVars;
    } else {
      this.modeTypeVars = Collections.emptyList();
    }
  }

  public AttributeInstance attributeInstance() { return this.attrInstance; }

  public void attributeInstance(AttributeInstance attrInstance) { this.attrInstance = attrInstance; }

  public ModeType overmode() { return this.overmode; }

  public void overmode(ModeType overmode) { this.overmode = overmode; }

  public EntProcedureInstance baseInstance() { return this.baseInstance; }

  public void baseInstance(EntProcedureInstance baseInstance) { this.baseInstance = baseInstance; }

  private List<List<ModeType>> actualModeArgStack = new ArrayList<List<ModeType>>();

  public List<ModeType> actualModeArgsTop() {
    return actualModeArgStack.get(actualModeArgStack.size() - 1);
  }

  public void actualModeArgsPop() { actualModeArgStack.remove(actualModeArgStack.size() - 1); }

  public void actualModeArgsPush(List<ModeType> actualModeArgs) {
    actualModeArgStack.add(actualModeArgs);
  }

  @Override
  public boolean callValidImpl(List<? extends Type> argTypes) {
    return super.callValidImpl(argTypes);
  }

  // MODE-NOTE: Needed to do this here to switch...
  // if (!ts.equals(
  // to
  // if (!ts.typeEquals(
  //
  // Because of our whole ModeSubstType/Type issues.
  @Override
  public boolean hasFormalsImpl(List<? extends Type> formalTypes) {
    List<? extends Type> l1 = this.formalTypes();
    List<? extends Type> l2 = formalTypes;

    Iterator<? extends Type> i1 = l1.iterator();
    Iterator<? extends Type> i2 = l2.iterator();

    while (i1.hasNext() && i2.hasNext()) {
      Type t1 = i1.next();
      Type t2 = i2.next();

      if (!ts.typeEquals(t1, t2)) {
        return false;
      }
    }

    return !(i1.hasNext() || i2.hasNext());
  }
}
