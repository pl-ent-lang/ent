package ent.types;

import polyglot.types.Type;

public interface ModeValueType extends Type {

  Type mode();

  boolean containsBuiltin();
  boolean containsVariable();
}
