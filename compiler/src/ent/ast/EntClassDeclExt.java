package ent.ast;

import ent.translate.*;
import ent.types.*;
import ent.visit.*;

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
import java.util.LinkedList;

public class EntClassDeclExt extends EntExt {

  protected List<ModeParamTypeNode> modeParams = Collections.emptyList();

  // Property Methods
  public List<ModeParamTypeNode> modeParams() { return this.modeParams; }

  public Node modeParams(List<ModeParamTypeNode> modeParams) {
    return this.modeParams(this.node(), modeParams);
  }

  public <N extends Node> N modeParams(N n, List<ModeParamTypeNode> modeParams) {
    EntClassDeclExt ext = (EntClassDeclExt)EntExt.ext(n);
    if (CollectionUtil.equals(ext.modeParams, modeParams))
      return n;
    if (this.node() == n) {
      n = Copy.Util.copy(n);
      ext = (EntClassDeclExt)EntExt.ext(n);
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
    List<ModeParamTypeNode> modeParams = visitList(this.modeParams(), v);
    Node n = superLang().visitChildren(this.node(), v);
    return this.reconstruct(n, modeParams);
  }

  @Override
  public Context enterChildScope(Node child, Context c) {
    EntContext ctx = (EntContext)superLang().enterChildScope(this.node(), child, c);
    for (ModeParamTypeNode t : this.modeParams()) {
      ctx.addModeTypeVariable((ModeTypeVariable)t.type());
    }
    return ctx;
  }

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    ClassDecl n = (ClassDecl)superLang().buildTypes(this.node(), tb);

    EntTypeSystem ts = (EntTypeSystem)tb.typeSystem();
    EntParsedClassType ct = (EntParsedClassType)n.type();

    if (this.modeParams() == null || this.modeParams().isEmpty()) {
      return n;
    }

    int dbInd = 0;
    List<ModeTypeVariable> mtVars = new ArrayList<>(this.modeParams().size());
    Set<String> mtVarCheck = new HashSet<>();
    for (ModeParamTypeNode m : this.modeParams()) {
      // If we are a declared mode type variable, say high, it is syntactic sugar for high <= _ <=
      // high.

      // Check and catch duplicate error as early as possible
      if (mtVarCheck.contains(m.name())) {
        throw new SemanticException("Duplicate mode type variable declaration.", n.position());
      }
      mtVarCheck.add(m.name());

      ModeTypeVariable mtVar = (ModeTypeVariable)m.type();
      mtVar.declaringClass(ct);
      mtVar.index(dbInd);
      mtVars.add(mtVar);
      ++dbInd;
    }

    ct.modeTypeVars(mtVars);

    return n;
  }

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    ClassDecl n = (ClassDecl)superLang().typeCheck(this.node(), tc);
    EntClassDeclExt ext = (EntClassDeclExt)EntExt.ext(n);

    for (int i = 1; i < ext.modeParams().size(); i++) {
      ModeParamTypeNode m = ext.modeParams().get(i);
      ModeTypeVariable mtVar = (ModeTypeVariable)m.type();
      if (mtVar.isDynRecvr()) {
        throw new SemanticException("Only the first mode type variable may be a dynamic reciever",
                                    m.position());
      }
    }

    EntParsedClassType ct = (EntParsedClassType)n.type();

    // NOTE : We force classes to implement
    if (ct.hasDynamicRecv() && !ct.flags().isInterface() && !ct.flags().isAbstract() &&
        !ct.hasAttribute()) {
      throw new SemanticException(
          "Class must define an attributor to receive the dynamic mode type.");
    }

    if ((ct.flags().isInterface() || ct.flags().isAbstract()) && ct.hasAttribute()) {
      throw new SemanticException("Only a concrete class can implement an attributor.");
    }

    if (!ct.hasDynamicRecv() && ct.hasAttribute()) {
      throw new SemanticException(
          "Class must declare a mode type variable with a dynamic mode receiver "
          +
          "to implement an attributor.");
    }

