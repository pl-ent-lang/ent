package panda.ast;

import polyglot.ast.Call;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.visit.TypeChecker;

public class PandaCallExt extends PandaExt {

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    Call n = (Call) superLang().typeCheck(this.node(), tc);
    return n;
  }

}
