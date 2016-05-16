package ent.ast;

import ent.types.ModeOrderingInstance;

import polyglot.ast.Id;
import polyglot.ast.Term;
import polyglot.ast.ClassDecl;

import java.util.List;

public interface ModesDecl extends ClassDecl {

  List<ModeOrder> orders();

  ModeOrderingInstance modeOrderingInstance();
  ModesDecl modeOrderingInstance(ModeOrderingInstance oi);
}