    return n;
  }

  // Right from jl5/visit/RemoveEnums
  private ClassBody fixClassBodyAsEnumBody(ClassBody body) {
    List<ClassMember> members = new ArrayList<>();
    for (ClassMember cm : body.members()) {
      if (cm instanceof FieldDecl) {
        FieldDecl fd = (FieldDecl)cm;
        if (fd.name().equals("values")) {
          continue;
        }
        members.add(cm);
      } else if (cm instanceof ConstructorDecl) {
        members.add(fixConstructorDeclAsEnumConstructorDecl(((ConstructorDecl)cm)));
      } else if (cm instanceof MethodDecl) {
        MethodDecl md = (MethodDecl)cm;
        if (md.name().equals("valueOf") || md.name().equals("values")) {
          continue;
        }
        members.add(cm);
      } else {
        members.add(cm);
      }
    }
    return body.members(members);
  }

  private ConstructorDecl fixConstructorDeclAsEnumConstructorDecl(ConstructorDecl cd) {
    // remove the two dummy arguments
    List<Formal> newFormals = new LinkedList<>(cd.formals());

    cd = (ConstructorDecl)cd.formals(newFormals);

    // remove the call to super

    List<Stmt> newStmts = new LinkedList<>(cd.body().statements());
    if (!newStmts.isEmpty() && newStmts.get(0) instanceof ConstructorCall) {
      newStmts.remove(0);
    }

    Block newBody = cd.body().statements(newStmts);
    cd = (ConstructorDecl)cd.body(newBody);
    return cd;
  }

  private ClassMember buildAttrProxy(EntNodeFactory nf, EntTypeSystem ts, MethodDecl md) {
    EntMethodDeclExt entext = (EntMethodDeclExt) EntExt.ext(md);

    EntMethodInstance mi = (EntMethodInstance) md.methodInstance();
    String name = "ENT_PROXY_" + mi.name();

    MethodDecl proxy = (MethodDecl) md.name(name);

    proxy = (MethodDecl) proxy.position(Position.COMPILER_GENERATED);
    proxy = (MethodDecl) ((EntMethodDeclExt) EntExt.ext(proxy)).attrDecl(null);
    proxy = (MethodDecl) ((EntMethodDeclExt) EntExt.ext(proxy)).proxy(true);

    return proxy;
  }


  @Override
  public Node typePreserve(TypePreserver tp) {
    ClassDecl n = (ClassDecl) this.node();
    EntClassDeclExt ext = (EntClassDeclExt) EntExt.ext(n);

    // LAST : Fix copy bug

    List<ClassMember> members = new ArrayList<>();
    ClassBody body = n.body();

    for (ClassMember m : body.members()) {
      if (!(m instanceof MethodDecl)) {
        members.add(m);
        continue;
      }
      EntExt ext2 = EntExt.ext(m);
      if (!(ext2 instanceof EntMethodDeclExt)) {
        members.add(m);
        continue;
      }

      EntMethodDeclExt md = (EntMethodDeclExt) EntExt.ext(m);
      if (md.attrDecl() != null) {
        members.add(md.attrDecl());
        members.add(
          buildAttrProxy((EntNodeFactory) tp.nodeFactory(), 
                         (EntTypeSystem) tp.typeSystem(), 
                         (MethodDecl) m)
          );

        m = (ClassMember) md.attrDecl(null);
      } 
      members.add(m);
    }

    body = body.members(members);
    n = n.body(body);

    return n;
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter)rw;
    JL5NodeFactory nf = (JL5NodeFactory)prw.to_nf();
    QQ qq = prw.qq();

    ClassDecl decl = (ClassDecl)this.node();
    JL5ClassDeclExt ext = (JL5ClassDeclExt)JL5Ext.ext(decl);
    EntParsedClassType ct = (EntParsedClassType)decl.type();

    // HACK : Fix enum, but not that bad of a hack, as this is just
    // how polyglot handles things.
    if (ext instanceof JL5EnumDeclExt) {
      ClassDecl n = nf.EnumDecl(decl.position(),
                                decl.flags(),
                                ext.annotationElems(),
                                decl.id(),
                                decl.superClass(),
                                decl.interfaces(),
                                fixClassBodyAsEnumBody(decl.body()));

      return n;
    }

    // Manual translation to JL5
    ClassDecl n = nf.ClassDecl(decl.position(),
                               decl.flags(),
                               ext.annotationElems(),
                               decl.id(),
                               decl.superClass(),
                               decl.interfaces(),
                               decl.body(),
                               ext.paramTypes());

    // 1. Generate ENT_Attributable interface
    List<TypeNode> interfaces = new ArrayList<>(decl.interfaces());
    if (ct.hasAttribute() || ct.hasDynamicRecv()) {
      // 1.1. Generate ENT_Attributable
      interfaces.add(qq.parseType("ENT_Attributable"));
      n = n.interfaces(interfaces);
    }

    // If we are an interface, we are done.
    if (n.flags().isInterface()) {
      return n;
    }

    // 2. Generate default constructor if there is not one
    EntConstructorInstance pci = null;
    if (ct.hasAttribute() && !ct.hasCopy()) {
      boolean genDef = true;
      for (ConstructorInstance ci : ct.constructors()) {
        if (ci.formalTypes().isEmpty()) {
          genDef = false;
          pci = (EntConstructorInstance)ci;
          break;
        }
      }
      if (genDef) {
        ClassBody body = n.body();
        List<ClassMember> members = new ArrayList<>(body.members());
        members.add(qq.parseMember("public %s() { }", decl.name()));
        body = body.members(members);
        n = n.body(body);
      }
    }

    // 3. Generate a builtin ENT_copy method
    if (ct.hasAttribute() && !ct.hasCopy()) {
      List<Stmt> stmts = new ArrayList<>();

      // 3.1. Create a new expression for a shallow copy
      String newStr = null;
      if (pci == null || pci.modeTypeVars().size() == 0) {
        newStr = "%T ENT_ld = new %T();";
      } else {
        newStr = "%T ENT_ld = new %T();";
      }
      stmts.add(qq.parseStmt(newStr, qq.parseType(decl.name()), qq.parseType(decl.name())));

      // 3.2. Copy each member of the class manually
      for (ClassMember m : decl.body().members()) {
        if (!(m instanceof FieldDecl)) {
          continue;
        }
        FieldDecl fd = (FieldDecl)m;
        if (fd.flags().isStatic() || fd.flags().isFinal()) {
          continue;
        }
        stmts.add(qq.parseStmt("ENT_ld.%s = this.%s;", fd.name(), fd.name()));
      }

      // 3.3. Simply return the shallow copy
      stmts.add(qq.parseStmt("return ENT_ld;"));

      ClassMember md = qq.parseMember("public ENT_Attributable ENT_copy() { %LS }", stmts);

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
