package panda.ast;

import polyglot.ast.Expr;
import polyglot.ast.Expr_c;
import polyglot.ast.Node;
import polyglot.ast.Term;
import polyglot.util.Position;
import polyglot.visit.NodeVisitor;
import polyglot.visit.CFGBuilder;

import java.util.List;

public class SnapshotExpr_c extends Expr_c implements SnapshotExpr {

  protected Expr target;
  protected Expr lower;
  protected Expr upper;

  SnapshotExpr_c(Position pos, Expr target, Expr lower, Expr upper) {
    super(pos);
    this.target = target;
    this.lower = lower;
    this.upper = upper;
  }

  // Property Methods
  protected Expr target() {
    return this.target;
  }

  protected <N extends SnapshotExpr_c> N target(N n, Expr target) {
    if (this.target() == target) return n;
    n = this.copyIfNeeded(n);
    n.target = target;
    return n;
  }

  protected Expr lower() {
    return this.lower;
  }

  protected <N extends SnapshotExpr_c> N lower(N n, Expr lower) {
    if (this.lower() == lower) return n;
    n = this.copyIfNeeded(n);
    n.lower = lower;
    return n;
  }

  protected Expr upper() {
    return this.upper;
  }

  protected <N extends SnapshotExpr_c> N upper(N n, Expr upper) {
    if (this.upper() == upper) return n;
    n = this.copyIfNeeded(n);
    n.upper = upper;
    return n;
  }

  // Node Methods
  protected <N extends SnapshotExpr_c> N reconstruct(N n, Expr target, Expr lower, Expr upper) {
    n = this.target(n, target);
    n = this.lower(n, lower);
    n = this.upper(n, upper);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    Expr target = visitChild(this.target(), v);
    Expr lower = visitChild(this.lower(), v);
    Expr upper = visitChild(this.upper(), v);
    return reconstruct(this, target, lower, upper);
  }

  // Term Methods
  @Override
  public Term firstChild() {
    return null;
  }

  @Override
  public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
    return null;
  }

}
