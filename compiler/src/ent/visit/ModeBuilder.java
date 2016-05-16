package ent.visit;

import ent.ast.*;

import polyglot.ast.*;
import polyglot.frontend.*;
import polyglot.visit.*;
import polyglot.types.*;
import polyglot.util.*;
import polyglot.qq.*;

public class ModeBuilder extends NodeVisitor {
  protected TypeSystem ts;
  protected NodeFactory nf;
  protected Job job;

  public ModeBuilder(Job job, TypeSystem ts, NodeFactory nf) {
    super(nf.lang());
    this.job = job;
    this.ts = ts;
    this.nf = nf;
  }

  public TypeSystem typeSystem() { return this.ts; }

  public ErrorQueue errorQueue() { return this.job.compiler().errorQueue(); }

  @Override
  public EntLang lang() {
    return (EntLang)super.lang();
  }

  @Override
  public NodeVisitor enter(Node n) {
    try {
      return this.lang().buildModesEnter(n, this);
    } catch (SemanticException e) {
      Position position = e.position();

      if (position == null) {
        position = n.position();
      }

      if (e.getMessage() != null) {
        errorQueue().enqueue(ErrorInfo.SEMANTIC_ERROR, e.getMessage(), position);
      }

      return this;
    }
  }

  @Override
  public Node leave(Node o, Node n, NodeVisitor v) {
    try {
      return this.lang().buildModes(n, (ModeBuilder)v);
    } catch (SemanticException e) {
      Position position = e.position();

      if (position == null) {
        position = n.position();
      }

      if (e.getMessage() != null) {
        errorQueue().enqueue(ErrorInfo.SEMANTIC_ERROR, e.getMessage(), position);
      }

      return n;
    }
  }
}
