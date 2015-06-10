package panda;

import panda.visit.AttributeExitChecker;
import panda.visit.PandaTranslator;

import polyglot.ext.jl7.JL7Scheduler;

import polyglot.ast.NodeFactory;
import polyglot.frontend.CyclicDependencyException;
import polyglot.frontend.ExtensionInfo;
import polyglot.frontend.JLExtensionInfo;
import polyglot.frontend.Job;
import polyglot.frontend.OutputPass;
import polyglot.frontend.Pass; 
import polyglot.frontend.Scheduler;
import polyglot.frontend.goals.Goal;
import polyglot.frontend.goals.VisitorGoal;
import polyglot.frontend.goals.CodeGenerated;
import polyglot.types.TypeSystem;
import polyglot.util.InternalCompilerError;

public class PandaScheduler extends JL7Scheduler {

  public PandaScheduler(JLExtensionInfo extInfo) {
    super(extInfo);
  }

  private static class PandaCodeGenerated extends CodeGenerated {
    public static Goal create(Scheduler scheduler, Job job) {
      return scheduler.internGoal(new PandaCodeGenerated(job));
    }

    /**
     * @param job The job to compile.
     */
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


}
