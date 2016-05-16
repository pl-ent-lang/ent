package ent.ast;

import ent.types.*;
import ent.visit.*;
import ent.translate.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.visit.*;
import polyglot.qq.*;
import polyglot.translate.*;
import polyglot.ext.jl5.ast.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EntConstructorDeclExt extends EntProcedureDeclExt {

  @Override
  protected int modeTypeVarStartIndex(EntProcedureInstance pi) {
    return 0;
  }

  @Override
  protected boolean preserveTypes() {
    ProcedureDecl n = (ProcedureDecl)this.node();
    EntProcedureInstance pi = (EntProcedureInstance)n.procedureInstance();
    EntClassType ct = (EntClassType)pi.container();

    return !ct.isImplicitModeTypeVar() && !pi.modeTypeVars().isEmpty();
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter)rw;
    JL5NodeFactory nf = (JL5NodeFactory)prw.to_nf();
    QQ qq = prw.qq();

    ConstructorDecl decl = (ConstructorDecl)this.node();
    JL5ConstructorDeclExt ext = (JL5ConstructorDeclExt)JL5Ext.ext(decl);

    ConstructorDecl n = nf.ConstructorDecl(decl.position(),
                                           decl.flags(),
                                           ext.annotationElems(),
                                           decl.id(),
                                           decl.formals(),
                                           decl.throwTypes(),
                                           decl.body(),
                                           ext.typeParams());

    return n;
  }
}
