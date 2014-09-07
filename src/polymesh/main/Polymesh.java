package polymesh.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import polymesh.framework.TransformationManager;
import polymesh.main.Main.State;

public class Polymesh {

	private List<Polygon> polygons;
	private List<Edge> edges;
	private List<Point> corners;
	private Point center;
	private double[][] transformationToAply;
	private double[][] transformation;
	
	public Polymesh() {
		polygons = new ArrayList<Polygon>();
		edges = new ArrayList<Edge>();
		corners = new ArrayList<Point>();
		transformationToAply = new double[][]{
				{ 1, 0, 0, 0 },
				{ 0, 1, 0, 0 },
				{ 0, 0, 1, 0 },
				{ 0, 0, 0, 1 }
		};
		transformation = new double[][]{
				{ 1, 0, 0, 0 },
				{ 0, 1, 0, 0 },
				{ 0, 0, 1, 0 },
				{ 0, 0, 0, 1 }
		};
		center = new Point(0, 0, 0);
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
	
	public double[][] getTransformation() {
		return transformationToAply;
	}
	
	public void setCenter(Point center) {
		this.center = center;
	}
	
	public void updateCenter(double[][] newTransf) {
		center.setTransformedCoordinates(TransformationManager.matrixMultiplication(center.getTransformedCoordinates(), newTransf));
	}
	
	public void updateTransformation(double[][] newTransf, boolean updateCenter) {
		transformation = TransformationManager.matrixMultiplication(transformation, newTransf);
		double[][] coord = center.getTransformedCoordinates();
		double[][] t = TransformationManager.translation3D(-coord[0][0], -coord[0][1], -coord[0][2]);
		double[][] t1 = TransformationManager.translation3D(coord[0][0], coord[0][1], coord[0][2]);
		transformationToAply = TransformationManager.matrixMultiplication(TransformationManager.matrixMultiplication(t, transformation), t1);
		for(int i = 0; i < corners.size(); i++) {
			corners.get(i).update();
		}
		if (updateCenter) {
			updateCenter(newTransf);
		}
		coord = center.getTransformedCoordinates();
		Main.state = State.Drawing;
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
	
	public void drawPerspective(Graphics g) {
		for (int i=0; i < edges.size(); i++) {
			edges.get(i).drawPerspective(g);
		}
	}
	
	public void drawFrontal(Graphics g) {
		for (int i=0; i < edges.size(); i++) {
			edges.get(i).drawFrontal(g);
		}
	}
	
	public void drawSide(Graphics g) {
		for (int i=0; i < edges.size(); i++) {
			edges.get(i).drawSide(g);
		}
	}
	
	public void drawTop(Graphics g) {
		for (int i=0; i < edges.size(); i++) {
			edges.get(i).drawTop(g);
		}
	}
}
