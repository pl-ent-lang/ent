package panda.ast;

import panda.types.*;
import polyglot.ast.*;
import polyglot.types.*;
import polyglot.visit.*;

public class PandaCanonicalTypeNodeExt extends PandaExt {

  public Node typeCheck(TypeChecker tc) throws SemanticException {
    PandaTypeSystem ts = (PandaTypeSystem) tc.typeSystem();

    CanonicalTypeNode n = 
      (CanonicalTypeNode) superLang().typeCheck(this.node(), tc);
    if (!(n.type() instanceof ModeSubstType)) {
      return n;
    }

    ModeSubstType st = (ModeSubstType) n.type();
    if (!(st.baseType() instanceof PandaClassType)) {
      if (st.modeType() == ts.DynamicModeType()) {
        throw new SemanticException(
          "Non-class types can never receive the dynamic mode type!");
      }
      return n;
    }

    SemanticException error = 
      ts.checkModeSubst((PandaClassType) st.baseType(), st.modeTypeArgs());
    if (error != null) {
      throw error;
    }

    return n;
  }

}
