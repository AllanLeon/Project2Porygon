package polymesh.framework;

public class TransformationManager {

	public static int[][] matrixMultiplication(int[][] m1, int[][] m2) {
		int m1Rows = m1.length;
		int m1Columns = m1[0].length;
		int m2Rows = m2.length;
		int m2Columns = m2[0].length;
		int[][] res = new int[m1Rows][m2Columns];
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
	
	private static int[][] toHomogeneousCoordinates(int[][] m) {
		int mRows = m.length;
		int mColumns = m[0].length;
		int res[][] = new int[mRows + 1][mColumns + 1];
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
	
	public static int[][] rotation3D(int[] axis, int angle) {
		int[][] res = new int[3][3];
		if (axis.length == 3) {
			int nx = axis[0];
			int ny = axis[1];
			int nz = axis[2];
			double cos = Math.cos(angle);
			double sin = Math.cos(angle);
			res[0][0] = (int) Math.round(nx*nx*(1 - cos) + cos);
			res[1][0] = (int) Math.round(nx*ny*(1 - cos) - nz*sin);
			res[2][0] = (int) Math.round(nx*nz*(1 - cos) + ny*sin);
			
			res[0][1] = (int) Math.round(nx*ny*(1 - cos) + nz*sin);
			res[1][1] = (int) Math.round(ny*ny*(1 - cos) + cos);
			res[2][1] = (int) Math.round(ny*nz*(1 - cos) - nx*sin);
			
			res[0][2] = (int) Math.round(nx*nz*(1 - cos) - ny*sin);
			res[1][2] = (int) Math.round(ny*nz*(1 - cos) + nx*sin);
			res[2][2] = (int) Math.round(nz*nz*(1 - cos) + cos);
		}
		return toHomogeneousCoordinates(res);
	}
}
