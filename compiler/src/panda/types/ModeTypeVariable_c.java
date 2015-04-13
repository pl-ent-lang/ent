package panda.types;

import polyglot.types.ReferenceType;
import polyglot.types.ReferenceType_c;
import polyglot.types.ClassType;
import polyglot.types.Type;
import polyglot.types.FieldInstance;
import polyglot.types.MethodInstance;
import polyglot.types.Resolver;
import polyglot.util.Position;


import java.util.List;
import java.util.Collections;

public class ModeTypeVariable_c extends ReferenceType_c implements ModeTypeVariable {

  protected String name;
  protected ClassType declaringClass;

  public ModeTypeVariable_c(PandaTypeSystem pandaTypeSystem,
                            Position pos,
                            String name) {
    super(pandaTypeSystem, pos);
    this.name = name;
  }

  // Property Methods
  public String name() {
    return this.name;
  }

  public void name(String name) {
    this.name = name;
  }

  public ClassType declaringClass() {
    return this.declaringClass;
  }

  public void declaringClass(ClassType declaringClass) {
    this.declaringClass = declaringClass;
  }

  @Override
  public boolean isCanonical() {
    return true;
  }

  @Override
  public Type superType() {
    return null;
  } 

  @Override
  public List<? extends ReferenceType> interfaces() {
    return Collections.emptyList();
  }

  @Override
  public List<? extends FieldInstance> fields() {
    return Collections.emptyList();
  }

  @Override
  public List<? extends MethodInstance> methods() {
    return Collections.emptyList();
  }

  @Override
  public FieldInstance fieldNamed(String name) {
    for (FieldInstance fi : fields()) {
      if (fi.name().equals(name)) {
        return fi;
      }
    }
    return null;
  }

  @Override
  public String fullName() {
    return this.name();
  }


  @Override
  public String toString() {
    return this.name();
  }

  @Override
  public String translate(Resolver c) {
    return this.name();
  }



}
