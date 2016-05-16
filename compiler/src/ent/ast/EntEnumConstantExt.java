package ent.ast;

import ent.translate.*;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.ext.jl5.ast.*;

public class EntEnumConstantExt extends EntExt {

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter)rw;
    JL5NodeFactory nf = (JL5NodeFactory)prw.to_nf();

    EnumConstant cons = (EnumConstant)this.node();

    EnumConstant n = nf.EnumConstant(cons.position(), cons.target(), cons.id());

    return n;
  }
}
