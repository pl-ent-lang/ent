package ent.visit;

import ent.ast.*;

import polyglot.ast.*;
import polyglot.frontend.*;
import polyglot.types.*;
import polyglot.visit.*;
import polyglot.visit.FlowGraph.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class AttributeExitChecker extends DataFlow<AttributeExitChecker.DataFlowItem> {
  protected CodeNode code;

  public AttributeExitChecker(Job job, TypeSystem ts, NodeFactory nf) {
    super(job, ts, nf, false /* backward analysis */);
  }

  @Override
  protected FlowGraph<DataFlowItem> initGraph(CodeNode code, Term root) {
    this.code = code;

    if (code instanceof AttributeDecl) {
      return super.initGraph(code, root);
    } else if (code instanceof CopyDecl) {
      return super.initGraph(code, root);
    }

    return null;
  }

  @Override
  public DataFlowItem createInitialItem(FlowGraph<DataFlowItem> graph, Term node, boolean entry) {
    return DataFlowItem.EXITS;
  }

  protected static class DataFlowItem extends DataFlow.Item {
    public final boolean exits; // whether all paths leaving this node lead to an exit

    protected DataFlowItem(boolean exits) { this.exits = exits; }

    public static final DataFlowItem EXITS = new DataFlowItem(true);
    public static final DataFlowItem DOES_NOT_EXIT = new DataFlowItem(false);

    @Override
    public String toString() {
      return "exits=" + exits;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof DataFlowItem) {
        return this.exits == ((DataFlowItem)o).exits;
      }
      return false;
    }

    @Override
    public int hashCode() {
      return (exits ? 5235 : 8673);
    }
  }

  @Override
  public Map<EdgeKey, DataFlowItem>
  flow(DataFlowItem in, FlowGraph<DataFlowItem> graph, Peer<DataFlowItem> peer) {
    Term n = peer.node();
    Set<EdgeKey> succEdgeKeys = peer.succEdgeKeys();
    // If every path from the exit node to the entry goes through a return,
    // we're okay.  So make the exit bit false at exit and true at every return;
    // the confluence operation is &&.
    // We deal with exceptions specially, and assume that any exception
    // edge to the exit node is OK.
    if (n instanceof Return) {
      return itemToMap(DataFlowItem.EXITS, succEdgeKeys);
    }

    if (n == graph.root() && !peer.isEntry()) {
      // all exception edges to the exit node are regarded as exiting
      // correctly. Make sure non-exception edges have the
      // exit bit false.
      Map<EdgeKey, DataFlowItem> m = itemToMap(DataFlowItem.EXITS, succEdgeKeys);
      if (succEdgeKeys.contains(FlowGraph.EDGE_KEY_OTHER)) {
        m.put(FlowGraph.EDGE_KEY_OTHER, DataFlowItem.DOES_NOT_EXIT);
      }
      if (succEdgeKeys.contains(FlowGraph.EDGE_KEY_TRUE)) {
        m.put(FlowGraph.EDGE_KEY_TRUE, DataFlowItem.DOES_NOT_EXIT);
      }
      if (succEdgeKeys.contains(FlowGraph.EDGE_KEY_FALSE)) {
        m.put(FlowGraph.EDGE_KEY_FALSE, DataFlowItem.DOES_NOT_EXIT);
      }

      return m;
    }

    return itemToMap(in, succEdgeKeys);
  }

  @Override
  public DataFlowItem
  confluence(List<DataFlowItem> inItems, Peer<DataFlowItem> peer, FlowGraph<DataFlowItem> graph) {
    // all paths must have an exit
    for (DataFlowItem item : inItems) {
      if (!item.exits) {
        return DataFlowItem.DOES_NOT_EXIT;
      }
    }
    return DataFlowItem.EXITS;
  }

  @Override
  public void check(FlowGraph<DataFlowItem> graph,
                    Term n,
                    boolean entry,
                    DataFlowItem inItem,
                    Map<EdgeKey, DataFlowItem> outItems) throws SemanticException {
    // Check for statements not on the path to exit; compound
    // statements are allowed to be off the path.  (e.g., "{ return; }"
    // or "while (true) S").  If a compound statement is truly
    // unreachable, one of its sub-statements will be also and we will
    // report an error there.
    if (n == graph.root() && entry) {
      if (outItems != null && !outItems.isEmpty()) {
        // due to the flow equations, all DataFlowItems in the outItems map
        // are the same, so just take the first one.
        DataFlowItem outItem = outItems.values().iterator().next();
        if (outItem != null && !outItem.exits) {
          throw new SemanticException("Missing return statement.", code.position());
        }
      }
    }
  }
}
