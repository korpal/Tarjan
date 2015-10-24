package tarjan;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TarjanTest {

    @Test
    public void firstGraphTest() {
        Node node0 = new Node("0");
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        Node node5 = new Node("5");
        Node node6 = new Node("6");

        node0.addNeighbors(node1, node2, node3);
        node1.addNeighbors(node0, node2, node4);
        node2.addNeighbors(node0, node1);
        node3.addNeighbors(node0);
        node4.addNeighbors(node1, node5, node6);
        node5.addNeighbors(node4, node6);
        node6.addNeighbors(node4, node5);

        Graph graph = new Graph(Arrays.asList(node0, node1, node2, node3, node4, node5, node6));

        Tarjan tarjan = new Tarjan();
        List<Edge> bridges = tarjan.calculate(graph);

        Assert.assertEquals(bridges.size(), 2);
        for (Edge bridge : bridges) {
            Set<String> labels = new HashSet<>();
            labels.add(bridge.getFirstNode().getLabel());
            labels.add(bridge.getSecondNode().getLabel());

            Assert.assertTrue((labels.contains("0") && labels.contains("3")) ||
                    (labels.contains("1") && labels.contains("4")));
        }
    }

    @Test
    public void secondGraphTest() {
        Node node0 = new Node("0");
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        Node node5 = new Node("5");

        node0.addNeighbors(node1, node2);
        node1.addNeighbors(node0, node2, node4);
        node2.addNeighbors(node0, node1);
        node3.addNeighbors(node4, node5);
        node4.addNeighbors(node1, node3, node5);
        node5.addNeighbors(node3, node4);

        Graph graph = new Graph(Arrays.asList(node0, node1, node2, node3, node4, node5));

        Tarjan tarjan = new Tarjan();
        List<Edge> bridges = tarjan.calculate(graph);

        Assert.assertEquals(bridges.size(), 1);

        Edge bridge = bridges.get(0);
        Set<String> labels = new HashSet<>();
        labels.add(bridge.getFirstNode().getLabel());
        labels.add(bridge.getSecondNode().getLabel());

        Assert.assertTrue(labels.contains("1") && labels.contains("4"));
    }
}
