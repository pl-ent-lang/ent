package panda.types;

import polyglot.ext.jl5.types.JL5ParsedClassType;

import java.util.List;

public interface PandaParsedClassType extends PandaClassType, JL5ParsedClassType { 

  boolean hasAttribute();
  
  boolean hasCopy();

}
