package ent.ast;

import ent.translate.*;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.ext.jl5.ast.*;

public class EntAnnotationElemExt extends EntExt {

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter)rw;
    JL5NodeFactory nf = (JL5NodeFactory)prw.to_nf();

    AnnotationElem elem = (AnnotationElem)this.node();

    AnnotationElem n = nf.NormalAnnotationElem(elem.position(), elem.typeName(), elem.elements());

    return n;
  }
}
