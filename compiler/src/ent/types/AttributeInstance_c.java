package ent.types;

import polyglot.types.*;
import polyglot.util.*;

import java.util.ArrayList;
import java.util.List;

public class AttributeInstance_c extends TypeObject_c implements AttributeInstance {

  protected Flags flags = Flags.PUBLIC;
  protected List<Type> modes = new ArrayList<>();
  protected Type lowerBound;
  protected Type upperBound;

  protected ReferenceType container;
  protected MethodInstance methodContainer;

  public AttributeInstance_c(EntTypeSystem ts, Position pos, Flags flags) {
    super(ts, pos);
  }

  // Property Methods
  protected List<Type> modes() { return this.modes; }

  protected Type lowerBound() { return this.lowerBound; }

  protected void lowerBound(Type lowerBound) { this.lowerBound = lowerBound; }

  protected Type upperBound() { return this.upperBound; }

  protected void upperBound(Type upperBound) { this.upperBound = upperBound; }

  // TypeObject Methods
  public boolean isCanonical() { return true; }

  // MemberInstance Methods
  public Flags flags() { return this.flags; }

  public void setFlags(Flags flags) { this.flags = flags; }

  public ReferenceType container() { return this.container; }

  public void setContainer(ReferenceType container) { this.container = container; }

  public MethodInstance methodContainer() { return this.methodContainer; }

  public void setMethodContainer(MethodInstance methodContainer) { this.methodContainer = methodContainer; }

  // AttributeInstance Methods
  public void addMode(Type mode) { this.modes().add(mode); }

  @Override
  public String toString() {
    String s = "attribute<";
    for (int i = 0; i < this.modes().size(); ++i) {
      s += this.modes().get(i);
      if (i + 1 < this.modes().size()) {
        s += ",";
      }
    }
    s += ">[" + this.lowerBound() + "," + this.upperBound() + "]";
    return s;
  }
}
