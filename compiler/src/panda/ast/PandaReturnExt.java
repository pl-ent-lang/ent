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


public class PandaReturnExt extends PandaExt {

  // Node Methods
  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    PandaContext c = (PandaContext) tc.context();
    CodeInstance ci = c.currentCode();
    Return n = (Return) this.node();
    if (ci instanceof AttributeInstance) {
      if (n.expr() == null || !(n.expr().type() instanceof ModeValueType)) {
        throw new SemanticException("Must return mode value in an attributor");
      }
      return n;
    } else {
      return superLang().typeCheck(this.node(), tc);
    }
  }

}
