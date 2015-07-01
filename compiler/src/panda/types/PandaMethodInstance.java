package panda.types;

import polyglot.ext.jl5.types.JL5MethodInstance;

public interface PandaMethodInstance extends PandaProcedureInstance, JL5MethodInstance {
  PandaMethodInstance baseInstance();
  void baseInstance(PandaMethodInstance baseInstance);
}
