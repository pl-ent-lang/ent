package panda.ast;

import panda.types.ModeType;
import panda.types.ModeTypeVariable;
import panda.types.ModeSubstParsedClassType;
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

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class AmbModeTypeInstantiation_c extends ModeTypeNode_c implements AmbModeTypeInstantiation {

  private TypeNode base;
  private List<ModeTypeNode> modeTypeArguments;

  public AmbModeTypeInstantiation_c(Position pos,
                                    TypeNode base,
                                    List<ModeTypeNode> modeTypeArguments) {
    super(pos, null);
    this.base = base;
    this.modeTypeArguments = modeTypeArguments;
  }

  public TypeNode base() {
    return this.base;
  }

  public void base(TypeNode base) {
    this.base = base;
  }

  public List<ModeTypeNode> modeTypeArguments() {
    return this.modeTypeArguments;
  }

  public void modeTypeArguments(List<ModeTypeNode> modeTypeArguments) {
    this.modeTypeArguments = modeTypeArguments;
  }

  protected Node reconstruct(TypeNode base, 
                             List<ModeTypeNode> modeTypeArguments) {
    this.base(base);
    this.modeTypeArguments(modeTypeArguments);
    return this;
  }

  // AST Methods
  @Override
  public Node visitChildren(NodeVisitor v) {
    super.visitChildren(v);
    TypeNode base = (TypeNode) visitChild(this.base(), v);
    List<ModeTypeNode> modeTypeArguments = visitList(this.modeTypeArguments(), v);
    return reconstruct(base, modeTypeArguments);
  }

  private boolean shouldDisambiguate() {
    if (!this.base().isDisambiguated()) {
      return false;
    }
    for (ModeTypeNode n : this.modeTypeArguments()) {
      if (!n.isDisambiguated()) {
        return false;
      }
    }
    return true;
  }

  private void checkBaseTypeModeTypeVariables(ModeSubstParsedClassType ct) 
      throws SemanticException {
    if (ct.modeTypeVariables().isEmpty()) {
      throw new SemanticException("Cannot instantiate " + ct + 
          " because it has no mode type parameter", this.position());
    } 
  }

  private void checkModeTypeParameters(ModeSubstParsedClassType ct) 
      throws SemanticException {
    // Check for proper arity first
    if (this.modeTypeArguments().size() != ct.modeTypeVariables().size()) {
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

    // TODO: This code assumes that the base type is always a 
    // ParsedClassType, that may not necessarily be true
    Type bt = this.base().type();
    ModeSubstParsedClassType ct = (ModeSubstParsedClassType) bt;

    this.checkBaseTypeModeTypeVariables(ct);
    this.checkModeTypeParameters(ct);

    // Now, begin to make the subst
    Map<ModeTypeVariable, Type> modeTypeMap = new HashMap<>();
    for (int i = 0; i < ct.modeTypeVariables().size(); ++i) {
      modeTypeMap.put(ct.modeTypeVariables().get(i), 
                      this.modeTypeArguments().get(i).type());
    }

    PandaTypeSystem ts = (PandaTypeSystem) sc.typeSystem();
    Type instType = ts.instModeTypeVariables(ct, modeTypeMap);
    return sc.nodeFactory().CanonicalTypeNode(position, instType);
  } 

  @Override
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
  }


}
