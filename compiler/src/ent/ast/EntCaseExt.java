package ent.ast;

import polyglot.ast.*;
import polyglot.ext.jl5.ast.*;
import polyglot.ext.jl5.types.*;
import polyglot.ext.jl7.ast.*;
import polyglot.ext.jl7.types.*;
import polyglot.util.*;
import polyglot.types.*;
import polyglot.visit.*;

public class EntCaseExt extends EntExt implements JL5CaseOps {

  private Case j5resolveCaseLabel(TypeChecker tc, Type switchType) throws SemanticException {
    JL5TypeSystem ts = (JL5TypeSystem)tc.typeSystem();
    JL5NodeFactory nf = (JL5NodeFactory)tc.nodeFactory();

    Case c = (Case) node();
    Expr expr = c.expr();

    if (expr == null) {
      return c;
    } else if (switchType.isClass() && !isNumericSwitchType(switchType, ts)) {
      // must be an enum...
      if (expr.type().isCanonical()) {
        // we have already resolved the expression
        EnumConstant ec = (EnumConstant)expr;
        return c.value(ec.enumInstance().ordinal());
      } else if (expr instanceof EnumConstant) {
        Field ec = (Field)expr;
        EnumConstant ext = (EnumConstant)ec;
        EnumInstance ei = ts.findEnumConstant(switchType.toReference(), ec.name());
        ec = (Field)ext.enumInstance(ei);
        ec = (Field)ec.type(ei.type());
        return c.expr(ec).value(ei.ordinal());
      } else if (expr instanceof AmbExpr) {
        AmbExpr amb = (AmbExpr)expr;
        EnumInstance ei = ts.findEnumConstant(switchType.toReference(), amb.name());
        Receiver r = nf.CanonicalTypeNode(Position.compilerGenerated(), switchType);
        Field e = nf.EnumConstant(expr.position(), r, amb.id());
        e = (Field)((EnumConstant)e).enumInstance(ei);
        e = (Field)e.type(ei.type());
        return c.expr(e).value(ei.ordinal());
      } else {
        throw new InternalCompilerError("Unexpected case label " + expr);
      }
    }

    // switch type is not a class.
    Case n = c;

    if (expr.isTypeChecked()) {
      // all good, nothing to do.
    } else if (expr instanceof AmbExpr) {
      AmbExpr amb = (AmbExpr)expr;
      // Disambiguate and typecheck
      Expr e =
          (Expr)tc.nodeFactory().disamb().disambiguate(amb, tc, expr.position(), null, amb.id());
      e = (Expr)e.visit(tc);
      n = n.expr(e);
    } else {
      // try type checking it
      n = c.expr((Expr)c.expr().visit(tc));
    }

    if (!tc.lang().constantValueSet(n.expr(), tc.lang())) {
      // Not ready yet; pass will get rerun.
      return n;
    }
    if (tc.lang().isConstant(n.expr(), tc.lang())) {
      Object o = tc.lang().constantValue(n.expr(), tc.lang());
      if (o instanceof Number && !(o instanceof Long) && !(o instanceof Float) &&
          !(o instanceof Double)) {
        return n.value(((Number)o).longValue());
      } else if (o instanceof Character) {
        return n.value(((Character)o).charValue());
      }
    }
    throw new SemanticException(
        "Case label must be an integral constant or an unqualified enum value.", node().position());
  }

  public boolean isNumericSwitchType(Type switchType, JL5TypeSystem ts) {
    if (ts.Char().typeEquals(switchType) || ts.wrapperClassOfPrimitive(ts.Char()).typeEquals(switchType)) {
      return true;
    }
    if (ts.Byte().typeEquals(switchType) || ts.wrapperClassOfPrimitive(ts.Byte()).typeEquals(switchType)) {
      return true;
    }
    if (ts.Short().typeEquals(switchType) ||
        ts.wrapperClassOfPrimitive(ts.Short()).typeEquals(switchType)) {
      return true;
    }
    if (ts.Int().typeEquals(switchType) || ts.wrapperClassOfPrimitive(ts.Int()).typeEquals(switchType)) {
      return true;
    }
    return false;
  }

  private Case j7resolveCaseLabel(TypeChecker tc, Type switchType) throws SemanticException {
    JL5TypeSystem ts = (JL5TypeSystem) tc.typeSystem();

    Case c = (Case) this.node();

    if (switchType.isClass() && ts.String().typeEquals(switchType)) {
      return c;
    }

    return this.j5resolveCaseLabel(tc, switchType);
  }

  @Override
  public Case resolveCaseLabel(TypeChecker tc, Type switchType) throws SemanticException {
    JL5TypeSystem ts = (JL5TypeSystem)tc.typeSystem();

    Case c = (Case)this.node();

    if (switchType.isClass() && ts.String().typeEquals(switchType)) {
      return c;
    }
    return this.j7resolveCaseLabel(tc, switchType);
  }
}
