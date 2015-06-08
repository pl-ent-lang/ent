package panda.ast;

import polyglot.ast.Id;
import polyglot.ast.TypeNode;

import java.util.List;

public interface ModeParamTypeNode extends TypeNode {

  public Id id();

  public List<ModeTypeNode> bounds();

}
