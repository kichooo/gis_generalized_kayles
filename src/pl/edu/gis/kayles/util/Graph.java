package pl.edu.gis.kayles.util;

import java.util.*;

/**
 * Created with IntelliJ IDEA. Date: 30.11.13 Time: 12:26
 */
public class Graph {
	private Map<String, Vertex> vertices;

	public Graph() {
		vertices = new HashMap<String, Vertex>();
	}

	public void kaylesRemove(final String vertex) {
		kaylesRemove(vertices.get(vertex));
	}

	public void kaylesRemove(Vertex vertex) {
		Set<Vertex> verticesToRemove = new HashSet<Vertex>();

		verticesToRemove.addAll(vertex.getNeighbours());
		verticesToRemove.add(vertex);

		for (Vertex v : verticesToRemove) {
			for (Vertex neighbour : v.getNeighbours()) {
				neighbour.getNeighbours().remove(v);
			}
			vertices.remove(v.toString());
		}

	}

	public void addVertex(Vertex vertex) {
		vertices.put(vertex.toString(), vertex);
	}

	public void addEdge(final String vertexFromName, final String vertexToName) {
		final Vertex vertexFrom, vertexTo;
		if (!vertices.containsKey(vertexFromName)) {
			vertexFrom = new Vertex(vertexFromName);
			vertices.put(vertexFromName, vertexFrom);
		} else
			vertexFrom = vertices.get(vertexFromName);

		if (!vertices.containsKey(vertexToName)) {
			vertexTo = new Vertex(vertexToName);
			vertices.put(vertexToName, vertexTo);
		} else
			vertexTo = vertices.get(vertexToName);

		addEdge(vertexFrom, vertexTo);
	}

	public void addEdge(Vertex vertexFrom, Vertex vertexTo) {
		vertexFrom.getNeighbours().add(vertexTo);
		vertexTo.getNeighbours().add(vertexFrom);
	}

	public Collection<Vertex> getVertices() {
		return vertices.values();
	}

	public Set<Vertex> getNeighbours(final String vertexName) {
		return vertices.get(vertexName).getNeighbours();
	}

	public Set<Vertex> getNeighbours(Vertex vertex) {
		return vertex.getNeighbours();
	}

	public int getDistanceToFarthestVertex(String vertexFrom) {
		return getDistanceToFarthestVertex(vertices.get(vertexFrom));
	}

	public int getDistanceToFarthestVertex(Vertex vertexFrom) {
		for (Vertex v : getVertices()) {
			v.setColor(Vertex.Color.UNVISITED);
			v.setDistance(Integer.MAX_VALUE);
		}
		Queue<Vertex> queue = new java.util.LinkedList<Vertex>();
		queue.offer(vertexFrom);
		vertexFrom.setColor(Vertex.Color.VISITED);
		vertexFrom.setDistance(0);
		// System.out.println("nowy");
		int farthest = 0;
		while (!queue.isEmpty()) {
			Vertex vertex = queue.poll();
			// System.out.println(vertex.toString());
			if (farthest < vertex.getDistance())
				farthest = vertex.getDistance();
			for (Vertex v : getNeighbours(vertex)) {
				if (v.getColor() == Vertex.Color.UNVISITED) {
					if (v.getDistance() > vertex.getDistance() + 1) {
						v.setDistance(vertex.getDistance() + 1);
					}
					v.setColor(Vertex.Color.VISITED);
					// System.out.println("Adding to queue" + v.toString());
					queue.offer(v);
				}

			}
		}
		return farthest; // implement me
	}

	public int getExtendedNeighboursCount(Vertex vertex) {
		Set<Vertex> set = new HashSet<Vertex>();
		for (Vertex v : getNeighbours(vertex)) {
			set.addAll(getNeighbours(v));
		}
		return set.size();
	}
}
