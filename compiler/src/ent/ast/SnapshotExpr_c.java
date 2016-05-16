package ent.ast;

import ent.types.*;
import ent.translate.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.translate.*;
import polyglot.util.*;
import polyglot.visit.*;
import polyglot.qq.*;

import java.util.List;

public class SnapshotExpr_c extends Expr_c implements SnapshotExpr {

  protected Expr target;
  protected Expr lower;
  protected Expr upper;
  protected boolean saveMode;
  protected boolean force;

  SnapshotExpr_c(Position pos,
                 Expr target,
                 Expr lower,
                 Expr upper,
                 boolean saveMode,
                 boolean force) {
    super(pos);
    this.target = target;
    this.lower = lower;
    this.upper = upper;
    this.saveMode = saveMode;
    this.force = force;
  }

  // Property Methods
  protected Expr target() { return this.target; }

  protected <N extends SnapshotExpr_c> N target(N n, Expr target) {
    if (this.target() == target)
      return n;
    n = this.copyIfNeeded(n);
    n.target = target;
    return n;
  }

  protected Expr lower() { return this.lower; }

  protected <N extends SnapshotExpr_c> N lower(N n, Expr lower) {
    if (this.lower() == lower)
      return n;
    n = this.copyIfNeeded(n);
    n.lower = lower;
    return n;
  }

  protected Expr upper() { return this.upper; }

  protected <N extends SnapshotExpr_c> N upper(N n, Expr upper) {
    if (this.upper() == upper)
      return n;
    n = this.copyIfNeeded(n);
    n.upper = upper;
    return n;
  }

  protected boolean saveMode() { return this.saveMode; }

  protected boolean force() { return this.force; }

  protected SnapshotExpr saveMode(boolean saveMode) { return this.saveMode(this, saveMode); }

  protected <N extends SnapshotExpr_c> N saveMode(N n, boolean saveMode) {
    if (this.saveMode() == saveMode)
      return n;
    n = this.copyIfNeeded(n);
    n.saveMode = saveMode;
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
  public Node copy(NodeFactory nf) {
    EntNodeFactory pnf = (EntNodeFactory)nf;
    return pnf.SnapshotExpr(
        this.position(), this.target(), this.lower(), this.upper(), this.saveMode(), this.force());
  }

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    EntTypeSystem ts = (EntTypeSystem)tb.typeSystem();
    return this.type(ts.unknownType(this.position()));
  }

  private ModeType resolveMode(Type t) {
    // We could have a mode value, or a mode subst type
    if (t instanceof ModeValueType) {
      return (ModeType)((ModeValueType)t).mode();
    } else if (t instanceof ModeSubstType) {
      return (ModeType)((ModeSubstType)t).modeType();
    } else {
      System.out.println("ERROR : Bounds of snapshot have not been resolved, " + t);
      System.exit(1);
      return null;
    }
  }

  private ModeType checkMode(Type t, Context ctx) throws SemanticException {
    ModeType mt = (ModeType)this.resolveMode(t);

    if (mt instanceof ModeTypeVariable) {
      ModeTypeVariable mtVar = (ModeTypeVariable)mt;
      EntClassType ct = (EntClassType)ctx.currentClass();
      // OPTIMIZATION-NOTE : This check lets us use a classes mode type variables inside snapshot
      // bounds without getting too performance critical. The same idea can be applied to 'this'
      // inside the bounds. These two are obvious without context sensitive analysis.
      if (ct.containsModeTypeVariable(mtVar)) {
        if (ctx.inStaticContext()) {
          throw new SemanticException(
              "Attempting to use class mode type variable inside a static context.");
        }
        ct.instancesNeedTypePreservation(true);
      }
    }

    return mt;
  }

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    EntTypeSystem ts = (EntTypeSystem)tc.typeSystem();
    SnapshotExpr_c n = this;

    // NOTE : Even though it is safe to re-typecheck a SnapshotExpr it
    // causes extra ModeTypeVariables to be created. This check prevents
    // that.
    if (n.type().isCanonical()) {
      return this;
    }

    EntClassType tt = (EntClassType)n.target().type();

    if (!(tt instanceof ModeSubstType)) {
      System.out.println("ERROR : Target type of snapshot is not a mode subst type");
      System.exit(1);
    }

    if (((ModeSubstType)tt).modeType() != ts.DynamicModeType()) {
      System.out.println(this.position() +
                         " ERROR : Target type of dynamic mode type (not yet impl)");
      System.exit(1);
    }

    // OPTIMIZATION-NOTE : For now, we wont save the resolved object's mode in the table
    // unless instances needs preservation
    if (tt.hasMcaseFields() || tt.instancesNeedTypePreservation() || tt.flags().isInterface()) {
      n = (SnapshotExpr_c)n.saveMode(true);
    }

    Type lt = n.lower().type();
    Type ut = n.upper().type();

    Context ctx = tc.context();
    Type lm = n.checkMode(lt, ctx);
    Type um = n.checkMode(ut, ctx);

    if (!ts.isSubtype(lm, um)) {
      throw new SemanticException("Lower bound must be a submode of upper bound.");
    }
    if (lm == ts.DynamicModeType() || um == ts.DynamicModeType()) {
      throw new SemanticException("Bounds must be concrete mode types.");
    }

    // TODO: Refactor into a clear ModeTypeVariable creation.
    ModeTypeVariable elm = ts.createBoundedExistential(this.position());
    elm.upperBound(um);
    elm.lowerBound(lm);
    // System.out.format("Existential: %s\n", elm);

    // We introduce a type variable bounded by the bounds supplied in snapshot
    ModeSubstType ct = ((ModeSubstType)tt).deepCopy();
    ct.modeType(elm);

    return n.type(ct);
  }

  @Override
  public ExtensionRewriter extRewriteEnter(ExtensionRewriter rw) {
    EntRewriter prw = (EntRewriter)rw;
    return prw.rewriteModeValue(false);
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter)rw;
    NodeFactory nf = prw.nodeFactory();
    QQ qq = prw.qq();

    if (!prw.translateEnt()) {
      return this.target();
    }

    Expr target = this.target();

    Expr lower;
    if (!(this.lower() instanceof ModeValue)) {
      lower = qq.parseExpr("ENT_Runtime.getObjMode(%E, 0)", this.lower());
    } else {
      lower = (Expr)((ModeValue)this.lower()).rewriteModeValue(rw);
    }

    Expr upper;
    if (!(this.upper() instanceof ModeValue)) {
      upper = qq.parseExpr("ENT_Runtime.getObjMode(%E, 0)", this.upper());
    } else {
      upper = (Expr)((ModeValue)this.upper()).rewriteModeValue(rw);
    }

    String snapshotStr = (this.force) ? "ENT_Snapshot.forceSnapshot(%E, %E, %E, %E)"
                                      : "ENT_Snapshot.snapshot(%E, %E, %E, %E)";

    Expr expr = qq.parseExpr(snapshotStr,
                             target,
                             lower,
                             upper,
                             nf.BooleanLit(Position.COMPILER_GENERATED, this.saveMode()));

    return expr;
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
