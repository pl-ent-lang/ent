package panda.ast;

import panda.types.PandaTypeSystem;
import panda.types.ModeType;
import panda.types.ModeSubstType;

import polyglot.ast.Node;
import polyglot.ast.LocalDecl;
import polyglot.ast.TypeNode;
import polyglot.types.ReferenceType;
import polyglot.types.Type;
import polyglot.types.SemanticException;
import polyglot.visit.TypeChecker;
import polyglot.visit.Translator;
import polyglot.visit.PrettyPrinter;
import polyglot.util.CodeWriter;

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

  @Override
  public void translate(CodeWriter w, Translator tr) {
    superLang().translate(this.node(), w, tr);

    // Need to save information in the type table. Let optimization
    // passes remove uneeded information, assume that anything
    // that reaches this stage must be saved.
    LocalDecl n = (LocalDecl) this.node();

    ModeSubstType st = (ModeSubstType) n.type().type();

    if (!(st.baseType() instanceof ReferenceType)) {
      // Only need type information for types that can have
      // mcases, just exit for now, optimztion pass will fix later.
      return;
    }

    ModeType mt = (ModeType) st.modeType();
    w.newline();
    w.write("PandaTypeTable.put(");
    w.write(n.name() + ", PandaMode." + mt.runtimeCode());
    w.write(");");

  }

}
