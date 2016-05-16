package ent.runtime;

public class ENT_Closure {
  private Integer[] modeVars;

  public ENT_Closure(Integer[] modeVars) { this.modeVars = modeVars; }

  public int getModeVar(int i) { return this.modeVars[i]; }

  public void setModeVar(int i, int mode) { this.modeVars[i] = mode; }
}
