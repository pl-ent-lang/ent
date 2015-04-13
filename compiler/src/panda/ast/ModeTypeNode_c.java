package panda.ast;

import panda.types.PandaTypeSystem;
import panda.types.ModeTypeVariable;
import panda.types.PandaContext;
import panda.types.PandaType;
import panda.types.ModeType;

import polyglot.ast.Id;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.ast.TypeNode_c;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.types.Type;
import polyglot.types.SemanticException;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeChecker;

public class ModeTypeNode_c extends TypeNode_c implements ModeTypeNode {

  private Id id;
  private TypeNode base;
  private boolean isImplicitMode;

  public ModeTypeNode_c(Position pos, Id id) {
    super(pos);
    this.id = id;
    this.isImplicitMode = false;
  }

  public ModeTypeNode_c(Position pos) {
    super(pos);
    this.id = null;
    this.isImplicitMode = true;
  }

  public boolean isImplicitMode() {
    return this.isImplicitMode;
  }

  public void isImplicitMode(boolean isImplicitMode) {
    this.isImplicitMode = isImplicitMode;
  }

  public Id id() {
    return this.id;
  }

  public void id(Id id) {
    this.id = id;
  }

  public TypeNode base() {
    return this.base;
  }

  public void base(TypeNode base) {
    this.base = base;
  }

  public Node reconstruct(TypeNode base) {
    this.base(base);
    return this;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    TypeNode typeNode = (TypeNode) visitChild(this.base(), v);
    return reconstruct(typeNode); 
  }

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    return this.type(tb.typeSystem().unknownType(this.position()));
  }

  private boolean shouldDisambiguate() {
    if (this.base == null) {
      return true;
    }
    return this.base().isDisambiguated();
  }

  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    if (!this.shouldDisambiguate()) {
      return this;
    }

    PandaTypeSystem typeSystem = (PandaTypeSystem) sc.typeSystem();
    if (this.isImplicitMode()) {
      PandaType type = 
        typeSystem.createPandaType(this.base().type(), 
                                   typeSystem.bottomModeType());
      return this.type(type);
    }

    if (typeSystem.createdModeTypes().containsKey(this.id().id())) {
      // We have a mode type
      ModeType modeType = typeSystem.createModeType(this.id().id());
      PandaType type = typeSystem.createPandaType(this.base().type(),
                                                  modeType);
      return this.type((Type)type);
    }

    // Check for mode type variables
    PandaContext context = (PandaContext) sc.context();
    ModeTypeVariable modeTypeVar = context.findModeTypeVariableInThisScope(this.id().id());
    if (modeTypeVar != null) {
      PandaType type = typeSystem.createPandaType(this.base().type(),
                                                  modeTypeVar);
      return this.type((Type)type);
    }

    // Invalid type
    throw new SemanticException("Unable to disambiguate ModeTypeNode!");
  }

  @Override
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
  }

}
