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
		//drawPerspective(g);
		//drawFrontal(g);
		//drawSide(g);
		//drawTop(g);
	}
	
	public void drawPerspective(Graphics g) {
		int x0 = initPoint.getPerspectiveProjection()[0][0];
		int y0 = initPoint.getPerspectiveProjection()[0][1];
		int x1 = finalPoint.getPerspectiveProjection()[0][0];
		int y1 = finalPoint.getPerspectiveProjection()[0][1];
		Drawer.drawLine(g, x0 + Main.BIG_PANEL_WIDTH / 2, y0 + Main.BIG_PANEL_HEIGHT / 2, x1 + Main.BIG_PANEL_WIDTH / 2, y1 + Main.BIG_PANEL_HEIGHT / 2, Color.CYAN);
	}
	
	public void drawFrontal(Graphics g) {
		int x0 = initPoint.getFrontalProjection()[0][0];
		int y0 = initPoint.getFrontalProjection()[0][1];
		int x1 = finalPoint.getFrontalProjection()[0][0];
		int y1 = finalPoint.getFrontalProjection()[0][1];
		Drawer.drawLine(g, x0 + Main.SMALL_PANEL_WIDTH / 2, y0 + Main.BIG_PANEL_HEIGHT - Main.SMALL_PANEL_HEIGHT / 2, x1 + Main.SMALL_PANEL_WIDTH / 2, y1 + Main.BIG_PANEL_HEIGHT - Main.SMALL_PANEL_HEIGHT / 2, Color.CYAN);
	}
	
	public void drawSide(Graphics g) {
		int x0 = initPoint.getSideProjection()[0][2];
		int y0 = initPoint.getSideProjection()[0][1];
		int x1 = finalPoint.getSideProjection()[0][2];
		int y1 = finalPoint.getSideProjection()[0][1];
		Drawer.drawLine(g, x0 + Main.SMALL_PANEL_WIDTH / 2, y0 + Main.BIG_PANEL_HEIGHT - Main.SMALL_PANEL_HEIGHT / 2, x1 + Main.SMALL_PANEL_WIDTH / 2, y1 + Main.BIG_PANEL_HEIGHT - Main.SMALL_PANEL_HEIGHT / 2, Color.CYAN);
	}
	
	public void drawTop(Graphics g) {
		int x0 = initPoint.getTopProjection()[0][0];
		int y0 = initPoint.getTopProjection()[0][2];
		int x1 = finalPoint.getTopProjection()[0][0];
		int y1 = finalPoint.getTopProjection()[0][2];
		Drawer.drawLine(g, x0 + Main.SMALL_PANEL_WIDTH / 2, y0 + Main.BIG_PANEL_HEIGHT - Main.SMALL_PANEL_HEIGHT / 2, x1 + Main.SMALL_PANEL_WIDTH / 2, y1 + Main.BIG_PANEL_HEIGHT - Main.SMALL_PANEL_HEIGHT / 2, Color.CYAN);
	}
}
