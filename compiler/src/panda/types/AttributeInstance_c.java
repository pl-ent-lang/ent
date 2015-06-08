package panda.types;

import polyglot.types.MemberInstance;
import polyglot.types.Flags;
import polyglot.types.ReferenceType;
import polyglot.types.TypeObject_c;
import polyglot.util.Position;

public class AttributeInstance_c extends TypeObject_c implements AttributeInstance {

  protected Flags flags;
  protected ReferenceType container;
  private ModeType lowerBound;
  private ModeType upperBound;

  public AttributeInstance_c(PandaTypeSystem ts, 
                             Position pos,
                             ReferenceType container,
                             Flags flags) {
    super(ts, pos);
    this.container = container;

    // AttributeInstance needs to set some default flags
    this.flags = Flags.PUBLIC;
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


}
