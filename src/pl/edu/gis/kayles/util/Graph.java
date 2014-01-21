package pl.edu.gis.kayles.util;

import java.util.*;

/**
 * Basic class of algorithm. Contains class data structure and heurestic method. 
 * Created with IntelliJ IDEA. Date: 30.11.13 Time: 12:26
 */
public class Graph {
	private Map<String, Vertex> vertices = new HashMap<String, Vertex>();

	public Graph() {
	}

	/**
	 * Create new random graph/
	 * @param verticesCount number of vertices to create.
	 * @param edgesCount number of edges to create.
	 */
	public Graph(int seed, int verticesCount, int edgesCount) {
		Random r = new Random(seed);
		for (int i = 0; i < verticesCount ; i++) {
			vertices.put(Integer.toString(i), new Vertex(Integer.toString(i)));
		}
		for (int i = 0; i < edgesCount * 2 ; i++) {
			int r1 = r.nextInt(verticesCount);
			int r2 = r.nextInt(verticesCount);
			if (r2 == r1) r1 = (r1+1) % verticesCount;
			addEdge(Integer.toString(r1), Integer.toString(r2));
		}
	}

	/**
	 * Create new random graph/
	 * @param verticesCount number of vertices to create.
	 */
	public Graph(int seed, int verticesCount) {
		
		this(seed, verticesCount, verticesCount * 3);
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
		if (vertexFrom.getNeighbours().contains(vertexTo)) return;
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

	public boolean isGameOver() {
		return vertices.isEmpty();
	}

	public Vertex makeDecision(float param1, float param2, float param3) {
		if (vertices.isEmpty())
			return null;
		for (Vertex v : this.vertices.values()) {
			v.setFarthest(param1 * getDistanceToFarthestVertex(v));
			v.setExtended(param2 * getExtendedNeighboursCount(v));
			v.setProximity(param3 * getNeighbours(v).size());
		}
		// Now, for each vertex, calculate ditance to closest relative. Try to
		// find vertex with the biggest distance.
		float biggestDistance = 0;
		Vertex winningVertex = null;

		for (Vertex v : this.vertices.values()) {
			float distanceToClosestRelative = Float.MAX_VALUE;
			for (Vertex other : this.vertices.values()) {
				float distance = (v.getExtended() - other.getExtended())
						* (v.getExtended() - other.getExtended())
						* Math.abs((v.getExtended() - other.getExtended()))
						+ (v.getFarthest() - other.getFarthest())
						* (v.getFarthest() - other.getFarthest())
						* Math.abs((v.getFarthest() - other.getFarthest()))
						+ (v.getProximity() - other.getProximity())
						* (v.getProximity() - other.getProximity())
						* Math.abs((v.getProximity() - other.getProximity()));
				if (distance < distanceToClosestRelative)
					distanceToClosestRelative = distance;
			}
			if (distanceToClosestRelative >= biggestDistance) {
				biggestDistance = distanceToClosestRelative;
				winningVertex = v;
			}
			// System.out.println(distanceToClosestRelative + ", " +
			// v.getExtended() + ", " + v.getFarthest() + ", " +
			// v.getProximity());

		}
		return winningVertex;
	}

	public int getExtendedNeighboursCount(Vertex vertex) {
		Set<Vertex> set = new HashSet<Vertex>();
		for (Vertex v : getNeighbours(vertex)) {
			set.addAll(getNeighbours(v));
		}
		return set.size();
	}
}
