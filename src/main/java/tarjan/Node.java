package tarjan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Wierzcholek. Zawiera etykiete oraz liste sasiadow. Reprezentowane sa w ten sposob krawedzie skierowane.
 * Krawedzie nieskierowane mozna reprezentowac umieszczajac sasiadow po obu stronach, np. krawedz nieskierowana
 * miedzy wierzcholkami A oraz B mozna reprezentowac poprzez umieszczenie w wierzcholku A wierzcholka B jako sasiada
 * oraz umieszczenie w wierzcholku B wierzcholka A jako sasiada.
 */
public class Node {

    private int label;
    private List<Node> neighbors;

    public Node(final int label) {
        this.label = label;
        neighbors = new ArrayList<>();
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
