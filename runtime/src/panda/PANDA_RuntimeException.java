package panda.runtime;

public class PANDA_RuntimeException extends RuntimeException {
  public PANDA_RuntimeException(String msg) {
    super(msg);
  }

  public PANDA_RuntimeException(String msg, int m, int lb, int ub) {
    super(msg + " - Resolved to @mode<" + Integer.toString(m) + "> with bounds ?mode[" + Integer.toString(lb) + "," + Integer.toString(ub) + "].");

  }
}
