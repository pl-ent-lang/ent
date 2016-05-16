package ent;

import polyglot.lex.Lexer;
import ent.parse.Lexer_c;
import ent.parse.Grm;
import ent.ast.*;
import ent.types.*;
import polyglot.ast.*;
import polyglot.frontend.*;
import polyglot.main.*;
import polyglot.types.*;
import polyglot.util.*;

import java.io.*;
import java.util.Set;

import polyglot.ext.jl5.ast.JL5ExtFactory_c;
import polyglot.ext.jl5.translate.JL5OutputExtensionInfo;

import polyglot.ext.jl7.JL7ExtensionInfo;
import polyglot.ext.jl7.ast.JL7ExtFactory_c;

/**
 * Extension information for ent extension.
 */
public class EntExtensionInfo extends JL7ExtensionInfo {
  static {
    // force Topics to load
    @SuppressWarnings("unused") Topics t = new Topics();
  }

  protected polyglot.frontend.ExtensionInfo outputExtensionInfo;

  @Override
  public String defaultFileExtension() {
    return "pan";
  }

  @Override
  public String[] defaultFileExtensions() {
    String ext = defaultFileExtension();
    return new String[] {ext, "java"};
  }

  @Override
  public String compilerName() {
    return "entc";
  }

  @Override
  public Parser parser(Reader reader, Source source, ErrorQueue eq) {
    Lexer lexer = new Lexer_c(reader, source, eq);
    Grm grm = new Grm(lexer, ts, nf, eq);
    return new CupParser(grm, source, eq);
  }

  @Override
  public Set<String> keywords() {
    return new Lexer_c(null).keywords();
  }

  @Override
  protected NodeFactory createNodeFactory() {
    return new EntNodeFactory_c(EntLang_c.instance,
                                new EntExtFactory_c(new JL7ExtFactory_c(new JL5ExtFactory_c())));
  }

  @Override
  protected TypeSystem createTypeSystem() {
    return new EntTypeSystem_c();
  }

  @Override
  public Scheduler createScheduler() {
    return new EntScheduler(this);
  }

  @Override
  public Options createOptions() {
    return new EntOptions(this);
  }

  @Override
  public polyglot.frontend.ExtensionInfo outputExtensionInfo() {
    if (this.outputExtensionInfo == null) {
      this.outputExtensionInfo = new JL5OutputExtensionInfo(this);
    }
    return outputExtensionInfo;
  }
}
