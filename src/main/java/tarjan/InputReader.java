package tarjan;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

public class InputReader {

    private InputStream inputStream;
    private List<CSVRecord> records;
    private Map<Integer, Node> nodes;

    public InputReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Graph read() throws IOException {
        Reader reader = new InputStreamReader(inputStream);
        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);
        records = parser.getRecords();

        nodes = createAllNodes();
        addNeighborsToAllNodes();

        return new Graph(new ArrayList<>(nodes.values()));
    }

    private Map<Integer, Node> createAllNodes() {
        Map<Integer, Node> nodes = new HashMap<>();
        records.forEach(record -> {
            Integer nodeLabel = Integer.parseInt(record.get(0));
            nodes.put(nodeLabel, new Node(nodeLabel));
        });
        return nodes;
    }

    private void addNeighborsToAllNodes() {
        records.forEach(record -> {
            Iterator<String> labels = record.iterator();
            Integer nodeLabel = Integer.parseInt(labels.next());
            Node node = nodes.get(nodeLabel);
            addNeighbors(node, labels);
        });
    }

    private void addNeighbors(final Node node, final Iterator<String> neighborLabels) {
        neighborLabels.forEachRemaining(neighborStrLabel -> {
            Integer neighborLabel = Integer.parseInt(neighborStrLabel);
            Node neighbor = nodes.get(neighborLabel);
            node.addNeighbors(neighbor);
        });
    }
}
