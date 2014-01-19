package pl.edu.gis.kayles;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import pl.edu.gis.kayles.util.Graph;
import pl.edu.gis.kayles.util.Vertex;

/**
 * Created with IntelliJ IDEA. Date: 30.11.13 Time: 12:10
 */
public class GraphTests {

	@Test
	public void shouldAddNewEdge() {
		Graph graph = new Graph();
		graph.addEdge("A", "B");

		Assert.assertEquals(graph.getVertices().size(), 2);
		
		Assert.assertTrue(graph.getNeighbours("A").contains(new Vertex("B")));
		Assert.assertEquals(graph.getNeighbours("A").size(), 1);
		Assert.assertTrue(graph.getNeighbours("B").contains(new Vertex("A")));
		Assert.assertEquals(graph.getNeighbours("B").size(), 1);

		graph.addEdge("A", "B");
		graph.addEdge("A", "C");
		graph.addEdge("A", "D");
		graph.addEdge("A", "E");
		graph.addEdge("D", "E");
		graph.addEdge("B", "C");

		Assert.assertEquals(graph.getVertices().size(), 5);
		Assert.assertTrue(new HashSet(Arrays.asList(new Vertex("A"),
				new Vertex("B"), new Vertex("C"), new Vertex("D"), new Vertex(
						"E"))).containsAll(graph.getNeighbours("A")));
		Assert.assertTrue(new HashSet(Arrays.asList(new Vertex("A"),
				new Vertex("C"))).containsAll(graph.getNeighbours("B")));
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
		Assert.assertTrue(graph.getVertices().contains(new Vertex("F")));
		Assert.assertTrue(graph.getNeighbours("F").isEmpty());
	}

	@Test
	public void testDistanceToFarthestVertex() {
		Graph graph = new Graph();
		graph.addEdge("1", "2");
		graph.addEdge("1", "5");
		graph.addEdge("2", "3");
		graph.addEdge("2", "5");
		graph.addEdge("3", "4");
		graph.addEdge("4", "5");
		graph.addEdge("4", "6");
		Assert.assertEquals(3, graph.getDistanceToFarthestVertex("1"));
		Assert.assertEquals(3, graph.getDistanceToFarthestVertex("2"));
		Assert.assertEquals(2, graph.getDistanceToFarthestVertex("3"));
		Assert.assertEquals(3, graph.getDistanceToFarthestVertex("6"));
	}
}
