package ent.types;

import polyglot.types.*;
import polyglot.util.*;
import polyglot.ext.param.types.*;
import polyglot.ext.jl5.types.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntSubst_c extends JL5Subst_c implements EntSubst {

  public EntSubst_c(ParamTypeSystem<TypeVariable, ReferenceType> ts,
                    Map<TypeVariable, ? extends ReferenceType> subst) {
    super(ts, subst);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Subst) {
      Map<TypeVariable, ReferenceType> substitutions =
          ((Subst<TypeVariable, ReferenceType>)o).substitutions();
      for (Map.Entry<TypeVariable, ReferenceType> e : substitutions.entrySet()) {
        ReferenceType ot = this.subst.get(e.getKey());
        if (ot == null || !ot.typeEquals(e.getValue())) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  // FIXME: (From Ent) Probably a bad equals issue with subst types. Bug
  // due to equals hack that allows ModeSubstType and Type to be equals if
  // bases are equal.

  // NOTE : This is an ugly hack to get mode types onto JL5SubstClassTypes,
  // but there is no nice solution without type ext objects.
  @Override
  protected ClassType substClassTypeImpl(ClassType t) {
    EntTypeSystem ts = (EntTypeSystem)this.ts;

    // Don't bother trying to substitute into a non-JL5 class.
    if (!(t instanceof JL5ClassType)) {
      return t;
    }

    if (t instanceof ModeSubstClassType) {

      // NOTE: This is here just until I am confident this is the right way to handle
      ModeSubstClassType st = (ModeSubstClassType)t;
      return (ClassType)ts.createModeSubst(ts.subst(st.baseType(), this.substitutions()),
                                           new ArrayList<>(st.modeTypeArgs()));
    }

    if (t instanceof EntRawClass) {
      // don't substitute raw classes
      return t;
    }
    if (t instanceof EntSubstClassType) {
      // this case should be impossible
      throw new InternalCompilerError("Should have no JL5SubstClassTypes");
    }

    if (t instanceof EntParsedClassType) {
      EntParsedClassType pct = (EntParsedClassType)t;
      List<TypeVariable> typeVars = ts.classAndEnclosingTypeVariables(pct);
      // are the type variables of pct actually relevant to this subst? If not, then return the pct.
      boolean typeVarsRelevant = false;
      for (TypeVariable tv : typeVars) {
        if (this.substitutions().containsKey(tv)) {
          typeVarsRelevant = true;
          break;
        }
      }
      if (!typeVarsRelevant) {
        // no parameters to be instantiated!
        return pct;
      }

      // NOTE : This is the change
      return new EntSubstClassType_c(ts, t.position(), pct, this);
    }

    if (t instanceof EntDiamondType) {
      // We won't substitute for now. See what happens.
      return t;
    }

    throw new InternalCompilerError("Don't know how to handle class type " + t.getClass());
  }
}
