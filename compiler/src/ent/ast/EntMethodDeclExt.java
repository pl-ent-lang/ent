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
import polyglot.ext.jl5.types.*;
import polyglot.util.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Collections;

public class EntMethodDeclExt extends EntProcedureDeclExt {

  CodeDecl attrDecl;
  boolean proxy;

  public CodeDecl attrDecl() {
    return (CodeDecl) this.attrDecl;
  }

  public Node attrDecl(CodeDecl attrDecl) {
    return this.attrDecl(this.node(), attrDecl);
  }

  public <N extends Node> N attrDecl(N n, CodeDecl attrDecl) {
    EntMethodDeclExt ext = (EntMethodDeclExt) EntExt.ext(n);
    if (ext.attrDecl == attrDecl) 
      return n;
    if (this.node() == n) {
      n = Copy.Util.copy(n);
      ext = (EntMethodDeclExt) EntExt.ext(n);
    }
    ext.attrDecl = attrDecl;
    return n;
  } 

  public boolean proxy() {
    return (boolean) this.proxy;
  }

  public Node proxy(boolean proxy) {
    return this.proxy(this.node(), proxy);
  }

  public <N extends Node> N proxy(N n, boolean proxy) {
    EntMethodDeclExt ext = (EntMethodDeclExt) EntExt.ext(n);
    if (ext.proxy == proxy) 
      return n;
    if (this.node() == n) {
      n = Copy.Util.copy(n);
      ext = (EntMethodDeclExt) EntExt.ext(n);
    }
    ext.proxy = proxy;
    return n;
  } 

  protected Node reconstruct(Node n, CodeDecl attrDecl) {
    n = this.attrDecl(n, attrDecl);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    Node n = super.visitChildren(v);
    List<ModeParamTypeNode> modeParams = visitList(this.modeParams(), v);
    CodeDecl attrDecl = visitChild(this.attrDecl(), v);
    return this.reconstruct(n, attrDecl);
  }

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
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    MethodDecl n = (MethodDecl)superLang().typeCheck(this.node(), tc);
    EntMethodDeclExt ext = (EntMethodDeclExt)EntExt.ext(n);

    /*
    if (this.attrDecl() != null) {
      CodeDecl attrDecl = ((AttributeDecl) this.attrDecl()).formals(n.formals());
      n = (MethodDecl) this.attrDecl(attrDecl);
    }
    */

    // TODO : Require dynamic method to have attributor implemented
    // and vice-versa

    return n;
  }


  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter)rw;
    JL5NodeFactory nf = (JL5NodeFactory)prw.to_nf();
    JL5TypeSystem ts = (JL5TypeSystem)prw.toTypeSystem();
    QQ qq = prw.qq();

    MethodDecl decl = (MethodDecl)this.node();
    EntMethodDeclExt entext = (EntMethodDeclExt) EntExt.ext(decl);
    JL5MethodDeclExt ext = (JL5MethodDeclExt)JL5Ext.ext(decl);

    if (!entext.proxy()) {
        return nf.MethodDecl(decl.position(),
                             EntFlags.clearModesafe(decl.flags()),
                             ext.annotationElems(),
                             decl.returnType(),
                             decl.id(),
                             decl.formals(),
                             decl.throwTypes(),
                             decl.body(),
                             ext.typeParams());
    }

    List<Formal> formals = new ArrayList<>();
    formals.add(
      nf.Formal(
        Position.COMPILER_GENERATED,
        Flags.NONE,
        nf.CanonicalTypeNode(
          Position.COMPILER_GENERATED,
          ts.Int()
          ),
        "senderMode"
        )
      );

    for (Formal f : decl.formals()) {
      formals.add(f);
    }


    List<Stmt> stmts = new ArrayList<>();

    EntMethodInstance mi = (EntMethodInstance) decl.methodInstance();

    List<Expr> attributeArgs = new ArrayList<>();
    for (int i = 0; i < decl.formals().size()-1; i++) {
      attributeArgs.add(
        nf.AmbExpr(Position.COMPILER_GENERATED, decl.formals().get(i).id())
        ); 
    }

    List<Expr> methodSnapshotArgs = new ArrayList<>();
    methodSnapshotArgs.add(
      nf.Call(
        Position.COMPILER_GENERATED,
        nf.This(Position.COMPILER_GENERATED),
        Collections.<TypeNode>emptyList(),
        nf.Id(
          Position.COMPILER_GENERATED,
          String.format("ENT_attribute_%s", mi.name())
          ),
        attributeArgs
        )
      );
    methodSnapshotArgs.add(
      nf.AmbExpr(
        Position.COMPILER_GENERATED,
        nf.Id(
          Position.COMPILER_GENERATED,
          "senderMode"
          )
        )
      ); 

    Stmt s1 =
      nf.LocalDecl(
        Position.COMPILER_GENERATED,
        Flags.NONE,
        Collections.<AnnotationElem>emptyList(),
        nf.CanonicalTypeNode(
          Position.COMPILER_GENERATED,
          ts.Int()
          ),
        nf.Id(
          Position.COMPILER_GENERATED,
          "methodMode"
          ),
        nf.Call(
          Position.COMPILER_GENERATED,
          nf.AmbReceiver(
            Position.COMPILER_GENERATED,
            nf.Id(
              Position.COMPILER_GENERATED,
              "ENT_Snapshot"
              )
            ),
          Collections.<TypeNode>emptyList(),
          nf.Id(
            Position.COMPILER_GENERATED,
            "methodSnapshot"
            ),
            methodSnapshotArgs
          )
        );

    List<Expr> setModeVarArgs = new ArrayList<>();
    setModeVarArgs.add(
      nf.IntLit(Position.COMPILER_GENERATED, IntLit.INT, 0)
      );
    setModeVarArgs.add(
      nf.AmbExpr(
        Position.COMPILER_GENERATED,
        nf.Id(
          Position.COMPILER_GENERATED,
          "methodMode"
          )
        )
      ); 

    Stmt s2 =
      nf.Eval(
        Position.COMPILER_GENERATED,
        nf.Call(
          Position.COMPILER_GENERATED,
          nf.AmbReceiver(
            Position.COMPILER_GENERATED,
            nf.Id(
              Position.COMPILER_GENERATED,
              "ENT_this"
              )
            ),
          Collections.<TypeNode>emptyList(),
          nf.Id(
            Position.COMPILER_GENERATED,
            "setModeVar"
            ), 
          setModeVarArgs
          )
        );

    List<Expr> forwardArgs = new ArrayList<>();
    for (Formal f : decl.formals()) {
      forwardArgs.add(
        nf.AmbExpr(Position.COMPILER_GENERATED, f.id())
        );
    }

    Call forward =
      nf.Call(
        Position.COMPILER_GENERATED,
        nf.This(Position.COMPILER_GENERATED),
        Collections.<TypeNode>emptyList(),
        nf.Id(Position.COMPILER_GENERATED, mi.name()), 
        forwardArgs
        );
    Stmt s3 = null;
    if (mi.returnType().isVoid()) {
      s3 = nf.Eval(Position.COMPILER_GENERATED, forward);
    } else {
      s3 = nf.Return(Position.COMPILER_GENERATED,forward);
    }

    stmts.add(s1);
    stmts.add(s2);
    stmts.add(s3);

    return nf.MethodDecl(decl.position(),
                         EntFlags.clearModesafe(decl.flags()),
                         ext.annotationElems(),
                         decl.returnType(),
                         decl.id(),
                         formals,
                         decl.throwTypes(),
                         decl.body().statements(stmts),
                         ext.typeParams());

  }
}
