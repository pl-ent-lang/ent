package panda.ast;

import panda.types.ModeType;
import panda.types.PandaTypeSystem;

import polyglot.ast.Node;
import polyglot.ast.Id;
import polyglot.ast.Term;
import polyglot.ast.Term_c;
import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.visit.CFGBuilder;
import polyglot.visit.TypeBuilder;

import java.util.List;

public class ModeOrder_c extends Term_c implements ModeOrder {

  private String lower;
  private String upper;

  public ModeOrder_c(Position pos, String lower, String upper) {
    super(pos);
    this.lower = lower;
    this.upper = upper;
  }

  public String lower() {
    return this.lower;
  }

  public void lower(String lower) {
    this.lower = lower;
  }

  public String upper() {
    return this.upper;
  }

  public void upper(String upper) {
    this.upper = upper;
  }

  // TODO : firstChild & acceptCFG not needed to visit the Id's
  // makes me think this shouldn't be a term.
  @Override
  public Term firstChild() {
    return null;
  }

  @Override
  public <T> List<T> acceptCFG(CFGBuilder<?> v, List<T> succs) {
    // TODO : I'll need to figure out exactly how the CFG visit
    // works
    return succs;
  }

}
