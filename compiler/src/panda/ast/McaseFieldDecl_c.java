package panda.ast;

import polyglot.ast.Expr;
import polyglot.ast.Id;
import polyglot.ast.Node;
import polyglot.ast.Term;
import polyglot.ast.Term_c;
import polyglot.util.Position;
import polyglot.util.CodeWriter;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;

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
  public Id field() {
    return this.field;
  }

  public Expr init() {
    return this.init;
  }

  protected <N extends McaseFieldDecl_c> N init(N n, Expr init) {
    if (this.init() == init) return n;
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



}
