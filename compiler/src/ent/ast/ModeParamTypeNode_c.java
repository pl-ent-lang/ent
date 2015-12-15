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
  protected List<ModeTypeNode> bounds;
  protected boolean isDynRecvr;

  public ModeParamTypeNode_c(
      Position pos, 
      Id id, 
      boolean isDynRecvr,
      List<ModeTypeNode> bounds) {
    super(pos);
    this.id = id;
    this.isDynRecvr = isDynRecvr;
    this.bounds = CollectionUtil.nonNullList(bounds);
  }

  // Property Methods
  public Id id() {
    return this.id;
  }

  public boolean isDynRecvr() {
    return this.isDynRecvr;
  }

  public List<ModeTypeNode> bounds() {
    return this.bounds;
  }

  protected <N extends ModeParamTypeNode_c> N bounds(N n, List<ModeTypeNode> bounds) {
    if (CollectionUtil.equals(this.bounds(),bounds)) return n;
    n = this.copyIfNeeded(n);
    n.bounds = ListUtil.copy(bounds, true);
    return n;
  }

  @Override
  public String name() {
    return this.id().id();
  }

  protected <N extends ModeParamTypeNode_c> N reconstruct(N n, List<ModeTypeNode> bounds) {
    n = this.bounds(n, bounds);
    return n;
  }

  // Node Methods
  @Override
  public Node visitChildren(NodeVisitor v) {
    List<ModeTypeNode> bounds = visitList(this.bounds(), v);
    return this.reconstruct(this, bounds);
  } 

  @Override
  public Node copy(NodeFactory nf) {
    EntNodeFactory pnf = (EntNodeFactory) nf;
    return pnf.ModeParamTypeNode(
                this.position(), 
                this.id(), 
                this.isDynRecvr(),
                ListUtil.copy(this.bounds(), true)
                );
  }


  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    EntTypeSystem ts = (EntTypeSystem) tb.typeSystem();
    ModeTypeVariable mtVar = 
      ts.createModeTypeVariable(this.position(), this.id().id());
    return this.type(mtVar);
  }

  // LAST 


  // Possible params
  // ? -> X <= high, Y <= ?

  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    ModeTypeVariable mtVar = (ModeTypeVariable) this.type();
    EntTypeSystem ts = (EntTypeSystem) sc.typeSystem();

    boolean ambiguous = false;
    List<Type> boundTypes = new ArrayList<Type>();
    for (ModeTypeNode tn : this.bounds()) {
      if (!tn.isDisambiguated()) {
        ambiguous = true;
      }
      boundTypes.add(tn.type());
    }
    
    if (ambiguous) {
      return this;
    }

    mtVar.bounds(boundTypes);

    if (this.isDynRecvr()) {
      mtVar.isDynRecvr(true);
    }

    if (!mtVar.inferUpperBound()) {
      throw new SemanticException(
          "Unable to infer upper bound for mode type variable");
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
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
  }

}
