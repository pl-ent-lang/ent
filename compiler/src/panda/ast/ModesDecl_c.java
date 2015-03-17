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

  private List<ModeOrder> orders;

  private ModeOrderingInstance modeOrderingInstance;

  public ModesDecl_c(Position pos, List<ModeOrder> orders) {
    super(pos);
    this.orders = orders;
  }

  public final String ModesDeclClassName() {
    return this.MODES_DECL_CLASS_NAME;
  }

  public List<ModeOrder> orders() {
    return this.orders;
  }

  public void orders(List<ModeOrder> orders) {
    this.orders = orders;
  }

  public ModeOrderingInstance modeOrderingInstance() {
    return this.modeOrderingInstance;
  }

  public void modeOrderingInstance(ModeOrderingInstance modeOrderingInstance) {
    this.modeOrderingInstance = modeOrderingInstance;
  }

  // TODO : firstChild & acceptCFG not needed to visit the Id's
  // makes me think this shouldn't be a term.
  @Override
  public Term firstChild() {
    return null;
  }

  @Override
  public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
    // TODO : I'll need to figure out exactly how the CFG visit
    // works
    return succs;
  }

  public Node reconstruct(Node n, List<ModeOrder> orders) {
    ModesDecl modesDecl = (ModesDecl) n;
    modesDecl.orders(orders);
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

    ModeOrderingInstance modeOrderingInstance = 
      pandaTypeSystem.ModeOrderingInstance();

    for (ModeOrder modeOrder : this.orders()) {
      ModeType lower = pandaTypeSystem.ModeType(modeOrder.lower());
      ModeType upper = pandaTypeSystem.ModeType(modeOrder.upper());
      modeOrderingInstance.insertModeTypeOrdering(lower, upper);
    }

    modeOrderingInstance.buildModeTypeOrdering();
    this.modeOrderingInstance(modeOrderingInstance);
    return this;
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
      w.write("public static final int " + modeType.compiledIdentifier() + " = " + modeType.rank() + ";");
    }
    w.end();
    w.newline(0);

    w.begin(0);
    w.write("}"); w.newline();
    w.end();

  }

}
