package panda.ast;

import panda.types.*;

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

  public ModeParamTypeNode_c(Position pos, Id id, List<ModeTypeNode> bounds) {
    super(pos);
    this.id = id;
    this.bounds = CollectionUtil.nonNullList(bounds);
  }

  // Property Methods
  public Id id() {
    return this.id;
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
    PandaNodeFactory pnf = (PandaNodeFactory) nf;
    return pnf.ModeParamTypeNode(
                this.position(), 
                this.id(), 
                ListUtil.copy(this.bounds(), true)
                );
  }


  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    PandaTypeSystem ts = (PandaTypeSystem) tb.typeSystem();
    ModeTypeVariable mtVar = 
      ts.createModeTypeVariable(this.position(), this.id().id());
    return this.type(mtVar);
  }

  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    List<Type> boundTypes = new ArrayList<Type>();
    for (ModeTypeNode tn : this.bounds()) {
      boundTypes.add(tn.type());
    }
    ModeTypeVariable t = (ModeTypeVariable) this.type();
    t.bounds(boundTypes);

    if (!t.inferUpperBound()) {
      throw new SemanticException("Unable to infer upper bound for mode type variable");
    }

    return super.disambiguate(sc);
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    return null;
  }

  @Override
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
  }

}
