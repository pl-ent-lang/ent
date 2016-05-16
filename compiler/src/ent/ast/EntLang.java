package ent.ast;

import ent.ast.*;
import ent.visit.*;

import polyglot.ast.*;
import polyglot.util.*;
import polyglot.types.*;
import polyglot.ext.jl7.ast.*;

public interface EntLang extends J7Lang {
  ModeBuilder buildModesEnter(Node n, ModeBuilder mb) throws SemanticException;
  Node buildModes(Node n, ModeBuilder mb) throws SemanticException;

  TypePreserver typePreserveEnter(Node n, TypePreserver tp);
  Node typePreserve(Node n, TypePreserver tp);
}
