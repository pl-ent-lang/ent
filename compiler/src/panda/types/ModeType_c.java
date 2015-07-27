package panda.types;

import panda.runtime.*;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.util.*;
import polyglot.visit.*;

public class ModeType_c extends Type_c implements ModeType {
  protected String name;

  private int uniqueId;
  private static int uniqueIdGen = PANDA_Modes.FREE_MODE;

  protected Type superType;
  protected Type subType;

  public ModeType_c(PandaTypeSystem ts, String name) {
    super(ts);
    this.uniqueId = ModeType_c.uniqueIdGen++;
    this.name = name;
  }

  public String name() {
    return this.name;
  }

  public void name(String name) {
    this.name = name;
  }

  public Type superType() {
    return this.superType;
  }

  public void superType(Type superType) {
    this.superType = superType;
  }

  public Type subType() {
    return this.subType;
  }

  public void subType(Type subType) {
    this.subType = subType;
  }

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
      return "DYNAMIC_MODE = PANDA_Modes.DYNAMIC_MODE";
    } else if (this.name().equals("*")) {
      return "WILDCARD_MODE = PANDA_Modes.WILDCARD_MODE";
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

    PandaTypeSystem ts = (PandaTypeSystem) this.typeSystem();

    return (this == o ||
            this == ts.WildcardModeType() ||
            o == ts.WildcardModeType());
  } 

  @Override
  public boolean descendsFromImpl(Type o) {
    PandaTypeSystem ts = (PandaTypeSystem) this.typeSystem();
    if (ts.typeEquals(this,o)) {
      return true;
    }
    if (this.superType() == null) {
      return false;
    }
    return ts.isSubtype(this.superType(), o);
  }

  @Override
  public Expr rewriteForLookup(NodeFactory nf, Context c, TypeSystem to_ts) {
    if (to_ts instanceof PandaTypeSystem) {
      new Exception().printStackTrace(System.out);
    }

    PandaTypeSystem ts = (PandaTypeSystem) this.typeSystem();
    Receiver recv = null;
      if (ts.modesDeclPackage() != null) {
        recv =
          nf.AmbReceiver(
            Position.COMPILER_GENERATED,
            nf.PackageNode(
              Position.COMPILER_GENERATED,
              to_ts.createPackage(ts.modesDeclPackage().fullName())
            ),
            nf.Id(Position.COMPILER_GENERATED, "PandaMode")
            );
      } else {
        recv =
          nf.AmbReceiver(
            Position.COMPILER_GENERATED,
            nf.Id(Position.COMPILER_GENERATED, "PandaMode")
            );
      }
    Expr n = 
      nf.Field(
        Position.COMPILER_GENERATED,
        recv,
        nf.Id(Position.COMPILER_GENERATED, this.runtimeCode())
        );
    return n;
  }

}
