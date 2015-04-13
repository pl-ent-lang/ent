package panda.ast;

import panda.types.ModeType;
import panda.types.ModeTypeVariable;
import panda.types.PandaParsedClassType;
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

  private ModeTypeNode baseMode;
  private List<ModeValueNode> modeTypeArguments;

  public AmbModeTypeInstantiation_c(Position pos,
                                    ModeTypeNode base,
                                    List<ModeValueNode> modeTypeArguments) {
    super(pos, null);
    this.baseMode = base;
    this.modeTypeArguments = modeTypeArguments;
  }

  public ModeTypeNode baseMode() {
    return this.baseMode;
  }

  public void baseMode(ModeTypeNode baseMode) {
    this.baseMode = baseMode;
  }

  public TypeNode base() {
    return this.baseMode().base();
  }

  public void base(TypeNode base) {
    this.baseMode().base(base);
  }

  public List<ModeValueNode> modeTypeArguments() {
    return this.modeTypeArguments;
  }

  public void modeTypeArguments(List<ModeValueNode> modeTypeArguments) {
    this.modeTypeArguments = modeTypeArguments;
  }

  protected Node reconstruct(ModeTypeNode baseMode, 
                             List<ModeValueNode> modeTypeArguments) {
    this.baseMode(baseMode);
    this.modeTypeArguments(modeTypeArguments);
    return this;
  }

  // AST Methods
  @Override
  public Node visitChildren(NodeVisitor v) {
    super.visitChildren(v);
    ModeTypeNode baseMode = (ModeTypeNode) visitChild(this.baseMode(), v);
    List<ModeValueNode> modeTypeArguments = visitList(this.modeTypeArguments(), v);
    return reconstruct(baseMode, modeTypeArguments);
  }

  private boolean shouldDisambiguate() {
    if (!this.baseMode().isDisambiguated()) {
      return false;
    }
    for (ModeValueNode n : this.modeTypeArguments()) {
      if (!n.isDisambiguated()) {
        return false;
      }
    }
    return true;
  }

  private void checkBaseTypeModeTypeVariables(PandaParsedClassType classType) 
      throws SemanticException {
    if (classType.modeTypeVariables().isEmpty()) {
      throw new SemanticException("Cannot instantiate " + classType + 
          " because it has no mode type parameter", this.position());
    } 
  }

  private void checkModeTypeParameters(PandaParsedClassType classType) 
      throws SemanticException {
    // Check for proper arity first
    if (this.modeTypeArguments().size() != classType.modeTypeVariables().size()) {
      throw new SemanticException("Cannot instantiate " + classType + 
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
    Type baseType = this.base().type();
    PandaParsedClassType classType = (PandaParsedClassType) baseType;

    this.checkBaseTypeModeTypeVariables(classType);
    this.checkModeTypeParameters(classType);

    // Now, begin to make the subst
    Map<ModeTypeVariable, Type> modeTypeMap = new HashMap<>();
    for (int i = 0; i < classType.modeTypeVariables().size(); ++i) {
      modeTypeMap.put(classType.modeTypeVariables().get(i), 
                      this.modeTypeArguments().get(i).type());
    }

    PandaTypeSystem typeSystem = (PandaTypeSystem) sc.typeSystem();
    Type substType = typeSystem.substModeTypeVariables(classType, 
                                                       modeTypeMap);
    return sc.nodeFactory().CanonicalTypeNode(position, substType);
  } 

  @Override
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
  }


}
