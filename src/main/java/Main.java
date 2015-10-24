import tarjan.Edge;
import tarjan.Graph;
import tarjan.Node;
import tarjan.Tarjan;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
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

        for (Edge bridge : bridges) {
            System.out.println(bridge.getFirstNode().getLabel() + " - " + bridge.getSecondNode().getLabel());
        }
    }
}
