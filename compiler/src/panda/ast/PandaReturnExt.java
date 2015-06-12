package panda.ast;

import panda.types.AttributeInstance;
import panda.types.PandaContext;
import panda.types.ModeValueType;

import polyglot.ast.Node;
import polyglot.ast.Return;
import polyglot.types.Type;
import polyglot.types.SemanticException;
import polyglot.types.CodeInstance;
import polyglot.visit.TypeChecker;
import polyglot.visit.AmbiguityRemover;

public class PandaReturnExt extends PandaExt {

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    Return n = (Return) this.node();

    PandaContext c = (PandaContext) tc.context();
    CodeInstance ci = c.currentCode();
    if (ci instanceof AttributeInstance) {
      if (n.expr() == null || !(n.expr().type() instanceof ModeValueType)) {
        throw new SemanticException("Must return mode value in an attributor");
      }

      Type m = ((ModeValueType) n.expr().type()).mode();
      ((AttributeInstance) ci).addMode(m);

      return n;
    }
    
    return superLang().typeCheck(this.node(), tc);
  }

}
