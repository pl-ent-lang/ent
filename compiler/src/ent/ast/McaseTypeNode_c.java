package ent.ast;

import ent.types.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.util.*;
import polyglot.visit.*;

public class McaseTypeNode_c extends TypeNode_c implements McaseTypeNode {

  protected TypeNode base;

  public McaseTypeNode_c(Position pos, TypeNode base) {
    super(pos);
    this.base = base;
  }

  // Property Methods
  protected TypeNode base() { return this.base; }

  protected <N extends McaseTypeNode_c> N base(N n, TypeNode base) {
    if (n.base() == base)
      return n;
    n = this.copyIfNeeded(n);
    n.base = base;
    return n;
  }

  // Node Methods
  protected <N extends McaseTypeNode_c> N reconstruct(N n, TypeNode base) {
    n = this.base(n, base);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    TypeNode base = visitChild(this.base(), v);
    return this.reconstruct(this, base);
  }

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    return this.type(tb.typeSystem().unknownType(this.position()));
  }

  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    if (!this.base().isDisambiguated()) {
      return this;
    }

    // Create new mode case type
    EntTypeSystem ts = (EntTypeSystem)sc.typeSystem();
    Type tt = ts.createMcaseType(this.base().type());

    return this.type(ts.createMcaseType(this.base().type()));
  }

  @Override
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
    w.write("mcase<");
    tr.lang().prettyPrint(this.base(), w, tr);
    w.write(">");
  }
}
