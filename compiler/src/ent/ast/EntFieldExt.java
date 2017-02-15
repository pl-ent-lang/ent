package ent.ast;

import ent.types.*;
import ent.translate.*;
import ent.visit.*;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.util.*;
import polyglot.visit.*;

public class EntFieldExt extends EntExt {

  public Node typeCheckCommon(TypeChecker tc) throws SemanticException {
    Field n = (Field)superLang().typeCheck(this.node(), tc);
    EntTypeSystem ts = (EntTypeSystem)tc.typeSystem();
    EntContext ctx = (EntContext)tc.context();
    EntClassType ct = (EntClassType)ctx.currentClassScope();

    // NOTE: No target means this is a static call, kick it up
    if (n.target() == null) {
      return n;
    }

    Type t = n.target().type();
    if (!(t instanceof ModeSubstType)) {
      return n;
    }

    ModeSubstType mt = (ModeSubstType)t;
    // Disallow dynamic type seperately for better diagnostics
    // NOTE: Formally, we allow this communication.
    /*
    if (mt.modeType() == ts.DynamicModeType() && !EntFlags.isModesafe(n.fieldInstance().flags())) {
      throw new SemanticException(
          "Dynamic mode type cannot receive messages. Resolve using snapshot.");
    }
    */

    if (ctx.inStaticContext()) {
      // Kick up to super lang for now
      return n;
    }

    // Field is valid if the first mode type variable upper bound is greater
    // than the recievers mode type.
    ModeTypeVariable mtThis = ct.modeTypeVars().get(0);
    if (!ts.isSubtype(mt.modeType(), mtThis) && mtThis.upperBound() != ts.WildcardModeType()) {
      throw new SemanticException("Cannot send message to " + t + " from mode " +
                                  mtThis.upperBound() + ".");
    }

    return n;
  }

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    Field n = (Field)this.typeCheckCommon(tc);

    if ((n.type() instanceof McaseType)) {
      // Special check for Mcases
      EntTypeSystem ts = (EntTypeSystem)tc.typeSystem();
      EntContext ctx = (EntContext)tc.context();
      EntClassType ct = (EntClassType)ctx.currentClassScope();
      if (ctx.currentCode() instanceof ConstructorInstance &&
          ts.typeEquals(n.fieldInstance().container(), ct)) {
        throw new SemanticException("Cannot use mcase expression inside containing constructor");
      }
    }

    return n;
  }

  @Override
  public Node typePreserve(TypePreserver tp) {
    Field f = (Field)this.node();
    NodeFactory nf = tp.nodeFactory();

    if (!(f.type() instanceof McaseType)) {
      return f;
    }

    Expr recv = null;
    if (f.target() instanceof Expr) {
      recv = (Expr)f.target();
    } else {
      recv = nf.This(Position.COMPILER_GENERATED);
    }

    Node n =
        nf.ArrayAccess(Position.COMPILER_GENERATED,
                       f,
                       nf.Call(Position.COMPILER_GENERATED,
                               nf.AmbReceiver(Position.COMPILER_GENERATED,
                                              nf.Id(Position.COMPILER_GENERATED, "ENT_Runtime")),
                               nf.Id(Position.COMPILER_GENERATED, "getObjMode"),
                               recv,
                               nf.IntLit(Position.COMPILER_GENERATED, IntLit.INT, 0)));

    return n;
  }
}
