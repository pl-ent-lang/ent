package ent.types;

import polyglot.types.*;

public interface AttributeInstance extends CodeInstance, MemberInstance { 
  void addMode(Type mode); 

  MethodInstance methodContainer();
  void setMethodContainer(MethodInstance mi);
}
