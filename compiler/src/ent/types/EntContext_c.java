package ent.types;

import polyglot.ast.Lang;
import polyglot.types.Context;
import polyglot.types.Context_c;
import polyglot.types.TypeSystem;
import polyglot.ext.jl5.types.JL5Context_c;

import java.util.Map;
import java.util.HashMap;

public class EntContext_c extends JL5Context_c implements EntContext {

  private Map<String, ModeTypeVariable> modeTypeVars;
  private AttributeInstance currentAttribute = null;

  public static final Kind ATTRIBUTE = new Kind("attribute");

  public EntContext_c(Lang lang, TypeSystem ts) {
    super(lang, ts);
    this.modeTypeVars = new HashMap<String, ModeTypeVariable>();
  }

  @Override
  protected Context_c push() {
    EntContext_c context = (EntContext_c) super.push();
    context.modeTypeVars = new HashMap<String, ModeTypeVariable>();
    return context;
  }

  @Override
  public EntTypeSystem typeSystem() {
    return (EntTypeSystem) this.ts;
  }

  // Property Methods
  public Map<String, ModeTypeVariable> modeTypeVars() {
    return this.modeTypeVars;
  }

  public void modeTypeVars(Map<String, ModeTypeVariable> modeTypeVars) {
    this.modeTypeVars = modeTypeVars;
  }


  public AttributeInstance currentAttribute() {
    return this.currentAttribute;
  }

  public void currentAttribute(AttributeInstance currentAttribute) {
    this.currentAttribute = currentAttribute;
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
    return this.kind == CODE && (this.code instanceof AttributeInstance);
  }

}
