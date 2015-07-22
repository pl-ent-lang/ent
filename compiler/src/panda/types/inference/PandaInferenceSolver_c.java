package panda.types.inference;

import panda.types.*;

import polyglot.types.*;
import polyglot.ext.jl5.types.inference.*;
import polyglot.ext.jl5.types.*;
import polyglot.ext.jl7.types.inference.*;
import polyglot.ext.jl7.types.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PandaInferenceSolver_c extends JL7InferenceSolver_c implements InferenceSolver {

  private boolean needsInjection = false;

  public PandaInferenceSolver_c(JL5ProcedureInstance pi, List<? extends Type> actuals, JL5TypeSystem ts) {
    super(pi, actuals, ts);
  }

  @Override
  public Map<TypeVariable, ReferenceType> solve(Type expectedReturnType) {
    if (expectedReturnType instanceof ModeSubstType) {
      this.needsInjection = true;
    } else {
      this.needsInjection = false;
    }
    return super.solve(expectedReturnType);
  }

  /*
  @Override
  protected Type returnType(JL5ProcedureInstance pi) {
    PandaTypeSystem ts = (PandaTypeSystem) this.typeSystem();
    Type t = super.returnType(pi);
    if (this.needsInjection) {
      t = 
        ts.createModeSubst(
          t,
          Arrays.<Type>asList(ts.WildcardModeType())
          );
    }
    return t;
  }
  */



}

