package polymesh.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Polygon {

	private List<Edge> edges;
	
	public Polygon() {
		edges = new ArrayList<Edge>();
	}
	
	public List<Edge> getEdges() {
		return edges;
	}
	
	public void addEdge(Edge edge) {
		edges.add(edge);
	}
	
	public void draw(Graphics g) {
		for (int i=0; i < edges.size(); i++) {
			edges.get(i).draw(g);
		}
	}
}
