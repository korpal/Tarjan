package tarjan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {

    private String label;
    private List<Node> neighbors;

    public Node(final String label) {
        this.label = label;
        neighbors = new ArrayList<>();
    }

    public Node(final List<Node> neighbors) {
        this.neighbors = neighbors;
    }

    public void addNeighbors(final Node... neighbors) {
        this.neighbors.addAll(Arrays.asList(neighbors));
    }

    public String getLabel() {
        return label;
    }

    List<Node> getNeighbors() {
        return neighbors;
    }
}
