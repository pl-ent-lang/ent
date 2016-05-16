package ent.ast;

import ent.translate.*;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.ext.jl5.ast.*;

public class EntEnumConstantDeclExt extends EntExt {

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter)rw;
    JL5NodeFactory nf = (JL5NodeFactory)prw.to_nf();

    EnumConstantDecl decl = (EnumConstantDecl)this.node();
    EnumConstantDeclExt ext = (EnumConstantDeclExt)JL5Ext.ext(decl);

    EnumConstantDecl n = nf.EnumConstantDecl(decl.position(),
                                             decl.flags(),
                                             ext.annotationElems(),
                                             decl.name(),
                                             decl.args(),
                                             decl.body());
    n = n.enumInstance(null);
    n = n.constructorInstance(null);
    n = n.type(null);

    return n;
  }
}
