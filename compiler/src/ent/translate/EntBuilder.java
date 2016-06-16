package ent.translate;

import ent.ast.*;
import ent.types.*;
import polyglot.ast.*;
import polyglot.frontend.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.types.Package;
import polyglot.util.*;
import polyglot.qq.*;

import java.util.Comparator;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.tools.JavaFileManager.*;
import javax.tools.*;

public class EntBuilder {
  private static EntBuilder instance = null;

  public static EntBuilder instance() {
    if (EntBuilder.instance == null) {
      EntBuilder.instance = new EntBuilder();
    }
    return EntBuilder.instance;
  }

  protected EntBuilder() {}

  private class ModeTypeComparator implements Comparator<ModeType> {
    public int compare(ModeType o1, ModeType o2) { return o1.uniqueId() - o2.uniqueId(); }
  }

  public Node buildNewArray(ExtensionInfo extInfo,
                            ExtensionInfo outInfo,
                            String base,
                            int dims,
                            List<Expr> initExprs) {
    NodeFactory nf = outInfo.nodeFactory();
    TypeSystem ts = outInfo.typeSystem();

    Expr newArr = nf.NewArray(
        Position.COMPILER_GENERATED,
        nf.AmbTypeNode(Position.COMPILER_GENERATED, nf.Id(Position.COMPILER_GENERATED, base)),
        dims,
        nf.ArrayInit(Position.COMPILER_GENERATED, initExprs));

    return newArr;
  }

  public SourceFile buildEntMode(ExtensionInfo extInfo, ExtensionInfo outInfo) {
    EntTypeSystem fromTs = (EntTypeSystem)extInfo.typeSystem();
    QQ qq = new QQ(outInfo);
    NodeFactory nf = outInfo.nodeFactory();
    TypeSystem ts = outInfo.typeSystem();

    List<ModeType> modeTypes = new ArrayList<>();
    for (Map.Entry<String, ModeType> e : fromTs.createdModeTypes().entrySet()) {
      modeTypes.add(e.getValue());
    }
    modeTypes.sort(new ModeTypeComparator());

    List<ClassMember> members = new ArrayList<>();
    List<Expr> initExprs = new ArrayList<>();
    for (ModeType mt : modeTypes) {
      Expr expr = null;
      if (mt == fromTs.DynamicModeType() || mt == fromTs.WildcardModeType()) {
        expr = nf.Field(Position.COMPILER_GENERATED,
                        nf.AmbReceiver(Position.COMPILER_GENERATED,
                                       nf.Id(Position.COMPILER_GENERATED, "ENT_Modes")),
                        nf.Id(Position.COMPILER_GENERATED, mt.compileExpr()));
      } else {
        expr =
            nf.IntLit(Position.COMPILER_GENERATED, IntLit.INT, Integer.parseInt(mt.compileExpr()));
      }

      members.add(qq.parseMember("public static final int %s = %E;", mt.compileId(), expr));

      // Preserve names of modes for better diagnostics
      if (mt.uniqueId() >= 0) {
        initExprs.add(nf.StringLit(Position.COMPILER_GENERATED, mt.name()));
      }
    }

    // Preserve names of modes for better diagnostics
    members.add(qq.parseMember("public static final String[] MODE_NAMES = %E;",
                               this.buildNewArray(extInfo, outInfo, "String", 1, initExprs)));

    ClassDecl cd = qq.parseDecl("public class EntMode { %LM }", members);

    PackageNode pkgNode = null;
    if (fromTs.modesDeclPackage() != null) {
      pkgNode = nf.PackageNode(Position.COMPILER_GENERATED,
                               ts.createPackage(fromTs.modesDeclPackage().fullName()));
    }
    SourceFile sf = nf.SourceFile(
        Position.COMPILER_GENERATED,
        pkgNode,
        Arrays.<Import>asList(
            nf.Import(Position.COMPILER_GENERATED, Import.TYPE_IMPORT_ON_DEMAND, "ent.runtime")),
        Arrays.<TopLevelDecl>asList(cd));

    return sf;
  }

  public Source buildSource(ExtensionInfo extInfo, Package pkg, String file) {
    String pkgName = pkg != null ? pkg.fullName() : "";
    Source source = null;
    try {
      Location lo = extInfo.getOptions().source_output;
      FileObject fo = extInfo.extFileManager().getFileForOutput(lo, pkgName, file, null);
      source = extInfo.createFileSource(fo, Source.Kind.COMPILER_GENERATED);
    } catch (Exception e) {
      String fullName = pkg != null ? pkgName + "." + file : file;
      throw new InternalCompilerError("Fatal, failed to get a file object for " + fullName + "!");
    }
    return source;
  }

