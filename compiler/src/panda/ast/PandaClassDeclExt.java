package panda.ast;

import java.util.List;

public class PandaClassDeclExt extends PandaExt {

  private List<ModeParamTypeNode> modeParamTypes;

  public List<ModeParamTypeNode> modeParamTypes() {
    return this.modeParamTypes;
  }

  public void modeParamTypes(List<ModeParamTypeNode> modeParamTypes) {
    this.modeParamTypes = modeParamTypes;
  }
}
