package tarjan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tarjan {

    private Map<Node, Integer> nodeNumbers;
    private int highestNodeNumber;
    private List<Edge> bridges;

    public List<Edge> calculate(final Graph graph) {
        nodeNumbers = new HashMap<>();
        highestNodeNumber = 0;
        bridges = new ArrayList<>();

        for (Node node : graph.getNodes()) {
            Integer nodeNumber = nodeNumbers.get(node);
            if (nodeNumber == null) {
                highestNodeNumber = 0;
                iteration(node, null);
            }
        }

        return bridges;
    }

    private int iteration(final Node node, final Node parent) {
        nodeNumbers.put(node, ++highestNodeNumber);
        int low = highestNodeNumber;

        for (Node neighbor : node.getNeighbors()) {
            if (neighbor.equals(parent)) {
                continue;
            }

            Integer neighborNodeNumber = nodeNumbers.get(neighbor);
            if (neighborNodeNumber != null) {
                // Not visited
                if (neighborNodeNumber < low) {
                    low = neighborNodeNumber;
                }
            } else {
                int temp = iteration(neighbor, node);
                if (temp < low) {
                    low = temp;
                }
            }
        }

        if (parent != null && low == nodeNumbers.get(node)) {
            bridges.add(new Edge(parent, node));
        }

        return low;
    }

}