  public New buildEntClosure(ExtensionInfo extInfo,
                             ExtensionInfo outInfo,
                             List<ModeTypeVariable> capturedMtArgs,
                             Map<ModeTypeVariable, Type> mtEnv,
                             Context ctx) {
    return this.buildEntClosure(
        extInfo.nodeFactory(), outInfo.typeSystem(), capturedMtArgs, mtEnv, ctx);
  }

  public List<Stmt> buildAndroidInjection(NodeFactory nf, TypeSystem toTs) {
    //Stmt s2 = qq.parseStmt("ENT_Util.registerAndroidContext(getApplicationContext());");

    List<Stmt> stmts = new ArrayList<>();
    Stmt s = nf.Eval(Position.COMPILER_GENERATED,
                     nf.Call(Position.COMPILER_GENERATED,
                             nf.AmbTypeNode(Position.COMPILER_GENERATED,
                                            nf.Id(Position.COMPILER_GENERATED, "ENT_Util")),
                             nf.Id(Position.COMPILER_GENERATED, "registerAndroidContext"),
                             nf.Call(Position.COMPILER_GENERATED, "getApplicationContext")));
    stmts.add(s);

    return stmts;
  }


  public New buildEntClosure(NodeFactory nf,
                             TypeSystem toTs,
                             List<ModeTypeVariable> capturedMtArgs,
                             Map<ModeTypeVariable, Type> mtEnv,
                             Context ctx) {
    // 1.1. Capture the inferred method mode type variables.
    List<Expr> closElems = new ArrayList<>();
    for (ModeTypeVariable v : capturedMtArgs) {
      ModeType mt = (ModeType)mtEnv.get(v);
      closElems.add(mt.rewriteForLookup(nf, toTs, ctx));
    }

    // 1.2. Build the closure expression
    List<Expr> closInit = new ArrayList<>();
    closInit.add(nf.NewArray(
        Position.COMPILER_GENERATED,
        nf.AmbTypeNode(Position.COMPILER_GENERATED, nf.Id(Position.COMPILER_GENERATED, "Integer")),
        1,
        nf.ArrayInit(Position.COMPILER_GENERATED, closElems)));

    New n = nf.New(Position.COMPILER_GENERATED,
                   nf.AmbTypeNode(Position.COMPILER_GENERATED,
                                  nf.Id(Position.COMPILER_GENERATED, "ENT_Closure")),
                   closInit);

    return n;
  }

  public Call buildModeTableObject(ExtensionInfo extInfo,
                                   ExtensionInfo outInfo,
                                   New newCall,
                                   List<Type> mtArgs,
                                   Context ctx) {
    return this.buildModeTableObject(
        extInfo.nodeFactory(), outInfo.typeSystem(), newCall, mtArgs, ctx);
  }

  public Call buildModeTableObject(NodeFactory nf,
                                   TypeSystem toTs,
                                   New newCall,
                                   List<Type> mtArgs,
                                   Context ctx) {
    List<Expr> preservedTypes = new ArrayList<>();
    for (Type t : mtArgs) {
      ModeType mt = (ModeType)t;
      preservedTypes.add(mt.rewriteForLookup(nf, toTs, ctx));
    }

    Call c = nf.Call(Position.COMPILER_GENERATED,
                     nf.AmbTypeNode(Position.COMPILER_GENERATED,
                                    nf.Id(Position.COMPILER_GENERATED, "ENT_Runtime")),
                     nf.Id(Position.COMPILER_GENERATED, "putObj"),
                     newCall,
                     nf.NewArray(Position.COMPILER_GENERATED,
                                 nf.AmbTypeNode(Position.COMPILER_GENERATED,
                                                nf.Id(Position.COMPILER_GENERATED, "Integer")),
                                 1,
                                 nf.ArrayInit(Position.COMPILER_GENERATED, preservedTypes)));

    return c;
  }

  public Cast buildModeTableObjectWithCast(NodeFactory nf,
                                           TypeSystem toTs,
                                           New newCall,
                                           List<Type> mtArgs,
                                           Context ctx) {
    Cast c = nf.Cast(Position.COMPILER_GENERATED,
                     newCall.objectType(),
                     this.buildModeTableObject(nf, toTs, newCall, mtArgs, ctx));
    return c;
  }
}
