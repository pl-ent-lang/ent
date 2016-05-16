package ent.types;

import polyglot.types.*;
import polyglot.util.*;

public class CopyInstance_c extends TypeObject_c implements CopyInstance {

  protected Flags flags = Flags.PUBLIC;
  protected ReferenceType container;

  public CopyInstance_c(EntTypeSystem ts, Position pos, ReferenceType container) {
    super(ts, pos);
    this.container = container;
  }

  // TypeObject Methods
  public boolean isCanonical() { return true; }

  // MemberInstance Methods
  public Flags flags() { return this.flags; }

  public void setFlags(Flags flags) { this.flags = flags; }

  public ReferenceType container() { return this.container; }

  public void setContainer(ReferenceType container) { this.container = container; }

  @Override
  public String toString() {
    return "copy<" + this.container() + ">";
  }
}
