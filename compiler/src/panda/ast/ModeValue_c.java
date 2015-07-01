package panda.ast;

import panda.translate.*;
import panda.types.*;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.visit.*;
import polyglot.util.*;

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
  protected <N extends ModeValue_c> N reconstruct(N n, ModeTypeNode mtNode) {
    n = this.modeTypeNode(n, mtNode);
    return n;
  } 

  @Override
  public Node visitChildren(NodeVisitor v) {
    ModeTypeNode mtNode = visitChild(this.modeTypeNode(), v);
    return this.reconstruct(this, mtNode);
  }

  @Override
  public Node copy(NodeFactory nf) {
    PandaNodeFactory pnf = (PandaNodeFactory) nf;
    return pnf.ModeValue(this.position(), this.modeTypeNode());
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
    return this.type(ts.createModeValueType(this.modeTypeNode().type()));
  }

  // TODO : Relaxing the restriction for ease implementation of Snapshot. Need to
  // revisit this.
  /*
  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    PandaContext c = (PandaContext) tc.context();
    if (!(c.currentCode() instanceof AttributeInstance)) {
      throw new SemanticException("Mode values can only be used inside an attributor.");
    }
    return this;
  }
  */

  public Node rewriteModeValue(ExtensionRewriter rw) throws SemanticException {
    PandaRewriter pwr = (PandaRewriter) rw;
    NodeFactory nf = pwr.nodeFactory();

    ModeValueType t = (ModeValueType) this.type();
    ModeType mt = (ModeType) t.mode();

    return mt.rewriteForLookup(nf);
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    PandaRewriter pwr = (PandaRewriter) rw;
    NodeFactory nf = pwr.nodeFactory();

    ModeValueType t = (ModeValueType) this.type();
    ModeType mt = (ModeType) t.mode();

    if (pwr.rewriteModeValue()) {
      return this.rewriteModeValue(rw);
    } else {
      return this;
    }
  }

}
