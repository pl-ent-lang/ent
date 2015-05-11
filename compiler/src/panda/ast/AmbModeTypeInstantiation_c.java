package panda.ast;

import panda.types.ModeType;
import panda.types.ModeTypeVariable;
import panda.types.ModeSubstParsedClassType;
import panda.types.ModeSubstSubstClassType;
import panda.types.PandaTypeSystem;

import polyglot.ast.Ambiguous;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.ast.TypeNode_c;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class AmbModeTypeInstantiation_c extends ModeTypeNode_c implements AmbModeTypeInstantiation {

  private TypeNode base;
  private List<ModeTypeNode> modeTypeArgs = Collections.emptyList();

  public AmbModeTypeInstantiation_c(Position pos,
                                    TypeNode base,
                                    List<ModeTypeNode> modeTypeArgs) {
    super(pos, null);
    this.base = base;
    this.modeTypeArgs = modeTypeArgs;
  }

  public TypeNode base() {
    return this.base;
  }

  public void base(TypeNode base) {
    this.base = base;
  }

  public List<ModeTypeNode> modeTypeArgs() {
    return this.modeTypeArgs;
  }

  public void modeTypeArgs(List<ModeTypeNode> modeTypeArgs) {
    this.modeTypeArgs = modeTypeArgs;
  }

  public boolean isImplicitMode() {
    return this.modeTypeArgs().isEmpty();
  }

  protected Node reconstruct(TypeNode base, 
                             List<ModeTypeNode> modeTypeArgs) {
    this.base(base);
    this.modeTypeArgs(modeTypeArgs);
    return this;
  }

  // AST Methods
  @Override
  public Node visitChildren(NodeVisitor v) {
    super.visitChildren(v);
    TypeNode base = (TypeNode) visitChild(this.base(), v);
    List<ModeTypeNode> modeTypeArgs = visitList(this.modeTypeArgs(), v);
    return reconstruct(base, modeTypeArgs);
  }

  private boolean shouldDisambiguate() {
    if (!this.base().isDisambiguated()) {
      return false;
    }
    for (ModeTypeNode n : this.modeTypeArgs()) {
      if (!n.isDisambiguated()) {
        return false;
      }
    }
    return true;
  }

  private void checkBaseTypeModeTypeVariables(ModeSubstParsedClassType ct) 
      throws SemanticException {
    if (ct.modeTypeVars().isEmpty()) {
      throw new SemanticException("Cannot instantiate " + ct + 
          " because it has no mode type parameter", this.position());
    } 
  }

  private void checkModeTypeParameters(ModeSubstParsedClassType ct) 
      throws SemanticException {
    // Check for proper arity first
    if (this.modeTypeArgs().size() != ct.modeTypeVars().size()) {
      throw new SemanticException("Cannot instantiate " + ct + 
          " because mode type arguments do not satisfy mode type parameter arity.", 
          this.position());
    }
  }

  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    if (!this.shouldDisambiguate()) {
      return this;
    }

    PandaTypeSystem ts = (PandaTypeSystem) sc.typeSystem();

    List<Type> mtArgs = new ArrayList<Type>();
    if (this.isImplicitMode()) {
      // Throw in a wildcard type and forward to the subst engine
      mtArgs.add(ts.WildcardModeType());
    }

    for (ModeTypeNode n : this.modeTypeArgs()) {
      mtArgs.add(n.type());
    }

    Type st = ts.createModeSubst(this.base().type(), mtArgs);
    return sc.nodeFactory().CanonicalTypeNode(this.position(), st);
  }

  /*
  public Node disambiguateType(AmbiguityRemover sc) throws SemanticException {
    PandaTypeSystem ts = (PandaTypeSystem) sc.typeSystem();
    Type mt = (this.isImplicitMode()) ? 
      ts.WildcardModeType() : this.modeTypeNode().type();

    // Create a mode subst type and set the type of the node
    Type st = ts.substModeType(this.base().type(), mt);
    return sc.nodeFactory().CanonicalTypeNode(this.position(), st);
  }

  public Node disambiguateModeSubstSubstClass(AmbiguityRemover sc) throws SemanticException {
    Type bt = this.base().type();

    // NOTE: We are subst through to a ModeSubstParsedClassType, then using that
    // is it enough?
    ModeSubstParsedClassType ct = (ModeSubstParsedClassType) ((ModeSubstSubstClassType) bt).base();

    this.checkBaseTypeModeTypeVariables(ct);
    this.checkModeTypeParameters(ct);

    // Now, begin to make the subst
    Map<ModeTypeVariable, Type> mtMap = new HashMap<>();
    for (int i = 0; i < ct.modeTypeVars().size(); ++i) {
      mtMap.put(ct.modeTypeVars().get(i), this.modeTypeArgs().get(i).type());
    }

    PandaTypeSystem ts = (PandaTypeSystem) sc.typeSystem();
    Type it = ts.instModeTypeVariables(ct, mtMap);
    return sc.nodeFactory().CanonicalTypeNode(this.position(), it);
  } 

  public Node disambiguateModeSubstParsedClass(AmbiguityRemover sc) throws SemanticException {
    Type bt = this.base().type();
    ModeSubstParsedClassType ct = (ModeSubstParsedClassType) bt;

    this.checkBaseTypeModeTypeVariables(ct);
    this.checkModeTypeParameters(ct);

    // Now, begin to make the subst
    Map<ModeTypeVariable, Type> mtMap = new HashMap<>();
    for (int i = 0; i < ct.modeTypeVars().size(); ++i) {
      mtMap.put(ct.modeTypeVars().get(i), this.modeTypeArgs().get(i).type());
    }

    PandaTypeSystem ts = (PandaTypeSystem) sc.typeSystem();
    Type it = ts.instModeTypeVariables(ct, mtMap);
    return sc.nodeFactory().CanonicalTypeNode(this.position(), it);
  }
  */

  @Override
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
  }


}
