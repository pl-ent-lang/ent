package ent.types;

import polyglot.types.*;
import polyglot.util.*;

import polyglot.ext.jl5.types.*;

import java.util.Arrays;

public class EntTypeVariable_c extends TypeVariable_c implements EntTypeVariable {

  public EntTypeVariable_c(EntTypeSystem ts, Position pos, String id, ReferenceType upperBound) {
    super(ts, pos, id, upperBound);
    if (this.upperBound == ts.Object()) {
      this.upperBound = (ReferenceType)ts.createModeSubst(
          ts.Object(), Arrays.<Type>asList(ts.WildcardModeType()));
    }
  }
}
