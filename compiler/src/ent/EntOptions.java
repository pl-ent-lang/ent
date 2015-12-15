package ent;

import polyglot.frontend.ExtensionInfo;
import polyglot.main.OptFlag;
import polyglot.main.OptFlag.Arg;
import polyglot.main.OptFlag.Switch;
import polyglot.main.Options;
import polyglot.main.UsageError;

import polyglot.ext.jl5.JL5Options;

import java.util.Set;

public class EntOptions extends JL5Options {
  public boolean translateEnt = true;
  public boolean preserveTypes = true;

  public EntOptions(ExtensionInfo extension) {
    super(extension);
  }

  @Override
  protected void populateFlags(Set<OptFlag<?> > flags) {
    super.populateFlags(flags);

    flags.add(
        new Switch(
          new String[]{ "-dontTranslateEnt", "--dontTranslateEnt" },
          "Do not translate Ent features to Java features",
          false
          ));

    flags.add(
        new Switch(
          new String[]{ "-dontPreserveTypes", "--dontPreserveTypes" },
          "Do not translate preserve types from Ent, removing overhead.",
          false
          ));

  }

  @Override
  protected void handleArg(Arg<?> arg) throws UsageError {
    if (arg.flag().ids().contains("-dontTranslateEnt")) {
      this.translateEnt = (Boolean) arg.value();
    } else if (arg.flag().ids().contains("-dontPreserveTypes")) {
      this.preserveTypes = (Boolean) arg.value();
    } else {
      super.handleArg(arg);
    }
  }

  @Override
  protected void postApplyArgs() {
    super.postApplyArgs();
    this.fully_qualified_names = true;
  }

}
