package simple;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class Reflect {

  private void foo() {
    Method meth = null;
    System.out.println(meth.getParameterTypes().length);

    Constructor cons = null;
    System.out.println(cons.getParameterTypes().length);
  }
}
