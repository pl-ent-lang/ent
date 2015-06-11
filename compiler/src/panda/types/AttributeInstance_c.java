package panda.types;

import polyglot.types.MemberInstance;
import polyglot.types.Flags;
import polyglot.types.ReferenceType;
import polyglot.types.TypeObject_c;
import polyglot.util.Position;

import java.util.ArrayList;
import java.util.List;

public class AttributeInstance_c extends TypeObject_c implements AttributeInstance {

  protected Flags flags = Flags.PUBLIC;
  protected ReferenceType container;
  protected List<Mode> modes = new ArrayList<>();
  protected Mode lowerBound;
  protected Mode upperBound;

  public AttributeInstance_c(PandaTypeSystem ts, 
                             Position pos,
                             ReferenceType container,
                             Flags flags) {
    super(ts, pos);
    this.container = container;
  }

  // Property Methods
  protected List<Mode> modes() {
    return this.modes;
  }

  protected Mode lowerBound() {
    return this.lowerBound;
  }

  protected void lowerBound(Mode lowerBound) {
    this.lowerBound = lowerBound;
  }

  protected Mode upperBound() {
    return this.upperBound;
  }

  protected void upperBound(Mode upperBound) {
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
  public void addMode(Mode mode) {
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
