package panda.visit;

import panda.ast.*;

import polyglot.ast.*;
import polyglot.frontend.*;
import polyglot.visit.*;
import polyglot.types.*;
import polyglot.qq.*;

public class TypePreserver extends ContextVisitor {
  protected QQ qq;
  protected TypeSystem to_ts;

  public TypePreserver(Job job, TypeSystem ts, NodeFactory nf, TypeSystem to_ts) {
    super(job, ts, nf);
    this.to_ts = to_ts;
  }

  public TypeSystem to_ts() {
    return this.to_ts;
  }

  public QQ qq() {
    return null;
  }

  @Override
  public PandaLang lang() {
    return (PandaLang) super.lang();
  }

  @Override
  public TypePreserver enterCall(Node n) {
    return this.lang().typePreserveEnter(n, this);
  }

  @Override
  public Node leaveCall(Node o, Node n, NodeVisitor v) {
    return this.lang().typePreserve(n, this);
  }

}
