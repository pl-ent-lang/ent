package panda.types;

import polyglot.ext.jl7.types.JL7TypeSystem;

import polyglot.ast.Id;
import polyglot.types.*;

import java.util.Map;

public interface PandaTypeSystem extends JL7TypeSystem {
  // Factory Methods
  ModeType ModeType(String mode);
  ModeOrderingInstance ModeOrderingInstance();

  // TypeSystem Methods
  public boolean isSubtypeModes(ModeType lowerBound, ModeType upperBound);

  public boolean isSupertypeModes(ModeType lowerBound, ModeType upperBound);

}
