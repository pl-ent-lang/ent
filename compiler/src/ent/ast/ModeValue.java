package ent.ast;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;

public interface ModeValue extends Lit {

  ModeTypeNode modeTypeNode();
  ModeValue modeTypeNode(ModeTypeNode modeTypeNode);

  Node rewriteModeValue(ExtensionRewriter rw) throws SemanticException;
}
