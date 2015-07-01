package panda.types;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.visit.*;
import polyglot.util.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ModeTypeVariable_c extends ModeType_c implements ModeTypeVariable {

  private static int gen = 0;

  protected String name;
  protected List<Type> bounds;
  protected Type upperBound;
  protected Type lowerBound;
  protected ClassType declaringClass;
  protected ProcedureInstance declaringProc;
  protected int uniqueId;
  protected int index;

  public ModeTypeVariable_c(PandaTypeSystem ts,
                            Position pos,
                            String name) {
    super(ts, name);
    this.bounds = Collections.emptyList();
    this.name = name;
    this.uniqueId = this.genId();
    this.index = -1;
  }

  // Property Methods
  public String name() {
    return this.name;
  }

  public void name(String name) {
    this.name = name;
  }

  public List<Type> bounds() {
    return this.bounds;
  }

  public void bounds(List<Type> bounds) {
    if (bounds == null) {
      this.bounds = Collections.emptyList();
    } else {
      this.bounds = bounds;
    }
  }

  public boolean hasLowerBound() {
    return this.lowerBound != null;
  }

  public Type lowerBound() {
    if (this.lowerBound == null) {
      // Our only bound is high
      return this.upperBound();
    }
    return this.lowerBound;
  }

  public void lowerBound(Type lowerBound) {
    this.lowerBound = lowerBound;
  }

  public Type upperBound() {
    return this.upperBound;
  }

  public void upperBound(Type upperBound) {
    this.upperBound = upperBound;
  }

  public ClassType declaringClass() {
    return this.declaringClass;
  }

  public void declaringClass(ClassType declaringClass) {
    this.declaringClass = declaringClass;
  }

  public ProcedureInstance declaringProc() {
    return this.declaringProc;
  }

  public void declaringProc(ProcedureInstance declaringProc) {
    this.declaringProc = declaringProc;
  }


  public int uniqueId() {
    return this.uniqueId;
  }

  public int index() {
    return this.index;
  }

  public void index(int index) {
    this.index = index;
  }

  public boolean inferUpperBound() {
    PandaTypeSystem ts = (PandaTypeSystem) this.ts;

    if (this.bounds().isEmpty()) {
      // What does this mean exactly?
      this.upperBound(ts.WildcardModeType());
      return true;
    }

    // We must be able to unify and select an upper bound
    // This part is really easy, just select the LUB,
    // the challenge is making sure subst satisfies constraints
    Type lub = null;
    for (Type m : this.bounds()) {
      if (lub == null) {
        lub = m;
        continue;
      }

      if (ts.isSubtype(m, lub)) {
        lub = m;
      }
    }
  
    this.upperBound(lub);
    return true;
  }

  @Override
  public String toString() {
    /*
    String s = "";
    if (this.hasLowerBound()) {
      s += this.lowerBound() + " <: ";
    }
    s += this.name() + " <: ";
    s += this.upperBound();
    return s;
    */
    return this.name();
  }

  @Override
  public String translate(Resolver c) {
    return this.name();
  }

  private int genId() {
    return ModeTypeVariable_c.gen++;
  }

  @Override
  public boolean typeEqualsImpl(Type o) {
    PandaTypeSystem ts = (PandaTypeSystem) this.typeSystem();

    // We allow wildcard for now
    // TODO: Not sure how to let this happen, wildcards need
    // to be allowed.
    if (o == ts.WildcardModeType()) {
      return true;
    } 

    if (!(o instanceof ModeTypeVariable)) {
      return false;
    } 
    
    ModeTypeVariable tv = (ModeTypeVariable) o;
    return this.uniqueId() == tv.uniqueId();
  }

  @Override
  public boolean descendsFromImpl(Type o) {
    PandaTypeSystem ts = (PandaTypeSystem) this.typeSystem();
    if (ts.typeEquals(this,o)) {
      return true;
    }
    return ts.isSubtype(this.upperBound(), o);
  }

  @Override
  public String runtimeCode() {
    return ((ModeType) this.upperBound()).runtimeCode();
  }

  @Override
  public Expr rewriteForLookup(NodeFactory nf) {
    Expr n = null;
    if (this.declaringClass() != null) {
      n = 
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
            this.index()
            )
          );
    } else if (this.declaringProc() != null) {
      n =
        nf.Call(
          Position.COMPILER_GENERATED,
          nf.Local(
            Position.COMPILER_GENERATED,
            nf.Id(
              Position.COMPILER_GENERATED,
              "PANDA_this"
              )
            ),
          nf.Id(
            Position.COMPILER_GENERATED,
            "getModeVar"
            ),
          nf.IntLit(
            Position.COMPILER_GENERATED,
            IntLit.INT,
            this.index()
            )
          );
    } else {
      System.out.println("ERROR - Mode type variable does not have declaring class or proc");
      System.exit(1);
    }
    return n;
  }
}
