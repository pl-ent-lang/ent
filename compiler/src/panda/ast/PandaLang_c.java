package panda.ast;

import panda.ast.*;
import panda.visit.*;

import polyglot.ast.*;
import polyglot.util.InternalCompilerError;

import polyglot.ext.jl5.ast.*;
import polyglot.ext.jl7.ast.J7Lang_c;

public class PandaLang_c extends J7Lang_c implements PandaLang {
  public static final PandaLang_c instance = new PandaLang_c();

  public static PandaLang lang(NodeOps n) {
    while (n != null) {
      Lang lang = n.lang();
      if (lang instanceof PandaLang) return (PandaLang) lang;
      if (n instanceof Ext)
        n = ((Ext) n).pred();
      else return null;
    }
    throw new InternalCompilerError("Impossible to reach");
  }

  protected PandaLang_c() {
  }

  protected static PandaExt pandaExt(Node n) {
    return PandaExt.ext(n);
  }

  @Override
  protected NodeOps NodeOps(Node n) {
    return pandaExt(n);
  }

  @Override
  protected NewOps NewOps(New n) {
    return (NewOps) pandaExt(n);
  } 

  @Override
  protected JL5CaseOps JL5CaseOps(Case n) {
    return (JL5CaseOps) pandaExt(n);
  }

  @Override
  protected JL5SwitchOps JL5SwitchOps(Switch n) {
    return (JL5SwitchOps) pandaExt(n);
  }

  protected PandaOps PandaOps(Node n) {
    return pandaExt(n);
  }

  public final TypePreserver typePreserveEnter(Node n, TypePreserver tp) {
    return this.PandaOps(n).typePreserveEnter(tp);
  }

  public final Node typePreserve(Node n, TypePreserver tp) {
    return this.PandaOps(n).typePreserve(tp);
  }

}
