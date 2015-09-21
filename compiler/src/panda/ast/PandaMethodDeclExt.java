package panda.ast;

import panda.types.*;
import panda.visit.*;
import panda.translate.*;

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

public class PandaMethodDeclExt extends PandaProcedureDeclExt { 

  @Override
  protected int modeTypeVarStartIndex(PandaProcedureInstance pi) {
    PandaClassType ct = (PandaClassType) pi.container();
    
    return ct.modeTypeVars().size();
  }

  @Override
  protected boolean preserveTypes() {
    ProcedureDecl n = (ProcedureDecl) this.node();
    PandaProcedureInstance pi = (PandaProcedureInstance) n.procedureInstance();

    return !pi.modeTypeVars().isEmpty();
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException { 
    PandaRewriter prw = (PandaRewriter) rw;
    JL5NodeFactory nf = (JL5NodeFactory) prw.to_nf();
    QQ qq = prw.qq();

    MethodDecl decl = (MethodDecl) this.node();
    JL5MethodDeclExt ext = (JL5MethodDeclExt) JL5Ext.ext(decl);

    MethodDecl n =
      nf.MethodDecl(
        decl.position(),
        PandaFlags.clearModesafe(decl.flags()),
        ext.annotationElems(),
        decl.returnType(),
        decl.id(),
        decl.formals(),
        decl.throwTypes(),
        decl.body(),
        ext.typeParams()
        );

    return n;
  }

}
