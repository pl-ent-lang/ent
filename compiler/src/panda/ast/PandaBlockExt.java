package panda.ast;

import panda.translate.*;
import panda.types.*;
import panda.visit.*;

import polyglot.ast.*;
import polyglot.translate.*;
import polyglot.types.*;
import polyglot.util.*;
import polyglot.visit.*;
import polyglot.qq.*;

import java.util.ArrayList;
import java.util.List;

public class PandaBlockExt extends PandaExt {

  public Node typePreserve(TypePreserver tp) {
    Block n = (Block) this.node();
    PandaNodeFactory nf = (PandaNodeFactory) tp.nodeFactory();

    // Scan the block for LocalDecls, and insert
    // the necessary mode type preserve calls
    List<Stmt> stmts = new ArrayList<>(n.statements());

    for (int i = 0; i < stmts.size(); ++i) {
      Stmt s = stmts.get(i);
      if (!(s instanceof LocalDecl)) {
        continue;
      }
      LocalDecl ld = (LocalDecl) s;

      if (!(ld.type().type() instanceof ModeSubstType) || 
          ld.init() instanceof SnapshotExpr) {
        continue;
      }

      ModeType mt = (ModeType) ((ModeSubstType) ld.type().type()).modeType();
      // This will error on ModeTypeVariable right now, exactly what I
      // want until I handle those

      Eval eval =
        nf.Eval(
          Position.COMPILER_GENERATED,
          nf.Call(
            Position.COMPILER_GENERATED,
            nf.AmbTypeNode(
              Position.COMPILER_GENERATED,
              nf.Id(
                Position.COMPILER_GENERATED,
                "PANDA_ModeTable"
                )
              ),
            nf.Id(
              Position.COMPILER_GENERATED,
              "put"
              ),
            nf.Local(
              Position.COMPILER_GENERATED,
              ld.name()
              ),
            nf.Field(
              Position.COMPILER_GENERATED,
              nf.AmbTypeNode(
                Position.COMPILER_GENERATED,
                nf.Id(
                  Position.COMPILER_GENERATED,
                  "PandaMode"
                  )
                ),
              nf.Id(
                Position.COMPILER_GENERATED,
                mt.runtimeCode()
                )
              )
            )
          );

      // Tricky, insert after the current index, but do not scan him
      stmts.add(i+1, eval);
      i++;
    }
    
    return n.statements(stmts);
  }

}
