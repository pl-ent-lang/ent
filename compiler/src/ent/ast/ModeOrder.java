package ent.ast;

import polyglot.ast.Id;
import polyglot.ast.Term;

public interface ModeOrder extends Term {

  String lower();

  String upper();
}
