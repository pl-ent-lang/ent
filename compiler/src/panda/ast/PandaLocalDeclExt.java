package panda.ast;

import panda.types.*;
import panda.translate.*;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.visit.*;
import polyglot.util.*;
import polyglot.qq.*;

import java.util.ArrayList;
import java.util.List;

public class PandaLocalDeclExt extends PandaExt {

  protected LocalDecl reconstruct(LocalDecl n, Type t) {
    // Uggly, but will work for now
    TypeNode tn = n.type().type(t);
    n = n.type(tn);
    return n;
  }

  @Override
  public Node typeCheck(TypeChecker tc) throws SemanticException {
    LocalDecl n = (LocalDecl) this.node();
    ModeSubstType st = (ModeSubstType) n.type().type();
    PandaTypeSystem ts = (PandaTypeSystem) tc.typeSystem();
    
    if (st.modeType() == ts.WildcardModeType() &&
        n.init() != null) {
      // Right now this will trigger inference
      ModeSubstType inf = (ModeSubstType) n.init().type();
      
      // Allow all wildcard types to be inferenced
      List<Type> mtArgs = st.modeTypeArgs();
      List<Type> infArgs = inf.modeTypeArgs();
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

      n = this.reconstruct(n, st);
    }

    return superLang().typeCheck(n, tc);
  }

}
