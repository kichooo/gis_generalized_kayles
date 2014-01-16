package pl.edu.gis.kayles.util;

import java.util.*;

/**
 * Created with IntelliJ IDEA. Date: 30.11.13 Time: 12:26
 */
public class Graph {
	private Map<Vertex, Set<Vertex>> neighbourList;

	public Graph() {
		neighbourList = new HashMap<Vertex, Set<Vertex>>();
	}

	public void kaylesRemove(final String vertex) {
		kaylesRemove(new Vertex(vertex));
	}

	public void kaylesRemove(Vertex vertex) {
		List<Vertex> removedVertices = new ArrayList<Vertex>(
				Arrays.asList(vertex));

		Set<Vertex> neighbours = neighbourList.remove(vertex);
		for (Vertex neighbour : neighbours) {
			neighbourList.remove(neighbour);
			removedVertices.add(neighbour);
		}

		for (Set<Vertex> v : neighbourList.values()) {
			v.removeAll(removedVertices);
		}

	}

	public void addVertex(Vertex vertex) {
		if (!neighbourList.containsKey(vertex)) {
			neighbourList.put(vertex, new HashSet<Vertex>());
		}
	}

	public void addEdge(final String vertexFrom, final String vertexTo) {
		addEdge(new Vertex(vertexFrom), new Vertex(vertexTo));
	}

	public void addEdge(Vertex vertexFrom, Vertex vertexTo) {
		Set<Vertex> fromVertexList = neighbourList.get(vertexFrom);
		Set<Vertex> toVertexList = neighbourList.get(vertexTo);

		if (fromVertexList == null) {
			neighbourList.put(vertexFrom,
					new HashSet<Vertex>(Arrays.asList(vertexTo)));
		} else {
			fromVertexList.add(vertexTo);
		}
		if (toVertexList == null) {
			neighbourList.put(vertexTo,
					new HashSet<Vertex>(Arrays.asList(vertexFrom)));
		} else {
			toVertexList.add(vertexFrom);
		}
	}

	public Set<Vertex> getVertices() {
		return neighbourList.keySet();
	}

	public Set<Vertex> getNeighbours(final String vertex) {
		return getNeighbours(new Vertex(vertex));
	}

	public Set<Vertex> getNeighbours(Vertex vertex) {
		return neighbourList.get(vertex);
	}

	public int getDistanceToFarthestVertex(String vertexFrom) {
		return getDistanceToFarthestVertex(new Vertex(vertexFrom));
	}
	public int getDistanceToFarthestVertex(Vertex vertexFrom) {
		for (Vertex v: getVertices()) {
			v.setColor(Vertex.Color.UNVISITED);
			v.setDistance(Integer.MAX_VALUE);
		}
		Queue<Vertex> queue = new java.util.LinkedList<Vertex>();
		queue.offer(vertexFrom);
		vertexFrom.setColor(Vertex.Color.VISITED);
		vertexFrom.setDistance(0);
		System.out.println("nowy");
		int farthest = 0;
		while (!queue.isEmpty()) {
			Vertex vertex = queue.poll();
			System.out.println(vertex.toString());
			if (farthest < vertex.getDistance())
				farthest = vertex.getDistance();
			for (Vertex v : getNeighbours(vertex)) {
				if (v.getColor() == Vertex.Color.UNVISITED) {
					if (v.getDistance() > vertex.getDistance() + 1) {
						v.setDistance(vertex.getDistance() + 1);
					}
					v.setColor(Vertex.Color.VISITED);
					System.out.println("Adding to queue" + v.toString());
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
