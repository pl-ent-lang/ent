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

  private static int gen = 0;

  protected String name;
  protected List<Mode> bounds;
  protected Mode upperBound;
  protected ClassType declaringClass;
  protected int id;

  public ModeTypeVariable_c(PandaTypeSystem ts,
                            Position pos,
                            String name) {
    super(ts, pos);
    this.name = name;
    this.bounds = Collections.emptyList();
    this.id = this.genId();
  }

  // Property Methods
  public String name() {
    return this.name;
  }

  public void name(String name) {
    this.name = name;
  }

  public List<Mode> bounds() {
    return this.bounds;
  }

  public void bounds(List<Mode> bounds) {
    if (bounds == null) {
      this.bounds = Collections.emptyList();
    } else {
      this.bounds = bounds;
    }
  }

  public Mode upperBound() {
    return this.upperBound;
  }

  public void upperBound(Mode upperBound) {
    this.upperBound = upperBound;
  }

  public ClassType declaringClass() {
    return this.declaringClass;
  }

  public void declaringClass(ClassType declaringClass) {
    this.declaringClass = declaringClass;
  }

  public int id() {
    return this.id;
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
    Mode lub = null;
    for (Mode m : this.bounds()) {
      if (lub == null) {
        lub = m;
        continue;
      }

      if (ts.isSubtypeModes(m, lub)) {
        lub = m;
      }
    }
  
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
  private int genId() {
    return ModeTypeVariable_c.gen++;
  }

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
