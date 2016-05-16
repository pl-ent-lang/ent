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

public class EntMethodDeclExt extends EntProcedureDeclExt {

  @Override
  protected int modeTypeVarStartIndex(EntProcedureInstance pi) {
    EntClassType ct = (EntClassType)pi.container();

    // return ct.modeTypeVars().size();
    return 0;
  }

  @Override
  protected boolean preserveTypes() {
    ProcedureDecl n = (ProcedureDecl)this.node();
    EntProcedureInstance pi = (EntProcedureInstance)n.procedureInstance();

    return !pi.modeTypeVars().isEmpty();
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter)rw;
    JL5NodeFactory nf = (JL5NodeFactory)prw.to_nf();
    QQ qq = prw.qq();

    MethodDecl decl = (MethodDecl)this.node();
    JL5MethodDeclExt ext = (JL5MethodDeclExt)JL5Ext.ext(decl);

    MethodDecl n = nf.MethodDecl(decl.position(),
                                 EntFlags.clearModesafe(decl.flags()),
                                 ext.annotationElems(),
                                 decl.returnType(),
                                 decl.id(),
                                 decl.formals(),
                                 decl.throwTypes(),
                                 decl.body(),
                                 ext.typeParams());

    return n;
  }
}
