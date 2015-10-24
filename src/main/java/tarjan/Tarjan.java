package tarjan;

import java.util.ArrayList;
import java.util.List;

public class Tarjan {

    private int[] bfsNodeNumbers;
    private int highestNodeNumber;
    private List<Edge> bridges;

    public List<Edge> calculate(final Graph graph) {
        bfsNodeNumbers = new int[graph.size()];
        highestNodeNumber = 0;
        bridges = new ArrayList<>();

        for (Node node : graph.getNodes()) {
            int nodeNumber = bfsNodeNumbers[node.getLabel()];
            if (nodeNumber == 0) {
                highestNodeNumber = 0;
                iteration(node, null);
            }
        }

        return bridges;
    }

    private int iteration(final Node node, final Node parent) {
        bfsNodeNumbers[node.getLabel()] = ++highestNodeNumber;
        // First assign nodeNumber to low
        int low = highestNodeNumber;

        for (Node neighbor : node.getNeighbors()) {
            if (neighbor.equals(parent)) {
                continue;
            }

            int neighborNodeNumber = bfsNodeNumbers[neighbor.getLabel()];
            if (neighborNodeNumber > 0) {
                // Neighbor already visited which means that this edge does not belong to the spanning tree.
                if (neighborNodeNumber < low) {
                    low = neighborNodeNumber;
                }
            } else {
                int childLow = iteration(neighbor, node);
                if (childLow < low) {
                    low = childLow;
                }
            }
        }

        if (parent != null && low == bfsNodeNumbers[node.getLabel()]) {
            bridges.add(new Edge(parent, node));
        }

        return low;
    }

}
