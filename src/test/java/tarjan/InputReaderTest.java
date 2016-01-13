package tarjan;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Sprawdza poprawnosc wczytywania grafu z pliku csv.
 */
public class InputReaderTest {

    private Graph graph;

    @Test
    public void shouldCreateGraphFromFileProperly() throws IOException {
        InputStream in = getClass().getResourceAsStream("/tarjan/graphA.csv");

        graph = new InputReader(in).read();

        checkIfNodeHaveExpectedNeighbors(getNodeWithLabel(0), 1, 2, 3);
        checkIfNodeHaveExpectedNeighbors(getNodeWithLabel(1), 0, 4, 5);
        checkIfNodeHaveExpectedNeighbors(getNodeWithLabel(2), 0, 3, 6);
        checkIfNodeHaveExpectedNeighbors(getNodeWithLabel(3), 0, 2, 6);
        checkIfNodeHaveExpectedNeighbors(getNodeWithLabel(4), 1, 5);
        checkIfNodeHaveExpectedNeighbors(getNodeWithLabel(5), 1, 4);
        checkIfNodeHaveExpectedNeighbors(getNodeWithLabel(6), 2, 3);
    }

    private Node getNodeWithLabel(final int label) {
        return graph.getNodes().stream()
                .filter(node -> node.getLabel() == label)
                .findFirst()
                .get();
    }

    private void checkIfNodeHaveExpectedNeighbors(final Node node, final Integer... expectedNeighborLabels) {
        List<Node> neighbors = node.getNeighbors();
        Set<Integer> neighborLabels = neighbors.stream()
                .map(Node::getLabel)
                .collect(Collectors.toSet());

        for (Integer expectedNeighborLabel : expectedNeighborLabels) {
            Assert.assertTrue(neighborLabels.contains(expectedNeighborLabel));
        }
    }
}
