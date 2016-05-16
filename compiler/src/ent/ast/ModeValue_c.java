package ent.ast;

import ent.translate.*;
import ent.types.*;

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
  public ModeTypeNode modeTypeNode() { return this.modeTypeNode; }

  public ModeValue modeTypeNode(ModeTypeNode mtNode) { return this.modeTypeNode(this, mtNode); }

  public <N extends ModeValue_c> N modeTypeNode(N n, ModeTypeNode mtNode) {
    if (this.modeTypeNode() == mtNode)
      return n;
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
    EntNodeFactory pnf = (EntNodeFactory)nf;
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
    EntTypeSystem ts = (EntTypeSystem)tb.typeSystem();
    return this.type(ts.unknownType(this.position()));
  }

  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    if (!this.modeTypeNode().isDisambiguated()) {
      return this;
    }

    EntTypeSystem ts = (EntTypeSystem)sc.typeSystem();
    return this.type(ts.createModeValueType(this.modeTypeNode().type()));
  }

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    // TODO : Add in a restriction to prevent ModeValues from being used in invalid
    // contexts. ModeValues should be allowed inside an attributor and snapshot bounds
    // only.

    Context ctx = tc.context();
    EntTypeSystem ts = (EntTypeSystem)tc.typeSystem();

    ModeValueType t = (ModeValueType)this.type();

    if (ctx.currentCode() instanceof ConstructorInstance && t.containsVariable()) {
      EntConstructorInstance ci = (EntConstructorInstance)ctx.currentCode();
      EntClassType ct = (EntClassType)ci.container();
      ModeTypeVariable mtVar = (ModeTypeVariable)t.mode();
      if (mtVar.declaringClass() != null && ts.typeEquals(ct, mtVar.declaringClass())) {
        throw new SemanticException("Invalid use of class mode type variable inside constructor!");
      }
    }

    return this;
  }

  public Node rewriteModeValue(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter)rw;
    NodeFactory nf = prw.nodeFactory();
    Context c = prw.context();

    ModeValueType t = (ModeValueType)this.type();
    ModeType mt = (ModeType)t.mode();

    return mt.rewriteForLookup(nf, prw.toTypeSystem(), c);
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter)rw;
    NodeFactory nf = prw.nodeFactory();

    ModeValueType t = (ModeValueType)this.type();
    ModeType mt = (ModeType)t.mode();
    Context c = prw.context();

    if (prw.rewriteModeValue()) {
      return this.rewriteModeValue(rw);
    } else {
      return this;
    }
  }
}
