package team.smd.vdsp.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class DirectedWSGTest {

	@Test
	public void createGraph() {
		int[][] matrix = DirectedWSG.getMatrix();
		for (int[] line : matrix) {
			assertEquals(line.length, matrix.length);
		}
	}

	@Test
	public void createGraphWithoutRing() {
		int[][] matrix = DirectedWSG.getMatrix();
		for (int[] row : matrix) {
			assertEquals(row.length, matrix.length);
		}
		int count = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length - i; j++) {
				count++;
				if (matrix[i][j] != 0) assertEquals(matrix[j][i], 0);
				else assertEquals(matrix[i][j], 0);
			}
		}
		assertEquals(count, (1 + matrix.length) * matrix.length / 2);
	}
}
