package panda;

import panda.translate.*;
import panda.types.*;
import panda.visit.*;

import polyglot.ext.jl7.*;

import polyglot.ast.*;
import polyglot.frontend.*;
import polyglot.frontend.goals.*;
import polyglot.qq.*;
import polyglot.types.*;
import polyglot.util.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.Map;

import javax.tools.JavaFileManager.*;
import javax.tools.*;

public class PandaScheduler extends JL7Scheduler {

  public PandaScheduler(JLExtensionInfo extInfo) {
    super(extInfo);
  }

  private void schedulePandaModes() {
    PandaTypeSystem ts = (PandaTypeSystem) extInfo.typeSystem();
    if (ts.createdModeTypes().isEmpty()) {
      return;
    }

    QQ qq = new QQ(extInfo.outputExtensionInfo());
    NodeFactory nf = extInfo.outputExtensionInfo().nodeFactory();

    List<ClassMember> members = new ArrayList<>();
    for (Map.Entry<String, ModeType> e : ts.createdModeTypes().entrySet()) {
      ModeType mt = e.getValue();

      Expr expr = null;
      if (mt == ts.DynamicModeType() || mt == ts.WildcardModeType()) {
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

    TypeSystem outTs = extInfo.outputExtensionInfo().typeSystem();
    PackageNode pkgNode = null;
    if (ts.modesDeclPackage() != null) {
      pkgNode = 
        nf.PackageNode(
          Position.COMPILER_GENERATED,
          outTs.createPackage(ts.modesDeclPackage().fullName())
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

    ExtensionInfo outExtInfo = extInfo.outputExtensionInfo();
    Scheduler outScheduler = outExtInfo.scheduler();

    Location lo = extInfo.getOptions().source_output;
    JavaFileManager fm = extInfo.extFileManager();
    FileObject fo;
    Source source;
    try {
      String pkg = 
        ts.modesDeclPackage() != null ? ts.modesDeclPackage().fullName() : "";
      fo = fm.getFileForOutput(lo, pkg, "PandaMode.java", null);
      source = extInfo.createFileSource(fo, Source.Kind.COMPILER_GENERATED);
    } catch (Exception e) {
      throw new InternalCompilerError("Fatal, failed to get a file object for PandaModes");
    }
    sf = sf.source(source);

    Job job = outScheduler.addJob(source, sf);
    outScheduler.addGoal(outExtInfo.getCompileGoal(job));
  }

  // For translation, then compilation in the target
  @Override
  public boolean runToCompletion() {
    boolean complete = super.runToCompletion();
    if (complete) {
      this.schedulePandaModes();

      // Call the compiler for output files to compile our translated
      // code.
      ExtensionInfo outExtInfo = extInfo.outputExtensionInfo();
      Scheduler outScheduler = outExtInfo.scheduler();

      // Create a goal to compile every source file.
      for (Job job : outScheduler.jobs()) {
        Job newJob = outScheduler.addJob(job.source(), job.ast());
        outScheduler.addGoal(outExtInfo.getCompileGoal(newJob));
      }
      return outScheduler.runToCompletion();
    }
    return complete;
  }

  /*
  private static class PandaCodeGenerated extends CodeGenerated {
    public static Goal create(Scheduler scheduler, Job job) {
      return scheduler.internGoal(new PandaCodeGenerated(job));
    }

    protected PandaCodeGenerated(Job job) {
      super(job);
    }

    @Override
    public Pass createPass(ExtensionInfo extInfo) {
      TypeSystem ts = extInfo.typeSystem();
      NodeFactory nf = extInfo.nodeFactory();
      return new OutputPass(this,
                            new PandaTranslator(job(),
                                                ts,
                                                nf,
                                                extInfo.targetFactory()));
    }
  }
  */

  public Goal PreRemovePanda(Job job) {
    Goal g = new EmptyGoal(job, "PreRemovePanda");
    try {
      g.addPrerequisiteGoal(AttributeExitChecked(job), this);
      g.addPrerequisiteGoal(Serialized(job), this);
    } catch(CyclicDependencyException e) {
      throw new InternalCompilerError(e);
    }
    return internGoal(g);
  }

  public Goal RemovePanda(Job job) {
    Goal g =
      new VisitorGoal(job,
                      new PandaRewriter(job,
                                        extInfo,
                                        extInfo.outputExtensionInfo()));
    try {
      g.addPrerequisiteGoal(PreRemovePanda(job), this);
      g.addPrerequisiteGoal(TypePreserver(job), this);
    } catch (CyclicDependencyException e) {
      throw new InternalCompilerError(e);
    }
    return internGoal(g);
  }

  // For compilation inside Panda
  /*
  @Override
  public Goal CodeGenerated(Job job) {
    Goal g = PandaCodeGenerated.create(this, job);
    try {
      g.addPrerequisiteGoal(AttributeExitChecked(job), this);
    } catch(CyclicDependencyException e) {
      throw new InternalCompilerError(e);
    }
    return g;
  }
  */

  // For translation to target, and compilation from there
  @Override
  public Goal CodeGenerated(Job job) {
    Goal g = new EmptyGoal(job, "CodeGenerated");
    try {
      g.addPrerequisiteGoal(RemovePanda(job), this);
    } catch(CyclicDependencyException e) {
      throw new InternalCompilerError(e);
    }
    return internGoal(g);
  }

  public Goal AttributeExitChecked(Job job) {
    TypeSystem ts = extInfo.typeSystem();
    NodeFactory nf = extInfo.nodeFactory();
    Goal g = 
      new VisitorGoal(job, new AttributeExitChecker(job, ts, nf));
    try {
      g.addPrerequisiteGoal(ExitPathsChecked(job), this);
    } catch (CyclicDependencyException e) {
      throw new InternalCompilerError(e);
    }
    return this.internGoal(g);
  }

  public Goal TypePreserver(Job job) {
    TypeSystem ts = extInfo.typeSystem();
    NodeFactory nf = extInfo.nodeFactory();
    TypeSystem to_ts = extInfo.outputExtensionInfo().typeSystem();
    Goal g = 
      new VisitorGoal(job, new TypePreserver(job, ts, nf, to_ts));
    try {
      g.addPrerequisiteGoal(AttributeExitChecked(job), this);
    } catch (CyclicDependencyException e) {
      throw new InternalCompilerError(e);
    }
    return this.internGoal(g);
  }



}
