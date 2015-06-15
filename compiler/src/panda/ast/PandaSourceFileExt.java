package panda.ast;

import polyglot.ast.Node;
import polyglot.ast.SourceFile;
import polyglot.visit.TypeBuilder;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.CollectionUtil;
import polyglot.util.Copy;
import polyglot.util.ListUtil;
import polyglot.util.Position;


public class PandaSourceFileExt extends PandaExt {

  protected ModesDecl modesDecl;

  public ModesDecl modesDecl() {
    return this.modesDecl;
  }

  public Node modesDecl(ModesDecl modesDecl) {
    return this.modesDecl(this.node(), modesDecl);
  }

  public <N extends Node> N modesDecl(N n, ModesDecl modesDecl) {
    PandaSourceFileExt ext = (PandaSourceFileExt) PandaExt.ext(n);
    if (this.modesDecl == modesDecl) return n;
    if (this.node() == n) {
      n = Copy.Util.copy(n);
      ext = (PandaSourceFileExt) PandaExt.ext(n);
    }
    ext.modesDecl = modesDecl;
    return n;
  }

  // Node Methods
  public Node reconstruct(Node n, ModesDecl modesDecl) {
    n = this.modesDecl(n, modesDecl);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    ModesDecl modesDecl = visitChild(this.modesDecl(), v);
    Node n = superLang().visitChildren(this.node(), v);
    return this.reconstruct(n, modesDecl);
  }

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    // Inject our runtime into the import table
    tb.importTable().addTypeOnDemandImport("panda.runtime", Position.compilerGenerated());
    return superLang().buildTypes(this.node(), tb);
  }

}
