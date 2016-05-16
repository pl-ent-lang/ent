package ent.ast;

import ent.types.*;
import ent.translate.*;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.visit.*;
import polyglot.util.*;
import polyglot.qq.*;

import java.util.ArrayList;
import java.util.List;

public class EntLocalDeclExt extends EntExt {

  protected LocalDecl reconstruct(LocalDecl n, Type t) {
    // Uggly, but will work for now
    TypeNode tn = n.type().type(t);
    n = n.type(tn);
    return n;
  }

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    LocalDecl n = (LocalDecl)this.node();
    EntTypeSystem ts = (EntTypeSystem)tc.typeSystem();

    // MODE-NOTE: Kicking up to superLang() if not a mode subst type
    if (!(n.type().type() instanceof ModeSubstType)) {
      return superLang().typeCheck(n, tc);
    }

    ModeSubstType st = (ModeSubstType)n.type().type();
    if (st.modeType() != ts.WildcardModeType() || n.init() == null ||
        !(n.init().type() instanceof ModeSubstType)) {
      return superLang().typeCheck(n, tc);
    }

    // Right now this will trigger inference
    List<Type> mtArgs = st.modeTypeArgs();
    ModeSubstType inf = (ModeSubstType)n.init().type();
    List<Type> infArgs = inf.modeTypeArgs();

    // Allow all wildcard types to be inferenced
    if (mtArgs.size() != infArgs.size()) {
      // This is an error, just send it up to the super lang for now, it will
      // catch it
      return superLang().typeCheck(n, tc);
    }

    List<Type> newArgs = new ArrayList<>();
    for (int i = 0; i < mtArgs.size(); ++i) {
      if (mtArgs.get(i) == ts.WildcardModeType()) {
        newArgs.add(infArgs.get(i));
      } else {
        newArgs.add(mtArgs.get(i));
      }
    }

    st = st.deepCopy();
    st.modeTypeArgs(newArgs);

    // Must reset type for local instance
    LocalInstance li = n.localInstance();
    li.setType(st);

    return superLang().typeCheck(this.reconstruct(n, st), tc);
  }
}
