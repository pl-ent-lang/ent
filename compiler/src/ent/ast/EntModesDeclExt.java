package ent.ast;

import ent.types.*;
import ent.visit.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.util.*;
import polyglot.visit.*;
import polyglot.translate.*;

public class EntModesDeclExt extends EntExt {

  @Override
  public Node buildModes(ModeBuilder mb) throws SemanticException {
    ModesDecl md = (ModesDecl)this.node();

    // Save the package we are defined in
    EntTypeSystem ts = (EntTypeSystem)mb.typeSystem();
    ModeOrderingInstance oi = ts.createModeOrderingInstance();

    for (ModeOrder modeOrder : md.orders()) {
      ModeType l = ts.createModeType(modeOrder.lower());
      ModeType u = ts.createModeType(modeOrder.upper());
      oi.insertModeTypeOrdering(l, u);
    }

    oi.buildModeTypeOrdering();
    md = md.modeOrderingInstance(oi);

    return md;
  }
}
