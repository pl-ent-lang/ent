package ent.runtime;

public class ENT_RuntimeException extends RuntimeException {
  public ENT_RuntimeException(String msg) { super(msg); }

  public ENT_RuntimeException(String msg, int m, int lb, int ub) {
    super(msg + " - Resolved to @mode<" + Integer.toString(m) + "> with bounds ?mode[" +
          Integer.toString(lb) + "," + Integer.toString(ub) + "].");
  }
}
