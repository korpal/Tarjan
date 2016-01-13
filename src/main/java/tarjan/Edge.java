package tarjan;

/**
 * Reprezentuje krawedz - polaczenie pomiedzy dwoma wezlami w grafie.
 * Uzywane do reprezentacji mostow.
 */
public class Edge {
    private Node firstNode;
    private Node secondNode;

    Edge(Node first, Node second) {
        this.firstNode = first;
        this.secondNode = second;
    }

    public Node getFirstNode() {
        return firstNode;
    }

    public Node getSecondNode() {
        return secondNode;
    }

}
