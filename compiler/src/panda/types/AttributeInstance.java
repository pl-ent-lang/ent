package panda.types;

import polyglot.types.CodeInstance;
import polyglot.types.MemberInstance;

public interface AttributeInstance extends CodeInstance, MemberInstance {

  void addMode(Mode mode);

}
