package panda.ast;

import panda.types.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.visit.*;

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

}
