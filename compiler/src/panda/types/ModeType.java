package panda.types;

import polyglot.ast.Id;
import polyglot.types.Type;

public interface ModeType extends Type {
  // Property Methods
  Type superType();
  void superType(Type superType);

  Type subType();
  void subType(Type subType);

  String compileId(); 
  String compileExpr();

  String compileCode(); 
  String runtimeCode(); 
}
