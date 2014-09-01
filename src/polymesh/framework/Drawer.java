package polymesh.framework;

import java.awt.Color;
import java.awt.Graphics;

import polymesh.main.Main;

public class Drawer {

	public static void drawLine(Graphics g, int x0, int y0, int x1, int y1, Color color) {
		g.setColor(color);
		
		int dx, dy, d, x, y, deltaE, deltaNE, stepx = 0, stepy = 0;
		dx = x1-x0;
		dy = y1-y0;
		if (dx<0) {
			dx = -dx;
			stepx = -1;
		} else if (dx>0) { 
			stepx = 1;
		} if (dy<0) {
			dy = -dy;
			stepy = -1;
		} else if (dy>0) {
			stepy = 1;
		}
		x = x0;
		y = y0;
		putPixel(g, x, y);
		if (dx > dy) {
			d = 2*dy - dx;
			deltaE = 2 * dy;
			deltaNE = 2 * (dy-dx);
			while (x != x1) {
				x += stepx;
				if (d<0) {
					d += deltaE;
				} else {
					y += stepy;
					d += deltaNE;
				}
				putPixel(g, x, y);
			}
		} else {
			d = -2*dx + dy;
			deltaE = -2 * dx;
			deltaNE = 2 * (dy-dx);
			while (y != y1) {
				y += stepy;
				if (d>0) {
					d += deltaE;
				} else {
					x += stepx;
					d += deltaNE;
				}
				putPixel(g, x, y);
			}
		}
	}
	
	private static void putPixel(Graphics g, int x, int y) {
		g.drawLine(x, Main.getWindowHeight() - y, x, Main.getWindowHeight() - y);
	}
}
