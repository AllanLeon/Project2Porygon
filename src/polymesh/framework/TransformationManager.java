package polymesh.framework;

public class TransformationManager {

	public static final int[] xAxis = new int[]{
		1, 0, 0
	};
	public static final int[] yAxis = new int[]{
		0, 1, 0
	};
	public static final int[] zAxis = new int[]{
		0, 0, 1
	};
	public static final int distance = 1;
	
	public static double[][] matrixMultiplication(double[][] m1, double[][] m2) {
		int m1Rows = m1.length;
		int m1Columns = m1[0].length;
		int m2Rows = m2.length;
		int m2Columns = m2[0].length;
		double[][] res = new double[m1Rows][m2Columns];
		if (m1Columns == m2Rows) {
			for (int i = 0; i < m1Rows; i++) {
				for (int j = 0; j < m2Columns; j++) {
					for (int k = 0; k < m1Columns; k++) {
						res[i][j] += m1[i][k]*m2[k][j];
					}
				}
			}
		}
		return res;
	}
	
	public static double[][] toHomogeneousCoordinates(double[][] m) {
		int mRows = m.length;
		int mColumns = m[0].length;
		double res[][] = new double[mRows + 1][mColumns + 1];
		for (int i = 0; i < mRows; i++) {
			for (int j = 0; j < mColumns; j++) {
				res[i][j] = m[i][j];
			}
			res[i][mColumns] = 0;
		}
		for (int j = 0; j < mColumns; j++) {
			res[mRows][j] = 0;
		}
		res[mRows][mColumns] = 1;
		return res;
	}
	
	public static double[][] rotation3D(double[] axis, double angle) {
		double[][] res = new double[3][3];
		if (axis.length == 3) {
			double nx = axis[0];
			double ny = axis[1];
			double nz = axis[2];
			double cos = Math.cos(angle);
			double sin = Math.cos(angle);
			res[0][0] = nx*nx*(1 - cos) + cos;
			res[1][0] = nx*ny*(1 - cos) - nz*sin;
			res[2][0] = nx*nz*(1 - cos) + ny*sin;
			
			res[0][1] = nx*ny*(1 - cos) + nz*sin;
			res[1][1] = ny*ny*(1 - cos) + cos;
			res[2][1] = ny*nz*(1 - cos) - nx*sin;
			
			res[0][2] = nx*nz*(1 - cos) - ny*sin;
			res[1][2] = ny*nz*(1 - cos) + nx*sin;
			res[2][2] = nz*nz*(1 - cos) + cos;
		}
		return toHomogeneousCoordinates(res);
	}
	
	public static double[][] scaling3D(double[] axis, double k) {
		double[][] res = new double[3][3];
		if (axis.length == 3) {
			double nx = axis[0];
			double ny = axis[1];
			double nz = axis[2];
			res[0][0] = 1 + (k - 1)*nx*nx; 
			res[1][0] = (k - 1)*nx*ny;
			res[2][0] = (k - 1)*nx*nz;
			
			res[0][1] = (k - 1)*nx*ny; 
			res[1][1] = 1 + (k - 1)*ny*ny;
			res[2][1] = (k - 1)*ny*nz;
			
			res[0][2] = (k - 1)*nx*nz; 
			res[1][2] = (k - 1)*ny*nz;
			res[2][2] = 1 + (k - 1)*nz*nz;
		}
		return toHomogeneousCoordinates(res);
	}
	
	public static double[][] translation3D(int dx, int dy, int dz) {
		double[][] res = new double[][]{
				{ 1, 0, 0, 0 },
				{ 0, 1, 0, 0 },
				{ 0, 0, 1, 0 },
				{ dx, dy, dz, 1 }
		};
		return res;
	}
	
	public static double[][] orthographicProjection3D(double[] axis) {
		return scaling3D(axis, 0);
	}
	
	public static double[][] perspectiveProjectionXY3D(int d) {
		double[][] res = new double[][]{
				{ 1, 0, 0, 0 },
				{ 0, 1, 0, 0 },
				{ 0, 0, 1, 1/d },
				{ 0, 0, 0, 0 }
		};
		return res;
	}
}
