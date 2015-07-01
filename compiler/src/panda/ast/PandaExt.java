package panda.ast;

import panda.visit.*;

import polyglot.ast.*;
import polyglot.util.*;

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

    @Override
    public boolean needsTypePreservation() {
      return this.needsTypePreservation;
    }

    public Node needsTypePreservation(boolean needsTypePreservation) {
      return this.needsTypePreservation(this.node(), needsTypePreservation);
    }

    protected <N extends Node> N needsTypePreservation(N n, boolean needsTypePreservation) {
      PandaExt ext = PandaExt.ext(n);
      if (ext.needsTypePreservation() == needsTypePreservation) return n;
      if (this.node() == n) {
        n = Copy.Util.copy(n);
        ext = PandaExt.ext(n);
      }
      ext.needsTypePreservation = needsTypePreservation;
      return n;
    }

}
