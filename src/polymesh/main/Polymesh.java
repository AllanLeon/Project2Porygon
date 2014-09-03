package polymesh.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Polymesh {

	private List<Polygon> polygons;
	private List<Edge> edges;
	private List<Point> corners;
	private static double[][] transformation;
	
	public Polymesh() {
		polygons = new ArrayList<Polygon>();
		edges = new ArrayList<Edge>();
		corners = new ArrayList<Point>();
		transformation = new double[][]{
				{ 1, 0, 0, 0 },
				{ 0, 1, 0, 0 },
				{ 0, 0, 1, 0 },
				{ 0, 0, 0, 1 }
		};
	}
	
	public List<Polygon> getPolygons() {
		return polygons;
	}
	
	public List<Polygon> getEdges() {
		return polygons;
	}
	
	public List<Polygon> getCorners() {
		return polygons;
	}
	
	public static double[][] getTransformation() {
		return transformation;
	}
	
	public void addPolygon(Polygon polygon) {
		polygons.add(polygon);
	}
	
	public void addEdge(Edge edge) {
		edges.add(edge);
	}
	
	public void addCorner(Point corner) {
		corners.add(corner);
	}
	
	public void draw(Graphics g) {
		for (int i=0; i < polygons.size(); i++) {
			polygons.get(i).draw(g);
		}
	}
}
