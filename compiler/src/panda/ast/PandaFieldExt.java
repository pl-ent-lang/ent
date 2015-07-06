package panda.ast;

import panda.types.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.util.*;
import polyglot.visit.*;

public class PandaFieldExt extends PandaExt {

  // TODO : I do not like how this is pretty much cut and paste from PandaCallExt.
  // I could add in a parent type to avoid the duplicate, maybe fix later.
  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException { 
    Field n = (Field) this.node();
    PandaTypeSystem ts = (PandaTypeSystem) tc.typeSystem();
    PandaContext ctx = (PandaContext) tc.context();
    PandaClassType ct = (PandaClassType) ctx.currentClassScope();

    Type t = n.target().type();
    if (!(t instanceof ModeSubstType)) {
      if (n.target() instanceof Special) {
        return superLang().typeCheck(n, tc);
      } else {
        throw new InternalCompilerError(n.position(), 
            "Field target does not have a mode substituted type and is not 'this'");
      }
    } 

    // Disallow dynamic type seperately for better diagnostics
    ModeSubstType mt = (ModeSubstType) t;
    if (mt.modeType() == ts.DynamicModeType()) {
      throw new SemanticException("Dynamic mode type cannot receive messages. Resolve using snapshot.");
    }

    if (ctx.inStaticContext()) {
      // Kick up to super lang for now
      return superLang().typeCheck(this.node(), tc);
    }

    // Call is valid if the first mode type variable upper bound is greater
    // than the recievers mode type.
    ModeTypeVariable mtThis = ct.modeTypeVars().get(0);
    if (!ts.isSubtype(mt.modeType(), mtThis) && mtThis.upperBound() != ts.WildcardModeType()) {
      throw new SemanticException("Cannot send message to " + t + " from mode " + mtThis.upperBound() + ".");
    }

    return superLang().typeCheck(n, tc);
  }

}
