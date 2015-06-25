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

public class ModeTypeVariable_c extends ModeType_c implements ModeTypeVariable {

  private static int gen = 0;

  protected String name;
  protected List<Type> bounds;
  protected Type upperBound;
  protected Type lowerBound;
  protected ClassType declaringClass;
  protected int uniqueId;

  public ModeTypeVariable_c(PandaTypeSystem ts,
                            Position pos,
                            String name) {
    super(ts, name);
    this.bounds = Collections.emptyList();
    this.name = name;
    this.uniqueId = this.genId();
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

  public int uniqueId() {
    return this.uniqueId;
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
    String s = "";
    if (this.hasLowerBound()) {
      s += this.lowerBound() + " <: ";
    }
    s += this.name() + " <: ";
    s += this.upperBound();
    return s;
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

}
