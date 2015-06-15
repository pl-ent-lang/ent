package panda.ast;

import panda.types.ModeOrderingInstance;
import panda.types.ModeType;
import panda.types.PandaTypeSystem;

import polyglot.ast.Node;
import polyglot.ast.Id;
import polyglot.ast.Term;
import polyglot.ast.Term_c;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeBuilder;
import polyglot.visit.PrettyPrinter;
import polyglot.util.CodeWriter;

import java.util.List;
import java.util.Map;

public class ModesDecl_c extends Term_c implements ModesDecl {

  private final String MODES_DECL_CLASS_NAME = "PandaMode";
  protected List<ModeOrder> orders;
  protected ModeOrderingInstance oi;

  public ModesDecl_c(Position pos, List<ModeOrder> orders) {
    super(pos);
    this.orders = orders;
  }

  public final String ModesDeclClassName() {
    return this.MODES_DECL_CLASS_NAME;
  }

  // Property Methods
  public List<ModeOrder> orders() {
    return this.orders;
  }

  public <N extends ModesDecl_c> N orders(N n, List<ModeOrder> orders) {
    if (this.orders() == orders) return n;
    n = this.copyIfNeeded(n);
    n.orders = orders;
    return n;
  }

  public ModeOrderingInstance modeOrderingInstance() {
    return this.oi;
  }

  public ModesDecl modeOrderingInstance(ModeOrderingInstance oi) {
    return this.modeOrderingInstance(this, oi);
  }

  public <N extends ModesDecl_c> N modeOrderingInstance(N n, ModeOrderingInstance oi) {
    if (this.modeOrderingInstance() == oi) return n;
    n = this.copyIfNeeded(n);
    n.oi = oi;
    return n;
  }

  // Node Methods
  protected <N extends ModesDecl_c> N reconstruct(N n, List<ModeOrder> orders) {
    n = this.orders(n, orders);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    List<ModeOrder> orders = visitList(this.orders(), v);
    return this.reconstruct(this, orders);
  }

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    PandaTypeSystem pandaTypeSystem = (PandaTypeSystem) tb.typeSystem();

    ModeOrderingInstance oi = pandaTypeSystem.createModeOrderingInstance();

    for (ModeOrder modeOrder : this.orders()) {
      ModeType l = pandaTypeSystem.createModeType(modeOrder.lower());
      ModeType u = pandaTypeSystem.createModeType(modeOrder.upper());
      oi.insertModeTypeOrdering(l, u);
    }

    oi.buildModeTypeOrdering();
    return this.modeOrderingInstance(oi);
  }

  @Override
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
    w.begin(0);
    w.write("public class " + MODES_DECL_CLASS_NAME);
    w.unifiedBreak(0);
    w.end();
    w.write("{");

    w.newline(2);
    w.begin(0);
    for (Map.Entry<ModeType, ModeType> e : this.modeOrderingInstance()
                                               .modeOrdering()
                                               .entrySet()) {
      ModeType modeType = e.getKey();
      w.newline(0);
      w.write("public static final int " + modeType.runtimeCode() + " = " + modeType.rank() + ";");
    }
    w.end();
    w.newline(0);

    w.begin(0);
    w.write("}"); w.newline();
    w.end();

  }

  // Term Methods
  @Override
  public Term firstChild() {
    return this.listChild(this.orders(), null);
  }

  @Override
  public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
    if (this.orders() != null) {
      v.visitCFGList(this.orders(), this, ENTRY);
    }
    return succs;
  }



}
