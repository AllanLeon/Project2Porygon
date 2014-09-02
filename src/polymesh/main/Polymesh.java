package polymesh.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Polymesh {

	private List<Polygon> polygons;
	
	public Polymesh() {
		polygons = new ArrayList<Polygon>();
	}
	
	public List<Polygon> getPolygons() {
		return polygons;
	}
	
	public void addPolygon(Polygon polygon) {
		polygons.add(polygon);
	}
	
	public void draw(Graphics g) {
		for (int i=0; i < polygons.size(); i++) {
			polygons.get(i).draw(g);
		}
	}
}
