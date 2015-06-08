package panda.ast;

import panda.types.Mode;
import panda.types.PandaTypeSystem;

import polyglot.ast.Lit;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.visit.TypeChecker;
import polyglot.types.Type;

import java.util.ArrayList;
import java.util.List;

public class PandaLitExt extends PandaExt {

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {

    // TODO : This is one big place holder for infering mode types

    Lit n = (Lit) superLang().typeCheck(this.node(), tc);
    PandaTypeSystem ts = (PandaTypeSystem) tc.typeSystem();

    List<Mode> mtArgs = new ArrayList<Mode>();
    mtArgs.add(ts.WildcardModeType());
    Type substType = ts.createModeSubst(n.type(), mtArgs);


    return n.type(substType);
  }

}
