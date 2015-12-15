package ent.ast;

import ent.visit.*;

import polyglot.ast.*;

public interface EntOps extends NodeOps {

  TypePreserver typePreserveEnter(TypePreserver tp);

  Node typePreserve(TypePreserver tp);

}
