package panda.ast;

import panda.types.ModeType;
import panda.types.ModeTypeVariable;
import panda.types.PandaContext;
import panda.types.PandaTypeSystem;

import polyglot.ast.Node;
import polyglot.ast.TypeNode_c;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeBuilder;

import java.util.Map;

public class ModeTypeNode_c extends TypeNode_c implements ModeTypeNode {

  private String name;

  public ModeTypeNode_c(Position pos, String name) {
    super(pos);
    this.name = name;
  }

  // Property Methods
  public String name() {
    return this.name;
  }

  // Node Methods
  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    return this.type(tb.typeSystem().unknownType(this.position()));
  }

  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    PandaTypeSystem ts = (PandaTypeSystem) sc.typeSystem();

    if (ts.createdModeTypes().containsKey(this.name())) {
      // We have a mode type
      return this.type(ts.createModeType(this.name()));
    }

    // Check for mode type variables
    PandaContext c = (PandaContext) sc.context();
    ModeTypeVariable mtVar = c.findModeTypeVariableInThisScope(this.name());
    if (mtVar != null) {
      return this.type(mtVar);
    }

    // Invalid type
    throw new SemanticException("Unable to disambiguate ModeTypeNode!");
  }

  @Override
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
  }

}
