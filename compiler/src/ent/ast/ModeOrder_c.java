package ent.ast;

import ent.types.ModeType;
import ent.types.EntTypeSystem;

import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.Id;
import polyglot.ast.Term;
import polyglot.ast.Term_c;
import polyglot.translate.ExtensionRewriter;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.TypeBuilder;

import java.util.List;

public class ModeOrder_c extends Term_c implements ModeOrder {

  protected String lower;
  protected String upper;

  public ModeOrder_c(Position pos, String lower, String upper) {
    super(pos);
    this.lower = lower;
    this.upper = upper;
  }

  // Property Methods
  public String lower() { return this.lower; }

  public ModeOrder lower(String lower) { return this.lower(this, lower); }

  public <N extends ModeOrder_c> N lower(N n, String lower) {
    if (this.lower == lower)
      return n;
    n = this.copyIfNeeded(n);
    n.lower = lower;
    return n;
  }

  public String upper() { return this.upper; }

  public ModeOrder upper(String upper) { return this.upper(this, upper); }

  public <N extends ModeOrder_c> N upper(N n, String upper) {
    if (this.upper == upper)
      return n;
    n = this.copyIfNeeded(n);
    n.upper = upper;
    return n;
  }

  // Node Methods
  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    return null;
  }

  // Term Methods
  @Override
  public Term firstChild() {
    return null;
  }

  @Override
  public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
    return succs;
  }
}
