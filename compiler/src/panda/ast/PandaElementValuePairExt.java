package panda.ast;

import panda.translate.*;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.ext.jl5.ast.*;

public class PandaElementValuePairExt extends PandaExt {

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException { 
    PandaRewriter prw = (PandaRewriter) rw;
    JL5NodeFactory nf = (JL5NodeFactory) prw.to_nf();

    ElementValuePair pair = (ElementValuePair) this.node();

    ElementValuePair n =
      nf.ElementValuePair(
        pair.position(),
        pair.id(),
        pair.value()
        );

    return n;
  }

}
