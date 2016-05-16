package ent.types;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.visit.*;

public interface ModeType extends Type {
  // Property Methods
  String name();

  Type superType();
  void superType(Type superType);

  Type subType();
  void subType(Type subType);

  int uniqueId();
  void uniqueId(int uniqueId);

  String compileId();
  String compileExpr();

  String compileCode();
  String runtimeCode();

  Expr rewriteForLookup(NodeFactory nf, TypeSystem ts, Context c);
}
