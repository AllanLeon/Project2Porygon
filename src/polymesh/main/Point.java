package polymesh.main;

public class Point {

	private int x;
	private int y;
	private int z;
	private int w;
	private int[] perspectiveProjection;
	private int[] frontalProjection;
	private int[] sideProjection;
	private int[] topProjection;

	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = 1;
		this.perspectiveProjection = new int[4];
		this.frontalProjection = new int[4];
		this.sideProjection = new int[4];
		this.topProjection = new int[4];
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

	public int[] getPerspectiveProjection() {
		return perspectiveProjection;
	}

	public int[] getFrontalProjection() {
		return frontalProjection;
	}

	public int[] getSideProjection() {
		return sideProjection;
	}

	public int[] getTopProjection() {
		return topProjection;
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
	
	private void updatePerspectiveProjection() {
		
	}
}
