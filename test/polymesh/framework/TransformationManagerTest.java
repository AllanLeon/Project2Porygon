package polymesh.framework;

import org.junit.Assert;
import org.junit.Test;

public class TransformationManagerTest {

	@Test
	public void testMatrixMultiplication() {
        int[][] m1 = new int[][]{
            { 1, 2, 0 },
            { 3, 4, 0 },
            { 0, 0, 1 }
        };
        int[][] m2 = new int[][]{
            { 1, 0, 0 },
            { 0, 1, 0 },
            { 5, 6, 1 }
        };
        int[][] m3 = new int[][]{
            { 1, 2, 0 },
            { 3, 4, 0 },
            { 5, 6, 1 }
        };
        int[][] res = TransformationManager.matrixMultiplication(m1, m2);
        Assert.assertArrayEquals(m3, res);
    }
	
	@Test
	public void testToHomogeneousCoordinates() {
        int[][] m1 = new int[][]{
            { 1, 2, 0 },
            { 3, 4, 0 },
            { 0, 0, 1 }
        };
        int[][] m2 = new int[][]{
            { 1, 2, 0, 0 },
            { 3, 4, 0, 0 },
            { 0, 0, 1, 0 },
            { 0, 0, 0, 1 }
        };
        int[][] res = TransformationManager.toHomogeneousCoordinates(m1);
        Assert.assertArrayEquals(m2, res);
    }

}
