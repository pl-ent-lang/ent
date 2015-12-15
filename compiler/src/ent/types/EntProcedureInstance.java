package ent.types;

import polyglot.ext.jl5.types.JL5ProcedureInstance;

import java.util.List;

public interface EntProcedureInstance extends JL5ProcedureInstance {

  List<ModeTypeVariable> modeTypeVars();
  void modeTypeVars(List<ModeTypeVariable> modeTypeVars);

  EntProcedureInstance baseInstance();
  void baseInstance(EntProcedureInstance baseInstance);
}

