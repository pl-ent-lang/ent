package panda.ast;

import panda.types.*;
import panda.translate.*;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.visit.*;

public class PandaReturnExt extends PandaExt {

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    PandaTypeSystem ts = (PandaTypeSystem) tc.typeSystem();

    Return n = (Return) this.node();

    PandaContext c = (PandaContext) tc.context();
    ClassType ct = c.currentClass();
    CodeInstance ci = c.currentCode();
    if (ci instanceof AttributeInstance) {
      if (n.expr() == null || !(n.expr().type() instanceof ModeValueType)) {
        throw new SemanticException("Must return mode value in an attributor");
      }

      Type m = ((ModeValueType) n.expr().type()).mode();
      ((AttributeInstance) ci).addMode(m);

      return n;
    } else if (ci instanceof CopyInstance) {
      if (n.expr() == null || !ts.typeEquals(n.expr().type(), ct)) {
        throw new SemanticException("Must return " + ct + " in a copy");
      }

      return n;
    }
    
    return superLang().typeCheck(this.node(), tc);
  }

  @Override 
  public ExtensionRewriter extRewriteEnter(ExtensionRewriter rw) {
    PandaRewriter prw = (PandaRewriter) rw;
    return prw.rewriteModeValue(true);
  }


}
