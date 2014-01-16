package pl.edu.gis.kayles.util;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Date: 30.11.13
 * Time: 12:26
 */
public class Graph {
    private Map<String, Set<String>> neighbourList;

    public Graph() {
        neighbourList = new HashMap<String, Set<String>>();
    }

    public void kaylesRemove(String vertex) {
        List<String> removedVertices = new ArrayList<String>(Arrays.asList(vertex));

        Set<String> neighbours = neighbourList.remove(vertex);
        for (String neighbour : neighbours) {
            neighbourList.remove(neighbour);
            removedVertices.add(neighbour);
        }

        for (Set<String> v : neighbourList.values()) {
            v.removeAll(removedVertices);
        }

    }

    public void addVertex(String vertex) {
        if (!neighbourList.containsKey(vertex)) {
            neighbourList.put(vertex, new HashSet<String>());
        }
    }

    public void addEdge(String vertexFrom, String vertexTo) {
        Set<String> fromVertexList = neighbourList.get(vertexFrom);
        Set<String> toVertexList = neighbourList.get(vertexTo);

        if (fromVertexList == null) {
            neighbourList.put(vertexFrom, new HashSet<String>(Arrays.asList(vertexTo)));
        } else {
            fromVertexList.add(vertexTo);
        }
        if (toVertexList == null) {
            neighbourList.put(vertexTo, new HashSet<String>(Arrays.asList(vertexFrom)));
        } else {
            toVertexList.add(vertexFrom);
        }
    }

    public Set<String> getVertices() {
        return neighbourList.keySet();
    }

    public Set<String> getNeighbours(String vertex) {
        return neighbourList.get(vertex);
    }

    public int getDistanceToFarthestVertex(String vertexFrom) {
        return -1; //implement me
    }

    public int getExtendedNeighboursCount(String vertex) {
        Set<String> set = new HashSet<String>();
        for (String v : getNeighbours(vertex)) {
            set.addAll(getNeighbours(v));
        }
        return set.size();
    }
}
