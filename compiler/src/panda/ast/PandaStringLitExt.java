package panda.ast;

import panda.types.PandaType;
import panda.types.PandaTypeSystem;

import polyglot.ast.Node;
import polyglot.ast.StringLit;
import polyglot.visit.TypeChecker;
import polyglot.types.SemanticException;

public class PandaStringLitExt extends PandaExt {

  @Override 
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    StringLit lit = (StringLit) superLang().typeCheck(this.node(), tc);
    PandaTypeSystem ts = (PandaTypeSystem) tc.typeSystem();
    PandaType type = ts.createPandaType(lit.type(), ts.bottomModeType());
    return lit.type(type);
  }

}
