package panda.ast;

import polyglot.ast.Node;
import polyglot.visit.TypeBuilder;
import polyglot.visit.NodeVisitor;
import polyglot.types.SemanticException;
import polyglot.visit.PrettyPrinter;
import polyglot.util.CodeWriter;

public class PandaSourceFileExt extends PandaExt {

  private ModesDecl modesDecl;

  public ModesDecl modesDecl() {
    return this.modesDecl;
  }

  public void modesDecl(ModesDecl modesDecl) {
    this.modesDecl = modesDecl;
  }

  public Node reconstruct(Node n, ModesDecl modesDecl) {
    PandaSourceFileExt ext = (PandaSourceFileExt) PandaExt.ext(n);
    ext.modesDecl(modesDecl);
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
    return superLang().buildTypes(node(), tb);
  }

}
