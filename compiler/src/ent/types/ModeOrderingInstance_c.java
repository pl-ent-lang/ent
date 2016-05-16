package ent.types;

import ent.runtime.ENT_Modes;

import polyglot.types.TypeObject_c;
import polyglot.types.SemanticException;

import java.util.Map;
import java.util.HashMap;

public class ModeOrderingInstance_c extends TypeObject_c implements ModeOrderingInstance {

  private Map<ModeType, ModeType> modeOrdering = new HashMap<ModeType, ModeType>();

  public ModeOrderingInstance_c(EntTypeSystem ts) {
    super(ts);

    // Add _ and ? type to ordering
    this.modeOrdering.put(ts.BottomModeType(), null);
    this.modeOrdering.put(ts.DynamicModeType(), null);
  }

  // Property Methods
  public Map<ModeType, ModeType> modeOrdering() { return this.modeOrdering; }

  @Override
  public boolean isCanonical() {
    return true;
  }

  // Business Methods
  public void insertModeTypeOrdering(ModeType lb, ModeType ub) throws SemanticException {
    EntTypeSystem ts = (EntTypeSystem)this.typeSystem();

    if (this.modeOrdering().containsKey(lb)) {
      if (!this.modeOrdering().get(lb).equals(ts.DynamicModeType())) {
        // Semantic Error, this means the mode was defined as a bottom
        // mode twice. Currently not allowing.
        throw new SemanticException(lb + " defined multiple times as a lower bound");
      }
    }
    this.modeOrdering().put(lb, ub);

    if (!this.modeOrdering().containsKey(ub)) {
      this.modeOrdering().put(ub, ts.DynamicModeType());
    }

    ModeType bottomUb = this.modeOrdering().get(ts.BottomModeType());
    if (bottomUb == null || bottomUb.equals(ub)) {
      this.modeOrdering.put(ts.BottomModeType(), lb);
    }
  }

  public void buildModeTypeOrdering() throws SemanticException {
    EntTypeSystem ts = (EntTypeSystem)this.typeSystem();
    Map<ModeType, Boolean> visited = new HashMap<>();
    for (Map.Entry<ModeType, ModeType> e : this.modeOrdering().entrySet()) {
      visited.put(e.getKey(), false);
    }

    // Set supertypes of all the modes
    ModeType iter = ts.BottomModeType();
    ModeType last = null;

    int uniqueId = 0;

    // Set a uniqueId for each mode for compilation
    while (iter != null) {
      if (visited.get(iter)) {
        throw new SemanticException("Modes do not form a partial ordering!");
      }

      if (iter != ts.BottomModeType() && iter != ts.DynamicModeType()) {
        iter.uniqueId(uniqueId++);
      }

      visited.put(iter, true);
      if (last != null) {
        last.superType(iter);
      }
      last = iter;
      iter = this.modeOrdering().get(iter);
    }
    last.superType(null);

    for (Map.Entry<ModeType, Boolean> e : visited.entrySet()) {
      if (!e.getValue()) {
        throw new SemanticException("Modes do not form a partial ordering!");
      }
    }
  }
}
