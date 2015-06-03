package panda.types;

import polyglot.types.Type_c;
import polyglot.types.ClassType;
import polyglot.types.Type;
import polyglot.types.FieldInstance;
import polyglot.types.MethodInstance;
import polyglot.types.Resolver;
import polyglot.util.Position;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ModeTypeVariable_c extends Type_c implements ModeTypeVariable {

  protected String name;
  protected List<Type> bounds;
  protected ModeType upperBound;
  protected ClassType declaringClass;

  public ModeTypeVariable_c(PandaTypeSystem ts,
                            Position pos,
                            String name) {
    super(ts, pos);
    this.name = name;
    this.upperBound = null;
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
    this.bounds = bounds;
  }

  public ModeType upperBound() {
    return this.upperBound;
  }

  public void upperBound(ModeType upperBound) {
    this.upperBound = upperBound;
  }

  public ClassType declaringClass() {
    return this.declaringClass;
  }

  public void declaringClass(ClassType declaringClass) {
    this.declaringClass = declaringClass;
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
    ModeType lub = null;
    for (Type t : this.bounds()) {
      ModeType tub = null;
      if (t instanceof ModeType) {
        tub = (ModeType) t;
      } else if (t instanceof ModeTypeVariable) {
        tub = ((ModeTypeVariable) t).upperBound();
      }

      if (lub == null) {
        lub = tub;
        continue;
      }

      if (ts.isSubtypeModes(tub, lub)) {
        lub = tub;
      }
    }

    System.out.println("Set : " + this.name() + " to " + lub);
  
    this.upperBound(lub);
    return true;
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
    return this.name();
  }

  // Mode Methods
  public int rank() {
    return this.upperBound().rank();
  }
  
  public final boolean isSubtypeOfMode(Mode mode) {
    return ((PandaTypeSystem) this.typeSystem()).isSubtypeModes(this, mode);
  }

  public final boolean isSupertypeOfMode(Mode mode) {
    return ((PandaTypeSystem) this.typeSystem()).isSupertypeModes(this, mode);
  }

  public boolean isSubtypeOfModeImpl(Mode mode) {
    return ((PandaTypeSystem) this.typeSystem()).isSubtypeModes(this.upperBound(), mode);
  }

  public boolean isSupertypeOfModeImpl(Mode mode) {
    return ((PandaTypeSystem) this.typeSystem()).isSupertypeModes(this.upperBound(), mode);
  }




}
