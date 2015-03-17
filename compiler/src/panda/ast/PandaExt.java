package panda.ast;

import polyglot.ast.*;
import polyglot.util.InternalCompilerError;
import polyglot.util.SerialVersionUID;

public class PandaExt extends Ext_c {
    private static final long serialVersionUID = SerialVersionUID.generate();

    public static PandaExt ext(Node n) {
        Ext e = n.ext();
        while (e != null && !(e instanceof PandaExt)) {
            e = e.ext();
        }
        if (e == null) {
            throw new InternalCompilerError("No Panda extension object for node "
                    + n + " (" + n.getClass() + ")", n.position());
        }
        return (PandaExt) e;
    }

    @Override
    public final PandaLang lang() {
        return PandaLang_c.instance;
    }

    // TODO:  Override operation methods for overridden AST operations.
}
