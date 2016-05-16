package ent.ast;

import polyglot.ast.Ambiguous;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;

import java.util.List;

public interface AmbModeTypeInstantiation extends ModeTypeNode {

  // Property Methods
  TypeNode base();
  AmbModeTypeInstantiation base(TypeNode base);

  List<ModeTypeNode> modeTypeArgs();
  AmbModeTypeInstantiation modeTypeArgs(List<ModeTypeNode> modeTypeArgs);
}
