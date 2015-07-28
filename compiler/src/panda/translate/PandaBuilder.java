package panda.translate;

import panda.ast.*;
import panda.types.*;
import polyglot.ast.*;
import polyglot.frontend.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.types.Package;
import polyglot.util.*;
import polyglot.qq.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.tools.JavaFileManager.*;
import javax.tools.*;

public class PandaBuilder {

  public static SourceFile buildPandaMode(ExtensionInfo extInfo, ExtensionInfo outInfo) {
    PandaTypeSystem fromTs = (PandaTypeSystem) extInfo.typeSystem();
    QQ qq = new QQ(outInfo);
    NodeFactory nf = outInfo.nodeFactory();
    TypeSystem ts = outInfo.typeSystem();

    List<ClassMember> members = new ArrayList<>();
    for (Map.Entry<String, ModeType> e : fromTs.createdModeTypes().entrySet()) {
      ModeType mt = e.getValue();

      Expr expr = null;
      if (mt == fromTs.DynamicModeType() || mt == fromTs.WildcardModeType()) {
        expr = 
          nf.Field(
            Position.COMPILER_GENERATED,
            nf.AmbReceiver(
              Position.COMPILER_GENERATED,
              nf.Id(Position.COMPILER_GENERATED, "PANDA_Modes")
              ),
            nf.Id(Position.COMPILER_GENERATED, mt.compileExpr())
            );
      } else {
        expr = nf.IntLit(Position.COMPILER_GENERATED, IntLit.INT, Integer.parseInt(mt.compileExpr()));
      }

      ClassMember fd = 
        qq.parseMember("public static final int %s = %E;", mt.compileId(), expr);

      members.add(fd);
    }

    ClassDecl cd = qq.parseDecl("public class PandaMode { %LM }", members);

    PackageNode pkgNode = null;
    if (fromTs.modesDeclPackage() != null) {
      pkgNode = 
        nf.PackageNode(
          Position.COMPILER_GENERATED,
          ts.createPackage(fromTs.modesDeclPackage().fullName())
          );
    }
    SourceFile sf = 
      nf.SourceFile(
        Position.COMPILER_GENERATED,
        pkgNode,
        Arrays.<Import>asList(
          nf.Import(
            Position.COMPILER_GENERATED,
            Import.TYPE_IMPORT_ON_DEMAND,
            "panda.runtime")
          ),
        Arrays.<TopLevelDecl>asList(cd)
        );

    return sf;
  }

  public static Source buildSource(ExtensionInfo extInfo, Package pkg, String file) {
    String pkgName = pkg != null ? pkg.fullName() : "";
    Source source = null;
    try {
      Location lo = extInfo.getOptions().source_output;
      FileObject fo = 
        extInfo.extFileManager().getFileForOutput(lo, pkgName, file, null);
      source = extInfo.createFileSource(fo, Source.Kind.COMPILER_GENERATED);
    } catch (Exception e) {
      String fullName = pkg != null ? pkgName + "." + file : file;
      throw new InternalCompilerError("Fatal, failed to get a file object for " + fullName + "!");
    }
    return source;
  }

}
