package panda.ast;

import panda.visit.*;

import polyglot.ast.*;
import polyglot.util.*;
import polyglot.translate.*;
import polyglot.types.*;

public class PandaExt extends Ext_c implements PandaOps {
  private static final long serialVersionUID = SerialVersionUID.generate();

  protected boolean needsTypePreservation = false;

  public static PandaExt ext(Node n) {
    Ext e = n.ext();
    while (e != null && !(e instanceof PandaExt)) {
        e = e.ext();
    }
    if (e == null) {
        throw new InternalCompilerError("No Panda extension object for node "
                + n + " (" + n.getClass() + ")", n.position());
    }
    return (PandaExt) e;
  }

  @Override
  public final PandaLang lang() {
    return PandaLang_c.instance;
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
