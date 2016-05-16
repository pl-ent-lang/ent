package ent.ast;

import ent.types.*;
import ent.visit.*;
import ent.translate.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.visit.*;
import polyglot.translate.*;
import polyglot.ext.jl5.ast.*;
import polyglot.ext.jl5.types.*;

public class EntFieldDeclExt extends EntExt {

  @Override
  public Node disambiguate(AmbiguityRemover tr) throws SemanticException {
    FieldDecl n = (FieldDecl)superLang().disambiguate(this.node(), tr);
    EntClassType ct = (EntClassType)tr.context().currentClass();
    if (n.declType() instanceof McaseType) {
      ct.hasMcaseFields(true);
    }
    return n;
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter)rw;
    JL5NodeFactory nf = (JL5NodeFactory)prw.to_nf();

    FieldDecl decl = (FieldDecl)this.node();
    JL5FieldDeclExt ext = (JL5FieldDeclExt)JL5Ext.ext(decl);

    FieldDecl n = nf.FieldDecl(decl.position(),
                               EntFlags.clearModesafe(decl.flags()),
                               ext.annotationElems(),
                               decl.type(),
                               decl.id(),
                               decl.init());

    return n;
  }
}
