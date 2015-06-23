package panda.ast;

import panda.visit.*;

import polyglot.ast.*;

public interface PandaOps extends NodeOps {

  TypePreserver typePreserveEnter(TypePreserver tp);

  Node typePreserve(TypePreserver tp);

}
