package ent.ast;

import ent.types.*;

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
    // Stealing the show from polyglot due to the whole typeEquals/typeEquals issue
    EntTypeSystem ts = (EntTypeSystem)type.typeSystem();
    if (ts.Char().typeEquals(type) || ts.Byte().typeEquals(type) || ts.Short().typeEquals(type) ||
        ts.Int().typeEquals(type) || ts.String().typeEquals(type)) {
      return true;
    }
    if (ts.wrapperClassOfPrimitive(ts.Char()).typeEquals(type) ||
        ts.wrapperClassOfPrimitive(ts.Byte()).typeEquals(type) ||
        ts.wrapperClassOfPrimitive(ts.Short()).typeEquals(type) ||
        ts.wrapperClassOfPrimitive(ts.Int()).typeEquals(type)) {
      return true;
    }
    if (type.isClass() && JL5Flags.isEnum(type.toClass().flags())) {
      return true;
    }
    return false;
  }
}
