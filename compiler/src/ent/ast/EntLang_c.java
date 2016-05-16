package ent.ast;

import ent.ast.*;
import ent.visit.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.util.InternalCompilerError;

import polyglot.ext.jl5.ast.*;
import polyglot.ext.jl7.ast.J7Lang_c;

public class EntLang_c extends J7Lang_c implements EntLang {
  public static final EntLang_c instance = new EntLang_c();

  public static EntLang lang(NodeOps n) {
    while (n != null) {
      Lang lang = n.lang();
      if (lang instanceof EntLang)
        return (EntLang)lang;
      if (n instanceof Ext)
        n = ((Ext)n).pred();
      else
        return null;
    }
    throw new InternalCompilerError("Impossible to reach");
  }

  protected EntLang_c() {}

  protected static EntExt entExt(Node n) { return EntExt.ext(n); }

  @Override
  protected NodeOps NodeOps(Node n) {
    return entExt(n);
  }

  @Override
  protected NewOps NewOps(New n) {
    return (NewOps)entExt(n);
  }

  @Override
  protected JL5CaseOps JL5CaseOps(Case n) {
    return (JL5CaseOps)entExt(n);
  }

  @Override
  protected JL5SwitchOps JL5SwitchOps(Switch n) {
    return (JL5SwitchOps)entExt(n);
  }

  protected EntOps EntOps(Node n) { return entExt(n); }

  public final TypePreserver typePreserveEnter(Node n, TypePreserver tp) {
    return this.EntOps(n).typePreserveEnter(tp);
  }

  public final Node typePreserve(Node n, TypePreserver tp) {
    return this.EntOps(n).typePreserve(tp);
  }

  public final ModeBuilder buildModesEnter(Node n, ModeBuilder tp) throws SemanticException {
    return this.EntOps(n).buildModesEnter(tp);
  }

  public final Node buildModes(Node n, ModeBuilder tp) throws SemanticException {
    return this.EntOps(n).buildModes(tp);
  }
}
