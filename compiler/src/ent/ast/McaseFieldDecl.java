package ent.ast;

import polyglot.ast.Id;
import polyglot.ast.Expr;
import polyglot.ast.Term;

public interface McaseFieldDecl extends Term {
  Id field();
  Expr init();
}
