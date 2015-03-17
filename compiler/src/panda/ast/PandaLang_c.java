package panda.ast;

import polyglot.ast.*;
import polyglot.util.InternalCompilerError;

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

    // TODO:  Implement dispatch methods for new AST operations.
    // TODO:  Override *Ops methods for AST nodes with new extension nodes.
}
