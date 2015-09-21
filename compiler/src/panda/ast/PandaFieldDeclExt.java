package panda.ast;

import panda.types.*;
import panda.visit.*;
import panda.translate.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.visit.*;
import polyglot.translate.*;
import polyglot.ext.jl5.ast.*;
import polyglot.ext.jl5.types.*;

public class PandaFieldDeclExt extends PandaExt {

  @Override
  public Node disambiguate(AmbiguityRemover tr) throws SemanticException { 
    FieldDecl n = (FieldDecl) superLang().disambiguate(this.node(), tr);
    PandaClassType ct = (PandaClassType) tr.context().currentClass();
    if (n.declType() instanceof McaseType) {
      ct.hasMcaseFields(true);
    }
    return n;
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException { 
    PandaRewriter prw = (PandaRewriter) rw;
    JL5NodeFactory nf = (JL5NodeFactory) prw.to_nf();

    FieldDecl decl = (FieldDecl) this.node();
    JL5FieldDeclExt ext = (JL5FieldDeclExt) JL5Ext.ext(decl);

    FieldDecl n =
      nf.FieldDecl(
        decl.position(),
        PandaFlags.clearModesafe(decl.flags()),
        ext.annotationElems(),
        decl.type(),
        decl.id(),
        decl.init()
      );

    return n;
  }

}
