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
}
