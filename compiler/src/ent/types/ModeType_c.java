package ent.types;

import java.io.IOException;
import java.io.ObjectInputStream;
import ent.runtime.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.util.*;
import polyglot.visit.*;

public class ModeType_c extends Type_c implements ModeType {
  private static final long serialVersionUID = SerialVersionUID.generate();

  protected String name;

  private int uniqueId;

  protected Type superType;
  protected Type subType;

  public ModeType_c(EntTypeSystem ts, String name) {
    super(ts);
    this.name = name;
  }

  public String name() { return this.name; }

  public void name(String name) { this.name = name; }

  public Type superType() { return this.superType; }

  public void superType(Type superType) { this.superType = superType; }

  public Type subType() { return this.subType; }

  public void subType(Type subType) { this.subType = subType; }

  public int uniqueId() { return this.uniqueId; }

  public void uniqueId(int uniqueId) { this.uniqueId = uniqueId; }

  public String compileId() {
    if (this.name().equals("?")) {
      return "DYNAMIC_MODE";
    } else if (this.name().equals("*")) {
      return "WILDCARD_MODE";
    } else {
      return this.name().toUpperCase() + "_MODE";
    }
  }

  public String compileExpr() {
    if (this.name().equals("?")) {
      return "DYNAMIC_MODE";
    } else if (this.name().equals("*")) {
      return "WILDCARD_MODE";
    } else {
      return Integer.toString(this.uniqueId);
    }
  }

  public String compileCode() {
    if (this.name().equals("?")) {
      return "DYNAMIC_MODE = ENT_Modes.DYNAMIC_MODE";
    } else if (this.name().equals("*")) {
      return "WILDCARD_MODE = ENT_Modes.WILDCARD_MODE";
    } else {
      return this.name().toUpperCase() + "_MODE = " + this.uniqueId;
    }
  }

  public String runtimeCode() {
    if (this.name().equals("?")) {
      return "DYNAMIC_MODE";
    } else if (this.name().equals("*")) {
      return "WILDCARD_MODE";
    } else {
      return this.name().toUpperCase() + "_MODE";
    }
  }

  @Override
  public boolean isCanonical() {
    return true;
  }

  @Override
  public String toString() {
    return this.name();
  }

  @Override
  public String translate(Resolver c) {
    return this.toString();
  }

  @Override
  public boolean equalsImpl(TypeObject o) {
    return (this == o);
  }

  @Override
  public boolean typeEqualsImpl(Type o) {
    if (this == o) {
      return true;
    }

    EntTypeSystem ts = (EntTypeSystem)this.typeSystem();

    // System.err.format("ModeType: %s %s\n", this, o);
    // System.err.format("ModeType: %s %s %s\n", this == o, this == ts.WildcardModeType(), o ==
    // ts.WildcardModeType());

    return (this == o || 
            (this == ts.WildcardModeType() && o != ts.DynamicModeType()) || 
            (o == ts.WildcardModeType() && this != ts.DynamicModeType()));
  }

  @Override
  public boolean descendsFromImpl(Type o) {
    EntTypeSystem ts = (EntTypeSystem)this.typeSystem();
    if (ts.typeEquals(this, o)) {
      return true;
    }
    if (this.superType() == null) {
      return false;
    }
    return ts.isSubtype(this.superType(), o);
  }

  @Override
  public boolean isImplicitCastValidImpl(Type o) {
    return ts.typeEquals(this, o);
  }

  @Override
  public boolean isCastValidImpl(Type o) {
    EntTypeSystem ts = (EntTypeSystem)this.typeSystem();
    return (ts.typeEquals(this, o) || 
        (this == ts.WildcardModeType()) || 
        (o == ts.WildcardModeType()));
  }


  @Override
  public Expr rewriteForLookup(NodeFactory nf, TypeSystem ts, Context c) {
    EntTypeSystem fromTs = (EntTypeSystem)this.typeSystem();
    Receiver recv = null;
    if (fromTs.modesDeclPackage() != null) {
      recv = nf.AmbReceiver(Position.COMPILER_GENERATED,
                            nf.PackageNode(Position.COMPILER_GENERATED,
                                           ts.createPackage(fromTs.modesDeclPackage().fullName())),
                            nf.Id(Position.COMPILER_GENERATED, "EntMode"));
    } else {
      recv = nf.AmbReceiver(Position.COMPILER_GENERATED,
                            nf.Id(Position.COMPILER_GENERATED, "EntMode"));
    }
    Expr n = nf.Field(
        Position.COMPILER_GENERATED, recv, nf.Id(Position.COMPILER_GENERATED, this.runtimeCode()));
    return n;
  }
}
