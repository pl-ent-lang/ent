package ent.types.inference;

import ent.types.*;

import polyglot.types.*;
import polyglot.util.*;
import polyglot.ext.jl5.types.*;
import polyglot.ext.jl5.types.inference.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;

public class EntLubType_c extends LubType_c implements EntLubType {

  public EntLubType_c(TypeSystem ts, Position pos, List<ReferenceType> lubElems) {
    super(ts, pos, lubElems);
  }

  private ReferenceType lub(ReferenceType... a) {
    List<ReferenceType> l = new ArrayList<>();
    JL5TypeSystem ts = (JL5TypeSystem)this.ts;
    for (ReferenceType t : a) {
      l.add(t);
    }
    return ts.lub(this.position, l);
  }

  @Override
  public ReferenceType calculateLub() {
    if (lubCalculated == null) {
      lubCalculated = lub_force();
    }
    return lubCalculated;
  }

  private ReferenceType lub_force() {
    EntTypeSystem ts = (EntTypeSystem)this.ts;

    // st is the set of all supertypes of the lubElems.
    Set<ReferenceType> st = new LinkedHashSet<>();

    // est is the intersection of all erased supertypes of lubElems.
    // That is, during the loop below, it is the intersection of
    // the sets of erased supertypes of elements considered so far.
    // By the end of the loop it will be mec, the minimal erased
    // candidate set (JLS 3rd ed, 15.12.2, p 464.)
    Set<ReferenceType> est = null;

    for (ReferenceType u : lubElems) {
      // TODO : To fix this hack, we need our own LinkedHashSet that
      // uses typeEquals and not equals OR a method that does a
      // complete erasue of modes
      Set<ReferenceType> ent_st = new LinkedHashSet<>();
      for (ReferenceType t : ts.allAncestorsOf(u)) {
        if (t instanceof ModeSubstType) {
          ent_st.add((ReferenceType)((ModeSubstType)t).baseType());
        } else {
          ent_st.add(t);
        }
      }
      st.addAll(ent_st);

      // Use ent_st in place of the subsequent allAncestorsOf calls found
      // in JL5::LubType_c

      List<ReferenceType> u_supers = new ArrayList<>();
      for (ReferenceType u_super : ent_st) {
        if (u_super instanceof RawClass) {
          u_supers.add(((RawClass)u_super).erased());
        } else
          u_supers.add(u_super);
      }

      Set<ReferenceType> est_of_u = new LinkedHashSet<>();
      for (ReferenceType super_of_u : u_supers) {
        if (super_of_u instanceof JL5SubstClassType) {
          JL5SubstClassType g = (JL5SubstClassType)super_of_u;
          est_of_u.add(g.base());
        } else
          est_of_u.add(super_of_u);
      }

      if (est == null) {
        est = new LinkedHashSet<>();
        est.addAll(est_of_u);
      } else {
        est.retainAll(est_of_u);
      }
    }
    Set<ReferenceType> mec = new LinkedHashSet<>(est);

    for (ReferenceType e1 : est) {
      for (ReferenceType e2 : est) {
        if (!ts.equals(e1, e2) && ts.isSubtype(e2, e1)) {
          mec.remove(e1);
          break;
        }
      }
    }
    List<ReferenceType> cand = new ArrayList<>();
    for (ReferenceType m : mec) {
      List<ReferenceType> inv = new ArrayList<>();
      for (ReferenceType t : st) {
        if (ts.equals(m, t) ||
            t instanceof JL5SubstClassType && ((JL5SubstClassType)t).base().equals(m) ||
            t instanceof RawClass && ((RawClass)t).erased().base().equals(m)) {
          inv.add(t);
        }
      }
      cand.add(lci(inv));
    }
    try {
      if (ts.checkIntersectionBounds(cand, true)) {
        return ts.intersectionType(this.position, cand);
      }
    } catch (SemanticException e) {
    }
    return ts.Object();
  }

