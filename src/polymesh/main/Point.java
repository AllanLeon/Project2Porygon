package polymesh.main;

import polymesh.framework.TransformationManager;

public class Point {

	private int x;
	private int y;
	private int z;
	private int w;
	private double[][] coordinates;
	private double[][] transformedCoord;
	private int[][] perspectiveProjection;
	private int[][] frontalProjection;
	private int[][] sideProjection;
	private int[][] topProjection;

	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = 1;
		this.coordinates = new double[][]{
				{ x, y, z, 1 }
		};
		this.transformedCoord = coordinates;
		this.perspectiveProjection = new int[1][4];
		this.frontalProjection = new int[1][4];
		this.sideProjection = new int[1][4];
		this.topProjection = new int[1][4];
		updatePerspectiveProjection();
		updateFrontalProjection();
		updateSideProjection();
		updateTopProjection();
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
	
	public double[][] getTransformedCoordinates() {
		return transformedCoord;
	}

	public int[][] getPerspectiveProjection() {
		return perspectiveProjection;
	}

	public int[][] getFrontalProjection() {
		return frontalProjection;
	}

	public int[][] getSideProjection() {
		return sideProjection;
	}

	public int[][] getTopProjection() {
		return topProjection;
	}

	public void setX(int x) {
		this.x = x;
		this.coordinates[0][0] = x;
	}

	public void setY(int y) {
		this.y = y;
		this.coordinates[0][1] = y;
	}

	public void setZ(int z) {
		this.z = z;
		this.coordinates[0][2] = z;
	}
	
	public void checkW(double[][] coord) {
		if (coord[0][3] == 0) {
			coord[0][3] = 1;
		} else if (coord[0][3] != 1) {
			coord[0][0] /= coord[0][3];
			coord[0][1] /= coord[0][3];
			coord[0][2] /= coord[0][3];
			coord[0][3] /= coord[0][3];
		}
	}
	
	public void update() {
		applyTransformation();
		updatePerspectiveProjection();
		updateFrontalProjection();
		updateSideProjection();
		updateTopProjection();
	}
	
	private void applyTransformation() {
		transformedCoord = TransformationManager.matrixMultiplication(coordinates,
				Polymesh.getTransformation());
		checkW(transformedCoord);
		System.out.println(transformedCoord[0][0] + " " + transformedCoord[0][1] + " " + transformedCoord[0][2] + " " + transformedCoord[0][3]);
	}
	
	private void updatePerspectiveProjection() {
		double[][] projection = TransformationManager.matrixMultiplication(transformedCoord,
				TransformationManager.perspectiveProjectionXY3D(TransformationManager.distance));
		checkW(projection);
		//System.out.println(projection[0][0] + " " + projection[0][1] + " " + projection[0][2] + " " + projection[0][3]);
		perspectiveProjection = TransformationManager.convertDoubleToIntMatrix(projection);
	}
	
	private void updateFrontalProjection() {
		double[][] projection = TransformationManager.matrixMultiplication(transformedCoord,
				TransformationManager.orthographicProjection3D(TransformationManager.zAxis));
		projection = TransformationManager.matrixMultiplication(projection,
				TransformationManager.scaling3D(TransformationManager.scale,
						TransformationManager.scale, TransformationManager.scale));
		checkW(projection);
		frontalProjection = TransformationManager.convertDoubleToIntMatrix(projection);
	}
	
	private void updateSideProjection() {
		double[][] projection = TransformationManager.matrixMultiplication(transformedCoord,
				TransformationManager.orthographicProjection3D(TransformationManager.xAxis));
		projection = TransformationManager.matrixMultiplication(projection,
				TransformationManager.scaling3D(TransformationManager.scale,
						TransformationManager.scale, TransformationManager.scale));
		checkW(projection);
		sideProjection = TransformationManager.convertDoubleToIntMatrix(projection);
	}
	
	private void updateTopProjection() {
		double[][] projection = TransformationManager.matrixMultiplication(transformedCoord,
				TransformationManager.orthographicProjection3D(TransformationManager.yAxis));
		projection = TransformationManager.matrixMultiplication(projection,
				TransformationManager.scaling3D(TransformationManager.scale,
						TransformationManager.scale, TransformationManager.scale));
		checkW(projection);
		topProjection = TransformationManager.convertDoubleToIntMatrix(projection);
	}
}
