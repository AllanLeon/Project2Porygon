package polymesh.main;

import java.awt.Graphics;
import java.util.ArrayList;

public class Polymesh {

	private ArrayList<Polygon> polygons;
	
	public Polymesh(Graphics g) {
		draw(g);
	}
	
	public void draw(Graphics g) {
		for (int i=0; i < polygons.size(); i++) {
			polygons.get(i).draw(g);
		}
	}
}
