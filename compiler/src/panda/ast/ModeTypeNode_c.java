package panda.ast;

import panda.translate.*;
import panda.types.*;

import polyglot.ast.*;
import polyglot.util.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.visit.*;

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
      // Check to make sure this is not a class mode type variable used inside the classes
      // constructor.
      if (c.currentCode() instanceof ConstructorInstance) {
        ConstructorInstance ci = (ConstructorInstance) c.currentCode();
        PandaClassType ct = (PandaClassType) ci.container();
        if (mtVar.declaringClass() != null && 
            ts.typeEquals(ct, mtVar.declaringClass())) {
          throw new SemanticException("Invalid use of class mode type variable inside constructor!");
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
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
  }

}
