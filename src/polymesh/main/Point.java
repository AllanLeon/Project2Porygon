package polymesh.main;

public class Point {

	private int x;
	private int y;
	private int z;
	private int nextx;
	private int nexty;
	private int nextz;

	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.nextx = 0;
		this.nexty = 0;
		this.nextz = 0;
	}
	
	public void update() {
		x += nextx;
		y += nexty;
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

	public int getNextx() {
		return nextx;
	}

	public int getNexty() {
		return nexty;
	}

	public int getNextz() {
		return nextz;
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

	public void setNextx(int nextx) {
		this.nextx = nextx;
	}

	public void setNexty(int nexty) {
		this.nexty = nexty;
	}

	public void setNextz(int nextz) {
		this.nextz = nextz;
	}
}
