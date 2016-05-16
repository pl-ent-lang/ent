package ent.types;

import polyglot.ext.jl5.types.JL5ProcedureInstance;

import java.util.List;

public interface EntProcedureInstance extends JL5ProcedureInstance {

  List<ModeTypeVariable> modeTypeVars();
  void modeTypeVars(List<ModeTypeVariable> modeTypeVars);

  ModeType overmode();
  void overmode(ModeType overmode);

  EntProcedureInstance baseInstance();
  void baseInstance(EntProcedureInstance baseInstance);

  List<ModeType> actualModeArgsTop();
  void actualModeArgsPop();
  void actualModeArgsPush(List<ModeType> actualModeArgs);
}
