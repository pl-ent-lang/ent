package panda.types;

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

public class PandaConstructorInstance_c extends JL5ConstructorInstance_c implements PandaConstructorInstance {

  private List<ModeTypeVariable> modeTypeVars;
  private PandaProcedureInstance baseInstance;

  public PandaConstructorInstance_c(PandaTypeSystem ts, 
                                    Position pos, 
                                    ClassType container, 
                                    Flags flags, 
                                    List<? extends Type> argTypes, 
                                    List<? extends Type> excTypes, 
                                    List<? extends TypeVariable> typeParams,
                                    List<ModeTypeVariable> modeTypeVars) {
    super((JL5TypeSystem_c) ts, pos, container, flags, argTypes, excTypes, typeParams);
    this.modeTypeVars(modeTypeVars);
  }

  public List<ModeTypeVariable> modeTypeVars() {
    return this.modeTypeVars;
  }

  public void modeTypeVars(List<ModeTypeVariable> modeTypeVars) {
    if (modeTypeVars != null) {
      this.modeTypeVars = modeTypeVars;
    } else {
      this.modeTypeVars = Collections.emptyList();
    }
  } 

  public PandaProcedureInstance baseInstance() {
    return this.baseInstance;
  }

  public void baseInstance(PandaProcedureInstance baseInstance) {
    this.baseInstance = baseInstance;
  }


}
