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
  protected boolean preserveTypes() {
    ProcedureDecl n = (ProcedureDecl) this.node();
    PandaProcedureInstance pi = (PandaProcedureInstance) n.procedureInstance();
    PandaClassType ct = (PandaClassType) pi.container();

    return !pi.modeTypeVars().isEmpty() || !ct.isImplicitModeTypeVar();
  }

}
