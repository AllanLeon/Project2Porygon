package polymesh.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import polymesh.framework.Drawer;

public class Polygon {

	private ArrayList<Edge> edges;
	
	public Polygon(Graphics g) {
		draw(g);
	}
	
	public void draw(Graphics g) {
		for (int i=0; i < edges.size(); i++) {
			Drawer.drawLine(g, edges.get(i).initPoint.getX(), edges.get(i).initPoint.getY(),
							edges.get(i).finalPoint.getY(), edges.get(i).finalPoint.getY(), Color.RED);
		}
	}
}
