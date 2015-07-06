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

public class PandaMethodDeclExt extends PandaProcedureDeclExt { 

  @Override
  protected boolean preserveTypes() {
    ProcedureDecl n = (ProcedureDecl) this.node();
    PandaProcedureInstance pi = (PandaProcedureInstance) n.procedureInstance();

    return !pi.modeTypeVars().isEmpty();
  }

}
