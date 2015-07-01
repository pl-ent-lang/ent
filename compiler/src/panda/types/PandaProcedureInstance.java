package panda.types;

import polyglot.ext.jl5.types.JL5ProcedureInstance;

import java.util.List;

public interface PandaProcedureInstance extends JL5ProcedureInstance {

  List<ModeTypeVariable> modeTypeVars();
  void modeTypeVars(List<ModeTypeVariable> modeTypeVars);

  PandaProcedureInstance baseInstance();
  void baseInstance(PandaProcedureInstance baseInstance);
}

