package ent.ast;

import ent.types.*;
import ent.translate.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.translate.*;
import polyglot.qq.*;
import polyglot.util.*;
import polyglot.visit.*;
import polyglot.ext.jl5.ast.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;

public class McaseLit_c extends Lit_c implements McaseLit {

  protected TypeNode mcaseTypeNode;
  protected List<McaseFieldDecl> fields;

  public McaseLit_c(Position pos, TypeNode mcaseTypeNode, List<McaseFieldDecl> fields) {
    super(pos);
    this.mcaseTypeNode = mcaseTypeNode;
    this.fields = CollectionUtil.nonNullList(fields);
  }

  // Property Methods
  protected TypeNode mcaseTypeNode() { return this.mcaseTypeNode; }

  protected <N extends McaseLit_c> N mcaseTypeNode(N n, TypeNode mcaseTypeNode) {
    if (this.mcaseTypeNode() == mcaseTypeNode)
      return n;
    n = this.copyIfNeeded(n);
    n.mcaseTypeNode = mcaseTypeNode;
    return n;
  }

  protected List<McaseFieldDecl> fields() { return this.fields; }

  protected <N extends McaseLit_c> N fields(N n, List<McaseFieldDecl> fields) {
    if (CollectionUtil.equals(this.fields(), fields))
      return n;
    n = this.copyIfNeeded(n);
    n.fields = ListUtil.copy(fields, true);
    return n;
  }

  // Node Methods
  protected <N extends McaseLit_c>
      N reconstruct(N n, TypeNode mcaseTypeNode, List<McaseFieldDecl> fields) {
    n = this.fields(n, fields);
    n = this.mcaseTypeNode(n, mcaseTypeNode);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) { 
    List<McaseFieldDecl> fields = visitList(this.fields(), v); 
    TypeNode mcaseTypeNode = visitChild(this.mcaseTypeNode(), v);
    return this.reconstruct(this, mcaseTypeNode, fields);
  }

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    return this.type(tb.typeSystem().unknownType(this.position()));
  }

  @Override
  public boolean isDisambiguated() {
    if (!this.mcaseTypeNode().isDisambiguated()) {
      return false;
    }
    for (McaseFieldDecl fd : this.fields()) {
      if (!fd.isDisambiguated()) {
        return false;
      }
    }
    return true;
  }

  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    if (!this.mcaseTypeNode().isDisambiguated()) {
      return this;
    }

    return this.type(this.mcaseTypeNode().type());
  }

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    // Current, for an mcase type to typecheck,
    //  1. Each init must have the type of the base
    //  2. All modes must be covered

    Set<String> found = new HashSet<>();
    EntTypeSystem ts = (EntTypeSystem)tc.typeSystem();
    McaseType mcT = (McaseType)this.mcaseTypeNode().type();

    for (McaseFieldDecl fd : this.fields()) {
      String mode = fd.field().id();
      if (found.contains(mode)) {
        throw new SemanticException("Duplicate mode fields found in mcase!");
      }

      if (!ts.createdModeTypes().containsKey(mode)) {
        throw new SemanticException("Undeclared mode " + mode + " in mcase!");
      }

      if (!ts.isSubtype(fd.init().type(), mcT.base())) {
        throw new SemanticException("Initializer type " + fd.init().type() +
                                    " does not match mcase type");
      }

      found.add(mode);
    }

    // Check to make sure all modes were covered
    for (Map.Entry<String, ModeType> e : ts.createdModeTypes().entrySet()) {
      if (e.getValue() == ts.WildcardModeType() || e.getValue() == ts.DynamicModeType() ||
          e.getValue() == ts.BottomModeType()) {
        continue;
      }
      if (!found.contains(e.getKey())) {
        throw new SemanticException("mcase must cover all declared modes, missing " + e.getKey());
      }
    }

    return this;
  }

  @Override
  public void translate(CodeWriter w, Translator tr) {
    System.out.println("Caught translate!");
  }

  @Override
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
    tr.lang().prettyPrint(this.mcaseTypeNode(), w, tr);
    w.allowBreak(0);
    w.write("{");
    w.begin(0);

    for (McaseFieldDecl fd : this.fields()) {
      w.allowBreak(2);
      print(fd, w, tr);
      w.write(";");
    }
    w.allowBreak(2);

    w.end();
    w.write("}");
  }

  // Lit Methods
  @Override
  public Object constantValue(Lang lang) {
    return null;
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter)rw;
    JL5NodeFactory nf = (JL5NodeFactory)prw.to_nf();
    QQ qq = prw.qq();

    List<Expr> exprs = new ArrayList<Expr>();
    for (int i = 0; i < this.fields().size(); i++) {
      exprs.add((Expr)this.fields().get(i));
    }

    Node n = nf.NewArray(Position.COMPILER_GENERATED,
                         prw.typeToJava(((McaseType)this.type()).base(), this.position()),
                         1,
                         nf.ArrayInit(Position.COMPILER_GENERATED, exprs));

    return n;
  }
}
