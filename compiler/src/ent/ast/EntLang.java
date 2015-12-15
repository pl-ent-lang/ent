package ent.ast;

import ent.ast.*;
import ent.visit.*;

import polyglot.ast.*;
import polyglot.ext.jl7.ast.*;

public interface EntLang extends J7Lang {

  TypePreserver typePreserveEnter(Node n, TypePreserver tp);

  Node typePreserve(Node n, TypePreserver tp);

}
