package polymesh.main;

import polymesh.framework.Drawer;

import java.awt.Color;
import java.awt.Graphics;

public class Edge {

	private Point initPoint;
	private Point finalPoint;
	private Color color;
	
	public Edge(int x0, int y0, int z0, int x1, int y1, int z1) {
		this.initPoint = new Point(x0, y0, z0);
		this.finalPoint = new Point(x1, y1, z1);
		this.color = Color.CYAN;
	}

	public Edge(Point A, Point B) {
		this.initPoint = A;
		this.finalPoint = B;
		this.color = Color.CYAN;
	}
	
	public Edge(Point A, Point B, Color color) {
		this.initPoint = A;
		this.finalPoint = B;
		this.color = color;
	}

	public void draw(Graphics g) {
	}
	
	public void drawPerspective(Graphics g) {
		int x0 = initPoint.getPerspectiveProjection()[0][0];
		int y0 = initPoint.getPerspectiveProjection()[0][1];
		int x1 = finalPoint.getPerspectiveProjection()[0][0];
		int y1 = finalPoint.getPerspectiveProjection()[0][1];
		x0 += Main.BIG_PANEL_WIDTH / 2;
		y0 = Main.BIG_PANEL_HEIGHT / 2 - y0;
		x1 += Main.BIG_PANEL_WIDTH / 2;
		y1 = Main.BIG_PANEL_HEIGHT / 2 - y1;
		Drawer.drawLine(g, x0, y0, x1, y1, color);
	}
	
	public void drawFrontal(Graphics g) {
		int x0 = initPoint.getFrontalProjection()[0][0];
		int y0 = initPoint.getFrontalProjection()[0][1];
		int x1 = finalPoint.getFrontalProjection()[0][0];
		int y1 = finalPoint.getFrontalProjection()[0][1];
		x0 += Main.SMALL_PANEL_WIDTH / 2;
		y0 = Main.SMALL_PANEL_HEIGHT / 2 - y0;
		x1 += Main.SMALL_PANEL_WIDTH / 2;
		y1 = Main.SMALL_PANEL_HEIGHT / 2 - y1;
		Drawer.drawLine(g, x0, y0, x1, y1, color);
	}
	
	public void drawSide(Graphics g) {
		int x0 = initPoint.getSideProjection()[0][2];
		int y0 = initPoint.getSideProjection()[0][1];
		int x1 = finalPoint.getSideProjection()[0][2];
		int y1 = finalPoint.getSideProjection()[0][1];
		x0 += Main.SMALL_PANEL_WIDTH / 2 - 40;
		y0 = Main.SMALL_PANEL_HEIGHT / 2 - y0;
		x1 += Main.SMALL_PANEL_WIDTH / 2 - 40;
		y1 = Main.SMALL_PANEL_HEIGHT / 2 - y1;
		Drawer.drawLine(g, x0, y0, x1, y1, color);
	}
	
	public void drawTop(Graphics g) {
		int x0 = initPoint.getTopProjection()[0][0];
		int y0 = initPoint.getTopProjection()[0][2];
		int x1 = finalPoint.getTopProjection()[0][0];
		int y1 = finalPoint.getTopProjection()[0][2];
		x0 += Main.SMALL_PANEL_WIDTH / 2;
		y0 = Main.SMALL_PANEL_HEIGHT / 2 - y0 + 40;
		x1 += Main.SMALL_PANEL_WIDTH / 2;
		y1 = Main.SMALL_PANEL_HEIGHT / 2 - y1 + 40;
		Drawer.drawLine(g, x0, y0, x1, y1, color);
	}
}
