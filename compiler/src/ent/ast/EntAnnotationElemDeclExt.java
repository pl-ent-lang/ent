package ent.ast;

import ent.translate.*;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.ext.jl5.ast.*;

public class EntAnnotationElemDeclExt extends EntExt {

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter)rw;
    JL5NodeFactory nf = (JL5NodeFactory)prw.to_nf();

    AnnotationElemDecl decl = (AnnotationElemDecl)this.node();

    AnnotationElemDecl n = nf.AnnotationElemDecl(
        decl.position(), decl.flags(), decl.type(), decl.id(), decl.defaultVal(), decl.javadoc());

    return n;
  }
}
