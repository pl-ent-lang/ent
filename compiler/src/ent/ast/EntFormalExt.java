package ent.ast;

import ent.translate.*;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.ext.jl5.ast.*;

public class EntFormalExt extends EntExt {

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter)rw;
    JL5NodeFactory nf = (JL5NodeFactory)prw.to_nf();

    Formal f = (Formal)this.node();
    JL5FormalExt ext = (JL5FormalExt)JL5Ext.ext(f);
    Formal n =
        nf.Formal(f.position(), f.flags(), ext.annotationElems(), f.type(), f.id(), ext.isVarArg());

    return n;
  }
}
