package panda.types;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.visit.*;

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

  Expr rewriteForLookup(NodeFactory nf, Context c, TypeSystem to_ts);
}
