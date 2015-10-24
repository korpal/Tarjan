package tarjan;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TarjanTest {

    private Graph graphA;
    private Graph graphB;
    private Graph graphC;
    private Tarjan tarjan;

    @BeforeClass
    public void setUp() throws IOException {
        tarjan = new Tarjan();

        graphA = new InputReader(getClass().getResourceAsStream("/tarjan/graphA.csv")).read();
        graphB = new InputReader(getClass().getResourceAsStream("/tarjan/graphB.csv")).read();
        graphC = new InputReader(getClass().getResourceAsStream("/tarjan/graphC.csv")).read();
    }

    @Test
    public void graphATest() {
        List<Edge> bridges = tarjan.calculate(graphA);

        validateBridges(bridges, new Integer[][]{{0, 1}});
    }

    @Test
    public void graphBTest() {
        List<Edge> bridges = tarjan.calculate(graphB);

        validateBridges(bridges, new Integer[][]{{1, 4}});
    }

    @Test
    public void graphCTest() {
        List<Edge> bridges = tarjan.calculate(graphC);

        validateBridges(bridges, new Integer[][]{{0, 3}, {1, 4}});
    }

    private void validateBridges(final List<Edge> bridges, final Integer[][] expectedConnections) {
        Assert.assertEquals(bridges.size(), expectedConnections.length);

        List<SimpleEdge> existingBridges = new ArrayList<>();
        bridges.forEach(bridge -> existingBridges.add(new SimpleEdge(bridge)));

        List<SimpleEdge> expectedBridges = new ArrayList<>();
        for (Integer[] connection : expectedConnections) {
            expectedBridges.add(new SimpleEdge(connection[0], connection[1]));
        }

        Assert.assertTrue(existingBridges.containsAll(expectedBridges) && expectedBridges.containsAll(existingBridges));
    }

    private static class SimpleEdge {
        private final int firstNodeLabel;
        private final int secondNodeLabel;

        public SimpleEdge(int firstNodeLabel, int secondNodeLabel) {
            this.firstNodeLabel = firstNodeLabel;
            this.secondNodeLabel = secondNodeLabel;
        }

        public SimpleEdge(final Edge edge) {
            firstNodeLabel = edge.getFirstNode().getLabel();
            secondNodeLabel = edge.getSecondNode().getLabel();
        }

        public int getFirstNodeLabel() {
            return firstNodeLabel;
        }

        public int getSecondNodeLabel() {
            return secondNodeLabel;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof SimpleEdge)) {
                return false;
            }
            SimpleEdge otherEdge = (SimpleEdge) other;
            return ((getFirstNodeLabel() == otherEdge.getFirstNodeLabel()) && (getSecondNodeLabel() == otherEdge.getSecondNodeLabel())) ||
                    ((getFirstNodeLabel() == otherEdge.getSecondNodeLabel()) && (getSecondNodeLabel() == otherEdge.getFirstNodeLabel()));
        }
    }
}
