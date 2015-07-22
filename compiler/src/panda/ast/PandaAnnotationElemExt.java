package panda.ast;

import panda.translate.*;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.ext.jl5.ast.*;

public class PandaAnnotationElemExt extends PandaExt {

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException { 
    PandaRewriter prw = (PandaRewriter) rw;
    JL5NodeFactory nf = (JL5NodeFactory) prw.to_nf();

    AnnotationElem elem = (AnnotationElem) this.node();

    AnnotationElem n =
      nf.NormalAnnotationElem(
        elem.position(),
        elem.typeName(),
        elem.elements()
        );

    return n;
  }

}
