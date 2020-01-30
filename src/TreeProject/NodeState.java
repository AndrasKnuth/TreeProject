package TreeProject;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class NodeState implements Serializable {

    protected Map<String, NodeState> states = new LinkedHashMap<String, NodeState>();


    public NodeState() {
        super();
    }

    public NodeState(Map<String, NodeState> states) {
        super();
        if (states != null) {
            setStates(states);
        }
    }

    public NodeState(boolean expanded, boolean selected) {
    }

    public Map<String, NodeState> getStates() {
        return states;
    }

    public void setStates(Map<String, NodeState> states) {
        this.states = states;
    }

    public void addState(String node, boolean expanded, boolean selected) {
        states.put(node, new NodeState(expanded, selected));
    }

    public boolean isExpanded(String nodeId) {
        final NodeState state = states.get(nodeId);
        return state != null && state.isExpanded(nodeId);
    }

    public boolean isSelected(String nodeId) {
        final NodeState state = states.get(nodeId);
        return state != null && state.isSelected(nodeId);
    }
}
