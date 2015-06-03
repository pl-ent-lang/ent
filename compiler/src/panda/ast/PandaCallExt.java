package panda.ast;

import polyglot.ast.Call;
import polyglot.ast.Node;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.visit.TypeChecker;

import panda.types.PandaTypeSystem;
import panda.types.PandaClassType;
import panda.types.ModeTypeVariable;
import panda.types.PandaParsedClassType;
import panda.types.ModeType;
import panda.types.ModeSubstType;
import panda.types.PandaContext;

public class PandaCallExt extends PandaExt {

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    Call c = (Call) this.node();

    Type t = c.target().type();
    if (!(t instanceof ModeSubstType)) {
      System.out.println("WARNING: call on target " + c.target() + " on type " + t);
      return superLang().typeCheck(this.node(), tc);
    }

    PandaContext ctx = (PandaContext) tc.context();
    PandaClassType ct = (PandaClassType) ctx.currentClassScope();

    System.out.println("CT: " + ct.getClass());

    ModeSubstType mt = (ModeSubstType) t;

    // Call is valid if the first mode type variable upper bound is greater
    // than the recievers mode type.
    ModeTypeVariable mtThis = ct.modeTypeVars().get(0);
    System.out.println("mtThis ModeType: " + mtThis.upperBound());      
    System.out.println("recv ModeType: " + mt.modeType());

    // Need to move this instanceof into mode types directly
    ModeType recvMode = null;
    if (mt.modeType() instanceof ModeType) {
      recvMode = (ModeType) mt.modeType();
    } else {
      recvMode = ((ModeTypeVariable) mt.modeType()).upperBound();
    }

    PandaTypeSystem ts = (PandaTypeSystem) tc.typeSystem();
    if (!ts.isSubtypeModes(recvMode, mtThis.upperBound())) {
      throw new SemanticException("Cannot send message to " + t + " from mode " + mtThis.upperBound());
    }

    return superLang().typeCheck(this.node(), tc);
  }

}
