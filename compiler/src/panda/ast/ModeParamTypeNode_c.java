package panda.ast;

import panda.types.PandaTypeSystem;
import panda.types.ModeTypeVariable;

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

  private Id id;
  private List<ModeTypeNode> bounds = Collections.emptyList();

  public ModeParamTypeNode_c(Position pos, Id id, List<ModeTypeNode> bounds) {
    super(pos);
    this.id = id;
    this.bounds(bounds);
  }

  public Id id() {
    return this.id;
  }

  public void id(Id id) {
    this.id = id;
  }

  public List<ModeTypeNode> bounds() {
    return this.bounds;
  }

  public void bounds(List<ModeTypeNode> bounds) {
    if (bounds != null) {
      this.bounds = bounds;
    } else {
      this.bounds = Collections.emptyList();
    }
  }

  public Node reconstruct(List<ModeTypeNode> bounds) {
    this.bounds(bounds);
    return this;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    List<ModeTypeNode> bounds = visitList(this.bounds(), v);
    return reconstruct(bounds);
  } 

  @Override
  public Node buildTypes(TypeBuilder typeBuilder) throws SemanticException {
    PandaTypeSystem pandaTypeSystem = (PandaTypeSystem) typeBuilder.typeSystem();
    ModeTypeVariable modeTypeVar = 
      pandaTypeSystem.createModeTypeVariable(this.position(), this.id().id());
    TypeNode n = this;
    return n.type(modeTypeVar);
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
