package panda.ast;

import panda.types.PandaClassType;
import panda.types.Mode;
import panda.types.ModeType;
import panda.types.ModeTypeVariable;
import panda.types.ModeSubstParsedClassType;
import panda.types.ModeSubstSubstClassType;
import panda.types.PandaTypeSystem;

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

  public AmbModeTypeInstantiation_c(Position pos,
                                    TypeNode base,
                                    List<ModeTypeNode> modeTypeArgs) {
    super(pos, null);
    this.base = base;
    this.modeTypeArgs = CollectionUtil.nonNullList(modeTypeArgs);
  }

  // Property Methods
  public TypeNode base() {
    return this.base;
  }

  public AmbModeTypeInstantiation base(TypeNode base) {
    return this.base(this, base);
  }

  public <N extends AmbModeTypeInstantiation_c> N base(N n, TypeNode base) {
    if (this.base == base) return n;
    n = this.copyIfNeeded(n);
    n.base = base;
    return n;
  }

  public List<ModeTypeNode> modeTypeArgs() {
    return this.modeTypeArgs;
  }

  public AmbModeTypeInstantiation modeTypeArgs(List<ModeTypeNode> modeTypeArgs) {
    return this.modeTypeArgs(this, modeTypeArgs);
  }

  public <N extends AmbModeTypeInstantiation_c> N modeTypeArgs(N n, List<ModeTypeNode> modeTypeArgs) {
    if (CollectionUtil.equals(this.modeTypeArgs,modeTypeArgs)) return n;
    n = this.copyIfNeeded(n);
    n.modeTypeArgs = ListUtil.copy(modeTypeArgs, true);
    return n;
  }

  public boolean isImplicitMode() {
    return this.modeTypeArgs().isEmpty();
  }

  // Node Methods
  protected <N extends AmbModeTypeInstantiation_c> N reconstruct(N n, TypeNode base, List<ModeTypeNode> modeTypeArgs) {
    n = this.base(n, base);
    n = this.modeTypeArgs(n, modeTypeArgs);
    return n;
  }

  @Override
  public Node visitChildren(NodeVisitor v) {
    TypeNode base = (TypeNode) visitChild(this.base(), v);
    List<ModeTypeNode> modeTypeArgs = visitList(this.modeTypeArgs(), v);
    return this.reconstruct(this, base, modeTypeArgs);
  }

  private boolean shouldDisambiguate() {
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

  public boolean validModeTypeArgs(List<Mode> mtArgs) {
    Type bt = this.base().type();

    if (!(bt instanceof PandaClassType)) {
      // Non class types can only be instantiated with one mode type arg
      return mtArgs.size() == 1;
    }

    return ((PandaClassType) bt).modeTypeVars().size() == mtArgs.size();
  }

  public boolean validDynamicModeType() {
    Type bt = this.base().type();
    if (!(bt instanceof PandaClassType)) {
      return false;
    }
    PandaClassType ct = (PandaClassType) bt;
    return ct.attributeInstance() != null;
  }

  @Override
  public Node disambiguate(AmbiguityRemover sc) throws SemanticException {
    if (!this.shouldDisambiguate()) {
      return this;
    }

    PandaTypeSystem ts = (PandaTypeSystem) sc.typeSystem();

    List<Mode> mtArgs = new ArrayList<Mode>();
    if (this.isImplicitMode()) {
      // Throw in a wildcard type and forward to the subst engine
      mtArgs.add(ts.WildcardModeType());
    }

    for (ModeTypeNode n : this.modeTypeArgs()) {
      mtArgs.add((Mode) n.type());
    }

    if (!this.validModeTypeArgs(mtArgs)) {
      throw new SemanticException(this.base().type() + " cannot be instantiated with mode type arguments.");
    }

    // Dynamic mode type can only be used on classes that have an attributor
    if (mtArgs.get(0) == ts.DynamicModeType() && !this.validDynamicModeType()) {
      throw new SemanticException(this.base().type() + " cannot be instantiated with a dynamic mode type. Implement an attributor.");
    }

    Type st = ts.createModeSubst(this.base().type(), mtArgs);
    if (st == null) {
      throw new SemanticException("Unable to create mode substition due to constraints.");
    }
    return sc.nodeFactory().CanonicalTypeNode(this.position(), st);
  }

  @Override
  public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
  }


}
