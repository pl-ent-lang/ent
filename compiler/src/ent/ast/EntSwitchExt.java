package ent.ast;

import polyglot.ast.*;
import polyglot.ext.jl5.ast.*;
import polyglot.types.*;
import polyglot.visit.*;
import polyglot.ext.jl5.types.*;
import polyglot.ext.jl7.ast.*;

public class EntSwitchExt extends EntExt implements JL5SwitchOps {

  @Override
  public Switch node() {
    return (Switch)super.node();
  }

  @Override
  public boolean isAcceptableSwitchType(Type type) {
    JL5TypeSystem ts = (JL5TypeSystem)type.typeSystem();
    if (ts.String().typeEquals(type)) {
      return true;
    }
    return J7Lang_c.lang(pred()).isAcceptableSwitchType(this.node(), type);
  }
}
