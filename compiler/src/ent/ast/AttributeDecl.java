package ent.ast;

import polyglot.ast.*;
import java.util.List;

public interface AttributeDecl extends CodeDecl {
    
  List<Formal> formals();

  AttributeDecl formals(List<Formal> formals);

}
