package ent.ast;

import ent.types.*;
import polyglot.ast.*;
import polyglot.frontend.*;
import polyglot.types.*;
import polyglot.visit.*;

public class EntCanonicalTypeNodeExt extends EntExt {

  public Node typeCheck(TypeChecker tc) throws SemanticException {
    EntTypeSystem ts = (EntTypeSystem)tc.typeSystem();

    CanonicalTypeNode n = (CanonicalTypeNode)superLang().typeCheck(this.node(), tc);
    if (!(n.type() instanceof ModeSubstType)) {
      return n;
    }

    ModeSubstType st = (ModeSubstType)n.type();
    if (!(st.baseType() instanceof EntClassType)) {
      if (st.modeType() == ts.DynamicModeType()) {
        throw new SemanticException("Non-class types can never receive the dynamic mode type!");
      }
      return n;
    }

    SemanticException error = ts.checkModeSubst((EntClassType)st.baseType(), st.modeTypeArgs());
    if (error != null) {
      throw error;
    }

    return n;
  }
}
