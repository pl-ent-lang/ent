package ent.ast;

import ent.types.*;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.util.*;
import polyglot.visit.*;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class ModeParamTypeNode_c extends TypeNode_c implements ModeParamTypeNode {

  protected Id id;
  protected List<ModeTypeNode> lowerBounds;
  protected List<ModeTypeNode> upperBounds;
  protected boolean isDynRecvr;

  public ModeParamTypeNode_c(Position pos,
                             Id id,
                             boolean isDynRecvr,
                             List<ModeTypeNode> lowerBounds,
                             List<ModeTypeNode> upperBounds) {
    super(pos);
    this.id = id;
    this.isDynRecvr = isDynRecvr;
    this.lowerBounds = CollectionUtil.nonNullList(lowerBounds);
    this.upperBounds = CollectionUtil.nonNullList(upperBounds);
  }

  // Property Methods
  public Id id() { return this.id; }

  public boolean isDynRecvr() { return this.isDynRecvr; }

  public List<ModeTypeNode> lowerBounds() { return this.lowerBounds; }

  protected <N extends ModeParamTypeNode_c> N lowerBounds(N n, List<ModeTypeNode> lb) {
    if (CollectionUtil.equals(this.lowerBounds(), lb))
      return n;
    n = this.copyIfNeeded(n);
    n.lowerBounds = ListUtil.copy(lb, true);
    return n;
  }

  public List<ModeTypeNode> upperBounds() { return this.upperBounds; }

  protected <N extends ModeParamTypeNode_c> N upperBounds(N n, List<ModeTypeNode> ub) {
    if (CollectionUtil.equals(this.upperBounds(), ub))
      return n;
    n = this.copyIfNeeded(n);
    n.upperBounds = ListUtil.copy(ub, true);
    return n;
  }

  @Override
  public String name() {
    return this.id().id();
  }

  protected <N extends ModeParamTypeNode_c>
      N reconstruct(N n, List<ModeTypeNode> lb, List<ModeTypeNode> ub) {
    n = this.lowerBounds(n, lb);
    n = this.upperBounds(n, ub);
    return n;
  }

  // Node Methods
  @Override
  public Node visitChildren(NodeVisitor v) {
    List<ModeTypeNode> lb = visitList(this.lowerBounds(), v);
    List<ModeTypeNode> ub = visitList(this.upperBounds(), v);
    Node n = this.reconstruct(this, lb, ub);
    return n;
  }

  @Override
  public Node copy(NodeFactory nf) {
    EntNodeFactory pnf = (EntNodeFactory)nf;
    return pnf.ModeParamTypeNode(this.position(),
                                 this.id(),
                                 this.isDynRecvr(),
                                 ListUtil.copy(this.lowerBounds(), true),
                                 ListUtil.copy(this.upperBounds(), true));
  }

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    EntTypeSystem ts = (EntTypeSystem)tb.typeSystem();

    ModeTypeVariable mtVar = null;
    if (ts.createdModeTypes().containsKey(this.name())) {
      ModeType mt = ts.createdModeTypes().get(this.name());
      List<Type> bounds = new ArrayList<>();
      bounds.add(mt);

      mtVar = ts.createModeTypeVariable(this.position(), "_");
      mtVar.upperBounds(bounds);
      mtVar.lowerBounds(bounds);
    } else {
      mtVar = ts.createModeTypeVariable(this.position(), this.id().id());
    }
    return this.type(mtVar);
  }

  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    ModeTypeVariable mtVar = (ModeTypeVariable)this.type();
    EntTypeSystem ts = (EntTypeSystem)sc.typeSystem();

    boolean ambiguous = false;
    List<Type> upperBoundTypes = new ArrayList<Type>();
    for (ModeTypeNode tn : this.upperBounds()) {
      if (!tn.isDisambiguated()) {
        ambiguous = true;
      }
      upperBoundTypes.add(tn.type());
    }

    List<Type> lowerBoundTypes = new ArrayList<Type>();
    for (ModeTypeNode tn : this.lowerBounds()) {
      if (!tn.isDisambiguated()) {
        ambiguous = true;
      }
      lowerBoundTypes.add(tn.type());
    }

    if (ambiguous) {
      return this;
    }

    if (!this.lowerBounds().isEmpty() || !this.upperBounds().isEmpty()) {
      mtVar.upperBounds(upperBoundTypes);
      mtVar.lowerBounds(lowerBoundTypes);
    }

    if (this.isDynRecvr()) {
      mtVar.isDynRecvr(true);
    }

    if (!mtVar.inferBounds()) {
      throw new SemanticException("Unable to infer bounds for mode type variable");
    }

    if (mtVar.upperBound() == ts.DynamicModeType() && mtVar.isDynRecvr()) {
      throw new SemanticException(
          "Mode type variable cannot be a dynamic reciever with a dynamic mode upper bound.");
    }

    return this;
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    return null;
  }

  @Override
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {}
}
