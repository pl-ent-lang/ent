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
  public String androidMainHint = "";

  public EntOptions(ExtensionInfo extension) { super(extension); }

  @Override
  protected void populateFlags(Set<OptFlag<?>> flags) {
    super.populateFlags(flags);

    flags.add(new Switch(new String[] {"-dontTranslateEnt", "--dontTranslateEnt"},
                         "Do not translate Ent features to Java features",
                         false));

    flags.add(new Switch(new String[] {"-dontPreserveTypes", "--dontPreserveTypes"},
                         "Do not translate preserve types from Ent, removing overhead.",
                         false));

    flags.add(new OptFlag<String>("-androidMainHint",
                                  "<fully-qualified-class>",
                                  "Provide the main activity class for an android application.") {
      @Override
      public Arg<String> handle(String[] args, int index) throws UsageError {
        return createArg(index + 1, args[index]);
      }
    });
  }

  @Override
  protected void handleArg(Arg<?> arg) throws UsageError {
    Set<String> ids = arg.flag().ids();

    if (ids.contains("-dontTranslateEnt")) {
      this.translateEnt = (Boolean)arg.value();
    } else if (ids.contains("-dontPreserveTypes")) {
      this.preserveTypes = (Boolean)arg.value();
    } else if (ids.contains("-androidMainHint")) {
       this.androidMainHint = ((String) arg.value());
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
