package panda.ast;

import panda.types.PandaType;
import panda.types.PandaTypeSystem;

import polyglot.ast.Node;
import polyglot.ast.NullLit;
import polyglot.visit.TypeChecker;
import polyglot.types.SemanticException;

public class PandaNullLitExt extends PandaExt {

  @Override 
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    NullLit lit = (NullLit) superLang().typeCheck(this.node(), tc);
    PandaTypeSystem ts = (PandaTypeSystem) tc.typeSystem();
    PandaType type = ts.createPandaType(lit.type(), ts.bottomModeType());
    return lit.type(type);
  }

}
