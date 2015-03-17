package panda;

import panda.visit.PandaTranslator;

import polyglot.ext.jl7.JL7Scheduler;

import polyglot.ast.NodeFactory;
import polyglot.frontend.ExtensionInfo;
import polyglot.frontend.JLExtensionInfo;
import polyglot.frontend.Job;
import polyglot.frontend.OutputPass;
import polyglot.frontend.Pass; 
import polyglot.frontend.Scheduler;
import polyglot.frontend.goals.Goal;
import polyglot.frontend.goals.CodeGenerated;
import polyglot.types.TypeSystem;

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
    return PandaCodeGenerated.create(this, job);
  }


}
