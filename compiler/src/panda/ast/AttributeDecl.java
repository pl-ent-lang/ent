package panda.ast;

import polyglot.ast.Block;

import polyglot.ast.CodeDecl;

public interface AttributeDecl extends CodeDecl {

  Block body();
  AttributeDecl body(Block body);

}
