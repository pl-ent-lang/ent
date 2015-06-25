package panda.ast;

import panda.ast.*;
import panda.translate.*;
import panda.types.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.util.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.util.*;
import polyglot.visit.*;
import polyglot.qq.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ModesDecl_c extends ClassDecl_c implements ModesDecl {
  private static final long serialVersionUID = SerialVersionUID.generate();

  protected List<ModeOrder> orders;
  protected ModeOrderingInstance oi;

  public ModesDecl_c(Position pos, List<ModeOrder> orders) {
    super(pos, null, null, null, null, null);
    this.orders = orders;
  }

  // Property Methods
  public List<ModeOrder> orders() {
    return this.orders;
  }

  public <N extends ModesDecl_c> N orders(N n, List<ModeOrder> orders) {
    if (CollectionUtil.equals(n.orders(), orders)) return n;
    n = this.copyIfNeeded(n);
    n.orders = ListUtil.copy(orders, true);
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
  public Node copy(NodeFactory nf) {
    PandaNodeFactory pnf = (PandaNodeFactory) nf;
    return pnf.ModesDecl(this.position(), ListUtil.copy(this.orders(), true));
  }

  @Override
  public Context enterChildScope(Node child, Context c) {
    return c;
  }

  @Override
  public NodeVisitor buildTypesEnter(TypeBuilder tb) throws SemanticException {
    return tb;
  }

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    PandaTypeSystem ts = (PandaTypeSystem) tb.typeSystem();

    ModeOrderingInstance oi = ts.createModeOrderingInstance();

    for (ModeOrder modeOrder : this.orders()) {
      ModeType l = ts.createModeType(modeOrder.lower());
      ModeType u = ts.createModeType(modeOrder.upper());
      oi.insertModeTypeOrdering(l, u);
    }

    oi.buildModeTypeOrdering();
    ModesDecl n = this.modeOrderingInstance(oi);

    // Give it a dummy class type so the rst of the passes work, side effect
    // of inheriting from ClassDecl_c for the rewrite
    ParsedClassType ct = ts.createClassType();
    ct.setMembersAdded(true);
    ct.setSupertypesResolved(true);
    return n.type(ct);
  }

  @Override
  public boolean isDisambiguated() {
    return true;
  } 

  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    return this;
  }

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    return this;
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    PandaRewriter prw = (PandaRewriter) rw;
    PandaTypeSystem ts = (PandaTypeSystem) prw.typeSystem();
    NodeFactory nf = prw.nodeFactory();
    QQ qq = prw.qq();

    List<ClassMember> members = new ArrayList<>();
    for (Map.Entry<String, ModeType> e : ts.createdModeTypes().entrySet()) {
      ModeType mt = e.getValue();

      Expr expr = null;
      if (mt == ts.DynamicModeType() || mt == ts.WildcardModeType()) {
        expr = 
          nf.Field(
            Position.COMPILER_GENERATED,
            nf.AmbReceiver(
              Position.COMPILER_GENERATED,
              nf.Id(Position.COMPILER_GENERATED, "PANDA_Modes")
              ),
            nf.Id(Position.COMPILER_GENERATED, mt.compileExpr())
            );
      } else {
        expr = nf.IntLit(Position.COMPILER_GENERATED, IntLit.INT, Integer.parseInt(mt.compileExpr()));
      }

      ClassMember fd = 
        qq.parseMember("public static final int %s = %E;", mt.compileId(), expr);

      members.add(fd);
    }

    ClassDecl cd = qq.parseDecl("class PandaMode { %LM }", members);

    return cd;
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
