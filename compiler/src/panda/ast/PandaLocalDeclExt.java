package panda.ast;

import panda.types.PandaTypeSystem;
import panda.types.ModeSubstType;

import polyglot.ast.Node;
import polyglot.ast.LocalDecl;
import polyglot.ast.TypeNode;
import polyglot.types.Type;
import polyglot.types.SemanticException;
import polyglot.visit.TypeChecker;

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
      st = st.deepCopy();
      st.modeType(inf.modeType());
      n = this.reconstruct(n, st);
    }

    return superLang().typeCheck(n, tc);
  }

}
