package panda.ast;

import panda.types.PandaContext;
import panda.types.ModeTypeVariable;
import panda.types.PandaTypeSystem;
import panda.types.PandaParsedClassType;
import panda.util.PandaUtil;

import polyglot.ast.ClassDecl;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.types.Context;
import polyglot.types.Type;
import polyglot.types.SemanticException;
import polyglot.util.CollectionUtil;
import polyglot.util.Copy;
import polyglot.util.ListUtil;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeChecker;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class PandaClassDeclExt extends PandaExt {

  protected List<ModeParamTypeNode> modeParams = Collections.emptyList();

  // Property Methods
  public List<ModeParamTypeNode> modeParams() {
    return this.modeParams;
  }

  public Node modeParams(List<ModeParamTypeNode> modeParams) {
    return this.modeParams(this.node(), modeParams);
  }

  public <N extends Node> N modeParams(N n, List<ModeParamTypeNode> modeParams) {
    PandaClassDeclExt ext = (PandaClassDeclExt) PandaExt.ext(n);
    if (CollectionUtil.equals(ext.modeParams,modeParams)) return n;
    if (this.node() == n) {
      n = Copy.Util.copy(n);
      ext = (PandaClassDeclExt) PandaExt.ext(n);
    }
    ext.modeParams = ListUtil.copy(modeParams, true); 
    return n;
  }

  // Node Methods
  protected <N extends Node> N reconstruct(N n, List<ModeParamTypeNode> modeParams) {
    n = this.modeParams(n, modeParams);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    Node n = superLang().visitChildren(this.node(), v);
    List<ModeParamTypeNode> modeParams = visitList(this.modeParams(), v);
    return this.reconstruct(n, modeParams);
  }

  @Override
  public Context enterChildScope(Node child, Context c) {
    PandaContext ctx = (PandaContext) superLang().enterChildScope(this.node(), child, c);
    for (ModeParamTypeNode t : this.modeParams()) {
      ctx.addModeTypeVariable((ModeTypeVariable) t.type());
    }
    return ctx;
  }

  @Override
  public Node buildTypes(TypeBuilder tb) throws SemanticException {
    ClassDecl decl = (ClassDecl) superLang().buildTypes(this.node(), tb);

    PandaTypeSystem ts = (PandaTypeSystem) tb.typeSystem();
    PandaParsedClassType ct = (PandaParsedClassType) decl.type();

    if (this.modeParams() != null && !this.modeParams().isEmpty()) {
      List<ModeTypeVariable> mtVars = 
        new ArrayList<ModeTypeVariable>(this.modeParams().size());
      Set<String> mtVarCheck = new HashSet<>();

      for (ModeParamTypeNode n : this.modeParams()) {
        // Check and catch duplicate error as early as possible
        if (mtVarCheck.contains(n.name())) {
          throw new SemanticException("Duplicate mode type variable declaration.",
                                      n.position());
        }
        mtVarCheck.add(n.name());

        ModeTypeVariable mtVar = (ModeTypeVariable) n.type();
        mtVar.declaringClass(ct);
        mtVars.add(mtVar);
      }
      ct.modeTypeVars(mtVars);
    } 

    return decl;
  } 
  
}
