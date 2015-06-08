package panda.ast;

import panda.types.Mode;
import panda.types.PandaTypeSystem;
import panda.types.ModeTypeVariable;
import panda.util.PandaUtil;


import polyglot.ast.Id;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.ast.TypeNode_c;
import polyglot.types.Type;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.util.CodeWriter;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeChecker;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class ModeParamTypeNode_c extends TypeNode_c implements ModeParamTypeNode {

  protected Id id;
  protected List<ModeTypeNode> bounds;

  public ModeParamTypeNode_c(Position pos, Id id, List<ModeTypeNode> bounds) {
    super(pos);
    this.id = id;
    this.bounds = PandaUtil.nonEmptyList(bounds);
  }

  // Property Methods
  public Id id() {
    return this.id;
  }

  public List<ModeTypeNode> bounds() {
    return this.bounds;
  }

  protected <N extends ModeParamTypeNode_c> N bounds(N n, List<ModeTypeNode> bounds) {
    if (this.bounds() == bounds) return n;
    n = this.copyIfNeeded(n);
    n.bounds = PandaUtil.nonEmptyList(bounds);
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
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    PandaTypeSystem ts = (PandaTypeSystem) tb.typeSystem();
    ModeTypeVariable mtVar = 
      ts.createModeTypeVariable(this.position(), this.id().id());
    TypeNode n = this;
    return n.type(mtVar);
  }

  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    List<Mode> boundTypes = new ArrayList<Mode>();
    for (ModeTypeNode tn : this.bounds()) {
      boundTypes.add((Mode) tn.type());
    }
    ModeTypeVariable t = (ModeTypeVariable) this.type();
    t.bounds(boundTypes);

    if (!t.inferUpperBound()) {
      throw new SemanticException("Unable to infer upper bound for mode type variable");
    }

    return super.disambiguate(sc);
  }

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    // Check that bounds are not a problem
    
    // Let the mode type try and find an upper bound if possible, otherwise
    // we error.
    
    return super.typeCheck(tc);
  }

  @Override
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
  }

}
