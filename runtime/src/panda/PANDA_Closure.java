package panda.runtime;

public class PANDA_Closure {
  private Integer[] modeVars;

  public PANDA_Closure(Integer[] modeVars) {
    this.modeVars = modeVars;
  }

  public int getModeVar(int i) {
    return this.modeVars[i];
  }

  public void setModeVar(int i, int mode) {
    this.modeVars[i] = mode;
  }

}
