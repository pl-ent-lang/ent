package panda.types;

import polyglot.types.TypeObject_c;
import polyglot.types.SemanticException;

import java.util.Map;
import java.util.HashMap;

public class ModeOrderingInstance_c extends TypeObject_c implements ModeOrderingInstance {

  private Map<ModeType, ModeType> modeOrdering = new HashMap<ModeType, ModeType>();

  public ModeOrderingInstance_c(PandaTypeSystem typeSystem) {
    super(typeSystem);

    // Add bottom and ? type to ordering
    this.modeOrdering.put(typeSystem.bottomModeType(), null);
    this.modeOrdering.put(typeSystem.dynamicModeType(), null);
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
  public void insertModeTypeOrdering(ModeType lowerBound, ModeType upperBound) 
    throws SemanticException { 
    PandaTypeSystem typeSystem = (PandaTypeSystem) this.typeSystem();

    if (this.modeOrdering().containsKey(lowerBound)) {
      if (!this.modeOrdering().get(lowerBound).equals(typeSystem.dynamicModeType())) {
        // Semantic Error, this means the mode was defined as a bottom
        // mode twice. Currently not allowing.
        throw new SemanticException(
            lowerBound.mode() + " defined multiple times as a lower bound");
      }
    }
    this.modeOrdering().put(lowerBound, upperBound);

    if (!this.modeOrdering().containsKey(upperBound)) {
      this.modeOrdering().put(upperBound, typeSystem.dynamicModeType());
    }

    ModeType bottomModeTypeUpperBound = this.modeOrdering().get(typeSystem.bottomModeType());
    if (bottomModeTypeUpperBound == null || bottomModeTypeUpperBound.equals(upperBound)) {
      this.modeOrdering.put(typeSystem.bottomModeType(), lowerBound);
    } 
  }

  public void buildModeTypeOrdering() throws SemanticException {
    PandaTypeSystem typeSystem = (PandaTypeSystem) this.typeSystem();
    Map<ModeType, Boolean> visited = new HashMap<>();
    for (Map.Entry<ModeType, ModeType> e : this.modeOrdering().entrySet()) {
      visited.put(e.getKey(), false);
    }

    // Construct the rank of the modes while we traverse
    int rank = 0;
    ModeType iter = typeSystem.bottomModeType();
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


