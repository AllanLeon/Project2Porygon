package polymesh.framework;

import org.junit.Assert;
import org.junit.Test;

public class TransformationManagerTest {

	@Test
	public void testMatrixMultiplication() {
        double[][] m1 = new double[][]{
            { 1, 2, 0 },
            { 3, 4, 0 },
            { 0, 0, 1 }
        };
        double[][] m2 = new double[][]{
            { 1, 0, 0 },
            { 0, 1, 0 },
            { 5, 6, 1 }
        };
        double[][] m3 = new double[][]{
            { 1, 2, 0 },
            { 3, 4, 0 },
            { 5, 6, 1 }
        };
        double[][] res = TransformationManager.matrixMultiplication(m1, m2);
        Assert.assertArrayEquals(m3, res);
    }
	
	@Test
	public void testToHomogeneousCoordinates() {
        double[][] m1 = new double[][]{
            { 1, 2, 0 },
            { 3, 4, 0 },
            { 0, 0, 1 }
        };
        double[][] m2 = new double[][]{
            { 1, 2, 0, 0 },
            { 3, 4, 0, 0 },
            { 0, 0, 1, 0 },
            { 0, 0, 0, 1 }
        };
        double[][] res = TransformationManager.toHomogeneousCoordinates(m1);
        Assert.assertArrayEquals(m2, res);
    }

}
