package panda.ast;

import panda.types.*;
import panda.visit.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.visit.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set; 

public class PandaConstructorDeclExt extends PandaProcedureDeclExt { 

  @Override
  public Node typePreserve(TypePreserver tp) {
    ProcedureDecl n = (ProcedureDecl) this.node();
    PandaProcedureInstance pi = (PandaProcedureInstance) n.procedureInstance();
    PandaClassType ct = (PandaClassType) pi.container();

    if (pi.modeTypeVars().isEmpty() && ct.isImplicitModeTypeVar()) {
      return n;
    }

    return super.typePreserve(tp);
  }

}