  private ReferenceType lci(List<ReferenceType> inv) {
    JL5TypeSystem ts = (JL5TypeSystem)this.ts;
    ReferenceType first = inv.get(0);
    if (inv.size() == 1 || first instanceof RawClass || first instanceof JL5ParsedClassType) {
      return first;
    }
    JL5SubstClassType res = (JL5SubstClassType)first;
    for (int i = 1; i < inv.size(); i++) {
      ReferenceType next = inv.get(i);
      if (next instanceof RawClass || next instanceof JL5ParsedClassType) {
        return next;
      }
      List<ReferenceType> lcta_args = new ArrayList<>();
      JL5SubstClassType nextp = (JL5SubstClassType)next;
      for (int argi = 0; argi < res.actuals().size(); argi++) {
        ReferenceType a1 = res.actuals().get(argi);
        ReferenceType a2 = nextp.actuals().get(argi);
        lcta_args.add(lcta(a1, a2));
      }
      try {
        res = (JL5SubstClassType)ts.instantiate(position, res.base(), lcta_args);
      } catch (SemanticException e) {
        throw new InternalCompilerError("Unexpected SemanticException", e);
      }
    }
    return res;
  }

  private ReferenceType lcta(ReferenceType a1, ReferenceType a2) {
    JL5TypeSystem ts = (JL5TypeSystem)this.ts;
    if (a1 instanceof WildCardType) {
      WildCardType a1wc = (WildCardType)a1;
      if (a2 instanceof WildCardType) {
        WildCardType a2wc = (WildCardType)a2;
        // a1 and a2 are wild cards
        if (a1wc.hasLowerBound() && a2wc.hasLowerBound()) {
          return ts.wildCardType(position, null, glb(a1wc.lowerBound(), a2wc.lowerBound()));
        }
        if (a1wc.hasLowerBound()) {
          // a1 has lower bound, a2 does not
          if (a1wc.lowerBound().equals(a2wc.upperBound())) {
            return a1wc.lowerBound();
          } else {
            return ts.wildCardType(position);
          }
        }
        if (a2wc.hasLowerBound()) {
          // a2 has lower bound, a1 does not
          if (a2wc.lowerBound().equals(a1wc.upperBound())) {
            return a2wc.lowerBound();
          } else {
            return ts.wildCardType(position);
          }
        }
        // neither a1 nor a2 has a lower bound
        return ts.wildCardType(
            position,
            ts.lub(position, CollectionUtil.list(a1wc.upperBound(), a2wc.upperBound())),
            null);
      }
      // a1 is a wildcard, a2 is not
      if (a1wc.hasLowerBound()) {
        return ts.wildCardType(position, null, glb(a1wc.lowerBound(), a2));
      } else {
        return ts.wildCardType(position, lub(a1wc.upperBound(), a2), null);
      }
    }
    // a1 is not a wildcard
    if (a2 instanceof WildCardType) {
      WildCardType a2wc = (WildCardType)a2;
      // a1 is not a wildcard, a2 is a wildcard
      if (a2wc.hasLowerBound()) {
        return ts.wildCardType(position, null, glb(a1, a2wc.lowerBound()));
      } else {
        return ts.wildCardType(position, lub(a1, a2wc.upperBound()), null);
      }
    }

    // neither a1 nor a2 is a wildcard.
    if (ts.equals(a1, a2)) {
      return a1;
    } else {
      return ts.wildCardType(position, ts.lub(position, CollectionUtil.list(a1, a2)), null);
    }
  }

  private ReferenceType glb(ReferenceType t1, ReferenceType t2) {
    JL5TypeSystem ts = (JL5TypeSystem)this.ts;
    List<ReferenceType> l = CollectionUtil.list(t1, t2);
    try {
      if (!ts.checkIntersectionBounds(l, true)) {
        return ts.Object();
      } else {
        return ts.intersectionType(position, l);
      }
    } catch (SemanticException e) {
      return ts.Object();
    }
  }
}
