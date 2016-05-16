package ent.ast;

import ent.translate.*;
import ent.types.*;

import polyglot.ast.*;
import polyglot.qq.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.util.*;
import polyglot.visit.*;

import java.util.List;
import java.util.Collections;

public class CopyDecl_c extends Term_c implements CopyDecl {

  protected Block body;
  protected CopyInstance copyInstance;

  public CopyDecl_c(Position pos, Block body) {
    super(pos, null);
    this.body = body;
  }

  // Property Methods
  public Block body() { return this.body; }

  public CopyDecl body(Block body) { return this.body(this, body); }

  public <N extends CopyDecl_c> N body(N n, Block body) {
    if (this.body() == body)
      return n;
    n = this.copyIfNeeded(n);
    n.body = body;
    return n;
  }

  public CopyInstance copyInstance() { return this.copyInstance; }

  public CopyDecl copyInstance(CopyInstance copyInstance) {
    return this.copyInstance(this, copyInstance);
  }

  public <N extends CopyDecl_c> N copyInstance(N n, CopyInstance copyInstance) {
    if (this.copyInstance() == copyInstance)
      return n;
    n = this.copyIfNeeded(n);
    n.copyInstance = copyInstance;
    return n;
  }

  // CodeDecl Methods
  @Override
  public Term codeBody() {
    return this.body();
  }

  @Override
  public CodeInstance codeInstance() {
    return this.copyInstance();
  }

  protected <N extends CopyDecl_c> N reconstruct(N n, Block body) {
    n = this.body(n, body);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    Block body = visitChild(this.body(), v);
    return this.reconstruct(this, body);
  }

  @Override
  public Node copy(NodeFactory nf) {
    EntNodeFactory pnf = (EntNodeFactory)nf;
    return pnf.CopyDecl(this.position(), this.body());
  }

  @Override
  public Context enterScope(Context c) {
    c = c.pushCode(this.copyInstance());
    return c;
  }

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    EntTypeSystem ts = (EntTypeSystem)tb.typeSystem();
    EntParsedClassType ct = (EntParsedClassType)tb.currentClass();

    if (ct == null) {
      // Big error, attributor needs to be part of a class only
    }

    if (ct.copyInstance() != null) {
      throw new SemanticException("Only one copy may be defined per class.");
    }

    CopyInstance ci = ts.createCopyInstance(this.position(), ct);
    ct.copyInstance(ci);

    return this.copyInstance(this, ci);
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter)rw;
    NodeFactory nf = (NodeFactory)prw.nodeFactory();
    QQ qq = prw.qq();

    ClassMember md =
        qq.parseMember("public ENT_Attributable ENT_copy() { %LS }", this.body().statements());

    return md;
  }

  @Override
  public void dump(CodeWriter w) {
    super.dump(w);

    if (this.copyInstance() != null) {
      w.allowBreak(4, " ");
      w.begin(0);
      w.write("(instance " + this.copyInstance() + ")");
      w.end();
    }
  }

  // Term Method
  @Override
  public Term firstChild() {
    return this.body();
  }

  @Override
  public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
    if (this.body() != null) {
      v.visitCFG(this.body(), this, EXIT);
    }

    return succs;
  }

  // ClassMember Methods
  @Override
  public MemberInstance memberInstance() {
    return this.copyInstance();
  }
}
