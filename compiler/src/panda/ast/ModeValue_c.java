package panda.ast;

import panda.types.AttributeInstance;
import panda.types.PandaTypeSystem;
import panda.types.Mode;

import polyglot.ast.Lang;
import polyglot.ast.Lit;
import polyglot.ast.Lit_c;
import polyglot.ast.Node;
import polyglot.ast.Term;

import polyglot.types.SemanticException;

import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeChecker;
import polyglot.visit.AmbiguityRemover;
import polyglot.util.Position;

import panda.types.PandaContext;

import java.util.List;

public class ModeValue_c extends Lit_c implements ModeValue {

  protected ModeTypeNode modeTypeNode;

  public ModeValue_c(Position pos, ModeTypeNode mtNode) {
    super(pos);
    this.modeTypeNode = mtNode;
  }

  // Property Methods
  public ModeTypeNode modeTypeNode() {
    return this.modeTypeNode;
  }

  public ModeValue modeTypeNode(ModeTypeNode mtNode) {
    return this.modeTypeNode(this, mtNode);
  }

  public <N extends ModeValue_c> N modeTypeNode(N n, ModeTypeNode mtNode) {
    if (this.modeTypeNode() == mtNode) return n;
    n = this.copyIfNeeded(n);
    n.modeTypeNode = mtNode;
    return n;
  }

  // Node Methods
  @Override
  public Node visitChildren(NodeVisitor v) {
    ModeTypeNode mtNode = visitChild(this.modeTypeNode(), v);
    return this.reconstruct(this, mtNode);
  }

  public <N extends ModeValue_c> N reconstruct(N n, ModeTypeNode mtNode) {
    n = this.modeTypeNode(n, mtNode);
    return n;
  } 

  // Term Method
  @Override
  public Term firstChild() {
    return this.modeTypeNode();
  }

  @Override
  public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
    if (this.modeTypeNode() != null) {
      v.visitCFG(this.modeTypeNode(), this, EXIT);
    }
    return succs;
  }


  // Lit Methods
  @Override
  public Object constantValue(Lang lang) {
    return null;
  }

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    PandaTypeSystem ts = (PandaTypeSystem) tb.typeSystem();
    return this.type(ts.unknownType(this.position()));
  }

  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    if (!this.modeTypeNode().isDisambiguated()) {
      return this;
    }

    PandaTypeSystem ts = (PandaTypeSystem) sc.typeSystem();
    return this.type(ts.createModeValueType((Mode)this.modeTypeNode().type()));
  }

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    PandaContext c = (PandaContext) tc.context();
    if (!(c.currentCode() instanceof AttributeInstance)) {
      throw new SemanticException("Mode values can only be used inside an attributor.");
    }
    return this;
  }

}
