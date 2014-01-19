package pl.edu.gis.kayles.util;

import java.util.HashSet;
import java.util.Set;

public class Vertex {
	public enum Color {
		VISITED, UNVISITED
	}

	private final String name;
	private Color color = Color.UNVISITED;
	private int distance = 0;
	private Set<Vertex> neighbours;
	private float farthest = 0;
	private float proximity = 0;
	private float extended = 0;

	public Vertex(final String name) {
		this.name = name;
		this.neighbours = new HashSet<>();
	}
	
	public float getFarthest() {
		return farthest;
	}

	public void setFarthest(float farthest) {
		this.farthest = farthest;
	}

	public float getProximity() {
		return proximity;
	}

	public void setProximity(float proximity) {
		this.proximity = proximity;
	}

	public float getExtended() {
		return extended;
	}

	public void setExtended(float extended) {
		this.extended = extended;
	}


	public Set<Vertex> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(Set<Vertex> neighbours) {
		this.neighbours = neighbours;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (name.equals(obj))
			return true;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

}
