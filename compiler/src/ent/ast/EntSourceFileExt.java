package ent.ast;

import ent.translate.*;

import polyglot.ast.*;
import polyglot.visit.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.util.*;

import java.util.List;
import java.util.LinkedList;

public class EntSourceFileExt extends EntExt {

  protected ClassDecl modesDecl;

  public ClassDecl modesDecl() { return this.modesDecl; }

  public Node modesDecl(ClassDecl modesDecl) { return this.modesDecl(this.node(), modesDecl); }

  public <N extends Node> N modesDecl(N n, ClassDecl modesDecl) {
    EntSourceFileExt ext = (EntSourceFileExt)EntExt.ext(n);
    if (this.modesDecl == modesDecl)
      return n;
    if (this.node() == n) {
      n = Copy.Util.copy(n);
      ext = (EntSourceFileExt)EntExt.ext(n);
    }
    ext.modesDecl = modesDecl;
    return n;
  }

  // Node Methods
  public Node reconstruct(Node n, ClassDecl modesDecl) {
    n = this.modesDecl(n, modesDecl);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    ClassDecl modesDecl = visitChild(this.modesDecl(), v);
    Node n = superLang().visitChildren(this.node(), v);
    return this.reconstruct(n, modesDecl);
  }

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    // Inject our runtime into the import table
    tb.importTable().addTypeOnDemandImport("ent.runtime", Position.compilerGenerated());
    return superLang().buildTypes(this.node(), tb);
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter)rw;
    NodeFactory nf = prw.nodeFactory();

    SourceFile n = (SourceFile)super.extRewrite(rw);

    // Inject the ent runtime into all source files
    List<Import> imports = new LinkedList<>(n.imports());
    imports.add(
        nf.Import(Position.COMPILER_GENERATED, Import.TYPE_IMPORT_ON_DEMAND, "ent.runtime"));
    n = n.imports(imports);

    /*
    // If this file had a modes decl, throw it in
    EntSourceFileExt ext = (EntSourceFileExt) EntExt.ext(this.node());
    if (ext.modesDecl() != null) {
      List<TopLevelDecl> decls = new LinkedList<>(n.decls());
      decls.add(ext.modesDecl());
      n = n.decls(decls);
    }
    */

    return n;
  }
}
