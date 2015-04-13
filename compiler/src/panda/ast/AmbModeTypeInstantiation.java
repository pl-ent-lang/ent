package panda.ast;

import polyglot.ast.Ambiguous;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;

import java.util.List;

public interface AmbModeTypeInstantiation extends ModeTypeNode {

  // Property Methods
  TypeNode base();
  void base(TypeNode base);

  ModeTypeNode baseMode();
  void baseMode(ModeTypeNode baseMode);

  List<ModeValueNode> modeTypeArguments();
  void modeTypeArguments(List<ModeValueNode> modeTypeArguments);

}
