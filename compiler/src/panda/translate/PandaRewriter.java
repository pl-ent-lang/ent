package panda.translate;

import panda.PandaOptions;

import polyglot.ast.NodeFactory;
import polyglot.frontend.ExtensionInfo;
import polyglot.frontend.Job;
import polyglot.translate.ExtensionRewriter;

public class PandaRewriter extends ExtensionRewriter {
  protected boolean translatePanda;
  
  public PandaRewriter(Job job, 
                       ExtensionInfo from_ext, 
                       ExtensionInfo to_ext) {
    super(job, from_ext, to_ext);
    PandaOptions options = (PandaOptions) from_ext.getOptions();
    this.translatePanda = options.translatePanda;
  }

  public boolean translatePanda() {
    return true;
  }

}
