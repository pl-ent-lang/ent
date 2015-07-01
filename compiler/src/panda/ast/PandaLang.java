package panda.ast;

import panda.ast.*;
import panda.visit.*;

import polyglot.ast.*;
import polyglot.ext.jl7.ast.*;

public interface PandaLang extends J7Lang {

  TypePreserver typePreserveEnter(Node n, TypePreserver tp);

  Node typePreserve(Node n, TypePreserver tp);

  boolean needsTypePreservation(Node n);

}
