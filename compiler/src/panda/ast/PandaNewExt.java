package panda.ast;

import panda.translate.*;
import panda.types.*;
import panda.visit.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.visit.*;
import polyglot.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PandaNewExt extends PandaExt implements NewOps {

  protected Map<ModeTypeVariable, Type> infModeTypes;

  @Override
  public New node() {
    return (New) super.node();
  }

  protected Map<ModeTypeVariable, Type> infModeTypes() {
    return this.infModeTypes;
  }

  protected New infModeTypes(Map<ModeTypeVariable, Type> infModeTypes) {
    return this.infModeTypes(this.node(), infModeTypes);
  }

  protected <N extends New> N infModeTypes(N n, Map<ModeTypeVariable, Type> infModeTypes) {
    PandaNewExt ext = (PandaNewExt) PandaExt.ext(n);
    if (ext.infModeTypes == infModeTypes) return n;
    if (this.node() == n) {
      n = Copy.Util.copy(n);
      ext = (PandaNewExt) PandaExt.ext(n);
    }
    ext.infModeTypes = infModeTypes;
    return n;
  }

  // ProcedureCallOps
  @Override
  public void printArgs(CodeWriter w, PrettyPrinter tr) {
    superLang().printArgs(this.node(), w, tr);
  }

  // ExprOps
  @Override
  public boolean constantValueSet(Lang lang) {
    return superLang().constantValueSet(node(), lang);
  }

  @Override
  public boolean isConstant(Lang lang) {
    return superLang().isConstant(node(), lang);
  }

  @Override
  public Object constantValue(Lang lang) {
    return superLang().constantValue(node(), lang);
  }


  @Override
  public TypeNode findQualifiedTypeNode(AmbiguityRemover ar, ClassType outer,
            TypeNode objectType) throws SemanticException {
    return superLang().findQualifiedTypeNode(this.node(), ar, outer, objectType);
  }

  @Override
  public Expr findQualifier(AmbiguityRemover ar, ClassType ct) throws SemanticException {
    return superLang().findQualifier(this.node(), ar, ct);
  }

  @Override
  public void typeCheckFlags(TypeChecker tc) throws SemanticException {
    superLang().typeCheckFlags(this.node(), tc);
  }

  @Override
  public void typeCheckNested(TypeChecker tc) throws SemanticException {
    superLang().typeCheckNested(this.node(), tc);
  }

  @Override
  public void printQualifier(CodeWriter w, PrettyPrinter tr) {
    superLang().printQualifier(this.node(), w, tr);
  }

  @Override
  public void printShortObjectType(CodeWriter w, PrettyPrinter tr) {
    superLang().printShortObjectType(this.node(), w, tr);
  } 

  @Override
  public void printBody(CodeWriter w, PrettyPrinter tr) {
    superLang().printBody(this.node(), w, tr);
  }

  @Override
  public ClassType findEnclosingClass(Context c, ClassType ct) {
    if (ct instanceof ModeSubstType) {
      ct = (ClassType) ((ModeSubstType) ct).baseType();
    }
    return superLang().findEnclosingClass(this.node(), c, ct);
  } 

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException { 
    New n = (New) superLang().typeCheck(this.node(), tc);
    PandaTypeSystem ts = (PandaTypeSystem) tc.typeSystem();

    PandaProcedureInstance pi = 
      (PandaProcedureInstance) n.constructorInstance();

    if (pi.modeTypeVars().isEmpty()) {
      return n;
    }

    List<Type> argTypes = new ArrayList<>();
    for (Expr e : n.arguments()) {
      argTypes.add(e.type());
    }
    ModeSubst subst = ts.inferModeTypeArgs(pi.baseInstance(), argTypes, null);

    PandaNewExt ext = (PandaNewExt) PandaExt.ext(n);
    return ext.infModeTypes(subst.modeTypeMap());
  }

  public boolean needsPandaClosure() {
    return this.infModeTypes() != null;
  }
  
  public boolean needsModeTablePreservation(TypePreserver tp) {
    ModeSubstType st = (ModeSubstType) this.node().type();
    PandaTypeSystem ts = (PandaTypeSystem) tp.typeSystem();
    return st.modeType() == ts.DynamicModeType() || 
           ((PandaClassType) st.baseType()).hasMcaseFields();
  }

  @Override
  public Node typePreserve(TypePreserver tp) {
    New n = this.node();

    PandaNodeFactory nf = (PandaNodeFactory) tp.nodeFactory();
    PandaTypeSystem ts = (PandaTypeSystem) tp.typeSystem();
    Context ctx = tp.context();

    if (!(n.type() instanceof ModeSubstType)) {
      return n;
    }

    // Steps to preserve types
    // 1. Create a PANDA_Closure for method mode type variables
    // 2. Rewrite new A() to new A(new PANDA_Closure()) if non
    // builtin constructor.
    // 3. Rewrite new A() to ((A) PANDA_Runtime.putObj(new A(), constants))
    if (this.needsPandaClosure()) {
      List<Expr> args = new ArrayList<>(n.arguments());
      args.add(
        PandaBuilder.instance().buildPandaClosure(
          nf,
          tp.toTypeSystem(),
          ((PandaProcedureInstance) n.procedureInstance()).modeTypeVars(),
          this.infModeTypes(),
          ctx
          )
        ); 
      n = n.arguments(args);
    }

    if (this.needsModeTablePreservation(tp)) { 
      return
        PandaBuilder.instance().
          buildModeTableObjectWithCast(
            nf, 
            tp.toTypeSystem(), 
            n, 
            ((ModeSubstType) n.type()).modeTypeArgs(), 
            ctx
            );
    } else {
      return n;
    }
  }
}
