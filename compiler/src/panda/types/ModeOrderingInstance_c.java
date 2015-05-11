package panda.types;

import polyglot.types.TypeObject_c;
import polyglot.types.SemanticException;

import java.util.Map;
import java.util.HashMap;

public class ModeOrderingInstance_c extends TypeObject_c implements ModeOrderingInstance {

  private Map<ModeType, ModeType> modeOrdering = new HashMap<ModeType, ModeType>();

  public ModeOrderingInstance_c(PandaTypeSystem ts) {
    super(ts);

    // Add bottom and ? type to ordering
    this.modeOrdering.put(ts.WildcardModeType(), null);
    this.modeOrdering.put(ts.DynamicModeType(), null);
  }

  // Property Methods
  public Map<ModeType, ModeType> modeOrdering() {
    return this.modeOrdering;
  }

  @Override
  public boolean isCanonical() {
    return true;
  } 

  // Business Methods
  public void insertModeTypeOrdering(ModeType lb, ModeType ub) 
    throws SemanticException { 
    PandaTypeSystem ts = (PandaTypeSystem) this.typeSystem();

    if (this.modeOrdering().containsKey(lb)) {
      if (!this.modeOrdering().get(lb).equals(ts.DynamicModeType())) {
        // Semantic Error, this means the mode was defined as a bottom
        // mode twice. Currently not allowing.
        throw new SemanticException(
            lb.mode() + " defined multiple times as a lower bound");
      }
    }
    this.modeOrdering().put(lb, ub);

    if (!this.modeOrdering().containsKey(ub)) {
      this.modeOrdering().put(ub, ts.DynamicModeType());
    }

    ModeType wildcardUb = this.modeOrdering().get(ts.WildcardModeType());
    if (wildcardUb == null || wildcardUb.equals(ub)) {
      this.modeOrdering.put(ts.WildcardModeType(), lb);
    } 
  }

  public void buildModeTypeOrdering() throws SemanticException {
    PandaTypeSystem ts = (PandaTypeSystem) this.typeSystem();
    Map<ModeType, Boolean> visited = new HashMap<>();
    for (Map.Entry<ModeType, ModeType> e : this.modeOrdering().entrySet()) {
      visited.put(e.getKey(), false);
    }

    // Construct the rank of the modes while we traverse
    int rank = 0;
    ModeType iter = ts.WildcardModeType();
    while(iter != null) {
      if (visited.get(iter)) {
        throw new SemanticException("Modes do not form a partial ordering!");
      }

      visited.put(iter, true);
      iter.rank(rank);
      iter = this.modeOrdering().get(iter);
      ++rank;
    }

    for (Map.Entry<ModeType, Boolean> e : visited.entrySet()) {
      if (!e.getValue()) {
        throw new SemanticException("Modes do not form a partial ordering!");
      }
    }
  }



}


