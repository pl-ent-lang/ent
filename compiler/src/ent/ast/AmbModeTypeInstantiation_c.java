package ent.ast;

import ent.types.EntClassType;
import ent.types.ModeType;
import ent.types.ModeTypeVariable;
import ent.types.ModeSubstParsedClassType;
import ent.types.ModeSubstSubstClassType;
import ent.types.EntTypeSystem;

import polyglot.ast.Ambiguous;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.ast.TypeNode_c;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.util.CodeWriter;
import polyglot.util.CollectionUtil;
import polyglot.util.Copy;
import polyglot.util.ListUtil;
import polyglot.util.Position;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class AmbModeTypeInstantiation_c extends ModeTypeNode_c implements AmbModeTypeInstantiation {

  protected TypeNode base;
  protected List<ModeTypeNode> modeTypeArgs;

  public AmbModeTypeInstantiation_c(Position pos, TypeNode base, List<ModeTypeNode> modeTypeArgs) {
    super(pos, null);
    this.base = base;
    this.modeTypeArgs = CollectionUtil.nonNullList(modeTypeArgs);
  }

  // Property Methods
  public TypeNode base() { return this.base; }

  public AmbModeTypeInstantiation base(TypeNode base) { return this.base(this, base); }

  public <N extends AmbModeTypeInstantiation_c> N base(N n, TypeNode base) {
    if (this.base == base)
      return n;
    n = this.copyIfNeeded(n);
    n.base = base;
    return n;
  }

  public List<ModeTypeNode> modeTypeArgs() { return this.modeTypeArgs; }

  public AmbModeTypeInstantiation modeTypeArgs(List<ModeTypeNode> modeTypeArgs) {
    return this.modeTypeArgs(this, modeTypeArgs);
  }

  public <N extends AmbModeTypeInstantiation_c> N modeTypeArgs(N n,
                                                               List<ModeTypeNode> modeTypeArgs) {
    if (CollectionUtil.equals(this.modeTypeArgs, modeTypeArgs))
      return n;
    n = this.copyIfNeeded(n);
    n.modeTypeArgs = ListUtil.copy(modeTypeArgs, true);
    return n;
  }

  public boolean isImplicitMode() { return this.modeTypeArgs().isEmpty(); }

  // Node Methods
  protected <N extends AmbModeTypeInstantiation_c>
      N reconstruct(N n, TypeNode base, List<ModeTypeNode> modeTypeArgs) {
    n = this.base(n, base);
    n = this.modeTypeArgs(n, modeTypeArgs);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    TypeNode base = (TypeNode)visitChild(this.base(), v);
    List<ModeTypeNode> modeTypeArgs = visitList(this.modeTypeArgs(), v);
    return this.reconstruct(this, base, modeTypeArgs);
  }

  @Override
  protected boolean shouldDisambiguate() {
    if (!this.base().isDisambiguated()) {
      return false;
    }
    for (ModeTypeNode n : this.modeTypeArgs()) {
      if (!n.isDisambiguated()) {
        return false;
      }
    }
    return true;
  }

  public boolean validModeTypeArgs(List<Type> mtArgs, EntTypeSystem ts) {
    Type bt = this.base().type();

    if (!(bt instanceof EntClassType)) {
      // Non class types can only be instantiated with one mode type arg
      return mtArgs.size() == 1;
    }

    // NOTE : Expand a single wildcard to allow inference
    EntClassType ct = (EntClassType)bt;
    if (mtArgs.size() == 1 && ts.WildcardModeType() == mtArgs.get(0)) {
      for (int i = 1; i < ct.modeTypeVars().size(); i++) {
        mtArgs.add(ts.WildcardModeType());
      }
    }

    return ct.modeTypeVars().size() == mtArgs.size();
  }

  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    if (!this.shouldDisambiguate()) {
      return this;
    }

    EntTypeSystem ts = (EntTypeSystem)sc.typeSystem();

    List<Type> mtArgs = new ArrayList<Type>();
    if (this.isImplicitMode()) {
      // Throw in a wildcard type and forward to the subst engine
      mtArgs.add(ts.WildcardModeType());
    }

    for (ModeTypeNode n : this.modeTypeArgs()) {
      mtArgs.add(n.type());
    }

    // NOTE: We can check size here, but anything else needs to wait util
    // the typecheck pass.
    if (!this.validModeTypeArgs(mtArgs, ts)) {
      throw new SemanticException(this.base().type() +
                                  " cannot be instantiated with mode type arguments");
    }

    Type st = ts.createModeSubst(this.base().type(), mtArgs);
    return sc.nodeFactory().CanonicalTypeNode(this.position(), st);
  }

  @Override
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {}
}
