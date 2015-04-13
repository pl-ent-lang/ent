package panda.types;

import polyglot.types.Type;

public interface PandaType extends Type {
  // Property Methods
  Type baseType();
  void baseType(Type baseType);
    
  Type modeType(); 
  void modeType(Type modeType); 
}
