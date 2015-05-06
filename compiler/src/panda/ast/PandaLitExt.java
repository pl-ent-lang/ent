package panda.ast;

import panda.types.PandaTypeSystem;

import polyglot.ast.Lit;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.visit.TypeChecker;
import polyglot.types.Type;

public class PandaLitExt extends PandaExt {

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {

    // TODO : This is one big place holder for infering mode types

    Lit n = (Lit) superLang().typeCheck(this.node(), tc);
    PandaTypeSystem ts = (PandaTypeSystem) tc.typeSystem();
    Type substType = ts.substModeType(n.type(), ts.bottomModeType());
    return n.type(substType);
  }

}
