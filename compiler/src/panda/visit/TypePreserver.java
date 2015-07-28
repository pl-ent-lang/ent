package panda.visit;

import panda.ast.*;

import polyglot.ast.*;
import polyglot.frontend.*;
import polyglot.visit.*;
import polyglot.types.*;
import polyglot.qq.*;

public class TypePreserver extends ContextVisitor {
  protected QQ qq;
  protected TypeSystem toTypeSystem;

  public TypePreserver(Job job, TypeSystem fromTs, NodeFactory fromNf, TypeSystem toTs) {
    super(job, fromTs, fromNf);
    this.toTypeSystem = toTs;
  }

  public TypeSystem toTypeSystem() {
    return this.toTypeSystem;
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
