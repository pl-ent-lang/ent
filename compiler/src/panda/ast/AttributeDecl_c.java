package panda.ast;

import panda.types.*;
import panda.translate.*;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.visit.*;
import polyglot.util.*;
import polyglot.qq.*;

import java.util.List;

public class AttributeDecl_c extends Term_c implements AttributeDecl {

  protected Block body;
  protected AttributeInstance ai;

  public AttributeDecl_c(Position pos, Block body) {
    super(pos, null);
    this.body = body;
  }

  // Property Methods
  public Block body() {
    return this.body;
  }

  public AttributeDecl body(Block body) {
    return this.body(this, body);
  }

  public <N extends AttributeDecl_c> N body(N n, Block body) {
    if (this.body == body) return n;
    n = copyIfNeeded(n);
    n.body = body;
    return n;
  }

  public AttributeInstance attributeInstance() {
    return this.ai;
  }

  public <N extends AttributeDecl_c> N attributeInstance(N n, AttributeInstance ai) {
    if (this.ai == ai) return n;
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
  protected <N extends AttributeDecl_c> N reconstruct(N n, Block body) {
    n = this.body(n, body);
    return n;
  } 

  @Override
  public Node visitChildren(NodeVisitor v) {
    Block body = visitChild(this.body(), v);
    return this.reconstruct(this, body);
  }

  @Override
  public Node copy(NodeFactory nf) {
    PandaNodeFactory pnf = (PandaNodeFactory) nf;
    return pnf.AttributeDecl(this.position(), this.body());
  }

  @Override
  public Context enterScope(Context c) {
    c = c.pushCode(this.ai);
    return c;
  }

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    PandaTypeSystem ts = (PandaTypeSystem) tb.typeSystem();
    PandaParsedClassType ct = (PandaParsedClassType) tb.currentClass();
  
    if (ct == null) {
      // Big error, attributor needs to be part of a class only
    }

    if (ct.attributeInstance() != null) {
      throw new SemanticException("Only one attributor may be defined per class.");
    }

    AttributeInstance ai = 
      ts.createAttributeInstance(this.position(),ct,null);
    ct.attributeInstance(ai);

    return this.attributeInstance(this, ai);
  }

  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    return this;
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    PandaRewriter prw = (PandaRewriter) rw;
    NodeFactory nf = (NodeFactory) prw.nodeFactory();
    QQ qq = prw.qq();

    ClassMember md = 
      qq.parseMember(
          "public int PANDA_attribute() { %LS }", 
          this.body().statements()
          );

    return md;
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
    return this.body();
  }

  @Override
  public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
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
