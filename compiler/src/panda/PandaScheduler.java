package panda;

import panda.translate.*;
import panda.visit.*;

import polyglot.ext.jl7.*;

import polyglot.ast.NodeFactory;
import polyglot.frontend.*;
import polyglot.frontend.goals.*;
import polyglot.types.*;
import polyglot.util.*;

public class PandaScheduler extends JL7Scheduler {

  public PandaScheduler(JLExtensionInfo extInfo) {
    super(extInfo);
  }

  // For translation, then compilation in the target
  @Override
  public boolean runToCompletion() {
    boolean complete = super.runToCompletion();
    if (complete) {
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
    Goal g = 
      new VisitorGoal(job, new TypePreserver(job, ts, nf));
    try {
      g.addPrerequisiteGoal(AttributeExitChecked(job), this);
    } catch (CyclicDependencyException e) {
      throw new InternalCompilerError(e);
    }
    return this.internGoal(g);
  }



}
