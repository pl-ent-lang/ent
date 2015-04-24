package panda.ast;

import panda.types.PandaType;
import panda.types.PandaTypeSystem;

import polyglot.ast.Node;
import polyglot.ast.CharLit;
import polyglot.visit.TypeChecker;
import polyglot.types.SemanticException;

public class PandaCharLitExt extends PandaExt {

  @Override 
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    CharLit lit = (CharLit) superLang().typeCheck(this.node(), tc);
    PandaTypeSystem ts = (PandaTypeSystem) tc.typeSystem();
    PandaType type = ts.createPandaType(lit.type(), ts.bottomModeType());
    return lit.type(type);
  }

}
