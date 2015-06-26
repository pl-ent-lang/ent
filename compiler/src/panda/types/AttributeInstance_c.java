package panda.types;

import polyglot.types.*;
import polyglot.util.*;

import java.util.ArrayList;
import java.util.List;

public class AttributeInstance_c extends TypeObject_c implements AttributeInstance {

  protected Flags flags = Flags.PUBLIC;
  protected ReferenceType container;
  protected List<Type> modes = new ArrayList<>();
  protected Type lowerBound;
  protected Type upperBound;

  public AttributeInstance_c(PandaTypeSystem ts, 
                             Position pos,
                             ReferenceType container,
                             Flags flags) {
    super(ts, pos);
    this.container = container;
  }

  // Property Methods
  protected List<Type> modes() {
    return this.modes;
  }

  protected Type lowerBound() {
    return this.lowerBound;
  }

  protected void lowerBound(Type lowerBound) {
    this.lowerBound = lowerBound;
  }

  protected Type upperBound() {
    return this.upperBound;
  }

  protected void upperBound(Type upperBound) {
    this.upperBound = upperBound;
  }

  // TypeObject Methods
  public boolean isCanonical() {
    return true;
  }

  // MemberInstance Methods
  public Flags flags() {
    return this.flags;
  }

  public void setFlags(Flags flags) {
    this.flags = flags;
  }

  public ReferenceType container() {
    return this.container;
  }

  public void setContainer(ReferenceType container) {
    this.container = container;
  }

  // AttributeInstance Methods
  public void addMode(Type mode) {
    this.modes().add(mode);
  }

  @Override
  public String toString() {
    String s = "attribute<";
    for (int i = 0; i < this.modes().size(); ++i) {
      s += this.modes().get(i);
      if (i + 1 < this.modes().size()) {
        s += ",";
      }
    }
    s +=  ">[" + this.lowerBound() + "," + this.upperBound() + "]";
    return s;
  }
}
