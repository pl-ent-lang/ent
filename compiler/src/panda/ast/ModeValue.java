package panda.ast;

import polyglot.ast.Lit;

public interface ModeValue extends Lit {

  ModeTypeNode modeTypeNode();
  ModeValue modeTypeNode(ModeTypeNode modeTypeNode);

}
