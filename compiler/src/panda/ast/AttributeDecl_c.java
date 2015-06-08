package panda.ast;

import polyglot.ast.Term_c;

import polyglot.ast.Formal;
import polyglot.ast.Term;
import polyglot.ast.Node;
import polyglot.ast.Block;
import polyglot.ast.Id;
import polyglot.ast.TypeNode; 
import polyglot.types.Context;
import polyglot.types.CodeInstance;
import polyglot.types.Flags;
import polyglot.types.MemberInstance;
import polyglot.types.SemanticException;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeChecker;
import polyglot.util.Position;

import panda.types.PandaContext;
import panda.types.PandaParsedClassType;
import panda.types.PandaTypeSystem;
import panda.types.AttributeInstance;

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
  public <N extends AttributeDecl_c> N reconstruct(N n, Block body) {
    n = this.body(n, body);
    return n;
  } 

  @Override
  public Node visitChildren(NodeVisitor v) {
    Block body = visitChild(this.body(), v);
    return this.reconstruct(this, body);
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
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    return this;
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
