package polymesh.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import polymesh.framework.TransformationManager;

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
	
	public static void updateTransformation(double[][] newTransf) {
		transformation = TransformationManager.matrixMultiplication(transformation, newTransf);
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
		for (int i=0; i < edges.size(); i++) {
			edges.get(i).draw(g);
		}
	}
}
