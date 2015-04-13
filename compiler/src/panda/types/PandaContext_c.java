package panda.types;

import polyglot.ast.Lang;
import polyglot.types.Context;
import polyglot.types.Context_c;
import polyglot.types.TypeSystem;
import polyglot.ext.jl5.types.JL5Context_c;

import java.util.Map;
import java.util.HashMap;

public class PandaContext_c extends JL5Context_c implements PandaContext {

  private Map<String, ModeTypeVariable> modeTypeVars;

  public PandaContext_c(Lang lang, TypeSystem ts) {
    super(lang, ts);
    this.modeTypeVars = new HashMap<String, ModeTypeVariable>();
  }

  @Override
  protected Context_c push() {
    PandaContext_c context = (PandaContext_c) super.push();
    context.modeTypeVars = new HashMap<String, ModeTypeVariable>();
    return context;
  }

  @Override
  public PandaTypeSystem typeSystem() {
    return (PandaTypeSystem) this.ts;
  }

  // Property Methods
  public Map<String, ModeTypeVariable> modeTypeVars() {
    return this.modeTypeVars;
  }

  public void modeTypeVars(Map<String, ModeTypeVariable> modeTypeVars) {
    this.modeTypeVars = modeTypeVars;
  }

  public void addModeTypeVariable(ModeTypeVariable modeTypeVar) {
    this.modeTypeVars().put(modeTypeVar.name(), modeTypeVar);
  }

  public ModeTypeVariable findModeTypeVariableInThisScope(String name) {
    if (this.modeTypeVars().containsKey(name)) {
      return this.modeTypeVars().get(name);
    }
    if (this.outer != null) {
      return ((PandaContext)this.outer).findModeTypeVariableInThisScope(name);
    }
    return null;
  }

}
