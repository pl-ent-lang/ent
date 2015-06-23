package panda.ast;

import panda.types.ModeOrderingInstance;

import polyglot.ast.Id;
import polyglot.ast.Term;
import polyglot.ast.ClassDecl;

import java.util.List;

public interface ModesDecl extends ClassDecl {

  public List<ModeOrder> orders();

  public ModeOrderingInstance modeOrderingInstance();
   
}
