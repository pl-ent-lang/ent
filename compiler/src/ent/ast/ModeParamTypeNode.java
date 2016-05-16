package ent.ast;

import polyglot.ast.Id;
import polyglot.ast.TypeNode;

import java.util.List;

public interface ModeParamTypeNode extends TypeNode {

  public Id id();
  public boolean isDynRecvr();

  public List<ModeTypeNode> lowerBounds();
  public List<ModeTypeNode> upperBounds();
}
