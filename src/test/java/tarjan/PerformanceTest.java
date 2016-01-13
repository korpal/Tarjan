package tarjan;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class PerformanceTest {

    private Graph graphA;
    private Graph graphB;
    private Graph graphC;
    private Graph graphLarge;
    private Tarjan tarjan;

    @BeforeClass
    public void setUp() throws IOException {
        tarjan = new Tarjan();

        graphA = new InputReader(getClass().getResourceAsStream("/tarjan/graphA.csv")).read();
        graphB = new InputReader(getClass().getResourceAsStream("/tarjan/graphB.csv")).read();
        graphC = new InputReader(getClass().getResourceAsStream("/tarjan/graphC.csv")).read();
        graphLarge = new InputReader(getClass().getResourceAsStream("/tarjan/graphLarge.csv")).read();
    }

    @Test
    public void graphATest() {
        for (int i = 0; i < 10000000; i++) {
            tarjan.calculate(graphA);
        }
    }

    @Test
    public void graphBTest() {
        for (int i = 0; i < 10000000; i++) {
            tarjan.calculate(graphB);
        }
    }

    @Test
    public void graphCTest() {
        for (int i = 0; i < 10000000; i++) {
            tarjan.calculate(graphC);
        }
    }

    @Test
    public void graphLargeTest() {
        for (int i = 0; i < 1000; i++) {
            tarjan.calculate(graphLarge);
        }
    }

}
