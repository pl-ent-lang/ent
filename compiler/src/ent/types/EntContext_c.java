package ent.types;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.ext.jl5.types.*;

import java.util.Map;
import java.util.HashMap;

public class EntContext_c extends JL5Context_c implements EntContext {

  private Map<String, ModeTypeVariable> modeTypeVars;

  public EntContext_c(Lang lang, TypeSystem ts) {
    super(lang, ts);
    this.modeTypeVars = new HashMap<String, ModeTypeVariable>();
  }

  @Override
  protected Context_c push() {
    EntContext_c context = (EntContext_c)super.push();
    context.modeTypeVars = new HashMap<String, ModeTypeVariable>();
    return context;
  }

  @Override
  public EntTypeSystem typeSystem() {
    return (EntTypeSystem)this.ts;
  }

  // Property Methods
  public Map<String, ModeTypeVariable> modeTypeVars() { return this.modeTypeVars; }

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
      return ((EntContext)this.outer).findModeTypeVariableInThisScope(name);
    }
    return null;
  }

  public boolean isAttribute() {
    return (this.code instanceof AttributeInstance);
  }

  public AttributeInstance currentAttribute() {
    return (AttributeInstance) this.code;
  }

  public EntContext attributorParent() {
    EntContext outer = (EntContext) this.pop();
    if (this.kind == CODE && this.isAttribute()) {
      return outer;
    }

    if (outer == null) {
      return outer;
    } else {
      return outer.attributorParent();
    }
  }

}
