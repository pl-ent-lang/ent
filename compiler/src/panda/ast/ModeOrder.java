package panda.ast;

import polyglot.ast.Id;
import polyglot.ast.Term;

public interface ModeOrder extends Term {

  public String lower();
  public void lower(String lower);

  public String upper();
  public void upper(String upper);
   
}
