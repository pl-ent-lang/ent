package ent.types;

import polyglot.ext.jl5.types.JL5MethodInstance;

public interface EntMethodInstance extends EntProcedureInstance, JL5MethodInstance {
  AttributeInstance attributeInstance();  
  void attributeInstance(AttributeInstance attrInstance);  
  
}
