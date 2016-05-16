package ent.visit;

import ent.ast.ModesDecl;
import ent.ast.EntSourceFileExt;
import ent.ast.EntExt;

import polyglot.ext.jl5.visit.JL5Translator;

import polyglot.ast.NodeFactory;
import polyglot.ast.PackageNode;
import polyglot.ast.SourceFile;
import polyglot.frontend.Job;
import polyglot.frontend.TargetFactory;
import polyglot.types.TypeSystem;
import polyglot.types.Context;
import polyglot.util.CodeWriter;
import polyglot.util.ErrorInfo;
import polyglot.visit.Translator;

import java.io.IOException;
import java.util.Collection;
import javax.tools.JavaFileObject;

public class EntTranslator extends JL5Translator {

  public EntTranslator(Job job, TypeSystem ts, NodeFactory nf, TargetFactory tf) {
    super(job, ts, nf, tf);
  }

  private static final String MODES_DECL_CLASS_NAME = "EntMode";

  @Override
  protected boolean translateSource(SourceFile sf) {
    EntSourceFileExt ext = (EntSourceFileExt)EntExt.ext(sf);

    if (ext.modesDecl() != null) {
      if (!this.translateModesDecl(sf)) {
        return false;
      }
    }

    return super.translateSource(sf);
  }

  protected boolean translateModesDecl(SourceFile sf) {
    TargetFactory tf = this.tf;
    int outputWidth = job.compiler().outputWidth();
    Collection<JavaFileObject> outputFiles = job.compiler().outputFiles();
    EntSourceFileExt ext = (EntSourceFileExt)EntExt.ext(sf);

    PackageNode pkgNode = sf.package_();
    String pkg = pkgNode != null ? pkgNode.package_().fullName() : "";

    JavaFileObject of = tf.outputFileObject(pkg, EntTranslator.MODES_DECL_CLASS_NAME, sf.source());
    String opfPath = of.getName();
    if (!opfPath.endsWith("$"))
      outputFiles.add(of);

    try (CodeWriter w = tf.outputCodeWriter(of, outputWidth)) {
      writeHeader(sf, w);

      Translator tr;
      if (sf.isDisambiguated() && sf.isTypeChecked()) {
        Context c = lang().enterScope(sf, context);
        tr = this.context(c);
      } else {
        tr = this.context(null);
      }
      lang().translate(ext.modesDecl(), w, tr);

    } catch (IOException e) {
      job.compiler().errorQueue().enqueue(ErrorInfo.IO_ERROR,
                                          "I/O error while translating: " + e.getMessage());
      return false;
    }
    return true;
  }

  @Override
  protected void writeHeader(SourceFile sfn, CodeWriter w) {
    super.writeHeader(sfn, w);

    // Dirty, but inject our runtime
    w.write("import ent.runtime.*;");
    w.newline(0);
  }
}
