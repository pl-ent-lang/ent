package panda.ast;

import panda.types.PandaTypeSystem;
import panda.types.ModeSubstType;
import panda.types.ModeValueType;
import panda.types.ModeTypeVariable;
import panda.types.Mode;

import polyglot.ast.Expr;
import polyglot.ast.Expr_c;
import polyglot.ast.Node;
import polyglot.ast.Term;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeChecker;

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

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    PandaTypeSystem ts = (PandaTypeSystem) tb.typeSystem();
    return this.type(ts.unknownType(this.position()));
  }

  private Mode resolveMode(Type t) {
    // We could have a mode value, or a mode subst type
    if (t instanceof ModeValueType) {
      return ((ModeValueType) t).mode(); 
    } else if (t instanceof ModeSubstType) {
      return ((ModeSubstType) t).modeType();
    } else {
      System.out.println("ERROR : Bounds of snapshot have not been resolved, " + t);
      System.exit(1);
      return null;
    }
  }

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {

    Type tt = this.target().type();

    if (!(tt instanceof ModeSubstType)) {
      System.out.println("ERROR : Target type of snapshot is not a mode subst type");
      System.exit(1);
    }

    PandaTypeSystem ts = (PandaTypeSystem) tc.typeSystem();
    if (((ModeSubstType) tt).modeType() != ts.DynamicModeType()) {
      System.out.println("ERROR : Target type of dynamic mode type (not yet impl)");
      System.exit(1);
    }

    Type lt = this.lower().type();
    Type ut = this.upper().type();

    Mode lm = this.resolveMode(lt);
    Mode um = this.resolveMode(ut);

    if (!ts.isSubtypeModes(lm,um)) {
      throw new SemanticException("Lower bound must be a submode of upper bound.");
    }
    if (lm == ts.DynamicModeType() || um == ts.DynamicModeType()) {
      throw new SemanticException("Bounds must be concrete mode types.");
    }

    ModeTypeVariable elm = ts.createModeTypeVariable(this.position(), "_");
    elm.upperBound(um);
    elm.lowerBound(lm);

    // We introduce a type variable bounded by the bounds supplied in snapshot
    ModeSubstType ct = ((ModeSubstType) tt).deepCopy();
    ct.modeType(elm);

    return this.type(ct);
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
