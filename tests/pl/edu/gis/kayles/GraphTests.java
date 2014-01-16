package pl.edu.gis.kayles;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.gis.kayles.util.Graph;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * Date: 30.11.13
 * Time: 12:10
 */
public class GraphTests {

    @Test
    public void shouldAddNewEdge() {
        Graph graph = new Graph();
        graph.addEdge("A", "B");

        Assert.assertEquals(graph.getVertices().size(), 2);
        Assert.assertTrue(graph.getNeighbours("A").contains("B"));
        Assert.assertEquals(graph.getNeighbours("A").size(), 1);
        Assert.assertTrue(graph.getNeighbours("B").contains("A"));
        Assert.assertEquals(graph.getNeighbours("B").size(), 1);

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "D");
        graph.addEdge("A", "E");
        graph.addEdge("D", "E");
        graph.addEdge("B", "C");

        Assert.assertEquals(graph.getVertices().size(), 5);
        Assert.assertTrue(new HashSet(Arrays.asList("A","B","C","D","E")).containsAll(graph.getNeighbours("A")));
        Assert.assertTrue(new HashSet(Arrays.asList("A", "C")).containsAll(graph.getNeighbours("B")));
    }

    @Test
    public void shouldClearGraph() {
        Graph graph = new Graph();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "D");
        graph.addEdge("A", "E");
        graph.addEdge("D", "E");
        graph.addEdge("B", "C");
        graph.addEdge("E", "F");

        graph.kaylesRemove("A");

        Assert.assertEquals(graph.getVertices().size(), 1);
        Assert.assertTrue(graph.getVertices().contains("F"));
        Assert.assertTrue(graph.getNeighbours("F").isEmpty());
    }

}
