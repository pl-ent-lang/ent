package ent.types;

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
  protected boolean isDynRecvr;
  protected List<Type> upperBounds;
  protected List<Type> lowerBounds;
  protected Type upperBound;
  protected Type lowerBound;
  protected ClassType declaringClass;
  protected ProcedureInstance declaringProc;
  protected int uniqueId;
  protected int index;
  protected boolean isExistential;

  public ModeTypeVariable_c(EntTypeSystem ts, Position pos, String name, boolean isExistential) {
    super(ts, name);
    this.upperBounds = Collections.emptyList();
    this.lowerBounds = Collections.emptyList();
    this.name = name;
    this.uniqueId = this.genId();
    this.index = -1;
    // this.upperBound = null;
    // this.lowerBound = null;

    this.upperBound = ts.unknownType(pos);
    this.lowerBound = ts.unknownType(pos);
    this.isExistential = isExistential;
  }

  // Property Methods
  public String name() { return this.name; }

  public void name(String name) { this.name = name; }

  public boolean isDynRecvr() { return this.isDynRecvr; }

  public void isDynRecvr(boolean isDynRecvr) { this.isDynRecvr = isDynRecvr; }

  public List<Type> upperBounds() { return this.upperBounds; }

  public void upperBounds(List<Type> upperBounds) {
    if (upperBounds == null) {
      this.upperBounds = Collections.emptyList();
    } else {
      this.upperBounds = upperBounds;
    }
  }

  public List<Type> lowerBounds() { return this.lowerBounds; }

  public void lowerBounds(List<Type> lowerBounds) {
    if (lowerBounds == null) {
      this.lowerBounds = Collections.emptyList();
    } else {
      this.lowerBounds = lowerBounds;
    }
  }

  public boolean hasLowerBound() { return this.lowerBound != null; }

  public Type lowerBound() { return this.lowerBound; }

  public void lowerBound(Type lowerBound) { this.lowerBound = lowerBound; }

  public Type upperBound() { return this.upperBound; }

  public void upperBound(Type upperBound) { this.upperBound = upperBound; }

  public ClassType declaringClass() { return this.declaringClass; }

  public void declaringClass(ClassType declaringClass) { this.declaringClass = declaringClass; }

  public ProcedureInstance declaringProc() { return this.declaringProc; }

  public void declaringProc(ProcedureInstance declaringProc) { this.declaringProc = declaringProc; }

  public int uniqueId() { return this.uniqueId; }

  public int index() { return this.index; }

  public void index(int index) { this.index = index; }

  public boolean isExistential() { return this.isExistential; }

  public void isExistential(boolean isExistential) { this.isExistential = isExistential; }

  public boolean inferBounds() {
    boolean r1 = this.inferLowerBound();
    boolean r2 = this.inferUpperBound();
    return (r1 && r2);
  }

  public boolean inferLowerBound() {
    EntTypeSystem ts = (EntTypeSystem)this.ts;

    if (this.lowerBounds().isEmpty()) {
      // What does this mean exactly?
      this.lowerBound(ts.BottomUserModeType());
      return true;
    }

    Type glb = null;
    for (Type m : this.lowerBounds()) {
      if (glb == null) {
        glb = m;
        continue;
      }

      if (ts.isSubtype(glb, m)) {
        glb = m;
      }
    }

    this.lowerBound(glb);
    return true;
  }

  public boolean inferUpperBound() {
    EntTypeSystem ts = (EntTypeSystem)this.ts;

    if (this.upperBounds().isEmpty()) {
      // What does this mean exactly?
      this.upperBound(ts.WildcardModeType());
      return true;
    }

    Type lub = null;
    for (Type m : this.upperBounds()) {
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
    String s = "(";
    if (this.isDynRecvr()) {
      s += "? -> ";
    }
    if (this.hasLowerBound()) {
      s += this.lowerBound() + " <= ";
    }
    s += this.name() + " <= ";
    s += this.upperBound() + ")";
    return s;
  }

  @Override
  public String translate(Resolver c) {
    return this.name();
  }

  private int genId() { return ModeTypeVariable_c.gen++; }

  @Override
  public boolean typeEqualsImpl(Type o) {
    EntTypeSystem ts = (EntTypeSystem)this.typeSystem();

    // We allow wildcard for now
    // TODO: Not sure how to let this happen, wildcards need
    // to be allowed.
    if (o == ts.WildcardModeType()) {
      return true;
    }

    if (!(o instanceof ModeTypeVariable)) {
      return false;
    }

    ModeTypeVariable tv = (ModeTypeVariable)o;
    if (this.isExistential() && tv.isExistential()) {
      return (ts.typeEquals(this.lowerBound(), tv.lowerBound()) &&
              ts.typeEquals(this.upperBound(), tv.upperBound()));
    }

    return this.uniqueId() == tv.uniqueId();
  }

  @Override
  public boolean descendsFromImpl(Type o) {
    EntTypeSystem ts = (EntTypeSystem)this.typeSystem();
    if (ts.typeEquals(this, o)) {
      return true;
    }
    return ts.isSubtype(this.upperBound(), o);
  }

  @Override
  public boolean isImplicitCastValidImpl(Type toType) {
    EntTypeSystem ts = (EntTypeSystem)this.typeSystem();
    if (ts.typeEquals(this, toType)) {
      return true;
    }
    return ts.isImplicitCastValid(this.upperBound(), toType);
  }

  @Override
  public String runtimeCode() {
    return ((ModeType)this.upperBound()).runtimeCode();
  }

  @Override
  public Expr rewriteForLookup(NodeFactory nf, TypeSystem ts, Context c) {
    Expr n = null;
    // Use class variable context if class mode type variable
    // and used outside of constructor.
    if (this.declaringClass() != null && !(c.currentCode() instanceof ConstructorInstance)) {
      n = nf.Call(Position.COMPILER_GENERATED,
                  nf.AmbTypeNode(Position.COMPILER_GENERATED,
                                 nf.Id(Position.COMPILER_GENERATED, "ENT_Runtime")),
                  nf.Id(Position.COMPILER_GENERATED, "getObjMode"),
                  nf.This(Position.COMPILER_GENERATED),
                  nf.IntLit(Position.COMPILER_GENERATED, IntLit.INT, this.index()));
    } else if (this.declaringProc() != null || c.currentCode() instanceof ConstructorInstance) {
      n = nf.Call(
          Position.COMPILER_GENERATED,
          nf.Local(Position.COMPILER_GENERATED, nf.Id(Position.COMPILER_GENERATED, "ENT_this")),
          nf.Id(Position.COMPILER_GENERATED, "getModeVar"),
          nf.IntLit(Position.COMPILER_GENERATED, IntLit.INT, this.index()));
    } else {
      System.out.println("ERROR - Mode type variable does not have declaring class or proc");
      System.exit(1);
    }
    return n;
  }
}
