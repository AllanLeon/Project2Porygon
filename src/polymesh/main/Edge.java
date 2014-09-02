package polymesh.main;

import polymesh.framework.Drawer;

import java.awt.Color;
import java.awt.Graphics;

public class Edge {

	public Point initPoint;
	public Point finalPoint;
	
	public Edge(int x0, int y0, int z0, int x1, int y1, int z1) {
		this.initPoint = new Point(x0, y0, z0);
		this.finalPoint = new Point(x1, y1, z1);
	}

	public Edge(Point A, Point B) {
		this.initPoint = A;
		this.finalPoint = B;
	}

	
	public void draw(Graphics g) {
		Drawer.drawLine(g, initPoint.getX(), initPoint.getY(), finalPoint.getX(),
						finalPoint.getY(), Color.CYAN);
	}
}
