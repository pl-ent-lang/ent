package panda.ast;

import panda.translate.*;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.ext.jl5.ast.*;

public class PandaAnnotationElemDeclExt extends PandaExt {

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException { 
    PandaRewriter prw = (PandaRewriter) rw;
    JL5NodeFactory nf = (JL5NodeFactory) prw.to_nf();

    AnnotationElemDecl decl = (AnnotationElemDecl) this.node();

    AnnotationElemDecl n = 
      nf.AnnotationElemDecl(
        decl.position(),
        decl.flags(),
        decl.type(),
        decl.id(),
        decl.defaultVal(),
        decl.javadoc()
        );

    return n;
  }

}
