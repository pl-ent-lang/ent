package ent.ast;

import ent.types.*;
import ent.translate.*;

import polyglot.ast.*;
import polyglot.qq.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.visit.*;
import polyglot.util.*;
import polyglot.ext.jl5.ast.*;
import polyglot.ext.jl5.types.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class AttributeDecl_c extends Term_c implements AttributeDecl {

  protected Block body;
  protected AttributeInstance ai;
  protected List<Formal> formals;

  public AttributeDecl_c(Position pos, Block body, List<Formal> formals) {
    super(pos, null);
    this.body = body;
    this.formals = formals;
  }

  // Property Methods
  public Block body() { return this.body; }

  public AttributeDecl body(Block body) { return this.body(this, body); } 

  public <N extends AttributeDecl_c> N body(N n, Block body) {
    if (this.body() == body)
      return n;
    n = this.copyIfNeeded(n);
    n.body = body;
    return n;
  }

  public List<Formal> formals() { return this.formals; }

  public AttributeDecl formals(List<Formal> formals) { return this.formals(this, formals); } 

  public <N extends AttributeDecl_c> N formals(N n, List<Formal> formals) {
    if (this.formals() == formals)
      return n;
    n = this.copyIfNeeded(n);
    n.formals = formals;
    return n;
  }

  public AttributeInstance attributeInstance() { return this.ai; }

  public <N extends AttributeDecl_c> N attributeInstance(N n, AttributeInstance ai) {
    if (this.ai == ai)
      return n;
    n = copyIfNeeded(n);
    n.ai = ai;
    return n;
  }

  // CodeDecl Methods
  @Override
  public Term codeBody() {
    return this.body();
  }

  @Override
  public CodeInstance codeInstance() {
    return this.attributeInstance();
  }

  // Node Methods
  protected <N extends AttributeDecl_c> N reconstruct(N n, Block body, List<Formal> formals) {
    n = this.body(n, body);
    n = this.formals(n, formals);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    List<Formal> formals = visitList(this.formals(), v);
    Block body = visitChild(this.body(), v);
    return this.reconstruct(this, body, formals);
  }

  @Override
  public Node copy(NodeFactory nf) {
    EntNodeFactory pnf = (EntNodeFactory)nf;
    return pnf.AttributeDecl(this.position(), this.body(), this.formals());
  }

  @Override
  public Context enterScope(Context c) {
    c = c.pushCode(this.attributeInstance());
    return c;
  }

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    EntTypeSystem ts = (EntTypeSystem)tb.typeSystem();
    AttributeInstance ai = ts.createAttributeInstance(this.position(), null);
    return this.attributeInstance(this, ai);
  }

  protected boolean shouldDisambiguate() {
    AttributeInstance ai = this.attributeInstance();
    return (ai.container() == null && ai.methodContainer() == null);
  }

  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    EntTypeSystem ts = (EntTypeSystem)sc.typeSystem();

    if (!this.shouldDisambiguate()) {
      return this;
    }

    AttributeInstance ai = this.attributeInstance();
    Context parentCtxt = sc.context().pop();

    if (parentCtxt.inCode()) {
      // Method level attributor
      EntMethodInstance mi = (EntMethodInstance) parentCtxt.currentCode();

      mi.attributeInstance(ai);
      ai.setMethodContainer(mi);

    } else {
      EntParsedClassType ct = (EntParsedClassType) sc.context().currentClass();

      if (ct.attributeInstance() != null) {
        throw new SemanticException("Only one attributor may be defined per class.");
      }

      ct.attributeInstance(ai);
      ai.setContainer(ct); 
    }

    return this.attributeInstance(this, ai);
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    EntRewriter prw = (EntRewriter)rw;
    JL5NodeFactory nf = (JL5NodeFactory)prw.to_nf();
    JL5TypeSystem ts = (JL5TypeSystem)prw.toTypeSystem();
    QQ qq = prw.qq();

    ClassMember cm = null;
    if (this.attributeInstance().container() != null) {
      cm = qq.parseMember("public int ENT_attribute() { %LS }", this.body().statements());
    } else if (this.attributeInstance().methodContainer() != null) {
      EntMethodInstance mi = (EntMethodInstance) this.attributeInstance().methodContainer();

      /*
      List<Object> toPass = new ArrayList<Object>();
      toPass.add((Object) mi.name());
      for (Formal f : this.formals()) {
        toPass.add((Object) f);
      }
      for (Stmt s : this.body().statements()) {
        toPass.add((Object) s);
      }
      */

      Node n = nf.MethodDecl(
                 Position.COMPILER_GENERATED,
                 Flags.PUBLIC,
                 Collections.<AnnotationElem>emptyList(),
                 nf.CanonicalTypeNode(Position.COMPILER_GENERATED, ts.Int()),
                 nf.Id(
                   Position.COMPILER_GENERATED, 
                   String.format("ENT_attribute_%s", mi.name())
                   ),
                 this.formals(),
                 Collections.<TypeNode>emptyList(),
                 this.body(),
                 Collections.<ParamTypeNode>emptyList()
                 );

      cm = (ClassMember) n;
    }

    return cm;
  }

  @Override
  public void dump(CodeWriter w) {
    super.dump(w);

    if (this.attributeInstance() != null) {
      w.allowBreak(4, " ");
      w.begin(0);
      w.write("(instance " + this.attributeInstance() + ")");
      w.end();
    }
  }

  // Term Method
  @Override
  public Term firstChild() {
    return listChild(formals(), body());
  }

  @Override
  public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
    if (formals() != null) {
      v.visitCFGList(formals(), body(), ENTRY);
    }

    if (this.body() != null) {
      v.visitCFG(this.body(), this, EXIT);
    }

    return succs;
  }

  // ClassMember Methods
  @Override
  public MemberInstance memberInstance() {
    return this.ai;
  }
}
