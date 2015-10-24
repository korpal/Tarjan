package tarjan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {

    private int label;
    private List<Node> neighbors;

    public Node(final int label) {
        this.label = label;
        neighbors = new ArrayList<>();
    }

    public Node(final List<Node> neighbors) {
        this.neighbors = neighbors;
    }

    public void addNeighbors(final Node... neighbors) {
        this.neighbors.addAll(Arrays.asList(neighbors));
    }

    public int getLabel() {
        return label;
    }

    List<Node> getNeighbors() {
        return neighbors;
    }
}
