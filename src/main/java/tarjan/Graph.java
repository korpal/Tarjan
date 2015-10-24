package tarjan;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Node> nodes;

    public Graph() {
        nodes = new ArrayList<>();
    }

    public Graph(final List<Node> nodes) {
        this.nodes = nodes;
    }

    public void addNode(final Node node) {
        nodes.add(node);
    }

    List<Node> getNodes() {
        return nodes;
    }
}