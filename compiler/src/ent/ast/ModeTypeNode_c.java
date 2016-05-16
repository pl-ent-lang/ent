package ent.ast;

import ent.translate.*;
import ent.types.*;

import polyglot.ast.*;
import polyglot.util.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.visit.*;

import java.util.Map;

public class ModeTypeNode_c extends TypeNode_c implements ModeTypeNode {

  protected String name;
  protected ModeTypeNode lowerBound;
  protected ModeTypeNode upperBound;

  public ModeTypeNode_c(Position pos, String name) { this(pos, name, null, null); }

  public ModeTypeNode_c(Position pos, String name, ModeTypeNode lb, ModeTypeNode ub) {
    super(pos);
    this.name = name;
    this.lowerBound = lb;
    this.upperBound = ub;
  }

  // Property Methods
  public String name() { return this.name; }

  public ModeTypeNode lowerBound() { return this.lowerBound; }

  protected <N extends ModeTypeNode_c> N lowerBound(N n, ModeTypeNode lowerBound) {
    if (this.lowerBound == lowerBound)
      return n;
    n = this.copyIfNeeded(n);
    n.lowerBound = lowerBound;
    return n;
  }

  public ModeTypeNode upperBound() { return this.upperBound; }

  protected <N extends ModeTypeNode_c> N upperBound(N n, ModeTypeNode upperBound) {
    if (this.upperBound == upperBound)
      return n;
    n = this.copyIfNeeded(n);
    n.upperBound = upperBound;
    return n;
  }

  protected <N extends ModeTypeNode_c>
      N reconstruct(N n, ModeTypeNode lowerBound, ModeTypeNode upperBound) {
    n = this.lowerBound(n, lowerBound);
    n = this.upperBound(n, upperBound);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    ModeTypeNode lowerBound = null;
    ModeTypeNode upperBound = null;
    if (this.lowerBound() != null) {
      lowerBound = visitChild(this.lowerBound(), v);
    }
    if (this.upperBound() != null) {
      upperBound = visitChild(this.upperBound(), v);
    }
    return this.reconstruct(this, lowerBound, upperBound);
  }

  // Node Methods
  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    return this.type(tb.typeSystem().unknownType(this.position()));
  }

  protected boolean shouldDisambiguate() {
    if (this.lowerBound() != null && this.upperBound() != null) {
      return this.lowerBound().isDisambiguated() && this.upperBound().isDisambiguated();
    }
    return true;
  }

  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    if (!this.shouldDisambiguate()) {
      return this;
    }

    EntTypeSystem ts = (EntTypeSystem)sc.typeSystem();

    if (this.name().equals("_") && this.lowerBound() != null && this.upperBound() != null) {
      // TODO: Refactor into a clear ModeTypeVariable creation.
      ModeTypeVariable mtv = ts.createBoundedExistential(this.position());
      mtv.upperBound(this.upperBound().type());
      mtv.lowerBound(this.lowerBound().type());
      return this.type(mtv);
    }

    if (ts.createdModeTypes().containsKey(this.name())) {
      // We have a mode type
      return this.type(ts.createModeType(this.name()));
    }

    // Check for mode type variables
    EntContext c = (EntContext)sc.context();
    ModeTypeVariable mtVar = c.findModeTypeVariableInThisScope(this.name());
    if (mtVar != null) {
      // Check to make sure this is not a class mode type variable used inside the classes
      // constructor.
      if (c.currentCode() instanceof ConstructorInstance) {
        ConstructorInstance ci = (ConstructorInstance)c.currentCode();
        EntClassType ct = (EntClassType)ci.container();
        if (mtVar.declaringClass() != null && ts.typeEquals(ct, mtVar.declaringClass())) {
          throw new SemanticException(
              "Invalid use of class mode type variable inside constructor!");
        }
      }
      return this.type(mtVar);
    }

    // Invalid type
    throw new SemanticException("Unable to disambiguate ModeTypeNode!");
  }

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    // TODO : Come back for copy
    return this;
  }

  @Override
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {}
}
