package ent;

import ent.translate.*;
import ent.types.*;
import ent.visit.*;

import polyglot.ext.jl7.*;
import polyglot.ext.jl5.visit.RemoveEnums;

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

public class EntScheduler extends JL7Scheduler {

  public EntScheduler(JLExtensionInfo extInfo) { super(extInfo); }

  private void scheduleEntModes() {
    ExtensionInfo outInfo = extInfo.outputExtensionInfo();
    Scheduler outScheduler = outInfo.scheduler();

    SourceFile sf = EntBuilder.instance().buildEntMode(extInfo, outInfo);
    Source source = EntBuilder.instance().buildSource(
        extInfo, ((EntTypeSystem)extInfo.typeSystem()).modesDeclPackage(), "EntMode.java");
    sf = sf.source(source);

    Job job = outScheduler.addJob(source, sf);
    outScheduler.addGoal(outInfo.getCompileGoal(job));
  }

  // For translation, then compilation in the target
  @Override
  public boolean runToCompletion() {
    boolean complete = super.runToCompletion();
    if (complete) {
      EntTypeSystem ts = (EntTypeSystem)extInfo.typeSystem();
      if (!ts.createdModeTypes().isEmpty()) {
        this.scheduleEntModes();
      }

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
  private static class EntCodeGenerated extends CodeGenerated {
    public static Goal create(Scheduler scheduler, Job job) {
      return scheduler.internGoal(new EntCodeGenerated(job));
    }

    protected EntCodeGenerated(Job job) {
      super(job);
    }

    @Override
    public Pass createPass(ExtensionInfo extInfo) {
      TypeSystem ts = extInfo.typeSystem();
      NodeFactory nf = extInfo.nodeFactory();
      return new OutputPass(this,
                            new EntTranslator(job(),
                                                ts,
                                                nf,
                                                extInfo.targetFactory()));
    }
  }
  */

  public Goal PreRemoveEnt(Job job) {
    Goal g = new EmptyGoal(job, "PreRemoveEnt");
    try {
      g.addPrerequisiteGoal(AttributeExitChecked(job), this);
      g.addPrerequisiteGoal(Serialized(job), this);
    } catch (CyclicDependencyException e) {
      throw new InternalCompilerError(e);
    }
    return internGoal(g);
  }

  public Goal RemoveEnt(Job job) {
    Goal g = new VisitorGoal(job, new EntRewriter(job, extInfo, extInfo.outputExtensionInfo()));
    try {
      g.addPrerequisiteGoal(PreRemoveEnt(job), this);

      EntOptions entOpt = (EntOptions)extInfo.getOptions();
      if (entOpt.preserveTypes && entOpt.translateEnt) {
        g.addPrerequisiteGoal(TypePreserver(job), this);
      }
    } catch (CyclicDependencyException e) {
      throw new InternalCompilerError(e);
    }
    return internGoal(g);
  }

  // For compilation inside Ent
  /*
  @Override
  public Goal CodeGenerated(Job job) {
    Goal g = EntCodeGenerated.create(this, job);
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
      g.addPrerequisiteGoal(RemoveEnt(job), this);
    } catch (CyclicDependencyException e) {
      throw new InternalCompilerError(e);
    }
    return internGoal(g);
  }

  @Override
  public Goal TypesInitialized(Job job) {
    TypeSystem ts = extInfo.typeSystem();
    NodeFactory nf = extInfo.nodeFactory();
    Goal g = TypesInitialized.create(this, job, ts, nf);
    try {
      g.addPrerequisiteGoal(ModesBuilt(job), this);
    } catch (CyclicDependencyException e) {
      throw new InternalCompilerError(e);
    }
    return g;
  }

  public Goal ModesBuilt(Job job) {
    TypeSystem ts = extInfo.typeSystem();
    NodeFactory nf = extInfo.nodeFactory();
    Goal g = new VisitorGoal(job, new ModeBuilder(job, ts, nf));
    try {
      g.addPrerequisiteGoal(Parsed(job), this);
    } catch (CyclicDependencyException e) {
      throw new InternalCompilerError(e);
    }
    return this.internGoal(g);
  }

  public Goal AttributeExitChecked(Job job) {
    TypeSystem ts = extInfo.typeSystem();
    NodeFactory nf = extInfo.nodeFactory();
    Goal g = new VisitorGoal(job, new AttributeExitChecker(job, ts, nf));
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
    Goal g = new VisitorGoal(job, new TypePreserver(job, ts, nf, to_ts));
    try {
      g.addPrerequisiteGoal(AttributeExitChecked(job), this);
    } catch (CyclicDependencyException e) {
      throw new InternalCompilerError(e);
    }
    return this.internGoal(g);
  }
}
