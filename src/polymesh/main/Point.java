package polymesh.main;

public class Point {

	private int x;
	private int y;
	private int z;
	private int w;

	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = 1;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
	
	public int getW() {
		return w;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public void checkW() {
		if (w != 1) {
			x /= w;
			y /= w;
			z /= w;
			w /= w;
		}
	}
}
