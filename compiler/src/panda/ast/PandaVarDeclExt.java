package panda.ast;

public class PandaVarDeclExt extends PandaExt {

  private ModeTypeNode modeTypeNode;

  public ModeTypeNode modeTypeNode() {
    return this.modeTypeNode;
  }

  public void modeTypeNode(ModeTypeNode modeTypeNode) {
    this.modeTypeNode = modeTypeNode;
  }

}
