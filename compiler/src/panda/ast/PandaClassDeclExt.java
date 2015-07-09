package panda.ast;

import panda.translate.*;
import panda.types.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.util.*;
import polyglot.visit.*;
import polyglot.translate.*;
import polyglot.qq.*;

import polyglot.ext.jl5.ast.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PandaClassDeclExt extends PandaExt {

  protected List<ModeParamTypeNode> modeParams = Collections.emptyList();

  // Property Methods
  public List<ModeParamTypeNode> modeParams() {
    return this.modeParams;
  }

  public Node modeParams(List<ModeParamTypeNode> modeParams) {
    return this.modeParams(this.node(), modeParams);
  }

  public <N extends Node> N modeParams(N n, List<ModeParamTypeNode> modeParams) {
    PandaClassDeclExt ext = (PandaClassDeclExt) PandaExt.ext(n);
    if (CollectionUtil.equals(ext.modeParams,modeParams)) return n;
    if (this.node() == n) {
      n = Copy.Util.copy(n);
      ext = (PandaClassDeclExt) PandaExt.ext(n);
    }
    ext.modeParams = ListUtil.copy(modeParams, true); 
    return n;
  }

  // Node Methods
  protected <N extends Node> N reconstruct(N n, List<ModeParamTypeNode> modeParams) {
    n = this.modeParams(n, modeParams);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    Node n = superLang().visitChildren(this.node(), v);
    List<ModeParamTypeNode> modeParams = visitList(this.modeParams(), v);
    return this.reconstruct(n, modeParams);
  }

  @Override
  public Context enterChildScope(Node child, Context c) {
    PandaContext ctx = (PandaContext) superLang().enterChildScope(this.node(), child, c);
    for (ModeParamTypeNode t : this.modeParams()) {
      ctx.addModeTypeVariable((ModeTypeVariable) t.type());
    }
    return ctx;
  }

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    ClassDecl n = (ClassDecl) superLang().buildTypes(this.node(), tb);

    PandaTypeSystem ts = (PandaTypeSystem) tb.typeSystem();
    PandaParsedClassType ct = (PandaParsedClassType) n.type();

    if (this.modeParams() == null || this.modeParams().isEmpty()) {
      return n;
    }

    int dbInd = 0;
    List<ModeTypeVariable> mtVars = new ArrayList<>(this.modeParams().size());
    Set<String> mtVarCheck = new HashSet<>();
    for (ModeParamTypeNode m : this.modeParams()) {
      // Check and catch duplicate error as early as possible
      if (mtVarCheck.contains(m.name())) {
        throw new SemanticException("Duplicate mode type variable declaration.",
                                    n.position());
      }
      mtVarCheck.add(m.name());

      ModeTypeVariable mtVar = (ModeTypeVariable) m.type();
      mtVar.declaringClass(ct);
      mtVar.index(dbInd);
      mtVars.add(mtVar);
      ++dbInd;
    }
    ct.modeTypeVars(mtVars);

    return n;
  } 

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException { 
    PandaRewriter prw = (PandaRewriter) rw;
    JL5NodeFactory nf = (JL5NodeFactory) prw.to_nf();
    QQ qq = prw.qq();

    ClassDecl decl = (ClassDecl) this.node();
    JL5ClassDeclExt ext = (JL5ClassDeclExt) JL5Ext.ext(decl);
    PandaParsedClassType ct = (PandaParsedClassType) decl.type();

    // Manual translation to JL5
    ClassDecl n = 
      nf.ClassDecl(
        decl.position(),
        decl.flags(),
        ext.annotationElems(),
        decl.id(),
        decl.superClass(),
        decl.interfaces(),
        decl.body(),
        ext.paramTypes()
        );

    // 1. Generate PANDA_Attributable interface
    List<TypeNode> interfaces = new ArrayList<>(decl.interfaces());
    if (ct.hasAttribute()) {
      // 1.1. Generate PANDA_Attributable
      interfaces.add(qq.parseType("PANDA_Attributable")); 
      n = n.interfaces(interfaces);
    }

    // 2. Generate default constructor if there is not one
    if (ct.hasAttribute() && !ct.hasCopy()) {
      boolean genDef = true;
      for (ConstructorInstance ci : ct.constructors()) {
        if (ci.formalTypes().isEmpty()) {
          genDef = false;
          break;
        }
      }
      if (genDef) {
        ClassBody body = n.body();
        List<ClassMember> members = new ArrayList<>(body.members());
        members.add(qq.parseMember("public %s(PANDA_Closure PANDA_this) { }", decl.name()));
        body = body.members(members);
        n = n.body(body);
      }
    }

    // 3. Generate a builtin PANDA_copy method
    if (ct.hasAttribute() && !ct.hasCopy()) {
      List<Stmt> stmts = new ArrayList<>();

      // 3.1. Create a new expression for a shallow copy
      stmts.add(
        qq.parseStmt(
          "%T PANDA_ld = new %T(null);", 
          qq.parseType(decl.name()),
          qq.parseType(decl.name())
          )
        ); 

      // 3.2. Copy each member of the class manually
      for (ClassMember m : decl.body().members()) {
        if (!(m instanceof FieldDecl)) {
          continue;
        }
        FieldDecl fd = (FieldDecl) m;
        if (fd.flags().isStatic()) {
          continue;
        }
        stmts.add(qq.parseStmt("PANDA_ld.%s = this.%s;", fd.name(), fd.name()));
      }

      // 3.3. Simply return the shallow copy
      stmts.add(qq.parseStmt("return PANDA_ld;"));

      ClassMember md = qq.parseMember("public PANDA_Attributable PANDA_copy() { %LS }", stmts);

      // Handle the immutable part of polyglot
      ClassBody body = n.body();
      List<ClassMember> members = new ArrayList<>(body.members());
      members.add(md);
      body = body.members(members);
      n = n.body(body);
    } 

    return n;
  }

}
