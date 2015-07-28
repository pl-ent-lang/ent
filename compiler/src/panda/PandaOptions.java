package panda;

import polyglot.frontend.ExtensionInfo;
import polyglot.main.OptFlag;
import polyglot.main.OptFlag.Arg;
import polyglot.main.OptFlag.Switch;
import polyglot.main.Options;
import polyglot.main.UsageError;

import polyglot.ext.jl5.JL5Options;

import java.util.Set;

public class PandaOptions extends JL5Options {
  public boolean translatePanda = true;
  public boolean preserveTypes = true;

  public PandaOptions(ExtensionInfo extension) {
    super(extension);
  }

  @Override
  protected void populateFlags(Set<OptFlag<?> > flags) {
    super.populateFlags(flags);

    flags.add(
        new Switch(
          new String[]{ "-dontTranslatePanda", "--dontTranslatePanda" },
          "Do not translate Panda features to Java features",
          false
          ));

    flags.add(
        new Switch(
          new String[]{ "-dontPreserveTypes", "--dontPreserveTypes" },
          "Do not translate preserve types from Panda, removing overhead.",
          false
          ));

  }

  @Override
  protected void handleArg(Arg<?> arg) throws UsageError {
    if (arg.flag().ids().contains("-dontTranslatePanda")) {
      this.translatePanda = (Boolean) arg.value();
    } else if (arg.flag().ids().contains("-dontPreserveTypes")) {
      this.preserveTypes = (Boolean) arg.value();
    } else {
      super.handleArg(arg);
    }
  }

}
