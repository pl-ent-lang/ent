package ent.ast;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.util.*;
import polyglot.visit.*;

import java.util.List;

public class McaseFieldDecl_c extends Term_c implements McaseFieldDecl {
  protected Id field;
  protected Expr init;

  public McaseFieldDecl_c(Position pos, Id field, Expr init) {
    super(pos);
    this.field = field;
    this.init = init;
  }

  // Property Methods
  public Id field() { return this.field; }

  public Expr init() { return this.init; }

  protected <N extends McaseFieldDecl_c> N init(N n, Expr init) {
    if (this.init() == init)
      return n;
    n = this.copyIfNeeded(n);
    n.init = init;
    return n;
  }

  // Node Methods
  protected <N extends McaseFieldDecl_c> N reconstruct(N n, Expr init) {
    n = this.init(n, init);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    Expr init = visitChild(this.init(), v);
    return this.reconstruct(this, init);
  }

  @Override
  public boolean isDisambiguated() {
    return this.init().isDisambiguated();
  }

  /*
  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    System.err.format("Type of init: %s\n", this.init().type());
    System.err.format("Disambig of init: %s\n", this.init().isDisambiguated());
    System.err.format("Init: %s %s\n", this.init(), this.init().getClass());
    return this;
  }
  */

  @Override
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
    print(this.field(), w, tr);
    w.write(":");
    print(this.init(), w, tr);
  }

  // Term Methods
  @Override
  public Term firstChild() {
    return null;
  }

  @Override
  public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
    return succs;
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    return this.init();
  }
}
