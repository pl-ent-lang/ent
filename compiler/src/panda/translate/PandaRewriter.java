package panda.translate;

import panda.PandaOptions;

import polyglot.ast.*;
import polyglot.frontend.*;
import polyglot.translate.*;
import polyglot.util.*;

public class PandaRewriter extends ExtensionRewriter {
  protected boolean translatePanda;
  protected boolean rewriteModeValue;
  
  public PandaRewriter(Job job, 
                       ExtensionInfo from_ext, 
                       ExtensionInfo to_ext) {
    super(job, from_ext, to_ext);
    PandaOptions options = (PandaOptions) from_ext.getOptions();
    this.translatePanda = options.translatePanda;
  }

  public boolean translatePanda() {
    return this.translatePanda;
  }

  public boolean rewriteModeValue() {
    return this.rewriteModeValue;
  }

  public PandaRewriter rewriteModeValue(boolean rewrite) {
    PandaRewriter prw = Copy.Util.copy(this);
    prw.rewriteModeValue = rewrite;
    return prw;
  }

}
