package ent.types;

import polyglot.types.*;
import polyglot.util.*;
import polyglot.ext.jl5.types.*;

import java.util.Collections;
import java.util.List;

public class EntAnnotationTypeElemInstance_c
    extends EntMethodInstance_c implements EntAnnotationTypeElemInstance {

  private static final long serialVersionUID = SerialVersionUID.generate();

  protected boolean hasDefault;

  public EntAnnotationTypeElemInstance_c(EntTypeSystem ts,
                                         Position pos,
                                         ReferenceType container,
                                         Flags flags,
                                         Type type,
                                         String name,
                                         boolean hasDefault) {
    super(ts,
          pos,
          container,
          flags,
          type,
          name,
          Collections.<Type>emptyList(),
          Collections.<Type>emptyList(),
          Collections.<TypeVariable>emptyList(),
          Collections.<ModeTypeVariable>emptyList());
    this.hasDefault = hasDefault;
  }

  @Override
  public Type type() {
    return this.returnType();
  }

  @Override
  public boolean hasDefault() {
    return hasDefault;
  }
}
