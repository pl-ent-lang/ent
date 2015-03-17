package panda.types;

import polyglot.types.TypeObject_c;
import polyglot.types.SemanticException;

import java.util.Map;
import java.util.HashMap;

public class ModeOrderingInstance_c extends TypeObject_c implements ModeOrderingInstance {

  private Map<ModeType, ModeType> modeOrdering = new HashMap<ModeType, ModeType>();
  private ModeType dynModeType = null;
  private ModeType bottomModeType = null;

  public ModeOrderingInstance_c(PandaTypeSystem pandaTypeSystem) {
    super(pandaTypeSystem);
    // Construct the ? mode type
    this.dynModeType = pandaTypeSystem.ModeType("?");

    // Add ? type to the type system
    this.modeOrdering().put(this.dynModeType(), null);
  }

  // Property Methods
  public Map<ModeType, ModeType> modeOrdering() {
    return this.modeOrdering;
  }

  public ModeType bottomModeType() {
    return this.bottomModeType;
  }

  public void bottomModeType(ModeType bottomModeType) {
    this.bottomModeType = bottomModeType;
  } 

  public ModeType dynModeType() {
    return this.dynModeType;
  }

  public void dynModeType(ModeType dynModeType) {
    this.dynModeType = dynModeType;
  }

  @Override
  public boolean isCanonical() {
    return true;
  } 

  // Business Methods
  public void insertModeTypeOrdering(ModeType lowerBound, ModeType upperBound) 
    throws SemanticException { 

    if (this.modeOrdering().containsKey(lowerBound)) {
      if (!this.modeOrdering().get(lowerBound).equals(dynModeType)) {
        // Semantic Error, this means the mode was defined as a bottom
        // mode twice. Currently not allowing.
        throw new SemanticException(
            lowerBound.mode() + " defined multiple times as a lower bound");
      }
    }
    this.modeOrdering().put(lowerBound, upperBound);

    if (!this.modeOrdering().containsKey(upperBound)) {
      this.modeOrdering().put(upperBound, dynModeType);
    }

    if (this.bottomModeType() == null) {
      this.bottomModeType(lowerBound);
    } else if (this.bottomModeType().equals(upperBound)) {
      this.bottomModeType(lowerBound);
    }
  }

  public void buildModeTypeOrdering() throws SemanticException {
    Map<ModeType, Boolean> visited = new HashMap();
    for (Map.Entry<ModeType, ModeType> e : this.modeOrdering().entrySet()) {
      visited.put(e.getKey(), false);
    }

    // Construct the rank of the modes while we traverse

    int rank = 0;
    ModeType iter = this.bottomModeType();
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


