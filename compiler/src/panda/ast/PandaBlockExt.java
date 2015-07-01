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

  private Block preserveLocalDecls(Block n, TypePreserver tp) {
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

      //ModeType mt = (ModeType) ((ModeSubstType) ld.type().type()).modeType();
      List<Type> mtArgs = ((ModeSubstType) ld.type().type()).modeTypeArgs();
      List<Expr> elems = new ArrayList<>();
      for (int j = 0; j < mtArgs.size(); ++j) {
        Type t = mtArgs.get(j);
        Expr e = null;
        if (t instanceof ModeTypeVariable) {
          ModeTypeVariable mt = (ModeTypeVariable) t;
          e =
            nf.Call(
              Position.COMPILER_GENERATED,
              nf.AmbTypeNode(
                Position.COMPILER_GENERATED,
                nf.Id(
                  Position.COMPILER_GENERATED,
                  "PANDA_Runtime"
                  )
                ),
              nf.Id(
                Position.COMPILER_GENERATED,
                "getObjMode"
                ),
              nf.This(Position.COMPILER_GENERATED),
              nf.IntLit(
                Position.COMPILER_GENERATED,
                IntLit.INT,
                mt.index()
                )
              );
        } else if (t instanceof ModeType) {
          ModeType mt = (ModeType) t;
          e = 
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
              );
        } else {
          System.out.println("ERROR : Type other than ModeType encountered during code gen");
          System.exit(1);
        }
        elems.add(e);
      }

      Eval eval =
        nf.Eval(
          Position.COMPILER_GENERATED,
          nf.Call(
            Position.COMPILER_GENERATED,
            nf.AmbTypeNode(
              Position.COMPILER_GENERATED,
              nf.Id(
                Position.COMPILER_GENERATED,
                "PANDA_Runtime"
                )
              ),
            nf.Id(
              Position.COMPILER_GENERATED,
              "putObj"
              ),
            nf.Local(
              Position.COMPILER_GENERATED,
              ld.name()
              ),
            nf.NewArray(
              Position.COMPILER_GENERATED,
              nf.AmbTypeNode(
                Position.COMPILER_GENERATED,
                nf.Id(
                  Position.COMPILER_GENERATED,
                  "Integer"
                  )
                ),
              1,
              nf.ArrayInit(
                Position.COMPILER_GENERATED,
                elems
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

  public Node typePreserve(TypePreserver tp) {
    Block n = (Block) this.node();

    n = this.preserveLocalDecls(n, tp);

    return n;
  }

}
