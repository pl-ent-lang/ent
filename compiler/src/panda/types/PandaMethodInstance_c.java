package panda.types;

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

public class PandaMethodInstance_c extends JL5MethodInstance_c implements PandaMethodInstance {

  private List<ModeTypeVariable> modeTypeVars;
  private PandaProcedureInstance baseInstance;

  public PandaMethodInstance_c(PandaTypeSystem ts, 
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
    this.baseInstance = null;
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

