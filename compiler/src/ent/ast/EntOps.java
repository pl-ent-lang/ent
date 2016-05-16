package ent.ast;

import ent.visit.*;

import polyglot.ast.*;
import polyglot.types.*;

public interface EntOps extends NodeOps {

  TypePreserver typePreserveEnter(TypePreserver tp);
  Node typePreserve(TypePreserver tp);

  ModeBuilder buildModesEnter(ModeBuilder tp) throws SemanticException;
  Node buildModes(ModeBuilder tp) throws SemanticException;
}
