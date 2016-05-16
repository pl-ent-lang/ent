package ent.ast;

import ent.visit.*;

import polyglot.ast.*;
import polyglot.util.*;
import polyglot.translate.*;
import polyglot.types.*;

public class EntExt extends Ext_c implements EntOps {
  private static final long serialVersionUID = SerialVersionUID.generate();

  protected boolean needsTypePreservation = false;

  public static EntExt ext(Node n) {
    Ext e = n.ext();
    while (e != null && !(e instanceof EntExt)) {
      e = e.ext();
    }
    if (e == null) {
      throw new InternalCompilerError(
          "No Ent extension object for node " + n + " (" + n.getClass() + ")", n.position());
    }
    return (EntExt)e;
  }

  @Override
  public final EntLang lang() {
    return EntLang_c.instance;
  }

  @Override
  public ModeBuilder buildModesEnter(ModeBuilder mb) throws SemanticException {
    return mb;
  }

  @Override
  public Node buildModes(ModeBuilder mb) throws SemanticException {
    return this.node();
  }

  @Override
  public TypePreserver typePreserveEnter(TypePreserver tp) {
    return tp;
  }

  @Override
  public Node typePreserve(TypePreserver tp) {
    return this.node();
  }
}
