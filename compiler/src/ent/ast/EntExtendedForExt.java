package ent.ast;

import polyglot.ast.*;
import polyglot.util.*;
import polyglot.types.*;
import polyglot.translate.*;
import polyglot.util.*;

public class EntExtendedForExt extends EntExt {

  @Override
  public Node extRewrite(ExtensionRewriter rw) throws SemanticException {
    return Copy.Util.copy(this.node());
  }
}
