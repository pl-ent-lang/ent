package super_abstract;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* POLY-BUG: The following is a bug in polyglot. javac can compile. */
public class A<V> extends FutureTask<V> {
  public A(Callable<V> c) {
    super(c);
  }

  @Override
  public boolean equals(Object other) {
    return super.equals(other);
  }
}
