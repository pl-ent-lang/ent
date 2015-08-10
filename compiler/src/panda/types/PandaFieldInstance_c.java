package panda.types;

import polyglot.types.*;
import polyglot.util.*;
import polyglot.ext.jl5.types.*;

public class PandaFieldInstance_c extends JL5FieldInstance_c implements PandaFieldInstance {

  public PandaFieldInstance_c(
      PandaTypeSystem ts, 
      Position pos, 
      ReferenceType container, 
      Flags flags, 
      Type type, 
      String name) {
    super(ts, pos, container, flags, type, name);
  }

  @Override
  public boolean equalsImpl(TypeObject o) {
    if (o instanceof FieldInstance) {
      FieldInstance i = (FieldInstance) o;
      return flags.equals(i.flags()) && 
             ts.equals(type, i.type()) && 
             name.equals(i.name()) && 
             ts.typeEquals(container, i.container());
    }

    return false;
  }


}
