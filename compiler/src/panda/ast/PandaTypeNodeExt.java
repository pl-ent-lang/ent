package panda.ast;

import panda.types.ModeTypeVariable;
import panda.types.ModeSubstArrayType_c;
import panda.types.ModeSubstPrimitiveType_c;
import panda.types.ModeSubstParsedClassType_c;
import panda.types.ModeType;
import panda.types.PandaContext;
import panda.types.PandaParsedClassType;
import panda.types.PandaType;
import panda.types.PandaTypeSystem;

import polyglot.ast.CanonicalTypeNode;
import polyglot.ast.Id;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.types.Type;
import polyglot.types.PrimitiveType;
import polyglot.types.SemanticException;
import polyglot.util.CodeWriter;
import polyglot.util.InternalCompilerError;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeChecker;

import polyglot.ext.jl5.ast.ParamTypeNode;
import polyglot.ext.jl5.types.JL5PrimitiveType;
import polyglot.ext.jl5.types.JL5ArrayType;

public class PandaTypeNodeExt extends PandaExt {

  private ModeTypeNode modeTypeNode = null;
  private boolean isImplicitMode = true;

  // Property Methods
  public ModeTypeNode modeTypeNode() {
    return this.modeTypeNode;
  }

  public void modeTypeNode(ModeTypeNode modeTypeNode) {
    if (modeTypeNode != null) {
      this.isImplicitMode(false);
    } else {
      this.isImplicitMode(true);
    }
    this.modeTypeNode = modeTypeNode;
  } 

  public boolean isImplicitMode() {
    return this.isImplicitMode;
  }

  public void isImplicitMode(boolean isImplicitMode) {
    this.isImplicitMode = isImplicitMode;
  }

  public Node reconstruct(Node n, ModeTypeNode m) {
    PandaTypeNodeExt newExt = (PandaTypeNodeExt) PandaExt.ext(n);
    newExt.modeTypeNode(m);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    ModeTypeNode m = null;
    if (this.modeTypeNode() != null) {
      m = visitChild(this.modeTypeNode(), v);
    }
    Node n = superLang().visitChildren(this.node(), v);
    return this.reconstruct(n, m);
  }

  public boolean shouldDisambiguate(TypeNode n) {
    PandaTypeNodeExt ext = (PandaTypeNodeExt) PandaExt.ext(n);
    return (n.isDisambiguated() &&
            (ext.isImplicitMode() || ext.modeTypeNode().isDisambiguated()));
  }


  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    PandaTypeNodeExt ext = (PandaTypeNodeExt) PandaExt.ext(this.node());
    TypeNode n = (TypeNode) superLang().disambiguate(this.node(), sc);

    // TODO : We need to ignore this check on param type variables,
    // there must be a better way to do this
    if (n instanceof ParamTypeNode || 
        n instanceof ModeParamTypeNode ||
        n instanceof ModeTypeNode) {
      return n;
    }

    n = (TypeNode) this.reconstruct(n, ext.modeTypeNode());
    ext = (PandaTypeNodeExt) PandaExt.ext(n);

    // TODO : Why do I need this check?
    if (!this.shouldDisambiguate(n) || (n.type() instanceof PandaType)) {
      return n;
    }

    // NOTE: This check here will throw an exception a lot at first, this
    // will help find the types I need to override.
    PandaTypeSystem ts = (PandaTypeSystem) sc.typeSystem();
    Type modeType = null;

    if (ext.isImplicitMode()) {
      modeType = ts.bottomModeType();
    } else {
      modeType = ext.modeTypeNode().type();
    }

    /*
    if (ext.isImplicitMode()) {
      modeType = ts.bottomModeType();

    } else if (ts.createdModeTypes().containsKey(ext.modeTypeNode().id())) {
      // We have a mode type
      modeType = ts.createModeType(ext.modeTypeNode().id());

    } else {
      // Check for mode type variables
      PandaContext c = (PandaContext) sc.context();
      ModeTypeVariable modeTypeVar = 
        c.findModeTypeVariableInThisScope(ext.modeTypeNode().id());
      if (modeTypeVar == null) {
        // Invalid type
        throw new SemanticException("Unable to disambiguate PandaTypeNodeExt!",
                                    ext.modeTypeNode().position());
      }

      modeType = modeTypeVar;
    }
    */

    // Create a mode subst type and set the type of the node
    Type substType = ts.substModeType(n.type(), modeType);
    return sc.nodeFactory().CanonicalTypeNode(n.position(), substType);
  }

  @Override
  public void dump(CodeWriter w) {
    w.begin(0);
    w.write("(modeTypeNode " + this.modeTypeNode() + ")");
    w.end();
  }


}
