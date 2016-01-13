package tarjan;

import java.util.ArrayList;
import java.util.List;

/**
 * Algorytm Tarjana znajdujacy wszystkie mosty w grafie.
 */
public class Tarjan {

    private int[] bfsNodeNumbers;
    private int highestNodeNumber;
    private List<Edge> bridges;

    /**
     * Dla podanego grafu zwraca liste krawedzi bedacych wszystkimi mostami w tym grafie.
     */
    public List<Edge> calculate(final Graph graph) {
        bfsNodeNumbers = new int[graph.size()];
        highestNodeNumber = 0;
        bridges = new ArrayList<>();

        for (Node node : graph.getNodes()) {
            int nodeNumber = bfsNodeNumbers[node.getLabel()];
            // Jezeli numer wierzcholka wynosi 0 to znaczy, ze nie byl on jeszcze odwiedzony
            if (nodeNumber == 0) {
                highestNodeNumber = 0;
                iteration(node, null);
            }
        }

        return bridges;
    }

    private int iteration(final Node node, final Node parent) {
        bfsNodeNumbers[node.getLabel()] = ++highestNodeNumber;
        // Wyliczamy wartosc parametru low. Najpierw przypisujemy jej numer najwiekszego wierzcholka
        int low = highestNodeNumber;

        for (Node neighbor : node.getNeighbors()) {
            if (neighbor.equals(parent)) {
                continue;
            }

            int neighborNodeNumber = bfsNodeNumbers[neighbor.getLabel()];
            if (neighborNodeNumber > 0) {
                // Sasiad byl juz odwiedzony, co oznacza, ze ta krawedz nie nalezy do drzewa rozpinajacego.
                if (neighborNodeNumber < low) {
                    low = neighborNodeNumber;
                }
            } else {
                // Sasiad nie byl jeszcze odwiedzony - wykonujemy dla niego iteracje.
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
