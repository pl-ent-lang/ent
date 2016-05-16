package ent.ast;

import ent.types.*;
import ent.translate.*;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.visit.*;

public class EntReturnExt extends EntExt {

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    Return n = (Return)this.node();
    EntTypeSystem ts = (EntTypeSystem)tc.typeSystem();
    EntContext ctx = (EntContext)tc.context();
    ClassType ct = ctx.currentClass();
    CodeInstance ci = ctx.currentCode();

    if (ci instanceof AttributeInstance) {
      if (n.expr() == null || !(n.expr().type() instanceof ModeValueType)) {
        throw new SemanticException("Must return mode value in an attributor");
      }

      Type m = ((ModeValueType)n.expr().type()).mode();
      ((AttributeInstance)ci).addMode(m);

      return n;
    } else if (ci instanceof CopyInstance) {
      if (n.expr() == null || !ts.typeEquals(n.expr().type(), ct)) {
        throw new SemanticException("Must return " + ct + " in a copy");
      }

      return n;
    } else {
      return superLang().typeCheck(n, tc);
    }
  }

  @Override
  public ExtensionRewriter extRewriteEnter(ExtensionRewriter rw) {
    EntRewriter prw = (EntRewriter)rw;
    return prw.rewriteModeValue(true);
  }
}
