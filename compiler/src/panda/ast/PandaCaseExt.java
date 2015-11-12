package panda.ast;

import polyglot.ast.*;
import polyglot.ext.jl5.ast.*;
import polyglot.ext.jl5.types.*;
import polyglot.ext.jl7.ast.*;
import polyglot.ext.jl7.types.*;
import polyglot.types.*;
import polyglot.visit.*;

public class PandaCaseExt extends PandaExt implements JL5CaseOps {

  @Override
  public Case resolveCaseLabel(TypeChecker tc, Type switchType) throws SemanticException {
    JL5TypeSystem ts = (JL5TypeSystem) tc.typeSystem();

    Case c = (Case) this.node();

    if (switchType.isClass() && ts.String().typeEquals(switchType)) {
      return c;
    }
    return J7Lang_c.lang(pred()).resolveCaseLabel(c, tc, switchType);
  }


}
