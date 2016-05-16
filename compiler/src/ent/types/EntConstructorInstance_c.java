package ent.types;

import polyglot.types.ClassType;
import polyglot.types.Flags;
import polyglot.types.ReferenceType;
import polyglot.types.Type;
import polyglot.util.Position;

import polyglot.ext.jl5.types.JL5TypeSystem;
import polyglot.ext.jl5.types.JL5TypeSystem_c;

import polyglot.ext.jl5.types.JL5ConstructorInstance;
import polyglot.ext.jl5.types.JL5ConstructorInstance_c;
import polyglot.ext.jl5.types.TypeVariable;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class EntConstructorInstance_c
    extends JL5ConstructorInstance_c implements EntConstructorInstance {

  private List<ModeTypeVariable> modeTypeVars;
  private EntProcedureInstance baseInstance;
  private ModeType overmode;

  public EntConstructorInstance_c(EntTypeSystem ts,
                                  Position pos,
                                  ClassType container,
                                  Flags flags,
                                  List<? extends Type> argTypes,
                                  List<? extends Type> excTypes,
                                  List<? extends TypeVariable> typeParams,
                                  List<ModeTypeVariable> modeTypeVars) {
    super((JL5TypeSystem_c)ts, pos, container, flags, argTypes, excTypes, typeParams);
    this.modeTypeVars(modeTypeVars);
    this.overmode(overmode);
  }

  public List<ModeTypeVariable> modeTypeVars() { return this.modeTypeVars; }

  public void modeTypeVars(List<ModeTypeVariable> modeTypeVars) {
    if (modeTypeVars != null) {
      this.modeTypeVars = modeTypeVars;
    } else {
      this.modeTypeVars = Collections.emptyList();
    }
  }

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
